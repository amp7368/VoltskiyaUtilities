package voltskiya.apple.utilities.util.wand;

import plugin.util.plugin.plugin.util.plugin.PluginManagedModule;

public class PluginWand extends PluginManagedModule {
    private static PluginWand instance;

    public static PluginWand get() {
        return instance;
    }

    @Override
    public void enable() {
        instance = this;
        new WandToolList();
    }

    @Override
    public String getName() {
        return "wand";
    }
}
