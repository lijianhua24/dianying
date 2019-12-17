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
import com.bw.movie.adapter.WDYYPLAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.DYPLBean;
import com.bw.movie.bean.YYPLBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.WDPLPresenter;

import java.util.List;

import butterknife.BindView;


public class YYPLFragment extends BaseFragment<WDPLPresenter> implements HomeConteract.WDPLContreact.IView {


    @BindView(R.id.wdyypl_recy)
    RecyclerView wdyyplRecy;
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
        String userId = App.sharedPreferences.getString("userId", "");
        mPresenter.getWDYYPLPresenter(userId, sessionId, "0", "0", "1", "10");
        wdyyplRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_yypl;
    }

    @Override
    public void onWDDYPLSuccess(DYPLBean data) {

    }

    @Override
    public void onWDDYPLFailure(Throwable e) {

    }

    @Override
    public void onWDYYPLSuccess(YYPLBean data) {
        List<YYPLBean.ResultBean> result = data.getResult();
        if (result!=null){
            wdyyplRecy.setAdapter(new WDYYPLAdapter(getActivity(), result));
        }else {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.zanwuguanzhu);
            meiyouXinxi.setText("暂无评论");
        }

    }

    @Override
    public void onWDYYPLFailure(Throwable e) {

    }
}
