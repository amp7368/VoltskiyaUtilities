package voltskiya.apple.utilities.trash.gui.acd.slot;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import voltskiya.apple.utilities.trash.InventoryUtils;

import java.util.function.Supplier;

public class InventoryGuiSlotDoNothingACD implements InventoryGuiSlotACD {
    public static final InventoryGuiSlotDoNothingACD EMPTY = new InventoryGuiSlotDoNothingACD(InventoryUtils.makeItem(Material.AIR));
    private final ItemStack item;
    private final Supplier<ItemStack> itemSupplier;

    public InventoryGuiSlotDoNothingACD(ItemStack item) {
        this.itemSupplier = null;
        this.item = item;
    }

    public InventoryGuiSlotDoNothingACD(Supplier<ItemStack> item) {
        this.itemSupplier = item;
        this.item = null;
    }

    @Override
    public ItemStack getItem() {
        if (itemSupplier != null) return itemSupplier.get();
        return item;
    }

    @Override
    public void dealWithClick(InventoryClickEvent event) {
    }
}
