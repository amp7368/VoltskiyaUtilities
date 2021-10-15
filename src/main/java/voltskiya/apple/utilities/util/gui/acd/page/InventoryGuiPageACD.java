package voltskiya.apple.utilities.util.gui.acd.page;

import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import voltskiya.apple.utilities.util.gui.OnGuiInventoryEvent;
import voltskiya.apple.utilities.util.gui.acd.slot.InventoryGuiSlotACD;

public interface InventoryGuiPageACD extends OnGuiInventoryEvent {
    String getName();

    @NotNull Inventory getInventory();

    void setSlot(InventoryGuiSlotACD item, int... slot);

    default void setSlot(int slot, InventoryGuiSlotACD item) {
        setSlot(item, slot);
    }

    int size();
}
