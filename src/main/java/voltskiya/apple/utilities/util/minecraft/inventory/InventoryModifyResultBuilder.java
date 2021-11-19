package voltskiya.apple.utilities.util.minecraft.inventory;

import org.bukkit.Material;

public class InventoryModifyResultBuilder {
    public boolean preconditionSatisfied = false;
    public int amountModified = 0;
    public int stacksModified = 0;
    public int stacksOfTypeFound = 0;
    public int countOfTypeFound = 0;
    public Material materialDesired;

    public InventoryModifyResultBuilder(Material materialDesired) {
        this.materialDesired = materialDesired;
    }

    public InventoryModifyResult build() {
        return new InventoryModifyResult(preconditionSatisfied, amountModified, stacksModified, stacksOfTypeFound, countOfTypeFound, materialDesired);
    }
}
