package voltskiya.apple.utilities.event_listener.manage;

import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;

public interface EntityEventListener<Event> {
    default boolean shouldHandle(Event event) {
        return true;
    }

    default boolean isHandleCancelled() {
        return false;
    }

    void onEvent(Event event);

    boolean shouldRemove();

    interface EntityDamageByEntity {
        default boolean shouldHandleEntityDamageByEntity(EntityDamageByEntityEvent event) {
            return true;
        }

        default boolean isHandleCancelledEntityDamageByEntity() {
            return false;
        }

        void onEventEntityDamageByEntity(EntityDamageByEntityEvent event);

        boolean shouldRemoveEntityDamageByEntity();

        default void registerListenerEntityDamageByEntity(ListenerManaged managed) {
            managed.register(new EntityEventListenerImpl<>(
                    this::shouldHandleEntityDamageByEntity,
                    this::isHandleCancelledEntityDamageByEntity,
                    this::onEventEntityDamageByEntity,
                    this::shouldRemoveEntityDamageByEntity
            ), EntityDamageByEntityEvent.class);
        }
    }

    interface PlayerInteractAtEntity {
        default boolean shouldHandlePlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
            return true;
        }

        default boolean isHandleCancelledPlayerInteractAtEntity() {
            return false;
        }

        void onEventPlayerInteractAtEntity(PlayerInteractAtEntityEvent event);

        boolean shouldRemovePlayerInteractAtEntity();

        default void registerListenerPlayerInteractAtEntity(ListenerManaged managed) {
            managed.register(new EntityEventListenerImpl<>(
                    this::shouldHandlePlayerInteractAtEntity,
                    this::isHandleCancelledPlayerInteractAtEntity,
                    this::onEventPlayerInteractAtEntity,
                    this::shouldRemovePlayerInteractAtEntity
            ), PlayerInteractAtEntityEvent.class);
        }
    }
}
