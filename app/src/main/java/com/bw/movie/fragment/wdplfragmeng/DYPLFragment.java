package com.bw.movie.fragment.wdplfragmeng;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.WDDYPLAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.DYPLBean;
import com.bw.movie.bean.YYPLBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.WDPLPresenter;

import java.util.List;

import butterknife.BindView;


public class DYPLFragment extends BaseFragment<WDPLPresenter> implements HomeConteract.WDPLContreact.IView {


    @BindView(R.id.dypl_recy)
    RecyclerView dyplRecy;
    @BindView(R.id.meiyou_tu)
    ImageView meiyouTu;
    @BindView(R.id.meiyou_xinxi)
    TextView meiyouXinxi;
    @BindView(R.id.zong)
    LinearLayout zong;

    @Override
    protected WDPLPresenter providePresenter() {
        return new WDPLPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);
        mPresenter.getWDDYPLPresenter(userId, sessionId, "1", "10");
        dyplRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_dypl;
    }

    @Override
    public void onWDDYPLSuccess(DYPLBean data) {
        List<DYPLBean.ResultBean> result = data.getResult();
        if (result!=null){
            dyplRecy.setAdapter(new WDDYPLAdapter(getActivity(), result));
        }else {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.zanwuguanzhu);
            meiyouXinxi.setText("暂无评论");
        }

    }

    @Override
    public void onWDDYPLFailure(Throwable e) {

    }

    @Override
    public void onWDYYPLSuccess(YYPLBean data) {

    }

    @Override
    public void onWDYYPLFailure(Throwable e) {

    }
}
