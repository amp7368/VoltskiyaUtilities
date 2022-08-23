package voltskiya.apple.utilities.trash.wand;

import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public interface WandPlayer {
    /**
     * @param event        the playerInteractEvent which the wand was used in
     * @param wandItemMeta the value of the wand NamespacedKey
     */
    default void dealWithUse(PlayerInteractEvent event, String wandItemMeta) {
    }

    default Action[] getOnActions() {
        return Action.values();
    }

    /**
     * @return true if this WandPlayer should be pruned, otherwise false
     */
    default boolean onLeave() {
        return false;
    }

    default boolean shouldCancel() {
        return true;
    }
}
