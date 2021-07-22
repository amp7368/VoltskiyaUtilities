package voltskiya.apple.utilities.util;

import voltskiya.apple.utilities.UtilitiesVoltskiyaModule;

public class PluginUtils extends UtilitiesVoltskiyaModule {
    private static PluginUtils instance;

    public static PluginUtils get() {
        return instance;
    }

    @Override
    public void init() {
        instance = this;
    }

    @Override
    public void enable() {
    }

    @Override
    public String getName() {
        return "utilities";
    }
}
