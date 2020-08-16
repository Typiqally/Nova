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

    public boolean onSubCommand(CommandSender sender, String[] args) {
        if (args.length == 0) {
            return false;
        }

        final NovaCommand subCommand = getSubCommand(args[0]);
        if (subCommand == null) {
            return false;
        }

        final String[] modifiedArgs = Arrays.copyOfRange(args, 1, args.length);
        return subCommand.onCommand(sender, modifiedArgs);
    }

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

    public NovaCommand getSubCommand(String name) {
        return subCommands.stream()
                .filter(c -> c.getManifest().name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<NovaCommand> getSubCommands() {
        return subCommands;
    }

    public CommandManifest getManifest() {
        return getClass().getAnnotation(CommandManifest.class);
    }
}
