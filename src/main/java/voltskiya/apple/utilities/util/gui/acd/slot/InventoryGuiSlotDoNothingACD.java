package voltskiya.apple.utilities.util.gui.acd.slot;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import voltskiya.apple.utilities.util.minecraft.InventoryUtils;

public class InventoryGuiSlotDoNothingACD implements InventoryGuiSlotACD {
    public static final InventoryGuiSlotDoNothingACD EMPTY = new InventoryGuiSlotDoNothingACD(InventoryUtils.makeItem(Material.AIR));
    private ItemStack item;

    public InventoryGuiSlotDoNothingACD(ItemStack item) {
        this.item = item;
    }

    @Override
    public ItemStack getItem() {
        return item;
    }

    @Override
    public void dealWithClick(InventoryClickEvent event) {
    }
}
