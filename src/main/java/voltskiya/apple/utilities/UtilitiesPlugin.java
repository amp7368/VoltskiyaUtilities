package voltskiya.apple.utilities;


import apple.lib.pmc.ApplePlugin;
import apple.lib.pmc.PluginModule;
import voltskiya.apple.utilities.event_listener.PluginListeners;

import java.util.Collection;
import java.util.List;

public class UtilitiesPlugin extends ApplePlugin {
    private static UtilitiesPlugin instance;

    public UtilitiesPlugin() {
        instance = this;
    }

    public static UtilitiesPlugin get() {
        return instance;
    }

    @Override
    public Collection<PluginModule> getModules() {
        return List.of(
                new PluginUtils(),
                new PluginListeners()
        );
    }
}
