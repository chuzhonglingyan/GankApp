package com.yuntian.gankapp.ui.gank;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.yuntian.basecomponent.dragger.AppComponent;
import com.yuntian.basecomponent.util.ToolBarUtil;
import com.yuntian.baselibs.adapter.BaseFPageStateAdapter;
import com.yuntian.baselibs.util.FragmentHelper;
import com.yuntian.gankapp.R;
import com.yuntian.gankapp.databinding.FragmentGankMainBinding;
import com.yuntian.gankapp.storage.cons.AppConstants;
import com.yuntian.gankapp.ui.gank.inject.DaggerGankComponent;
import com.yuntian.gankapp.ui.gank.inject.GankModule;
import com.yuntian.gankapp.ui.gank.mvp.GankContract;
import com.yuntian.gankapp.ui.gank.mvp.GankViewFragment;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.yuntian.gankapp.storage.cons.AppConstants.GANK_ARTICLE;
import static com.yuntian.gankapp.storage.cons.AppConstants.GANK_REST;
import static com.yuntian.gankapp.storage.cons.AppConstants.GANK_WELFARE;

/**
 * description  .
 * Created by ChuYingYan on 2018/5/3.
 */
public class GankMainFragment extends GankViewFragment<FragmentGankMainBinding, GankContract.Presenter> {


    @Inject
    BaseFPageStateAdapter baseFPageStateAdapter;


    public static final String TAG = "GankMainFragment";


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_gank_main;
    }


    @Override
    protected void initView() {
        ToolBarUtil.initToolBar(mActivity, mViewBinding.toolBar, true, "干货");
    }

    @Override
    protected void initData(Bundle savedInstanceState) {

        List<Fragment> fragments = new ArrayList<>();
        Bundle bundlearticle = new Bundle();
        bundlearticle.putString(AppConstants.GANK_DATATYPE, GANK_ARTICLE);

        Bundle bundlearWelfare = new Bundle();
        bundlearWelfare.putString(AppConstants.GANK_DATATYPE, GANK_WELFARE);

        Bundle bundleRest = new Bundle();
        bundleRest.putString(AppConstants.GANK_DATATYPE, GANK_REST);

        fragments.add(FragmentHelper.newInstance(ArticleListFragment.class, bundlearticle));
        fragments.add(FragmentHelper.newInstance(WelfareListFragment.class, bundlearWelfare));
        fragments.add(FragmentHelper.newInstance(RestListFragment.class, bundleRest));

        baseFPageStateAdapter.updateFragments(getChildFragmentManager(), fragments, new String[]{"技术文章", "福利生活", "休息视频"});
        mViewBinding.viewPager.setAdapter(baseFPageStateAdapter);
        mViewBinding.tabLayout.setupWithViewPager(mViewBinding.viewPager);
    }

    @Override
    protected void lazyLoad() {

    }

    @Override
    protected void setupActivityComponent(AppComponent appComponent) {
        DaggerGankComponent
                .builder()
                .appComponent(appComponent)
                .gankModule(new GankModule(this))
                .build()
                .inject(this);  //调用inject，注入就完成了。此时使用@Inject来标记成员变量就可以使用了
    }


}
