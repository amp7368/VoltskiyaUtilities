package voltskiya.apple.utilities.util.minecraft;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ChatUtils {
    public static void sendMessage(Player player, ChatColor color, String message) {
        player.sendMessage(color + message);
    }

    public static void sendMessage(Player player, ChatColor color, String format, Object... args) {
        sendMessage(player, color, String.format(format, args));
    }
}
