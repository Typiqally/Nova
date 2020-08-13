package org.nova.packagemanager.commands;

import org.bukkit.command.CommandSender;
import org.nova.framework.CommandManifest;
import org.nova.framework.CommandType;
import org.nova.framework.NovaCommand;

import java.util.Arrays;

@CommandManifest(
        name = "package",
        description = "The main package command",
        permissions = "nova.packagemanager",
        type = CommandType.BOTH
)
public class PackageCommand extends NovaCommand {

    private static final NovaCommand[] SUB_COMMANDS = {
            new PackageInstallCommand(),
            new PackageRemoveCommand()
    };

    public PackageCommand() {
        addSubCommands(SUB_COMMANDS);
    }

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        sender.sendMessage(String.format("Running the main package command with args %s", Arrays.toString(args)));
        return true;
    }
}
