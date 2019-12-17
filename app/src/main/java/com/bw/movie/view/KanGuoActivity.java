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
import com.bw.movie.adapter.KgAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.KanGuoBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.KanGuoPresenter;
import com.bw.movie.utils.ActivityCollectorUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class KanGuoActivity extends BaseActivity<KanGuoPresenter> implements HomeConteract.KanGuoContreact.IView {


    @BindView(R.id.kg_recy)
    RecyclerView kgRecy;
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
    protected KanGuoPresenter providePresenter() {
        return new KanGuoPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        ActivityCollectorUtil.addActivity(this);
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);
        if (userId!=null && sessionId!=null){
            mPresenter.getKanGuoPresenter(userId, sessionId);
        }else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        kgRecy.setLayoutManager(new LinearLayoutManager(this));
        titleBiaoti.setText("我看过的电影");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_kan_guo;
    }

    @Override
    public void onKanGuoSuccess(KanGuoBean data) {
        List<KanGuoBean.ResultBean> result = data.getResult();
        if (result != null) {
            kgRecy.setAdapter(new KgAdapter(this, result));
        } else {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.zanwuguanzhu);
            meiyouXinxi.setText("暂无信息");
        }
    }

    @Override
    public void onKanGuoFailure(Throwable e) {

    }


    @OnClick(R.id.title_fanhui)
    public void onViewClicked() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
