package voltskiya.apple.utilities;


import plugin.util.plugin.plugin.util.plugin.PluginManaged;
import plugin.util.plugin.plugin.util.plugin.PluginManagedModule;
import voltskiya.apple.utilities.util.PluginUtils;
import voltskiya.apple.utilities.util.event_listener.PluginListeners;
import voltskiya.apple.utilities.util.gui.PluginInventoryGui;
import voltskiya.apple.utilities.util.wand.PluginWand;

import java.util.Collection;
import java.util.List;

public class UtilitiesVoltskiyaPlugin extends PluginManaged {
    private static UtilitiesVoltskiyaPlugin instance;

    public UtilitiesVoltskiyaPlugin() {
        instance = this;
    }

    public static UtilitiesVoltskiyaPlugin get() {
        return instance;
    }

    @Override
    public Collection<PluginManagedModule> getModules() {
        return List.of(
                new PluginUtils(),
                new PluginInventoryGui(),
                new PluginWand(),
                new PluginListeners()
        );
    }
}
