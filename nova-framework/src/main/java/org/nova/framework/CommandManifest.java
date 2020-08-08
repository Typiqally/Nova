package org.nova.framework;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandManifest {

    String name();

    String description();

    String usage() default "/<command>";

    CommandType type();

    String[] aliases() default {};

    String[] permissions() default {};

}
