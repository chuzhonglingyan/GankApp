package com.yuntian.basecomponent.mvp;

import android.databinding.ViewDataBinding;

import com.yuntian.basecomponent.databinding.BaseLazyBindingFragment;
import com.yuntian.basecomponent.dragger.DraggerApp;
import com.yuntian.basecomponent.dragger.AppComponent;

import javax.inject.Inject;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/29.
 */
public abstract class BaseMvpFrgament<P extends BasePresenter, B extends ViewDataBinding> extends BaseLazyBindingFragment<B> implements BaseView {



    @Inject
    protected P mPresenter;


    @Override
    protected void init() {
        setupActivityComponent(getApplicationComponent());
        if (mPresenter != null) {
            mPresenter.onCreate();
        }
    }


    protected AppComponent getApplicationComponent() {
        return ((DraggerApp) mActivity.getApplication()).component();
    }

    /**
     * 依赖注入的入口
     */
    protected abstract void setupActivityComponent(AppComponent appComponent);


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();
    }

    @Override
    public void transmit() {

    }
}
