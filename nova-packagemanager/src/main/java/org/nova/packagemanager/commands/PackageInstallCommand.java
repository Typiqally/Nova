package org.nova.packagemanager.commands;

import org.bukkit.command.CommandSender;
import org.nova.framework.CommandManifest;
import org.nova.framework.CommandType;
import org.nova.framework.NovaCommand;

import java.util.Arrays;

@CommandManifest(
        name = "install",
        description = "Had to create a description :)",
        permissions = "nova.packagemanager.install",
        type = CommandType.BOTH
)
public class PackageInstallCommand extends NovaCommand {

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        sender.sendMessage(String.format("Running the install package command with args %s", Arrays.toString(args)));
        return true;
    }
}
