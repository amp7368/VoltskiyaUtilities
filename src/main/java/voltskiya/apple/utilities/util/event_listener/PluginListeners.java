package voltskiya.apple.utilities.util.event_listener;

import plugin.util.plugin.plugin.util.plugin.PluginManagedModule;
import voltskiya.apple.utilities.util.event_listener.manage.EntityMainListener;

public class PluginListeners extends PluginManagedModule {
    @Override
    public void enable() {
        new EntityMainListener();
    }

    @Override
    public String getName() {
        return "listeners";
    }
}
