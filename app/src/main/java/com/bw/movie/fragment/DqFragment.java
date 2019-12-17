package com.bw.movie.fragment;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.CinemaAdapter;
import com.bw.movie.adapter.FindAdapter;
import com.bw.movie.bean.CinemaBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.DqPresenter;

import java.util.List;

import butterknife.BindView;


public class DqFragment extends BaseFragment<DqPresenter> implements HomeConteract.FindContreact.IView {

    public static final String TAG = "DqFragment";
    @BindView(R.id.dp_dprecy)
    RecyclerView dpDprecy;
    @BindView(R.id.dp_yingyuan)
    RecyclerView dpYingyuan;
    @BindView(R.id.meiyou_tu)
    ImageView meiyouTu;
    @BindView(R.id.meiyou_xinxi)
    TextView meiyouXinxi;
    @BindView(R.id.zong)
    LinearLayout zong;

    @Override
    protected DqPresenter providePresenter() {
        return new DqPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        if (hasNetwork()){
            mPresenter.getFindPresenter();
        }else {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.wuwang);
            meiyouXinxi.setText("暂无网络");
        }
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_dq;
    }

    @Override
    public void onFindSuccess(FindBean data) {
        Log.d(TAG, "onFindSuccess: " + data.getMessage());
        List<FindBean.ResultBean> result = data.getResult();
        dpDprecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        FindAdapter findAdapter = new FindAdapter(getActivity(), result);
        dpDprecy.setAdapter(findAdapter);
        findAdapter.setOnClickListenter(new FindAdapter.setChangListenter() {
            @Override
            public void getChang(String id) {
                if (id != null) {
                    mPresenter.getLoginPresenter(id);
                }
            }
        });
    }

    @Override
    public void onFindFailure(Throwable e) {
        Log.d(TAG, "onFindFailure: " + e);
    }

    @Override
    public void onCinemaSuccess(CinemaBean data) {
        Log.d(TAG, "onCinemaSuccess: " + data.getMessage());
        List<CinemaBean.ResultBean> result = data.getResult();
        dpYingyuan.setLayoutManager(new LinearLayoutManager(getActivity()));
        dpYingyuan.setAdapter(new CinemaAdapter(getActivity(), result));
    }

    @Override
    public void onCinemaFailure(Throwable e) {
        Log.d(TAG, "onCinemaFailure: " + e);
    }
}
