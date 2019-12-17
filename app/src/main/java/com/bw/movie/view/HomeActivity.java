package com.bw.movie.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;

import com.bw.movie.R;
import com.bw.movie.bean.XQBean;
import com.bw.movie.fragment.DianYingFragment;
import com.bw.movie.fragment.WoDeFragment;
import com.bw.movie.fragment.YingYuanFragment;
import com.bw.movie.myview.CustomScrollViewPager;
import com.bw.movie.utils.ActivityCollectorUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.image_movei_dj)
    ImageView imageMoveiDj;
    @BindView(R.id.lin_movei)
    LinearLayout linMovei;
    @BindView(R.id.image_cinem_dj)
    ImageView imageCinemDj;
    @BindView(R.id.lin_cinem)
    LinearLayout linCinem;
    @BindView(R.id.image_myy_dj)
    ImageView imageMyyDj;
    @BindView(R.id.lin_myy)
    LinearLayout linMyy;
    List<Fragment> list = new ArrayList<>();
    List<LinearLayout> llist = new ArrayList<>();
    @BindView(R.id.lay_one)
    LinearLayout layOne;
    @BindView(R.id.lay_two)
    LinearLayout layTwo;
    @BindView(R.id.lay_swe)
    LinearLayout laySwe;
    @BindView(R.id.view_pager)
    CustomScrollViewPager viewPager;
    private static final String TAG = "MainActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z);
        ButterKnife.bind(this);
        ActivityCollectorUtil.addActivity(this);
        list.add(new DianYingFragment());
        list.add(new YingYuanFragment());
        list.add(new WoDeFragment());
        llist.add(linMovei);
        llist.add(linCinem);
        llist.add(linMyy);

        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getCount() {
                return list.size();
            }
        });
        viewPager.clearOnPageChangeListeners();
        imageMoveiDj.setOnClickListener(this);
        imageCinemDj.setOnClickListener(this);
        imageMyyDj.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_movei_dj:
                imageMoveiDj.setVisibility(View.GONE);
                imageCinemDj.setVisibility(View.VISIBLE);
                imageMyyDj.setVisibility(View.VISIBLE);
                linMovei.setVisibility(View.VISIBLE);
                linCinem.setVisibility(View.GONE);
                linMyy.setVisibility(View.GONE);
                viewPager.setCurrentItem(0);
                break;
            case R.id.image_cinem_dj:
                imageMoveiDj.setVisibility(View.VISIBLE);
                imageCinemDj.setVisibility(View.GONE);
                imageMyyDj.setVisibility(View.VISIBLE);
                linMovei.setVisibility(View.GONE);
                linCinem.setVisibility(View.VISIBLE);
                linMyy.setVisibility(View.GONE);
                viewPager.setCurrentItem(1);
                break;
            case R.id.image_myy_dj:
                imageMoveiDj.setVisibility(View.VISIBLE);
                imageCinemDj.setVisibility(View.VISIBLE);
                imageMyyDj.setVisibility(View.GONE);
                linMovei.setVisibility(View.GONE);
                linCinem.setVisibility(View.GONE);
                linMyy.setVisibility(View.VISIBLE);
                viewPager.setCurrentItem(2);
                break;
        }
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();

        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (secondTime - firstTime < 2000) {
                System.exit(0);
            } else {
                Toast.makeText(getApplicationContext(), "再按一次返回键退出", Toast.LENGTH_SHORT).show();
                firstTime = System.currentTimeMillis();
            }

            return true;
        }

        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.addActivity(this);
    }
}
