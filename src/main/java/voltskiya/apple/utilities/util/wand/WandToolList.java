package voltskiya.apple.utilities.util.wand;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import voltskiya.apple.utilities.UtilitiesVoltskiyaPlugin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Level;

public class WandToolList implements Listener {
    private static final Map<NamespacedKey, WandTool<? extends WandPlayer>> wands = new HashMap<>();

    public WandToolList() {
        Bukkit.getPluginManager().registerEvents(this, UtilitiesVoltskiyaPlugin.get());
    }

    public static void addWand(NamespacedKey name, Function<Player, WandPlayer> createWandFromPlayer) {
        wands.put(name, new WandTool<>(name, createWandFromPlayer));
    }

    public static void addWand(WandTool<?> wandTool) {
        wands.put(wandTool.getName(), wandTool);
    }


    public static <T extends WandPlayer> T getPlayerWand(@NotNull NamespacedKey name, @NotNull Player player, @NotNull Class<T> clazz) {
        WandPlayer wand = wands.get(name).getOrCreateWand(player);
        return clazz.cast(wand);
    }

    @EventHandler
    public void onWand(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item != null) {
            @NotNull PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
            for (WandTool<?> wand : wands.values()) {
                String wandValue = container.get(wand.getName(), PersistentDataType.STRING);
                if (wandValue != null) {
                    WandPlayer playerWand = wand.getOrCreateWand(event.getPlayer());
                    try {
                        Method method = playerWand.getClass().getMethod("dealWithUse", PlayerInteractEvent.class, String.class);
                        WandUse wanduse = method.getAnnotation(WandUse.class);
                        Action actionDone = event.getAction();
                        for (Action action : wanduse.action()) {
                            if (action == actionDone) {
                                playerWand.dealWithUse(event, wandValue);
                                break;
                            }
                        }
                    } catch (NoSuchMethodException e) {
                        PluginWand.get().log(Level.SEVERE, "Annotation in dealWithUse in WandPlayer does not exist");
                    }
                }
            }
        }
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerLeave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        for (WandTool<?> wand : wands.values()) {
            WandPlayer wandPlayer = wand.hasPlayer(player);
            if (wandPlayer != null && wandPlayer.onLeave()) {
                wand.remove(player);
            }
        }
    }
}
