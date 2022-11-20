package voltskiya.apple.utilities;

import com.voltskiya.lib.AbstractModule;

public class ModuleUtils extends AbstractModule {

    private static ModuleUtils instance;

    public static ModuleUtils get() {
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
