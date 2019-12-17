package com.bw.movie.fragment.gpjlFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.YFAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.GPJLBean;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.YFPresenter;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

import butterknife.BindView;


public class YFFragment extends BaseFragment<YFPresenter> implements HomeConteract.GPJLContreact.IView {


    @BindView(R.id.yf_recy)
    XRecyclerView yfRecy;
    @BindView(R.id.meiyou_tu)
    ImageView meiyouTu;
    @BindView(R.id.meiyou_xinxi)
    TextView meiyouXinxi;
    @BindView(R.id.zong)
    LinearLayout zong;

    @Override
    protected YFPresenter providePresenter() {
        return new YFPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);
        mPresenter.getGPJLPresenter(userId, sessionId, 1, "10", "2");
        yfRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_yf;
    }

    @Override
    public void onGPJLSuccess(GPJLBean data) {
        List<GPJLBean.ResultBean> result = data.getResult();
        if (result != null) {
            yfRecy.setAdapter(new YFAdapter(getActivity(), result));
        } else {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.zanwuguanzhu);
            meiyouXinxi.setText("暂无记录");
        }
    }

    @Override
    public void onGPJLFailure(Throwable e) {

    }

    @Override
    public void onZFSuccess(ZhiFuBean data) {

    }

    @Override
    public void onZFFailure(Throwable e) {

    }
}
