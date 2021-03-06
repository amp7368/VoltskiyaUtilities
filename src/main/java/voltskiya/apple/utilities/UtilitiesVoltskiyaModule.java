package voltskiya.apple.utilities;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class UtilitiesVoltskiyaModule {

    private boolean isEnabled;
    private Logger logger;

    public void init() {
    }

    public abstract void enable();

    public abstract String getName();

    public void onDisable() {
    }

    boolean shouldEnable() {
        return true;
    }

    protected void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public File getDataFolder() {
        File dataFolder = new File(UtilitiesVoltskiyaPlugin.get().getDataFolder(), getName().toLowerCase());
        if (!dataFolder.exists()) dataFolder.mkdirs();
        return dataFolder;
    }

    public void log(Level level, String message) {
        logger.log(level, String.format("[%s] %s", getName(), message));
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @Override
    public int hashCode() {
        return getName().hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UtilitiesVoltskiyaModule) return obj.hashCode() == hashCode();
        return false;
    }


}
