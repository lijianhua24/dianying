package com.bw.movie.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.bean.TimeBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.fragment.TimeFragment;
import com.bw.movie.presenter.TimePresenter;
import com.google.android.material.tabs.TabLayout;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PaiQiActivity extends BaseActivity<TimePresenter> implements HomeConteract.TimeContract.IView {


    @BindView(R.id.paiqi_tab)
    TabLayout paiqiTab;
    @BindView(R.id.paiqi_pager)
    ViewPager paiqiPager;
    private List<String> result;

    @Override
    protected TimePresenter providePresenter() {
        return new TimePresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        mPresenter.getTimeS();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_pai_qi;
    }

    @Override
    public void onTimeSuccess(TimeBean data) {
        result = data.getResult();
        paiqiPager.setAdapter(new MyAdapter(getSupportFragmentManager()));
        paiqiTab.setupWithViewPager(paiqiPager);
    }

    @Override
    public void onTimeFailure(Throwable e) {

    }
    public class MyAdapter extends FragmentPagerAdapter {
        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            TimeFragment timeFragment = new TimeFragment();
            return timeFragment;
        }

        @Override
        public int getCount() {
            return result.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return result.get(position);
        }
    }

}
