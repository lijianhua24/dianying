package com.bw.movie.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.TimesAdapter;
import com.bw.movie.bean.YYPQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.PaiQiPresenter;

import java.util.List;

import butterknife.BindView;


public class TimeFragment extends BaseFragment<PaiQiPresenter> implements HomeConteract.PaiQiContreact.IView {
    public static final String TAG = "TimeFragment";
    @BindView(R.id.time_recy)
    RecyclerView timeRecy;
    @BindView(R.id.meiyou_tu)
    ImageView meiyouTu;
    @BindView(R.id.meiyou_xinxi)
    TextView meiyouXinxi;
    @BindView(R.id.zong)
    LinearLayout zong;

    @Override
    protected PaiQiPresenter providePresenter() {
        return new PaiQiPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("yyid", Context.MODE_PRIVATE);
        String id = sharedPreferences.getString("id", "");
        Log.d(TAG, "initView: " + id);
        if (id != null) {
            mPresenter.getPaiQiPresenter(id, 1, "10");
        }
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_time;
    }

    @Override
    public void onPaiQiSuccess(YYPQBean data) {
        List<YYPQBean.ResultBean> result = data.getResult();
        timeRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        String message = data.getMessage();
        if (result != null) {
            timeRecy.setAdapter(new TimesAdapter(getActivity(), result));
        } else {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.zanwuguanzhu);
            meiyouXinxi.setText("暂无信息");
            }
        }



    @Override
    public void onPaiQiFailure(Throwable e) {

    }
}
