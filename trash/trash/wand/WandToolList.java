package voltskiya.apple.utilities.trash.wand;

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
import org.jetbrains.annotations.Nullable;
import voltskiya.apple.utilities.UtilitiesVoltskiyaPlugin;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

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

    public static ItemStack createWandItem(NamespacedKey key, ItemStack item, @Nullable String wandDataValue) {
        return wands.get(key).createWandItem(item, wandDataValue);
    }

    public static ItemStack createWandItem(NamespacedKey key, ItemStack item) {
        return createWandItem(key, item, null);
    }

    @NotNull
    public static <T extends WandPlayer> T getPlayerWand(@NotNull NamespacedKey name, @NotNull Player player, @NotNull Class<T> clazz) {
        WandPlayer wand = wands.get(name).getOrCreateWand(player);
        return clazz.cast(wand);
    }

    @EventHandler
    public void onWand(PlayerInteractEvent event) {
        ItemStack item = event.getItem();
        if (item == null) return;
        @NotNull PersistentDataContainer container = item.getItemMeta().getPersistentDataContainer();
        for (NamespacedKey key : container.getKeys()) {
            WandTool<? extends WandPlayer> wand = wands.get(key);
            if (wand == null) continue;
            String wandValue = container.get(wand.getName(), PersistentDataType.STRING);
            if (wandValue == null) continue;
            WandPlayer playerWand = wand.getOrCreateWand(event.getPlayer());
            try {
                Method method = playerWand.getClass().getMethod("dealWithUse", PlayerInteractEvent.class, String.class);
                WandUse wanduse = method.getAnnotation(WandUse.class);
                Action[] actionsAllowed;
                if (wanduse != null) {
                    actionsAllowed = wanduse.action();
                } else {
                    actionsAllowed = playerWand.getOnActions();
                }
                Action actionDone = event.getAction();
                for (Action action : actionsAllowed) {
                    if (action == actionDone) {
                        event.setCancelled(playerWand.shouldCancel());
                        playerWand.dealWithUse(event, wandValue);
                        break;
                    }
                }
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
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
