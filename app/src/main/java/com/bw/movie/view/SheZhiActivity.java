package com.bw.movie.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.Base.BasePresenter;
import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.utils.ActivityCollectorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SheZhiActivity extends BaseActivity {

    @BindView(R.id.title_fanhui)
    ImageView titleFanhui;
    @BindView(R.id.title_biaoti)
    TextView titleBiaoti;
    @BindView(R.id.setup_clear)
    RelativeLayout setupClear;
    @BindView(R.id.gengxin)
    ImageView gengxin;
    @BindView(R.id.setup_rela)
    RelativeLayout setupRela;
    @BindView(R.id.setup_close)
    TextView setupClose;
    @BindView(R.id.xiugai)
    ImageView xiugai;
    @BindView(R.id.tuichu_but)
    TextView tuichuBut;

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_she_zhi;
    }

    @OnClick({R.id.title_fanhui, R.id.gengxin, R.id.xiugai, R.id.tuichu_but})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_fanhui:
                finish();
                break;
            case R.id.gengxin:
                startActivity(new Intent(this,GengXinActivity.class));
                break;
            case R.id.xiugai:
                startActivity(new Intent(this,XiuGaiActivity.class));
                break;
            case R.id.tuichu_but:
                App.sharedPreferences.edit().clear().commit();
                ActivityCollectorUtil.finishAllActivity();
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

    }
}
