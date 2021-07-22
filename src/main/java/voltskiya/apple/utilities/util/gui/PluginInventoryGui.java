package voltskiya.apple.utilities.util.gui;


import voltskiya.apple.utilities.UtilitiesVoltskiyaModule;

public class PluginInventoryGui extends UtilitiesVoltskiyaModule {
    @Override
    public void enable() {
        new InventoryGuiListener();
    }

    @Override
    public String getName() {
        return "inventory_gui";
    }
}