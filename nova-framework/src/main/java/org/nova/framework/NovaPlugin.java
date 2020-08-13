package org.nova.framework;

import org.bukkit.plugin.java.JavaPlugin;

public abstract class NovaPlugin extends JavaPlugin {

    private final NovaCommandHandler commandHandler = new NovaCommandHandler(this);

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {

    }

    @Override
    public void onDisable() {

    }

    public NovaCommandHandler getCommandHandler() {
        return commandHandler;
    }

    public PluginManifest getManifest() {
        return getClass().getAnnotation(PluginManifest.class);
    }
}
