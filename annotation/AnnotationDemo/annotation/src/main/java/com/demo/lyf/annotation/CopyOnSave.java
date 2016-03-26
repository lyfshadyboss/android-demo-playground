package com.demo.lyf.annotation;

/**
 * Created by yifengliu on 16/3/26.
 */
public @interface CopyOnSave {
    Class target();

    String field();
}
