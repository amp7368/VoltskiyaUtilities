package voltskiya.apple.utilities.util.gui.acd.slot;

import apple.utilities.lamdas.ObjectSupplier;
import apple.utilities.util.ArrayUtils;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import voltskiya.apple.utilities.util.minecraft.InventoryUtils;
import voltskiya.apple.utilities.util.minecraft.ItemNamed;

import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.function.Supplier;

public class InventoryGuiButtonTemplate {
    public static Supplier<ItemStack> configNamed(Material material, String name, ObjectSupplier currentVal, int increment) {
        return configNamed(material, name, Collections.emptyList(), currentVal, increment);
    }

    public static Supplier<ItemStack> configNamed(Material material, String name, List<String> lore, ObjectSupplier currentVal, int increment) {
        return new ItemNamed(material, 1, "{0}s: {2}d", ArrayUtils.combine(
                lore,
                "Left click - increase {1}s by {3}d",
                "Right click - decrease {1}s by {3}d"
        ), () -> name, () -> name.toLowerCase(Locale.ROOT), currentVal, () -> increment);
    }

    public static ItemStack blackGlassEmpty() {
        return InventoryUtils.makeItem(Material.BLACK_STAINED_GLASS_PANE, "");
    }

    public static InventoryGuiSlotDoNothingACD blackGlassDoNothing() {
        return new InventoryGuiSlotDoNothingACD(blackGlassEmpty());
    }
}
