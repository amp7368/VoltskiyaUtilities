package voltskiya.apple.utilities.util.gui.acd.slot;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.function.Supplier;

public class InventoryGuiSlotSplitImplACD implements InventoryGuiSlotSplitACD {
    private final ItemStack item;
    private final Supplier<ItemStack> itemSupplier;
    private InventoryGuiSlotClickableACD noShiftLeftClick = null;
    private InventoryGuiSlotClickableACD shiftLeftClick = null;
    private InventoryGuiSlotClickableACD noShiftRightClick = null;
    private InventoryGuiSlotClickableACD shiftRightClick = null;
    private InventoryGuiSlotClickableACD leftClick = null;
    private InventoryGuiSlotClickableACD rightClick = null;
    private InventoryGuiSlotClickableACD keyboardClick = null;

    public InventoryGuiSlotSplitImplACD(ItemStack item) {
        this.item = item;
        this.itemSupplier = null;
    }

    public InventoryGuiSlotSplitImplACD(Supplier<ItemStack> itemSupplier) {
        this.item = null;
        this.itemSupplier = itemSupplier;
    }

    public InventoryGuiSlotSplitImplACD withNoShiftLeftClick(InventoryGuiSlotClickableACD noShiftLeftClick) {
        this.noShiftLeftClick = noShiftLeftClick;
        return this;
    }

    public InventoryGuiSlotSplitImplACD withShiftLeftClick(InventoryGuiSlotClickableACD shiftLeftClick) {
        this.shiftLeftClick = shiftLeftClick;
        return this;
    }

    public InventoryGuiSlotSplitImplACD withNoShiftRightClick(InventoryGuiSlotClickableACD noShiftRightClick) {
        this.noShiftRightClick = noShiftRightClick;
        return this;
    }

    public InventoryGuiSlotSplitImplACD withShiftRightClick(InventoryGuiSlotClickableACD shiftRightClick) {
        this.shiftRightClick = shiftRightClick;
        return this;
    }

    public InventoryGuiSlotSplitImplACD withLeftClick(InventoryGuiSlotClickableACD leftClick) {
        this.leftClick = leftClick;
        return this;
    }

    public InventoryGuiSlotSplitImplACD withRightClick(InventoryGuiSlotClickableACD rightClick) {
        this.rightClick = rightClick;
        return this;
    }

    public InventoryGuiSlotSplitImplACD withKeyboardClick(InventoryGuiSlotClickableACD keyboardClick) {
        this.keyboardClick = keyboardClick;
        return this;
    }

    @Override
    public ItemStack getItem() {
        if (itemSupplier != null) return itemSupplier.get();
        return item;
    }

    @Override
    public void noShiftLeftClick(InventoryClickEvent event) {
        if (noShiftLeftClick != null) noShiftLeftClick.dealWithClick(event);
    }

    @Override
    public void shiftLeftClick(InventoryClickEvent event) {
        if (shiftLeftClick != null) shiftLeftClick.dealWithClick(event);
    }

    @Override
    public void noShiftRightClick(InventoryClickEvent event) {
        if (noShiftRightClick != null) noShiftRightClick.dealWithClick(event);
    }

    @Override
    public void shiftRightClick(InventoryClickEvent event) {
        if (shiftRightClick != null) shiftRightClick.dealWithClick(event);
    }

    @Override
    public void leftClick(InventoryClickEvent event) {
        if (leftClick != null) leftClick.dealWithClick(event);
    }

    @Override
    public void rightClick(InventoryClickEvent event) {
        if (rightClick != null) rightClick.dealWithClick(event);
    }

    @Override
    public void keyboardClick(InventoryClickEvent event) {
        if (keyboardClick != null) keyboardClick.dealWithClick(event);
    }
}
