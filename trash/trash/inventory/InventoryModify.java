package voltskiya.apple.utilities.trash.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryModify {
    private Material desiredMaterial;
    private int desiredAmount = 1;

    private ActionPreCondition actionPreCondition = ActionPreCondition.ALWAYS;
    private ActionOnItems actionOnItems = ActionOnItems.REMOVE_UNTIL_GOAL_REACHED;

    public InventoryModify(Material desiredMaterial) {
        this.desiredMaterial = desiredMaterial;
    }

    public static InventoryModify withMaterial(Material material) {
        return new InventoryModify(material);
    }

    public InventoryModify withDesiredMaterial(Material desiredMaterial) {
        this.desiredMaterial = desiredMaterial;
        return this;
    }

    public InventoryModify withDesiredAmount(int desiredAmount) {
        this.desiredAmount = desiredAmount;
        return this;
    }

    public InventoryModify withActionPreCondition(ActionPreCondition actionPreCondition) {
        this.actionPreCondition = actionPreCondition;
        return this;
    }

    public InventoryModify withActionOnItems(ActionOnItems actionOnItems) {
        this.actionOnItems = actionOnItems;
        return this;
    }

    public InventoryModifyResult runOnInventory(Inventory inventory) {
        InventoryModifyResultBuilder result = new InventoryModifyResultBuilder(desiredMaterial);
        if (!actionPreCondition.test(this, inventory)) {
            return result.build();
        }
        result.preconditionSatisfied = true;
        int countPassed = 0;
        ItemStack[] contents = inventory.getContents();
        for (int i = 0; i < contents.length; i++) {
            ItemStack itemThere = contents[i];
            if (itemThere != null && itemThere.getType() == desiredMaterial) {
                int amountThere = itemThere.getAmount();
                result.countOfTypeFound += amountThere;
                result.stacksOfTypeFound++;
                int oldCountPassed = countPassed;
                countPassed += amountThere;
                if (oldCountPassed > desiredAmount) {
                    contents[i] = actionOnItems.afterGoalReached(result, itemThere);
                } else if (countPassed < desiredAmount) {
                    contents[i] = actionOnItems.reachGoal(result, itemThere, desiredAmount - oldCountPassed);
                } else if (countPassed > desiredAmount) {
                    contents[i] = actionOnItems.beforeGoalReached(result, itemThere, desiredAmount, countPassed);
                }
            }
        }
        inventory.setContents(contents);
        return result.build();
    }

    @FunctionalInterface
    public interface ActionPreCondition {
        ActionPreCondition ONLY_IF_COUNT_EXISTS = (action, inv) -> inv.contains(action.desiredMaterial, action.desiredAmount);
        ActionPreCondition ALWAYS = (action, inv) -> true;

        boolean test(InventoryModify inventoryModify, Inventory inventory);
    }


    public interface ActionOnItems {
        ActionOnItems REMOVE_UNTIL_GOAL_REACHED = new ActionOnItems() {
            @Override
            public ItemStack afterGoalReached(InventoryModifyResultBuilder result, ItemStack itemThere) {
                return itemThere;
            }

            @Override
            public ItemStack reachGoal(InventoryModifyResultBuilder result, ItemStack itemThere, int amountToReachGoal) {
                int modified = itemThere.getAmount() - amountToReachGoal;
                result.amountModified += modified;
                result.stacksModified++;
                itemThere.setAmount(modified);
                return itemThere;
            }

            @Override
            public ItemStack beforeGoalReached(InventoryModifyResultBuilder result, ItemStack itemThere, int desiredAmount, int countPassed) {
                result.amountModified += itemThere.getAmount();
                result.stacksModified++;
                return null;
            }
        };
        ActionOnItems REMOVE_ALL = new ActionOnItems() {
            @Override
            public ItemStack afterGoalReached(InventoryModifyResultBuilder result, ItemStack itemThere) {
                result.amountModified += itemThere.getAmount();
                result.stacksModified++;
                return null;
            }

            @Override
            public ItemStack reachGoal(InventoryModifyResultBuilder result, ItemStack itemThere, int amountToReachGoal) {
                result.amountModified += itemThere.getAmount();
                result.stacksModified++;
                return null;
            }

            @Override
            public ItemStack beforeGoalReached(InventoryModifyResultBuilder result, ItemStack itemThere, int desiredAmount, int countPassed) {
                result.amountModified += itemThere.getAmount();
                result.stacksModified++;
                return null;
            }
        };
        ActionOnItems DO_NOTHING = new ActionOnItems() {
            @Override
            public ItemStack afterGoalReached(InventoryModifyResultBuilder result, ItemStack itemThere) {
                return itemThere;
            }

            @Override
            public ItemStack reachGoal(InventoryModifyResultBuilder result, ItemStack itemThere, int amountToReachGoal) {
                return itemThere;
            }

            @Override
            public ItemStack beforeGoalReached(InventoryModifyResultBuilder result, ItemStack itemThere, int desiredAmount, int countPassed) {
                return itemThere;
            }
        };

        ItemStack afterGoalReached(InventoryModifyResultBuilder result, ItemStack itemThere);

        ItemStack reachGoal(InventoryModifyResultBuilder result, ItemStack itemThere, int amountToReachGoal);

        ItemStack beforeGoalReached(InventoryModifyResultBuilder result, ItemStack itemThere, int desiredAmount, int countPassed);
    }
}
