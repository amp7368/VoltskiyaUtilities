package voltskiya.apple.utilities.util.minecraft;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class ItemSerializable {
    private Material material;
    private int count;
    private String name;
    private List<String> lore;

    public ItemSerializable(ItemStack item, boolean ignoreCount) {
        this.name = item.getItemMeta().getDisplayName();
        this.lore = item.getItemMeta().getLore();
        this.material = item.getType();
        this.count = ignoreCount ? 1 : item.getAmount();
    }

    public ItemSerializable(Material material, int count, String name, List<String> lore) {
        this.material = material;
        this.count = count;
        this.name = name;
        this.lore = lore;
    }

    public ItemSerializable() {
    }

    public Material getMaterial() {
        return material;
    }

    public int getCount() {
        return count;
    }

    public String getName() {
        return name;
    }

    public List<String> getLore() {
        return lore;
    }

    public ItemStack getItem() {
        return InventoryUtils.makeItem(material, count, name, lore);
    }

    public ItemSerializable copy() {
        return new ItemSerializable(this.material, this.count, this.name, this.lore);
    }
}
