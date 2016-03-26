package com.demo.lyf.annotationdemo;

import com.demo.lyf.annotation.CopyOnSave;

/**
 * Created by yifengliu on 16/3/25.
 */
public class TestCacheBeanASpec {
    @CopyOnSave(target = TestCacheBeanBSpec.class, field = "token")
    String token = "";
}
