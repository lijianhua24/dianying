package com.bw.movie.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.Base.BasePresenter;
import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.fragment.gpjlFragment.DFFragment;
import com.bw.movie.fragment.gpjlFragment.YFFragment;
import com.bw.movie.utils.ActivityCollectorUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GouPiaoJiLuActivity extends BaseActivity {


    @BindView(R.id.gpjl_tab)
    TabLayout gpjlTab;
    @BindView(R.id.gpjl_page)
    ViewPager gpjlPage;
    @BindView(R.id.title_fanhui)
    ImageView titleFanhui;
    @BindView(R.id.title_biaoti)
    TextView titleBiaoti;
    private ArrayList<Fragment> list;
    private ArrayList<String> name;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {
        list.add(new DFFragment());
        list.add(new YFFragment());
        name.add("代付款");
        name.add("已付款");
        gpjlPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {
                return name.get(position);
            }
        });
        gpjlTab.setupWithViewPager(gpjlPage);
        titleBiaoti.setText("购票记录");
    }

    @Override
    protected void initView() {
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);
        if (userId!=null && sessionId!=null){
        }else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        ActivityCollectorUtil.addActivity(this);
        list = new ArrayList<>();
        name = new ArrayList<>();

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_gou_piao_ji_lu;
    }




    @OnClick(R.id.title_fanhui)
    public void onViewClicked() {
        finish();
    }
}
