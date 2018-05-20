package com.yuntian.basecomponent.mvp;

import android.databinding.ViewDataBinding;

import com.yuntian.basecomponent.databinding.BaseDataBindingActivity;
import com.yuntian.basecomponent.dragger.DraggerApp;
import com.yuntian.basecomponent.dragger.AppComponent;

import javax.inject.Inject;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/29.
 */
public abstract class BaseMvpActivity<P extends BasePresenter, B extends ViewDataBinding> extends BaseDataBindingActivity<B> implements BaseView {


    @Inject
    protected P mPresenter;


    @Override
    protected void init() {
        super.init();
        setupActivityComponent(getApplicationComponent());

        if (mPresenter != null) {
            mPresenter.onCreate();
        }
    }

    protected AppComponent getApplicationComponent() {
        return ((DraggerApp) getApplication()).component();
    }

    /**
     * 依赖注入的入口
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
    }


    @Override
    public void transmit() {

    }
}
