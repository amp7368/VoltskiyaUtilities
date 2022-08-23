package voltskiya.apple.utilities.trash.gui.acd.wrapped;

import voltskiya.apple.utilities.trash.gui.acd.page.GuiPageable;
import voltskiya.apple.utilities.trash.gui.acd.page.InventoryGuiPageImplACD;

public abstract class InventoryGuiPageImplAWD<Parent extends GuiPageable, DirectParent> extends InventoryGuiPageImplACD<Parent> {
    protected DirectParent directParent;

    public InventoryGuiPageImplAWD(Parent parent, DirectParent directParent) {
        super(parent);
        this.directParent = directParent;
    }
}
