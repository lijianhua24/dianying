package com.bw.movie.fragment.gzfragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.DYGZAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.DYGZBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.DYGZPresenter;
import com.bw.movie.view.MainActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class DYGZFragment extends BaseFragment<DYGZPresenter> implements HomeConteract.DYGZContreact.IView {
    public static final String TAG = "DYGZFragment";
    @BindView(R.id.dygz_recy)
    XRecyclerView dygzRecy;
    @BindView(R.id.meiyou_tu)
    ImageView meiyouTu;
    @BindView(R.id.meiyou_xinxi)
    TextView meiyouXinxi;
    @BindView(R.id.zong)
    LinearLayout zong;

    @Override
    protected DYGZPresenter providePresenter() {
        return new DYGZPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);

           mPresenter.getDYGZPresenter(userId, sessionId, 1, "10");

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_dygz;
    }

    @Override
    public void onDYGZSuccess(DYGZBean data) {
        String message = data.getMessage();
        Log.d(TAG, "onDYGZSuccess: " + message);
        dygzRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
        List<DYGZBean.ResultBean> result = data.getResult();
        if (result != null) {
            dygzRecy.setAdapter(new DYGZAdapter(getActivity(),result));
        } else {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.zanwuguanzhu);
            meiyouXinxi.setText("暂无关注信息");
        }
    }

    @Override
    public void onDYGZFailure(Throwable e) {

    }

    @OnClick(R.id.zong)
    public void onViewClicked() {

    }
}
