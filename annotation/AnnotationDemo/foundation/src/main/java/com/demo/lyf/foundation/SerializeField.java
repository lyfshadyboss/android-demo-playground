package com.demo.lyf.foundation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER})
public @interface SerializeField {

    SerializeType type() default SerializeType.Default;

    int length() default 0;

    int index() default 0;

    boolean require() default false;

    String serverType() default "String";

    String format() default "";
}
