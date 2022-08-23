package voltskiya.apple.utilities.event_listener.manage;

import java.util.function.BooleanSupplier;
import java.util.function.Consumer;
import java.util.function.Predicate;

public record EntityEventListenerImpl<Event>(
        Predicate<Event> shouldHandleFunc,
        BooleanSupplier isHandleCancelledFunc,
        Consumer<Event> onEventFunc,
        BooleanSupplier shouldRemoveFunc) implements EntityEventListener<Event> {
    @Override
    public boolean shouldHandle(Event event) {
        return shouldHandleFunc.test(event);
    }

    @Override
    public boolean isHandleCancelled() {
        return isHandleCancelledFunc.getAsBoolean();
    }

    @Override
    public void onEvent(Event event) {
        onEventFunc.accept(event);
    }

    @Override
    public boolean shouldRemove() {
        return shouldRemoveFunc.getAsBoolean();
    }
}
