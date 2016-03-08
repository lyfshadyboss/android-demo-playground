package com.example.yifengliu.dagger2demo.service;


import com.example.yifengliu.dagger2demo.view.adapter.ListAdapter;

import java.util.ArrayList;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;

/**
 * GitHub服务
 * <p>
 * Created by yifengliu on 16/3/8.
 */
public interface GitHubService {
    String ENDPOINT = "https://api.github.com";

    // 获取库, 获取的是数组
    @GET("/users/{user}/repos")
    Observable<ArrayList<ListAdapter.Repo>> getRepoData(@Path("user") String user);
}
