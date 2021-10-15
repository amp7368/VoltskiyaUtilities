package voltskiya.apple.utilities.util.command;

import co.aikar.commands.BaseCommand;
import co.aikar.commands.PaperCommandManager;

public interface CommandManagerHolder {
    PaperCommandManager getCommandManager();

    default <Command extends BaseCommand> void registerCommand(Command command) {
        getCommandManager().registerCommand(command);
    }
}
