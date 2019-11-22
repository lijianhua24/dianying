package com.bw.movie.fragment.yingyuanxiangqing;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.webkit.WebView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.bean.YingYuanXQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.YingYuanXQSPresenter;

import butterknife.BindView;


public class YYXQFragment extends BaseFragment<YingYuanXQSPresenter> implements HomeConteract.YingYuanXqContreact.IView {
    public static final String TAG = "YYXQFragment";
    @BindView(R.id.yyxq_web)
    WebView yyxqWeb;

    @Override
    protected YingYuanXQSPresenter providePresenter() {
        return new YingYuanXQSPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String yyid = sharedPreferences.getString("yyid", "");
        String sessionId = getActivity().getIntent().getStringExtra("sessionId");
        String userId = getActivity().getIntent().getStringExtra("userId");
        if (yyid != null) {
            mPresenter.getYingYuanXqPresenter(userId, sessionId, yyid);
        }
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_yyxq;
    }

    @Override
    public void onYingYuanXqSuccess(YingYuanXQBean data) {
        Log.d(TAG, "onYingYuanXqSuccess: " + data.getResult());
        String vehicleRoute = data.getResult().getVehicleRoute();
        yyxqWeb.loadDataWithBaseURL(null,vehicleRoute,"text/html","utf-8",null);
    }

    @Override
    public void onYingYuanXqFailure(Throwable e) {

    }
}
