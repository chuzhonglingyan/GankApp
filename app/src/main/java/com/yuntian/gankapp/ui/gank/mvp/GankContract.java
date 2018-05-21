package com.yuntian.gankapp.ui.gank.mvp;


import com.yuntian.basecomponent.mvp.BaseModel;
import com.yuntian.basecomponent.mvp.BasePresenter;
import com.yuntian.basecomponent.mvp.BaseView;
import com.yuntian.gankapp.entity.GankInfo;

import java.util.List;

import io.reactivex.Observable;

/**
 * description 合约类 .
 * Created by ChuYingYan on 2018/4/29.
 */
public interface GankContract {

    interface View extends BaseView {

        void getWelfarePhotos(List<GankInfo> result);
    }

    abstract class Presenter extends BasePresenter<Model, View> {

        public abstract void getWelfarePhotos(String datatypeStr,int startPage);
    }

    interface Model extends BaseModel {
        Observable<List<GankInfo>>  getWelfarePhotos(String datatypeStr, int startPage);
    }
}
