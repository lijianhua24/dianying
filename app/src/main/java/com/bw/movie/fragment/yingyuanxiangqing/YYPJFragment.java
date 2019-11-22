package com.bw.movie.fragment.yingyuanxiangqing;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.YypjAdapter;
import com.bw.movie.bean.YYPJBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.YYPJPresenter;

import java.util.List;

import butterknife.BindView;


public class YYPJFragment extends BaseFragment<YYPJPresenter> implements HomeConteract.YingYuanPJContreact.IView {
    public static final String TAG = "YYPJFragment";
    @BindView(R.id.yypj_recy)
    RecyclerView yypjRecy;

    @Override
    protected YYPJPresenter providePresenter() {
        return new YYPJPresenter();
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
            mPresenter.getYingYuanPJPresenter(userId, sessionId, yyid, 1, "10");
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        yypjRecy.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_yypj;
    }

    @Override
    public void onYingYuanPJSuccess(YYPJBean data) {
        Log.d(TAG, "onYingYuanPJSuccess: " + data.getMessage());
        List<YYPJBean.ResultBean> result = data.getResult();

        if (result!=null){
           yypjRecy.setAdapter(new YypjAdapter(getActivity(),result));
       }
    }

    @Override
    public void onYingYuanPJFailure(Throwable e) {

    }
}
