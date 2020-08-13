package org.nova.framework;

import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class NovaCommand {

    //TODO: Potential optimization (only declare ArrayList when first command is added)
    private final List<NovaCommand> subCommands = new ArrayList<>();

    public abstract boolean onCommand(CommandSender sender, String[] args);

    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return subCommands.stream()
                .filter(command -> PermissionValidator.validate(sender, command.getManifest().permissions()))
                .map(command -> command.getManifest().name())
                .collect(Collectors.toList());
    }

    public void addSubCommand(NovaCommand command) {
        subCommands.add(command);
    }

    public void addSubCommands(NovaCommand[] commands) {
        Arrays.stream(commands).forEach(this::addSubCommand);
    }

    public List<NovaCommand> getSubCommands() {
        return subCommands;
    }

    public CommandManifest getManifest() {
        return getClass().getAnnotation(CommandManifest.class);
    }
}
