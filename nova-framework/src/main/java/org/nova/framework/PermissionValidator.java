package org.nova.framework;

import org.bukkit.permissions.Permissible;

import java.util.Arrays;

public class PermissionValidator {

    public static boolean validate(Permissible permissible, String[] permissions) {
        if (permissions.length == 0) {
            return true;
        }

        return Arrays.stream(permissions).allMatch(permissible::hasPermission);
    }

}
