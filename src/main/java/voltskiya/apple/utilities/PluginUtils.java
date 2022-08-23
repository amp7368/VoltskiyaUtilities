package voltskiya.apple.utilities;

import apple.lib.pmc.PluginModule;

public class PluginUtils extends PluginModule {
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
