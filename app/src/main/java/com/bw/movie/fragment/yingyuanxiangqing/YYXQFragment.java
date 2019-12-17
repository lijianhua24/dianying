package com.bw.movie.fragment.yingyuanxiangqing;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.bean.QXYYGZBean;
import com.bw.movie.bean.YYGZBean;
import com.bw.movie.bean.YingYuanXQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.YingYuanXQSPresenter;

import butterknife.BindView;


public class YYXQFragment extends BaseFragment<YingYuanXQSPresenter> implements HomeConteract.YingYuanXqContreact.IView {
    public static final String TAG = "YYXQFragment";
    @BindView(R.id.yyxq_dizhi)
    TextView yyxqDizhi;
    @BindView(R.id.yyxq_dianhua)
    TextView yyxqDianhua;
    @BindView(R.id.yyxq_luxian)
    TextView yyxqLuxian;

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
        String sessionId = sharedPreferences.getString("sessionId", "");
        String userId = sharedPreferences.getString("userId", "");
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("yyid", Context.MODE_PRIVATE);
        String id = sharedPreferences1.getString("id", "");
        if (id != null) {
            mPresenter.getYingYuanXqPresenter("0",null,id);
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
        yyxqDizhi.setText(data.getResult().getName());
        yyxqDianhua.setText(data.getResult().getPhone());
        yyxqLuxian.setText(data.getResult().getVehicleRoute());
    }

    @Override
    public void onYingYuanXqFailure(Throwable e) {

    }

    @Override
    public void onYYGZSuccess(YYGZBean data) {

    }

    @Override
    public void onYYGZXqFailure(Throwable e) {

    }

    @Override
    public void onQXYYGZSuccess(QXYYGZBean data) {

    }

    @Override
    public void onQXYYGZXqFailure(Throwable e) {

    }
}
