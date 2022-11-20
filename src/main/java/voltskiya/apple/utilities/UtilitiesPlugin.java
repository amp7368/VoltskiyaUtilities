package voltskiya.apple.utilities;


import com.voltskiya.lib.AbstractModule;
import com.voltskiya.lib.AbstractVoltPlugin;
import java.util.Collection;
import java.util.List;
import voltskiya.apple.utilities.event_listener.PluginListeners;

public class UtilitiesPlugin extends AbstractVoltPlugin {

    private static UtilitiesPlugin instance;

    public UtilitiesPlugin() {
        instance = this;
    }

    public static UtilitiesPlugin get() {
        return instance;
    }

    @Override
    public Collection<AbstractModule> getModules() {
        return List.of(
            new ModuleUtils(),
            new PluginListeners()
        );
    }
}
