package voltskiya.apple.utilities.trash.gui;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class InventoryGuiSlotGenericScrollable extends InventoryGuiSlotScrollable {
    private final Consumer<InventoryClickEvent> dealWithEvent;
    private final ItemStack item;
    private final Supplier<ItemStack> itemSupplier;

    public InventoryGuiSlotGenericScrollable(Consumer<InventoryClickEvent> dealWithEvent, ItemStack item) {
        this.dealWithEvent = dealWithEvent;
        this.item = item;
        this.itemSupplier = null;
    }

    public InventoryGuiSlotGenericScrollable(Consumer<InventoryClickEvent> dealWithEvent, Supplier<ItemStack> itemSupplier) {
        this.dealWithEvent = dealWithEvent;
        this.item = null;
        this.itemSupplier = itemSupplier;
    }

    @Override
    public void dealWithClick(InventoryClickEvent event) {
        dealWithEvent.accept(event);
    }

    @Override
    public ItemStack getItem() {
        if (itemSupplier != null) return itemSupplier.get();
        return item;
    }
}
