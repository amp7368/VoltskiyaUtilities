package voltskiya.apple.utilities.util.wand;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

public class WandTool<W extends WandPlayer> {
    private final HashMap<UUID, W> wands = new HashMap<>();
    private final NamespacedKey name;
    private final Function<Player, W> createWandFromPlayer;

    public WandTool(NamespacedKey name, Function<Player, W> createWandFromPlayer) {
        this.name = name;
        this.createWandFromPlayer = createWandFromPlayer;
    }

    public NamespacedKey getName() {
        return name;
    }

    @NotNull
    public W getOrCreateWand(Player player) {
        return wands.computeIfAbsent(player.getUniqueId(), p -> createWandFromPlayer.apply(player));
    }

    @Nullable
    public W hasPlayer(Player player) {
        return wands.get(player.getUniqueId());
    }

    public void remove(Player player) {
        wands.remove(player.getUniqueId());
    }

    public ItemStack createWandItem(ItemStack wand, @Nullable String wandDataValue) {
        final ItemMeta itemMeta = wand.getItemMeta();
        itemMeta.getPersistentDataContainer().set(name, PersistentDataType.STRING, Objects.requireNonNullElse(wandDataValue, ""));
        wand.setItemMeta(itemMeta);
        return wand;
    }

    public ItemStack createWandItem(ItemStack wand) {
        return this.createWandItem(wand, null);
    }
}
