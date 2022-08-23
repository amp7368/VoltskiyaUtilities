package voltskiya.apple.utilities.trash.inventory;

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
