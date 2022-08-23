package voltskiya.apple.utilities.trash.gui.acd.page;

import org.bukkit.entity.HumanEntity;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import voltskiya.apple.utilities.trash.gui.OnGuiInventoryEvent;
import voltskiya.apple.utilities.trash.gui.acd.slot.ACDSlotFactory;
import voltskiya.apple.utilities.trash.gui.acd.slot.InventoryGuiSlotACD;
import voltskiya.apple.utilities.trash.InventoryUtils;

import java.util.ArrayList;
import java.util.List;

public interface InventoryGuiPageACD extends OnGuiInventoryEvent, InventoryUtils, ACDSlotFactory {
    String getName();

    @NotNull Inventory getInventory();

    void setSlot(InventoryGuiSlotACD item, int... slot);

    default void setSlot(int slot, InventoryGuiSlotACD item) {
        setSlot(item, slot);
    }

    @Override
    default void showPageItems(@Nullable List<HumanEntity> viewers) {
        Inventory inventory = this.getInventory();
        if (viewers == null) viewers = inventory.getViewers();
        for (HumanEntity viewer : new ArrayList<>(viewers)) {
            viewer.openInventory(inventory);
        }
    }

    int size();
}
