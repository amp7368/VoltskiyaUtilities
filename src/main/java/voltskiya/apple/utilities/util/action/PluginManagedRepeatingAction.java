package voltskiya.apple.utilities.util.action;

import plugin.util.plugin.plugin.util.plugin.PluginManagedModule;

public interface PluginManagedRepeatingAction {
    PluginManagedModule getModule();

    default RepeatingActionManager createRepeatingAction() {
        return new RepeatingActionManager(getModule().getPlugin());
    }
}
