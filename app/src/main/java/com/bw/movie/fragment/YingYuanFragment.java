package com.bw.movie.fragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.Base.BasePresenter;
import com.bw.movie.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;


public class YingYuanFragment extends BaseFragment {

    @BindView(R.id.yuan_tab)
    TabLayout yuanTab;
    @BindView(R.id.yuan_pager)
    ViewPager yuanPager;
    private ArrayList<Fragment> fragments;
    private ArrayList<String> name;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
        yuanPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return name.get(position);
            }
        });
        yuanTab.setupWithViewPager(yuanPager);
    }

    @Override
    protected void initView() {
        fragments = new ArrayList<>();
        fragments.add(new TjFragment());
        fragments.add(new FjFragment());
        fragments.add(new DqFragment());
        name = new ArrayList<>();
        name.add("推荐影院");
        name.add("附近影院");
        name.add("海淀区");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_cinema;
    }
}
