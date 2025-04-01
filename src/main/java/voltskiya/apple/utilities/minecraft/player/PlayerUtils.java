package voltskiya.apple.utilities.minecraft.player;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public interface PlayerUtils {

    static boolean isSurvival(GameMode gamemode) {
        return gamemode == GameMode.SURVIVAL || gamemode == GameMode.ADVENTURE;
    }

    static boolean isSurvival(Player player) {
        return isSurvival(player.getGameMode());
    }

}
