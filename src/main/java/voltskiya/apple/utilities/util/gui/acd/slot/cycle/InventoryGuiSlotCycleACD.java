package voltskiya.apple.utilities.util.gui.acd.slot.cycle;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import voltskiya.apple.utilities.util.gui.acd.slot.InventoryGuiSlotSplitACD;
import voltskiya.apple.utilities.util.minecraft.InventoryUtils;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class InventoryGuiSlotCycleACD<EnumType extends SlotCycleable<EnumType>> implements InventoryGuiSlotSplitACD {
    private Supplier<EnumType> currentTypeGetter;
    private Consumer<EnumType> currentTypeSetter;
    private Function<EnumType, Material> convertToMaterial = null;
    private Function<EnumType, String> convertToName = null;
    private Function<EnumType, Integer> convertToCount = null;
    private Function<EnumType, List<String>> convertToLore = null;
    private Function<EnumType, ItemStack> convertToItem = null;

    public InventoryGuiSlotCycleACD(Supplier<EnumType> currentTypeGetter, Consumer<EnumType> currentTypeSetter) {
        this.currentTypeGetter = currentTypeGetter;
        this.currentTypeSetter = currentTypeSetter;
    }

    @Override
    public void leftClick(InventoryClickEvent event) {
        currentTypeSetter.accept(currentTypeGetter.get().left());
    }

    @Override
    public void rightClick(InventoryClickEvent event) {
        currentTypeSetter.accept(currentTypeGetter.get().right());
    }

    @Override
    public ItemStack getItem() {
        if (convertToItem != null) {
            return convertToItem.apply(currentTypeGetter.get());
        } else {
            Material material = convertToMaterial == null ? currentTypeGetter.get().itemMaterial() : convertToMaterial.apply(currentTypeGetter.get());
            int count = convertToCount == null ? currentTypeGetter.get().itemCount() : convertToCount.apply(currentTypeGetter.get());
            String name = convertToName == null ? currentTypeGetter.get().itemName() : convertToName.apply(currentTypeGetter.get());
            List<String> lore = convertToLore == null ? currentTypeGetter.get().itemLore() : convertToLore.apply(currentTypeGetter.get());

            return InventoryUtils.makeItem(
                    material,
                    count,
                    name,
                    lore
            );
        }
    }

    public InventoryGuiSlotCycleACD<EnumType> withConvertToMaterial(Function<EnumType, Material> convertToMaterial) {
        this.convertToMaterial = convertToMaterial;
        return this;
    }

    public InventoryGuiSlotCycleACD<EnumType> withConvertToName(Function<EnumType, String> convertToName) {
        this.convertToName = convertToName;
        return this;
    }

    public InventoryGuiSlotCycleACD<EnumType> withConvertToCount(Function<EnumType, Integer> convertToCount) {
        this.convertToCount = convertToCount;
        return this;
    }

    public InventoryGuiSlotCycleACD<EnumType> withConvertToLore(Function<EnumType, List<String>> convertToLore) {
        this.convertToLore = convertToLore;
        return this;
    }

    public InventoryGuiSlotCycleACD<EnumType> withConvertToItem(Function<EnumType, ItemStack> convertToItem) {
        this.convertToItem = convertToItem;
        return this;
    }
}
