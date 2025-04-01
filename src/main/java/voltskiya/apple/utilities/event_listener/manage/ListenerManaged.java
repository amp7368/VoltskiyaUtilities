package voltskiya.apple.utilities.event_listener.manage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.bukkit.event.Cancellable;

public class ListenerManaged {

    final List<UUID> entities;
    private final Map<Class<?>, EntityEventListener<?>> eventListeners = new HashMap<>();

    public ListenerManaged(List<UUID> entities) {
        this.entities = entities;
    }

    public synchronized <Event> void register(EntityEventListener<Event> eventListener, Class<Event> clazz) {
        boolean empty = eventListeners.isEmpty();
        eventListeners.put(clazz, eventListener);
        if (empty) {
            EntityMainListener.registerListener(this);
        }
    }

    public void unregister() {
        EntityMainListener.unregisterListener(this);
    }

    public synchronized <Ev extends Cancellable> void onEvent(Ev event, Class<Ev> type) {
        @SuppressWarnings("unchecked") EntityEventListener<Ev> listener = (EntityEventListener<Ev>) eventListeners.get(type);
        if (listener != null) {
            if (listener.shouldRemove()) unregister(type);
            if ((listener.isHandleCancelled() || !event.isCancelled()) &&
                listener.shouldHandle(event)) {
                listener.onEvent(event);
            }
        }
    }

    private synchronized <Ev extends Cancellable> void unregister(Class<Ev> type) {
        this.eventListeners.remove(type);
        if (this.eventListeners.isEmpty()) {
            unregister();
        }
    }
}
