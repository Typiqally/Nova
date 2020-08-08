package org.nova.framework;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class NovaPlugin extends JavaPlugin {

    private final List<NovaCommand> commands = new ArrayList<>();

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public void addCommand(NovaCommand command) {
        try {
            final CommandManifest manifest = Objects.requireNonNull(command.getManifest());
            final PluginCommand c = Objects.requireNonNull(getCommand(manifest.name()));
            c.setExecutor(new NovaCommandHandler());
        } catch (NullPointerException e) {
            Bukkit.getLogger().severe(String.format("%s does not contain a manifest or can't be found in the plugin.yml", command.getClass().getSimpleName()));
        }
    }

    public List<NovaCommand> getCommands() {
        return commands;
    }

    public PluginManifest getManifest() {
        return getClass().getAnnotation(PluginManifest.class);
    }
}
