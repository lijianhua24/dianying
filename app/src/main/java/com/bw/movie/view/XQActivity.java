package com.bw.movie.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.bean.XQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.fragment.xqfragment.JsFragment;
import com.bw.movie.fragment.xqfragment.JzFragment;
import com.bw.movie.fragment.xqfragment.YgFragment;
import com.bw.movie.fragment.xqfragment.YpFragment;
import com.bw.movie.presenter.XQPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XQActivity extends BaseActivity<XQPresenter> implements HomeConteract.XQContreact.IView {
    public static final String TAG = "XQActivity";
    @BindView(R.id.xq_image)
    SimpleDraweeView xqImage;
    @BindView(R.id.xq_pf)
    TextView xqPf;
    @BindView(R.id.xq_pl)
    TextView xqPl;
    @BindView(R.id.xq_name)
    TextView xqName;
    @BindView(R.id.xq_lx)
    TextView xqLx;
    @BindView(R.id.x1_time)
    TextView x1Time;
    @BindView(R.id.xq_shijian)
    TextView xqShijian;
    @BindView(R.id.xq_tab)
    TabLayout xqTab;
    @BindView(R.id.xq_pager)
    ViewPager xqPager;
    @BindView(R.id.xq_xieyingping)
    Button xqXieyingping;
    @BindView(R.id.xq_goupiao)
    Button xqGoupiao;
    private String resultName;
    private String movieId;


    @Override
    protected XQPresenter providePresenter() {
        return new XQPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SharedPreferences name =getSharedPreferences("users", Context.MODE_PRIVATE);
        movieId = name.getString("movieId", "");
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String sessionId = sharedPreferences.getString("sessionId", "");
        String userId = sharedPreferences.getString("userId", "");

        if (userId!=null && sessionId!=null && movieId !=null){
            SharedPreferences sharedPreferences2 = getSharedPreferences("Suser", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit1 = sharedPreferences2.edit();
            edit1.putString("sessionId",sessionId);
            edit1.putString("userId",userId);
            edit1.putString("movieId", movieId);
            edit1.commit();
        }

        Log.d(TAG, "movieId: " + movieId);
        mPresenter.getXQPresenter(userId, sessionId, movieId);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_xq;
    }

    @Override
    public void onXQSuccess(XQBean data) {
        Log.d(TAG, "onXQSuccess: " + data.getMessage());

        if (data != null) {
            XQBean.ResultBean result = data.getResult();
            Uri parse = Uri.parse(result.getImageUrl());
            xqImage.setImageURI(parse);
            xqPf.setText(data.getResult().getScore() + "分");
            resultName = result.getName();
            SharedPreferences sharedPreferences2 = getSharedPreferences("Suser", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences2.edit();
            edit.putString("resultName",resultName);
            edit.commit();
            xqName.setText(resultName);
            xqLx.setText(result.getMovieType());
            Log.d(TAG, "getMovieType: " + result.getMovieType());
            xqShijian.setText(result.getReleaseTime() + "");
            Date date = new Date(result.getReleaseTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            xqShijian.setText(simpleDateFormat.format(date) + "  " + result.getPlaceOrigin() + "上映");
            x1Time.setText(result.getDuration());
            ArrayList<Fragment> list = new ArrayList<>();
            list.add(new JsFragment());
            list.add(new YgFragment());
            list.add(new JzFragment());
            list.add(new YpFragment());
            ArrayList<String> name = new ArrayList<>();
            name.add("介绍");
            name.add("预告");
            name.add("剧照");
            name.add("影评");
            xqPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
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
            xqTab.setupWithViewPager(xqPager);
        }
    }

    @Override
    public void onXQFailure(Throwable e) {

    }




    @OnClick({R.id.xq_xieyingping, R.id.xq_goupiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.xq_xieyingping:

                if (movieId!=null){
                    Intent intent = new Intent();
                    intent.putExtra("ssss",movieId);
                    intent.setClass(this,XYPActivity.class);
                    startActivity(intent);
                }

                break;
            case R.id.xq_goupiao:
                startActivity(new Intent(XQActivity.this,GouPiaoActivity.class));
                break;
        }
    }
}
