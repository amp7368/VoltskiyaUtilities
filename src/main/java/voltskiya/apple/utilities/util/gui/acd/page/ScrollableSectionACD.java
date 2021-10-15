package voltskiya.apple.utilities.util.gui.acd.page;

import apple.utilities.util.NumberUtils;
import voltskiya.apple.utilities.util.gui.acd.slot.InventoryGuiSlotACD;
import voltskiya.apple.utilities.util.gui.acd.slot.InventoryGuiSlotDoNothingACD;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ScrollableSectionACD {
    private final List<InventoryGuiSlotACD> scrollables = new ArrayList<>();
    private final String name;
    private final int[] slotIndexes;
    private int currentIndex = 0;
    private int scrollAmount;

    public ScrollableSectionACD(String name, int[] slotIndexes) {
        this.name = name;
        this.slotIndexes = slotIndexes;
    }

    public ScrollableSectionACD(String name, int lower, int upper, int scrollAmount) {
        this(name, lower, upper);
        this.scrollAmount = scrollAmount;
    }

    public ScrollableSectionACD(String name, int lower, int upper) {
        this.name = name;
        List<Integer> slots = new ArrayList<>();
        int upperMod = (upper - 1) % 9;
        int lowerMod = lower % 9;
        for (int i = lower; i < upper; i++) {
            slots.add(i);
            if (i % 9 == upperMod) {
                i = (i / 9 + 1) * 9 - 1 + lowerMod;
            }
        }
        this.scrollAmount = upperMod - lowerMod;
        this.slotIndexes = slots.stream().mapToInt(Integer::intValue).toArray();
    }

    public void addItem(InventoryGuiSlotACD item) {
        scrollables.add(item);
    }

    public int[] getSlotIndex() {
        return slotIndexes;
    }

    public void scroll(int i) {
        this.currentIndex += i * scrollAmount;
    }

    public String getName() {
        return name;
    }

    public InventoryGuiSlotACD getItem(int index) {
        if (NumberUtils.between(0, index, scrollables.size()))
            return Objects.requireNonNullElse(scrollables.get(index), InventoryGuiSlotDoNothingACD.EMPTY);
        return InventoryGuiSlotDoNothingACD.EMPTY;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void clear() {
        this.scrollables.clear();
    }

    public void removeItem(InventoryGuiSlotACD slot) {
        this.scrollables.remove(slot);
    }
}
