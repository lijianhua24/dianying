package com.bw.movie.fragment.xqfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.YingPingAdapter;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.YingPingPresenter;

import java.util.List;

import butterknife.BindView;


public class YpFragment extends BaseFragment<YingPingPresenter> implements HomeConteract.PingLunContreact.IView {

    public static final String TAG="YpFragment";
    @BindView(R.id.yingping_recy)
    RecyclerView yingpingRecy;

    @Override
    protected YingPingPresenter providePresenter() {
        return new YingPingPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SharedPreferences name =getActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        String movieId = name.getString("movieId", "");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String sessionId = sharedPreferences.getString("sessionId", "");
        String userId = sharedPreferences.getString("userId", "");
        if (sessionId != null && userId != null && movieId != null) {
            mPresenter.getPingLunPresenter( movieId, 1, "10");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        yingpingRecy.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_yp;
    }

    @Override
    public void onPingLunSuccess(YingPingBean data) {
        List<YingPingBean.ResultBean> result = data.getResult();
        Log.d(TAG, "onPingLunSuccess: "+data.getMessage());
       if (result!=null){
           YingPingAdapter yingPingAdapter = new YingPingAdapter(getActivity(), result);
           yingpingRecy.setAdapter(yingPingAdapter);
       }
    }

    @Override
    public void onPingLunFailure(Throwable e) {

    }
}
