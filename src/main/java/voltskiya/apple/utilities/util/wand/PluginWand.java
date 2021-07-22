package voltskiya.apple.utilities.util.wand;

import voltskiya.apple.utilities.UtilitiesVoltskiyaModule;

public class PluginWand extends UtilitiesVoltskiyaModule {
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
