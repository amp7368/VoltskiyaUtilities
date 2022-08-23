package voltskiya.apple.utilities.trash;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class ChatUtils {
    public static void sendMessage(CommandSender player, ChatColor color, String message) {
        player.sendMessage(color + message);
    }

    public static void sendMessage(CommandSender player, ChatColor color, String format, Object... args) {
        sendMessage(player, color, String.format(format, args));
    }
}
