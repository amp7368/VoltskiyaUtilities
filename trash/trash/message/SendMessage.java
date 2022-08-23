package voltskiya.apple.utilities.trash.message;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class SendMessage {
    public static void sendMessage(CommandSender sender, ChatColor color, String formatted, Object... args) {
        sender.sendMessage(color + String.format(formatted, args));
    }

    public static void sendMessageRed(CommandSender sender, String formatted, Object... args) {
        sender.sendMessage(ChatColor.RED + String.format(formatted, args));
    }

    public static void sendMessageGreen(CommandSender sender, String formatted, Object... args) {
        sender.sendMessage(ChatColor.GREEN + String.format(formatted, args));
    }

    public static void sendMessageAqua(CommandSender sender, String formatted, Object... args) {
        sender.sendMessage(ChatColor.AQUA + String.format(formatted, args));
    }
}
