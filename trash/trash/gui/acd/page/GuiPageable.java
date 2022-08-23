package voltskiya.apple.utilities.trash.gui.acd.page;

import org.bukkit.inventory.InventoryHolder;
import voltskiya.apple.utilities.trash.gui.OnGuiInventoryEvent;

public interface GuiPageable extends InventoryHolder {

    void parentAddSubPage(OnGuiInventoryEvent subPage);

    void parentRemoveSubPage();

    void parentNext(int i);

    void parentRefresh();

    default void parentPrev() {
        parentNext(-1);
    }

    default void parentNext() {
        parentNext(1);
    }

}
