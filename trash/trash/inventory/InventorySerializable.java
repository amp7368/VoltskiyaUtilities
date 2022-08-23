package voltskiya.apple.utilities.trash.inventory;


import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import voltskiya.apple.utilities.trash.ItemSerializable;

public class InventorySerializable {
    private final ItemSerializable[] items;

    public InventorySerializable() {
        items = new ItemSerializable[0];
    }

    public InventorySerializable(Inventory inventory) {
        ItemStack[] contents = inventory.getContents();
        this.items = new ItemSerializable[contents.length];
        for (int i = 0; i < contents.length; i++) {
            ItemStack item = contents[i];
            this.items[i] = new ItemSerializable(item, false);
        }
    }

    public ItemStack[] toContents() {
        ItemStack[] contents = new ItemStack[items.length];
        for (int i = 0; i < items.length; i++) {
            contents[i] = items[i].getItem();
        }
        return contents;
    }
}

