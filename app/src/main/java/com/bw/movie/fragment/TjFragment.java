package com.bw.movie.fragment;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.TjyyAdapter;
import com.bw.movie.bean.TjyyBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.Tjyypresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


public class TjFragment extends BaseFragment<Tjyypresenter> implements HomeConteract.TjyyContreact.IView {
    private int anInt = 1;
    public static final String TAG="TjFragment";
    @BindView(R.id.tjyy_xrecy)
    XRecyclerView tjyyXrecy;
    private String sessionId;
    private String userId;
    private ArrayList<TjyyBean.ResultBean> resultBeans;

    @Override
    protected Tjyypresenter providePresenter() {
        return new Tjyypresenter();
    }

    @Override
    protected void initData() {
        tjyyXrecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        tjyyXrecy.setPullRefreshEnabled(true);
        tjyyXrecy.setLoadingMoreEnabled(true);
        tjyyXrecy.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                anInt = 1;
                mPresenter.getTjyyPresenter(userId, sessionId,anInt,"5");
                tjyyXrecy.refreshComplete();
            }

            @Override
            public void onLoadMore() {
                anInt++;
                mPresenter.getTjyyPresenter(userId, sessionId,anInt,"5");
                tjyyXrecy.loadMoreComplete();
            }
        });

    }

    @Override
    protected void initView() {
        sessionId = getActivity().getIntent().getStringExtra("sessionId");
        userId = getActivity().getIntent().getStringExtra("userId");
        mPresenter.getTjyyPresenter(userId, sessionId,1,"5");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_tj;
    }

    @Override
    public void onTjyySuccess(TjyyBean data) {
        Log.d(TAG, "onTjyySuccess: "+data.getMessage());
        List<TjyyBean.ResultBean> result = data.getResult();
        resultBeans = new ArrayList<>();
        resultBeans.addAll(result);
        tjyyXrecy.setAdapter(new TjyyAdapter(getActivity(),resultBeans));
    }

    @Override
    public void onTjyyFailure(Throwable e) {
        Log.d(TAG, "onTjyyFailure: "+e);
    }
}
