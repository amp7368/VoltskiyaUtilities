package voltskiya.apple.utilities.trash.wand;

import apple.lib.pmc.PluginModule;

public class PluginWand extends PluginModule {
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
