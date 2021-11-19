package voltskiya.apple.utilities.util.gui;


import plugin.util.plugin.plugin.util.plugin.PluginManagedModule;

public class PluginInventoryGui extends PluginManagedModule {
    @Override
    public void enable() {
        new InventoryGuiListener();
    }

    @Override
    public String getName() {
        return "inventory_gui";
    }
}