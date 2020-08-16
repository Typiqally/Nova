package org.nova.framework;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class NovaCommandHandler implements CommandExecutor, TabCompleter {

    public static String NO_PERMISSION_RESPONSE = "&cYou don't have permission to use this command.";

    private final JavaPlugin plugin;
    private final List<NovaCommand> commands;

    public NovaCommandHandler(JavaPlugin plugin, List<NovaCommand> commands) {
        this.plugin = plugin;
        this.commands = commands;
    }

    public NovaCommandHandler(JavaPlugin plugin) {
        this(plugin, new ArrayList<>());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command bukkitCommand, String label, String[] args) {
        final NovaCommand command = getCommand(bukkitCommand.getName());
        if (command == null) {
            return false;
        }

        if (!PermissionValidator.validate(sender, command.getManifest().permissions())) {
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', NO_PERMISSION_RESPONSE));
            return true;
        }

        if (onSubCommand(sender, command, args)) {
            return true;
        }

        return command.onCommand(sender, args);
    }

    private boolean onSubCommand(CommandSender sender, NovaCommand command, String[] args) {
        if (command == null || args.length == 0) {
            return false;
        }

        int i;
        NovaCommand subCommand = command;
        for (i = 0; i < args.length; i++) {
            final NovaCommand c = subCommand.getSubCommand(args[i]);
            if (c != null) {
                subCommand = c;
            } else {
                break;
            }
        }

        final String[] modifiedArgs = Arrays.copyOfRange(args, i, args.length);
        return subCommand.onCommand(sender, modifiedArgs);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        final NovaCommand novaCommand = getCommand(command.getName());
        if (novaCommand == null) {
            return null;
        }

        return novaCommand.onTabComplete(sender, args);
    }

    public void add(NovaCommand command) {
        if (commands.contains(command)) {
            return;
        }

        try {
            final CommandManifest manifest = Objects.requireNonNull(command.getManifest());
            final PluginCommand pluginCommand = Objects.requireNonNull(plugin.getCommand(manifest.name()));

            pluginCommand.setExecutor(this);
            pluginCommand.setTabCompleter(this);

            commands.add(command);
        } catch (NullPointerException exception) {
            Bukkit.getLogger().warning("Manifest does not exist or command is not registered in plugin.yml");
        }
    }

    public void add(NovaCommand[] commands) {
        Arrays.stream(commands).forEach(this::add);
    }

    public NovaCommand getCommand(String name) {
        return commands.stream()
                .filter(command -> command.getManifest().name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }

    public List<NovaCommand> getCommands() {
        return commands;
    }
}
