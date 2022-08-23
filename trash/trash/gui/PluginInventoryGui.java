package voltskiya.apple.utilities.trash.gui;


import apple.lib.pmc.PluginModule;

public class PluginInventoryGui extends PluginModule {
    @Override
    public void enable() {
        new InventoryGuiListener();
    }

    @Override
    public String getName() {
        return "inventory_gui";
    }
}