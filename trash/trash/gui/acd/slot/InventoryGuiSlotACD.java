package voltskiya.apple.utilities.trash.gui.acd.slot;

import org.bukkit.inventory.ItemStack;
import voltskiya.apple.utilities.trash.gui.InventoryGui;

public interface InventoryGuiSlotACD extends InventoryGuiSlotClickableACD, InventoryGui.InventoryGuiSlot {
    @Override
    ItemStack getItem();
}
