package com.bw.movie.fragment;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.FjyyAdapter;
import com.bw.movie.bean.FjYyBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.FjyyPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;


public class FjFragment extends BaseFragment<FjyyPresenter> implements HomeConteract.FjyyContreact.IView {
    public static final String TAG = "FjFragment";
    @BindView(R.id.fjyy_xrecy)
    XRecyclerView fjyyXrecy;

    @Override
    protected FjyyPresenter providePresenter() {
        return new FjyyPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String sessionId = getActivity().getIntent().getStringExtra("sessionId");
        String userId = getActivity().getIntent().getStringExtra("userId");
        mPresenter.getFjyyPresenter(userId, sessionId, "116.30551391385724", "40.04571807462411", 1, "10");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_fj;
    }

    @Override
    public void onFjyySuccess(FjYyBean data) {

        Log.d(TAG, "onFjyySuccess: " + data.getMessage());
        fjyyXrecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<FjYyBean.ResultBean> result = data.getResult();
        fjyyXrecy.setAdapter(new FjyyAdapter(getActivity(),result));
    }

    @Override
    public void onFjyyFailure(Throwable e) {
        Log.d(TAG, "onFjyyFailure: " + e);
    }
}
