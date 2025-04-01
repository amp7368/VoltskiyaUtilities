package voltskiya.apple.utilities.serializing;

import java.util.HashMap;
import java.util.Map;
import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class EnchantmentSerializeable {

    private static final EnchantmentSerializeable EMPTY = new EnchantmentSerializeable();

    private final Map<NamespacedKey, Integer> enchantments = new HashMap<>();

    public EnchantmentSerializeable() {
    }

    public EnchantmentSerializeable(ItemStack item) {
        for (Map.Entry<Enchantment, Integer> enchantment : item.getEnchantments().entrySet()) {
            enchantments.put(enchantment.getKey().getKey(), enchantment.getValue());
        }
    }

    public static EnchantmentSerializeable empty() {
        return EMPTY;
    }

    public int getEnchantment(Enchantment e) {
        return enchantments.getOrDefault(e.getKey(), 0);
    }

    public boolean hasEnchantment(Enchantment e) {
        return enchantments.containsKey(e.getKey());
    }

    public void applyEnchantments(ItemStack item) {
        for (Map.Entry<NamespacedKey, Integer> enchantment : this.enchantments.entrySet()) {
            Enchantment ench = Enchantment.getByKey(enchantment.getKey());
            if (ench != null)
                item.addEnchantment(ench, enchantment.getValue());
        }
    }
}
