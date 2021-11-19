package voltskiya.apple.utilities.util.event_listener.manage;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.jetbrains.annotations.NotNull;
import voltskiya.apple.utilities.UtilitiesVoltskiyaPlugin;

import java.util.*;

public class EntityMainListener implements Listener {
    private static final Map<UUID, List<ListenerManaged>> listeners = new HashMap<>();

    public EntityMainListener() {
        UtilitiesVoltskiyaPlugin.get().registerEvents(this);
    }

    public static void registerListener(ListenerManaged listener) {
        synchronized (listeners) {
            for (UUID entity : listener.entities) {
                listeners.compute(entity, (e, v) -> {
                    if (v == null) {
                        v = new ArrayList<>(1);
                    }
                    v.add(listener);
                    return v;
                });
            }
        }
    }

    public static void unregisterListener(ListenerManaged listener) {
        System.out.println("unregister");
        synchronized (listeners) {
            for (UUID entity : listener.entities) {
                List<ListenerManaged> listenersInList = listeners.get(entity);
                if (listenersInList.isEmpty()) listeners.remove(entity);
                else listenersInList.remove(listener);
            }
        }
    }

    private <Ev extends Cancellable> void onEvent(Ev event, Class<Ev> clazz, @NotNull Entity entity) {
        System.out.println("rawr2");
        List<ListenerManaged> listenersInList;
        synchronized (listeners) {
            listenersInList = listeners.get(entity.getUniqueId());
        }
        if (listenersInList != null) {
            listenersInList.forEach((listener) -> listener.onEvent(event, clazz));
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        System.out.println("rawr22");
        onEvent(event, EntityDamageByEntityEvent.class, event.getEntity());
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractAtEntityEvent event) {
        System.out.println("rawr21");
        onEvent(event, PlayerInteractAtEntityEvent.class, event.getRightClicked());
    }
}
