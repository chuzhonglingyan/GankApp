package com.yuntian.basecomponent.databinding;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;

import com.yuntian.baselibs.base.BaseActivity;

/**
 * description  .
 * Created by ChuYingYan on 2018/4/29.
 */
public abstract class BaseDataBindingActivity<B extends ViewDataBinding> extends BaseActivity{


    protected B mViewBinding;

    @Override
    protected void init() {
        mViewBinding = DataBindingUtil.setContentView(this, getLayoutId());
    }


}
