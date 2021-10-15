package voltskiya.apple.utilities.util.event_listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public interface PluginListenerHolder<Me extends JavaPlugin> {
    Me getMe();

    default void registerListener(@NotNull Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, getMe());
    }
}
