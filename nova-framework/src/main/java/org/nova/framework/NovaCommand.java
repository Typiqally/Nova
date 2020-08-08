package org.nova.framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class NovaCommand {

    //TODO: Potential optimization (only declare ArrayList when first command is added)
    private final List<NovaCommand> subCommands = new ArrayList<>();

    public void addSubCommands(NovaCommand... commands) {
        subCommands.addAll(Arrays.asList(commands));
    }

    public List<NovaCommand> getSubCommands() {
        return subCommands;
    }

    public CommandManifest getManifest() {
        return getClass().getAnnotation(CommandManifest.class);
    }
}
