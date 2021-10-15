package voltskiya.apple.utilities.util.gui.acd.page;

import org.bukkit.inventory.InventoryHolder;
import voltskiya.apple.utilities.util.gui.OnGuiInventoryEvent;

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
