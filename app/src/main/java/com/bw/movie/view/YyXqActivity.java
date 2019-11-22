package com.bw.movie.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.bean.FjYyBean;
import com.bw.movie.bean.YingYuanXQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.fragment.yingyuanxiangqing.YYPJFragment;
import com.bw.movie.fragment.yingyuanxiangqing.YYXQFragment;
import com.bw.movie.presenter.YingYuanXQPresenter;
import com.google.android.material.tabs.TabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YyXqActivity extends BaseActivity<YingYuanXQPresenter> implements HomeConteract.YingYuanXqContreact.IView {

    public static final String TAG = "YyXqActivity";
    @BindView(R.id.invalid_name)
    TextView invalidName;
    @BindView(R.id.yyxq_recy)
    TextView yyxqRecy;
    @BindView(R.id.yyxq_tab)
    TabLayout yyxqTab;
    @BindView(R.id.yyxq_page)
    ViewPager yyxqPage;
    @BindView(R.id.yyxq_paiqi)
    Button yyxqPaiqi;
    private ArrayList<Fragment> list;
    private ArrayList<String> name;

    @Override
    protected YingYuanXQPresenter providePresenter() {
        return new YingYuanXQPresenter();
    }


    @Override
    protected void initData() {
        list = new ArrayList<>();
        list.add(new YYXQFragment());
        list.add(new YYPJFragment());
        name = new ArrayList<>();
        name.add("电影详情");
        name.add("影院评价");
        yyxqPage.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
        yyxqTab.setupWithViewPager(yyxqPage);
    }

    @Override
    protected void initView() {

        String sessionId = getIntent().getStringExtra("sessionId");
        String userId = getIntent().getStringExtra("userId");
        SharedPreferences sharedPreferences1 = getSharedPreferences("yyid", Context.MODE_PRIVATE);
        String id = sharedPreferences1.getString("id", "");
        Log.d(TAG, "initView: "+id);
        if (id != null) {
            mPresenter.getYingYuanXqPresenter(userId, sessionId, id);
        }
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_yy_xq;
    }

    @Override
    public void onYingYuanXqSuccess(YingYuanXQBean data) {
        Log.d(TAG, "onYingYuanXqSuccess: " + data.getMessage());
        invalidName.setText(data.getResult().getName());
        Log.d(TAG, "onYingYuanXqSuccess: "+data.getResult().getName());
        invalidName.setText(data.getResult().getName());
        String label = data.getResult().getLabel();
        yyxqRecy.setText(label);

        Log.d(TAG, "onYingYuanXqSuccess: " + data.getResult().getVehicleRoute());

    }

    @Override
    public void onYingYuanXqFailure(Throwable e) {

    }




    @OnClick(R.id.yyxq_paiqi)
    public void onViewClicked() {
        startActivity(new Intent(this,PaiQiActivity.class));
    }


}
