package voltskiya.apple.utilities.trash.gui.acd.wrapped;

import voltskiya.apple.utilities.trash.gui.acd.page.GuiPageable;
import voltskiya.apple.utilities.trash.gui.acd.page.InventoryGuiPageScrollableACD;

public abstract class InventoryGuiPageScrollableAWD<Parent extends GuiPageable, DirectParent> extends InventoryGuiPageScrollableACD<Parent> {
    protected final DirectParent directParent;

    public InventoryGuiPageScrollableAWD(Parent parent, DirectParent directParent) {
        super(parent);
        this.directParent = directParent;
    }
}
