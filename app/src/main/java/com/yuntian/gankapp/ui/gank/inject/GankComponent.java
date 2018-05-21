package com.yuntian.gankapp.ui.gank.inject;

import com.yuntian.basecomponent.dragger.AppComponent;
import com.yuntian.basecomponent.dragger.scope.ActivityScope;
import com.yuntian.gankapp.ui.gank.ArticleListFragment;
import com.yuntian.gankapp.ui.gank.GankMainFragment;
import com.yuntian.gankapp.ui.gank.RestListFragment;
import com.yuntian.gankapp.ui.gank.WelfareListFragment;

import dagger.Component;

/**
 * description .
 * Created by ChuYingYan on 2018/5/1.
 */
@ActivityScope
@Component(modules = GankModule.class, dependencies = AppComponent.class)
public interface GankComponent {


    void inject(GankMainFragment fragment);

    void inject(WelfareListFragment fragment);

    void inject(RestListFragment fragment);

    void inject(ArticleListFragment fragment);
}
