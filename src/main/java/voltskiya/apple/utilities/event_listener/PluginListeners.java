package voltskiya.apple.utilities.event_listener;

import com.voltskiya.lib.AbstractModule;
import voltskiya.apple.utilities.event_listener.manage.EntityMainListener;

public class PluginListeners extends AbstractModule {

    @Override
    public void enable() {
        new EntityMainListener();
    }

    @Override
    public String getName() {
        return "listeners";
    }
}
