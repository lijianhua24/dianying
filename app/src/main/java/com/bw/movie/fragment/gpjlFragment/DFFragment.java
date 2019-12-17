package com.bw.movie.fragment.gpjlFragment;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.DFAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.GPJLBean;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.DFPresenter;
import com.bw.movie.view.ZuoActivity;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.util.List;

import butterknife.BindView;


public class DFFragment extends BaseFragment<DFPresenter> implements HomeConteract.GPJLContreact.IView {

    private double fare;
    @BindView(R.id.df_recy)
    XRecyclerView dfRecy;
    @BindView(R.id.meiyou_tu)
    ImageView meiyouTu;
    @BindView(R.id.meiyou_xinxi)
    TextView meiyouXinxi;
    @BindView(R.id.zong)
    LinearLayout zong;

    @Override
    protected DFPresenter providePresenter() {
        return new DFPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);
        mPresenter.getGPJLPresenter(userId, sessionId, 1, "10", "1");
        dfRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_df;
    }

    @Override
    public void onGPJLSuccess(GPJLBean data) {
        List<GPJLBean.ResultBean> result = data.getResult();
        if (result != null) {
            DFAdapter dfAdapter = new DFAdapter(getActivity(), result);
            dfRecy.setAdapter(dfAdapter);
            dfAdapter.getChange(new DFAdapter.onorderId() {
                @Override
                public void getorderId(String orderId) {
                    if (orderId!=null){
                        Dialog dialog = new Dialog(getActivity(), R.style.DialogTheme);
                        View inflate = View.inflate(getActivity(), R.layout.weizhi_layout, null);
                        RadioButton weixin = inflate.findViewById(R.id.weixin);
                        RadioButton zhifubao = inflate.findViewById(R.id.zhifubao);
                        dialog.setContentView(inflate);
                        Window window = dialog.getWindow();
                        window.setGravity(Gravity.BOTTOM);
                        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                        String s = String.valueOf(fare);
                        dialog.show();
                        inflate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });
                        weixin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                String sessionId = App.sharedPreferences.getString("sessionId", null);
                                String userId = App.sharedPreferences.getString("userId", null);
                                mPresenter.getZFs(userId,sessionId,"1",orderId);
                                dialog.dismiss();
                            }
                        });
                    }
                }
            });
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
        String message = data.getMessage();
        if (data.getStatus().equals("0000")){
            PayReq payReq = new PayReq();
            payReq.appId = data.getAppId();
            payReq.nonceStr = data.getNonceStr();
            payReq.partnerId = data.getPartnerId();
            payReq.prepayId = data.getPrepayId();
            payReq.sign = data.getSign();
            payReq.timeStamp = data.getTimeStamp();
            payReq.packageValue = data.getPackageValue();
            payReq.extData = "app data";
            App.api.sendReq(payReq);

        }
    }

    @Override
    public void onZFFailure(Throwable e) {

    }
}
