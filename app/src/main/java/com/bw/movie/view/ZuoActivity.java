package com.bw.movie.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.adapter.YingTingAdapter;
import com.bw.movie.adapter.ZuoAdapter;
import com.bw.movie.bean.XQBean;
import com.bw.movie.bean.YingTingBean;
import com.bw.movie.bean.ZuoBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.ZuoPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class ZuoActivity extends BaseActivity<ZuoPresenter> implements HomeConteract.ZuoContreact.IView {

    public static final String TAG = "ZuoActivity";
    @BindView(R.id.zuo_name)
    TextView zuoName;
    @BindView(R.id.zuo_pin)
    JCVideoPlayer zuoPin;
    @BindView(R.id.zuo_xuan)
    RecyclerView zuoXuan;
    @BindView(R.id.zuo_ying)
    TextView zuoYing;
    @BindView(R.id.zuo_recy_ying)
    RecyclerView zuoRecyYing;
    @BindView(R.id.zuo_jia)
    TextView zuoJia;

    @Override
    protected ZuoPresenter providePresenter() {
        return new ZuoPresenter();
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        zuoRecyYing.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected void initView() {

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String sessionId = sharedPreferences.getString("sessionId", "");
        String userId = sharedPreferences.getString("userId", "");
           String movieId = sharedPreferences.getString("movieId", "");
           String cinemaId = getIntent().getStringExtra("yyid");
           mPresenter.getXQPresenter(userId,sessionId,movieId);
           Log.d(TAG, "movieId: "+movieId);
           Log.d(TAG, "cinemaId: "+cinemaId);
           mPresenter.getYingTingPresenter(movieId, cinemaId);

        SharedPreferences sharedPreferences1 = getSharedPreferences("yyid", Context.MODE_PRIVATE);
        String id = sharedPreferences1.getString("id", "");
        String dyid = getIntent().getStringExtra("dyid");
        String usedd = getIntent().getStringExtra("usedd");
        Log.d(TAG, "initView: "+id+"============"+dyid);
            if (dyid!=null && id!=null){
                mPresenter.getYingTingPresenter(dyid,id);
                mPresenter.getXQPresenter(userId,sessionId,dyid);
        }

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_zuo;
    }

    @Override
    public void onXQSuccess(XQBean data) {
        Log.d(TAG, "onXQSuccess: " + data.getMessage());
        XQBean.ResultBean result = data.getResult();
        String videoUrl = result.getShortFilmList().get(0).getVideoUrl();
        zuoPin.setUp(videoUrl,null);
        String name = result.getName();
        if (name!=null){
            zuoName.setText(name);
        }
    }

    @Override
    public void onXQFailure(Throwable e) {

    }

    @Override
    public void onYingTingSuccess(YingTingBean data) {
        Log.d(TAG, "onYingTingSuccess: " + data.getMessage());
        List<YingTingBean.ResultBean> result = data.getResult();
        Log.d(TAG, "onYingTingSuccess: "+result);
        YingTingAdapter yingTingAdapter = new YingTingAdapter(this, result);
        zuoRecyYing.setAdapter(yingTingAdapter);
        yingTingAdapter.getListenter(new YingTingAdapter.onSetChange() {
            @Override
            public void getChange(String name) {
                mPresenter.getZuo(name);
            }
        });

    }

    @Override
    public void onYingTingFailure(Throwable e) {

    }

    @Override
    public void onZuoSuccess(ZuoBean data) {
        Log.d(TAG, "onZuoSuccess: " + data.getMessage());
        List<ZuoBean.ResultBean> result = data.getResult();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);
        zuoXuan.setLayoutManager(gridLayoutManager);
       if (result!=null){
           zuoXuan.setAdapter(new ZuoAdapter(this,result));
       }

    }

    @Override
    public void onZuoFailure(Throwable e) {

    }


}
