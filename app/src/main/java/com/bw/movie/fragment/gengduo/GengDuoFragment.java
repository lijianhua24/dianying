package com.bw.movie.fragment.gengduo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.GengDuoAdapter;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.ChaBean;
import com.bw.movie.bean.JjBean;
import com.bw.movie.bean.ReBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.GengduoPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;


public class GengDuoFragment extends BaseFragment<GengduoPresenter> implements HomeConteract.Dianying.IView {
    public static final String TAG = "GengDuoFragment";
    @BindView(R.id.gengduo_recy)
    RecyclerView gengduoRecy;
    private List<ReBean.ResultBean> re;
    private List<ChaBean.ResultBean> cha;
    private List<JjBean.ResultBean> jj;

    @Override
    protected GengduoPresenter providePresenter() {
        return new GengduoPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String sessionId = getActivity().getIntent().getStringExtra("sessionId");
        String userId = getActivity().getIntent().getStringExtra("userId");
        Bundle bundle = getArguments();
        String urls = bundle.getString("urls");
        if (urls.equals("正在热映")) {
            mPresenter.getChaPresenter("1", "10");
        } else if (urls.equals("即将上映")) {
            mPresenter.getJjPresenter(sessionId, userId, "1", "10");
        } else if (urls.equals("热门电影")) {
            mPresenter.getRePresenter("1", "10");
        }
        gengduoRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_geng_duo;
    }

    @Override
    public void onReSuccess(ReBean data) {
        re = data.getResult();
        Log.d(TAG, "onReSuccess: " + data.getMessage());
        gengduoRecy.setAdapter(new GengDuoAdapter(getActivity(),re,cha,jj));
    }

    @Override
    public void onReFailure(Throwable e) {

    }

    @Override
    public void onChaSuccess(ChaBean data) {
        String message = data.getMessage();
        cha = data.getResult();
        gengduoRecy.setAdapter(new GengDuoAdapter(getActivity(),re,cha,jj));
        Log.d(TAG, "onChaSuccess: " + data.getMessage());
    }

    @Override
    public void onChaFailure(Throwable e) {

    }

    @Override
    public void onJjuccess(JjBean data) {
        String message = data.getMessage();
        jj = data.getResult();
        gengduoRecy.setAdapter(new GengDuoAdapter(getActivity(),re,cha,jj));
        Log.d(TAG, "onJjuccess: " + message);
    }

    @Override
    public void onJjFailure(Throwable e) {

    }

    @Override
    public void onBannerSuccess(BannerBean data) {

    }

    @Override
    public void onBannerFailure(Throwable e) {

    }

    @Override
    public void onYuYueSuccess(YuYueBean data) {

    }

    @Override
    public void onYuYueFailure(Throwable e) {

    }

}
