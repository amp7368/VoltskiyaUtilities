package voltskiya.apple.utilities;


import co.aikar.commands.PaperCommandManager;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import voltskiya.apple.utilities.util.PluginUtils;
import voltskiya.apple.utilities.util.gui.PluginInventoryGui;
import voltskiya.apple.utilities.util.wand.PluginWand;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class UtilitiesVoltskiyaPlugin extends JavaPlugin {
    private static UtilitiesVoltskiyaPlugin instance;
    private PaperCommandManager commandManager;
    private final List<UtilitiesVoltskiyaModule> modules = new ArrayList<>();
    private final List<String> loadedJars = new ArrayList<>();
    private LuckPerms luckPerms;

    @Override
    public void onLoad() {
        instance = this;
        manuallyLoadModules();
    }


    @Override
    public void onEnable() {
//        loadDependencies();
        setupACF();
        setupLuckPerms();
        enableModules();
    }

    @Override
    public void onDisable() {
        for (UtilitiesVoltskiyaModule module : modules) {
            if (module.shouldEnable()) {
                module.onDisable();
            }
        }
    }

    private void manuallyLoadModules() {
        final UtilitiesVoltskiyaModule[] modules = new UtilitiesVoltskiyaModule[]{
                new PluginUtils(),
                new PluginInventoryGui(),
                new PluginWand()
        };
        for (UtilitiesVoltskiyaModule module : modules) {
            registerModule(module);
            if (module.shouldEnable()) {
                loadModule(module);
            }
        }
    }

    private void enableModules() {
        for (UtilitiesVoltskiyaModule module : modules) {
            if (module.shouldEnable()) {
                enableModule(module);
            }
        }
    }

    private void registerModule(UtilitiesVoltskiyaModule module) {
        modules.add(module);
        module.setLogger(getLogger());
        module.setEnabled(false);
    }

    private void loadModule(UtilitiesVoltskiyaModule module) {
        module.init();
    }

    public void enableModule(UtilitiesVoltskiyaModule module) {
        module.setEnabled(true);
        module.enable();
        getLogger().log(Level.INFO, "Enabled Voltskiya Module: " + module.getName());
    }

    private void setupACF() {
        commandManager = new PaperCommandManager(this);
    }

    private void setupLuckPerms() {
        if (Bukkit.getPluginManager().isPluginEnabled("LuckPerms")) {
            luckPerms = LuckPermsProvider.get();
        } else {
            getLogger().log(Level.WARNING, "LuckPerms is not enabled, some functions may be disabled.");
        }
    }

    public static UtilitiesVoltskiyaPlugin get() {
        return instance;
    }

    public @NotNull LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public PaperCommandManager getCommandManager() {
        return commandManager;
    }


}
