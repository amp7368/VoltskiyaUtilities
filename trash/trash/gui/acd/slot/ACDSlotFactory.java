package voltskiya.apple.utilities.trash.gui.acd.slot;

import org.bukkit.inventory.ItemStack;
import voltskiya.apple.utilities.trash.gui.acd.slot.cycle.InventoryGuiSlotCycleACD;
import voltskiya.apple.utilities.trash.gui.acd.slot.cycle.SlotCycleable;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface ACDSlotFactory {
    default InventoryGuiSlotImplACD slotImpl(InventoryGuiSlotClickableACD dealWithClick, Supplier<ItemStack> itemStack) {
        return new InventoryGuiSlotImplACD(dealWithClick, itemStack);
    }

    default InventoryGuiSlotImplACD slotImpl(InventoryGuiSlotClickableACD dealWithClick, ItemStack itemStack) {
        return new InventoryGuiSlotImplACD(dealWithClick, itemStack);
    }

    default InventoryGuiSlotDoNothingACD slotDoNothing(ItemStack item) {
        return new InventoryGuiSlotDoNothingACD(item);
    }

    default <EnumType extends SlotCycleable<EnumType>> InventoryGuiSlotCycleACD<EnumType> slotCycle(Supplier<EnumType> getter, Consumer<EnumType> setter) {
        return new InventoryGuiSlotCycleACD<>(getter, setter);
    }
}
