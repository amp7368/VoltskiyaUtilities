package voltskiya.apple.utilities.util.minecraft;

import apple.utilities.lamdas.ObjectSupplier;
import apple.utilities.structures.StringFormattedBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ItemNamed implements Supplier<ItemStack> {
    private final Material material;
    private final int count;
    private final StringFormattedBuilder name;
    private final List<StringFormattedBuilder> lore;

    public ItemNamed(Material material, int count, String name, List<String> lore, ObjectSupplier... arguments) {
        this.material = material;
        this.count = count;
        this.name = new StringFormattedBuilder(name, arguments);
        this.lore = lore.stream().map(l -> new StringFormattedBuilder(l, arguments)).collect(Collectors.toList());
    }

    public ItemStack getItem() {
        return InventoryUtils.makeItem(material, count, name.getString(), lore.stream().map(StringFormattedBuilder::getString).collect(Collectors.toList()));
    }

    @Override
    public ItemStack get() {
        return getItem();
    }
}
