package voltskiya.apple.utilities.util.minecraft.inventory;

import org.bukkit.Material;

public record InventoryModifyResult(
        boolean preconditionSatisfied,
        int countModified,
        int stacksModified,
        int stacksFound,
        int countFound,
        Material materialDesired
) {
}
