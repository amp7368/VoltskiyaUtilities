package voltskiya.apple.utilities.trash.gui.acd.slot;

import org.bukkit.event.inventory.InventoryClickEvent;

@FunctionalInterface
public interface InventoryGuiSlotClickableACD {
    void dealWithClick(InventoryClickEvent event);
}
