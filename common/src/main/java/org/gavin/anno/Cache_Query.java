package org.gavin.anno;

import org.gavin.eunm.KEY_ENUM;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Cache_Query {
    String key() default "";
    KEY_ENUM keyType() default KEY_ENUM.AUTO;
    int secondes() default 0;
}
