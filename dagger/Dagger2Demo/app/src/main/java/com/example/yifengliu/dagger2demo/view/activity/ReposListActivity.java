package com.example.yifengliu.dagger2demo.view.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.yifengliu.dagger2demo.MainApplication;
import com.example.yifengliu.dagger2demo.R;
import com.example.yifengliu.dagger2demo.service.GitHubService;
import com.example.yifengliu.dagger2demo.view.adapter.ListAdapter;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 代码库列表
 * <p>
 * Created by yifengliu on 16/3/8.
 */
public class ReposListActivity extends Activity {
    @Bind(R.id.repos_rv_list)
    RecyclerView mRvList;

    @Inject
    GitHubService mGitHubService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos_list);
        ButterKnife.bind(this);

        MainApplication.component().inject(this);

        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        mRvList.setLayoutManager(manager);

        ListAdapter adapter = new ListAdapter();
        mRvList.setAdapter(adapter);
        loadData(adapter);
    }

    // 加载数据
    private void loadData(ListAdapter adapter) {
        mGitHubService.getRepoData("lyfshadyboss")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(adapter::setRepos);
    }
}
