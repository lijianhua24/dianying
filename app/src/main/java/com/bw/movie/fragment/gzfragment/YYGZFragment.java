package com.bw.movie.fragment.gzfragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.YYGZAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.YYGZsBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.YYGZPresenter;
import com.bw.movie.view.MainActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;


public class YYGZFragment extends BaseFragment<YYGZPresenter> implements HomeConteract.YYGZContreact.IView {


    @BindView(R.id.yygz_recy)
    XRecyclerView yygzRecy;
    @BindView(R.id.meiyou_tu)
    ImageView meiyouTu;
    @BindView(R.id.meiyou_xinxi)
    TextView meiyouXinxi;
    @BindView(R.id.zong)
    LinearLayout zong;

    @Override
    protected YYGZPresenter providePresenter() {
        return new YYGZPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);

            mPresenter.getYYGZPresenter(userId, sessionId, 1, "10");

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_yygz;
    }

    @Override
    public void onYYGZSuccess(YYGZsBean data) {
        List<YYGZsBean.ResultBean> result = data.getResult();
        if (result != null) {
            yygzRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
            yygzRecy.setAdapter(new YYGZAdapter(getActivity(), result));
        } else {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.zanwuguanzhu);
            meiyouXinxi.setText("暂无关注信息");
        }
    }

    @Override
    public void onYYGZFailure(Throwable e) {

    }
}
