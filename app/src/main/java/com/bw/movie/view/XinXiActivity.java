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
import com.bw.movie.adapter.XinXiAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.XinXiBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.XinXiPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XinXiActivity extends BaseActivity<XinXiPresenter> implements HomeConteract.XinXiContreact.IView {


    @BindView(R.id.xinxi_recy)
    RecyclerView xinxiRecy;
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
    protected XinXiPresenter providePresenter() {
        return new XinXiPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);
        if (userId!=null && sessionId!=null){
            mPresenter.getXinXiPresenter(userId, sessionId, "1", "200");
        }else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        xinxiRecy.setLayoutManager(new LinearLayoutManager(this));
        titleBiaoti.setText("系统信息");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_xin_xi;
    }

    @Override
    public void onXinXiSuccess(XinXiBean data) {
        List<XinXiBean.ResultBean> result = data.getResult();
        if (result!=null){
            xinxiRecy.setAdapter(new XinXiAdapter(this, result));
        }else {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.zanwuguanzhu);
            meiyouXinxi.setText("暂无信息");
        }

    }

    @Override
    public void onXinXiFailure(Throwable e) {

    }


    @OnClick(R.id.title_fanhui)
    public void onViewClicked() {
        finish();
    }


}
