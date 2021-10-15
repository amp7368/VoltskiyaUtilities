package voltskiya.apple.utilities.util.gui.acd.slot;

import org.bukkit.inventory.ItemStack;
import voltskiya.apple.utilities.util.gui.InventoryGui;

public interface InventoryGuiSlotACD extends InventoryGuiSlotClickableACD, InventoryGui.InventoryGuiSlot {
    @Override
    ItemStack getItem();
}
