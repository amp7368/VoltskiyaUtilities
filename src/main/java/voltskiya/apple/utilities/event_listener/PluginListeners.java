package voltskiya.apple.utilities.event_listener;

import apple.lib.pmc.PluginModule;
import voltskiya.apple.utilities.event_listener.manage.EntityMainListener;

public class PluginListeners extends PluginModule {
    @Override
    public void enable() {
        new EntityMainListener();
    }

    @Override
    public String getName() {
        return "listeners";
    }
}
