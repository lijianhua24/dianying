package com.bw.movie.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.adapter.ChaYuYueAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.ChaYuYueBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.ChaYuYuePresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YuYueActivity extends BaseActivity<ChaYuYuePresenter> implements HomeConteract.ChaYuYueContreact.IView {


    @BindView(R.id.yuyue_recy)
    RecyclerView yuyueRecy;
    @BindView(R.id.title_fanhui)
    ImageView titleFanhui;
    @BindView(R.id.title_biaoti)
    TextView titleBiaoti;
    @BindView(R.id.meiyou_tu)
    ImageView meiyouTu;
    @BindView(R.id.meiyou_xinxi)
    TextView meiyouXinxi;
    @BindView(R.id.zong)
    LinearLayout zong;

    @Override
    protected ChaYuYuePresenter providePresenter() {
        return new ChaYuYuePresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);
       if (userId!=null && sessionId!=null){
           mPresenter.getChaYuYuePresenter(userId, sessionId);
       }else {
           Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
           startActivity(new Intent(this,MainActivity.class));
           finish();
       }
        yuyueRecy.setLayoutManager(new LinearLayoutManager(this));
        titleBiaoti.setText("我的预约");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_yu_yue;
    }

    @Override
    public void onChaYuYueSuccess(ChaYuYueBean data) {
        List<ChaYuYueBean.ResultBean> result = data.getResult();
        if (result!=null){
            yuyueRecy.setAdapter(new ChaYuYueAdapter(this, result));
        }else {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.zanwuguanzhu);
            meiyouXinxi.setText("暂无预约");
        }
    }

    @Override
    public void onChaYuYueFailure(Throwable e) {

    }


    @OnClick(R.id.title_fanhui)
    public void onViewClicked() {
        finish();
    }


}
