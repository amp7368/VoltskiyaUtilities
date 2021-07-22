package voltskiya.apple.utilities.util.wand;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
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
}
