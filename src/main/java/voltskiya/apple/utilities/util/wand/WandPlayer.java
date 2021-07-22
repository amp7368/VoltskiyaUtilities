package voltskiya.apple.utilities.util.wand;

import org.bukkit.NamespacedKey;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public interface WandPlayer {
    /**
     * @param event        the playerInteractEvent which the wand was used in
     * @param wandItemMeta the value of the wand NamespacedKey
     */
    @WandUse
    void dealWithUse(PlayerInteractEvent event, String wandItemMeta);

    /**
     * @return true if this WandPlayer should be pruned, otherwise false
     */
    default boolean onLeave() {
        return false;
    }
}
