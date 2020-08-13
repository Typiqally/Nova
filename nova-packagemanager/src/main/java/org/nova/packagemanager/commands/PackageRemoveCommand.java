package org.nova.packagemanager.commands;

import org.bukkit.command.CommandSender;
import org.nova.framework.CommandManifest;
import org.nova.framework.CommandType;
import org.nova.framework.NovaCommand;

import java.util.Arrays;

@CommandManifest(
        name = "remove",
        description = "Oh woopsie, another dEsCrIpTiOn",
        permissions = "nova.packagemanager.remove",
        type = CommandType.BOTH
)
public class PackageRemoveCommand extends NovaCommand {

    @Override
    public boolean onCommand(CommandSender sender, String[] args) {
        sender.sendMessage(String.format("Running the remove package command with args %s", Arrays.toString(args)));
        return true;
    }
}
