package com.bw.movie.fragment;

import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.MyAdapter;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.ChaBean;
import com.bw.movie.bean.JjBean;
import com.bw.movie.bean.ReBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.DiYingPresenter;
import com.bw.movie.view.SouActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class DianYingFragment extends BaseFragment<DiYingPresenter> implements HomeConteract.Dianying.IView {

    public static final String TAG = "DianYingFragment";
    @BindView(R.id.dianying_recy)
    RecyclerView dianyingRecy;
    @BindView(R.id.main_sou)
    ImageView mainSou;
    private List<ReBean.ResultBean> relist;
    private List<ChaBean.ResultBean> cha;
    private List<JjBean.ResultBean> jj;
    private List<BannerBean.ResultBean> banner;

    @Override
    protected DiYingPresenter providePresenter() {
        return new DiYingPresenter();
    }

    @Override
    protected void initData() {

        dianyingRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initView() {
        String sessionId = getActivity().getIntent().getStringExtra("sessionId");
        String userId = getActivity().getIntent().getStringExtra("userId");
        mPresenter.getChaPresenter("1", "5");
        mPresenter.getJjPresenter(userId, sessionId, "1", "5");
        mPresenter.getRePresenter("1", "5");
        mPresenter.getBannerPresenter();
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void onReSuccess(ReBean data) {
        Log.d(TAG, "ReBean: " + data.getMessage());
        relist = data.getResult();
        MyAdapter myAdapter = new MyAdapter(getActivity(), relist, cha, jj, banner);
        dianyingRecy.setAdapter(myAdapter);

    }

    @Override
    public void onReFailure(Throwable e) {

    }

    @Override
    public void onChaSuccess(ChaBean data) {
        Log.d(TAG, "ChaBean: " + data.getMessage());
        cha = data.getResult();
        MyAdapter myAdapter = new MyAdapter(getActivity(), relist, cha, jj, banner);
        dianyingRecy.setAdapter(myAdapter);
    }

    @Override
    public void onChaFailure(Throwable e) {

    }

    @Override
    public void onJjuccess(JjBean data) {
        Log.d(TAG, "JjBean: " + data.getMessage());
        jj = data.getResult();
        MyAdapter myAdapter = new MyAdapter(getActivity(), relist, cha, jj, banner);
        dianyingRecy.setAdapter(myAdapter);
    }

    @Override
    public void onJjFailure(Throwable e) {

    }

    @Override
    public void onBannerSuccess(BannerBean data) {
        Log.d(TAG, "BannerBean: " + data.getMessage());
        banner = data.getResult();
        MyAdapter myAdapter = new MyAdapter(getActivity(), relist, cha, jj, banner);
        dianyingRecy.setAdapter(myAdapter);


    }

    @Override
    public void onBannerFailure(Throwable e) {

    }

    @OnClick(R.id.main_sou)
    public void onViewClicked() {
        startActivity(new Intent(getActivity(), SouActivity.class));
    }
}
