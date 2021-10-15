package voltskiya.apple.utilities.util.gui.acd.slot;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class InventoryGuiSlotImplACD implements InventoryGuiSlotACD {
    private final InventoryGuiSlotClickableACD dealWithEvent;
    private final ItemStack item;
    private final Supplier<ItemStack> itemSupplier;

    public InventoryGuiSlotImplACD(InventoryGuiSlotClickableACD dealWithEvent, ItemStack item) {
        this.dealWithEvent = dealWithEvent;
        this.item = item;
        this.itemSupplier = null;
    }

    public InventoryGuiSlotImplACD(InventoryGuiSlotClickableACD dealWithEvent, Supplier<ItemStack> itemSupplier) {
        this.dealWithEvent = dealWithEvent;
        this.item = null;
        this.itemSupplier = itemSupplier;
    }

    @Override
    public void dealWithClick(InventoryClickEvent event) {
        dealWithEvent.dealWithClick(event);
    }

    @Override
    public ItemStack getItem() {
        if (itemSupplier != null) return itemSupplier.get();
        return item;
    }
}
