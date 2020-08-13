package org.nova.packagemanager;

import org.nova.framework.NovaCommand;
import org.nova.framework.NovaPlugin;
import org.nova.framework.PluginManifest;
import org.nova.packagemanager.commands.PackageCommand;

@PluginManifest(
        name = "Nova",
        authors = "Typically",
        prefix = "&bNova"
)
public class PackageManagerPlugin extends NovaPlugin {

    private static final NovaCommand[] COMMANDS = {
            new PackageCommand()
    };

    @Override
    public void onLoad() {

    }

    @Override
    public void onEnable() {
        getCommandHandler().add(COMMANDS);
    }

    @Override
    public void onDisable() {

    }
}
