package voltskiya.apple.utilities.util.gui.acd.page;

import org.bukkit.Material;
import voltskiya.apple.utilities.util.gui.acd.slot.InventoryGuiSlotACD;
import voltskiya.apple.utilities.util.gui.acd.slot.InventoryGuiSlotImplACD;
import voltskiya.apple.utilities.util.minecraft.InventoryUtils;

import java.util.HashMap;
import java.util.Map;

public abstract class InventoryGuiPageScrollableACD<Parent extends GuiPageable> extends InventoryGuiPageImplACD<Parent> {
    private final Map<String, ScrollableSectionACD> sections = new HashMap<>();
    private boolean shouldAddDefaultSection = true;

    public InventoryGuiPageScrollableACD(Parent holder) {
        this(holder, true);
    }

    public InventoryGuiPageScrollableACD(Parent holder, boolean shouldAddDefaultSection) {
        super(holder);
        this.shouldAddDefaultSection = shouldAddDefaultSection;
        if (shouldAddDefaultSection()) {
            addSection(new ScrollableSectionACD("", 9, size() - 1));
            setSlot(new InventoryGuiSlotImplACD((e) -> this.getSection("").scroll(-1),
                    InventoryUtils.makeItem(Material.REDSTONE_TORCH, 1, "Up", null)), 17);
            setSlot(new InventoryGuiSlotImplACD((e) -> this.getSection("").scroll(1),
                    InventoryUtils.makeItem(Material.LEVER, 1, "Down", null)), 26);
        }
    }

    @Override
    public void initialize2() {
        setSlots();
        super.initialize2();
    }


    protected boolean shouldAddDefaultSection() {
        return shouldAddDefaultSection;
    }

    protected void addSection(ScrollableSectionACD section) {
        this.sections.put(section.getName(), section);
    }

    protected ScrollableSectionACD getSection(String name) {
        return this.sections.get(name);
    }

    @Override
    public void finalizePageItems() {
        setSlots();
        super.finalizePageItems();
    }

    public void setSlots() {
        for (ScrollableSectionACD section : sections.values()) {
            int itemIndex = section.getCurrentIndex();
            for (int slotIndex : section.getSlotIndex()) {
                this.setSlot(section.getItem(itemIndex++), slotIndex);
            }
        }
    }

    public void add(String section, InventoryGuiSlotACD slot) {
        this.sections.get(section).addItem(slot);
    }

    public void add(InventoryGuiSlotACD slot) {
        this.sections.get("").addItem(slot);
    }

    public void remove(String section, InventoryGuiSlotACD slot) {
        this.sections.get(section).removeItem(slot);
    }

    public void clear() {
        for (ScrollableSectionACD section : sections.values()) {
            section.clear();
        }
    }

}
