package com.bw.movie.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.bw.movie.R;
import com.bw.movie.fragment.gengduo.GengDuoFragment;
import com.bw.movie.utils.ActivityCollectorUtil;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class GengDuoActivity extends AppCompatActivity {
    private TabLayout gengduo_tab;
    private ViewPager gengduo_pager;
    private ArrayList<String> list = new ArrayList<>();
    private String [] attas = new String[] {"正在热映","即将上映","热门电影"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geng_duo);
        gengduo_pager = findViewById(R.id.gengduo_page);
        gengduo_tab = findViewById(R.id.gengduo_tab);
        for (int i = 0; i < attas.length; i++) {
            list.add(attas[i]);
        }
        gengduo_pager.setAdapter(new GengDuoAdapter(getSupportFragmentManager()));
        gengduo_tab.setupWithViewPager(gengduo_pager);
        ActivityCollectorUtil.addActivity(this);
    }
    class GengDuoAdapter extends FragmentPagerAdapter{
        public GengDuoAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            GengDuoFragment gengDuoFragment = new GengDuoFragment();
            Bundle bundle = new Bundle();
            bundle.putString("urls",attas[position]);
            gengDuoFragment.setArguments(bundle);
            return gengDuoFragment;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return list.get(position);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.removeActivity(this);
    }
}
