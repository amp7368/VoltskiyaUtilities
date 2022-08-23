package voltskiya.apple.utilities.trash.gui.acd.slot;

import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

public interface InventoryGuiSlotSplitACD extends InventoryGuiSlotACD {
    @Override
    default void dealWithClick(InventoryClickEvent event) {
        ClickType click = event.getClick();
        if (click.isRightClick()) {
            if (click.isShiftClick()) {
                this.shiftRightClick(event);
            } else {
                this.noShiftRightClick(event);
            }
            this.rightClick(event);
        } else if (click.isLeftClick()) {
            if (click.isShiftClick()) {
                this.shiftLeftClick(event);
            } else {
                this.noShiftLeftClick(event);
            }
            this.leftClick(event);
        } else if (click.isKeyboardClick()) {
            this.keyboardClick(event);
        }
    }

    default void noShiftLeftClick(InventoryClickEvent event) {
    }

    default void shiftLeftClick(InventoryClickEvent event) {
    }

    default void noShiftRightClick(InventoryClickEvent event) {
    }

    default void shiftRightClick(InventoryClickEvent event) {
    }

    default void leftClick(InventoryClickEvent event) {
    }

    default void rightClick(InventoryClickEvent event) {
    }

    default void keyboardClick(InventoryClickEvent event) {
    }
}
