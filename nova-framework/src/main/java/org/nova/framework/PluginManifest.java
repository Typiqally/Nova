package org.nova.framework;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface PluginManifest {

    String name();

    double version() default 1.0;

    String[] authors();

    String prefix();

    char altColorChar() default '&';

}
