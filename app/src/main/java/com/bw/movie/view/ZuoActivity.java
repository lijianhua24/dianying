package com.bw.movie.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.adapter.YingTingAdapter;
import com.bw.movie.adapter.ZuoAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.XQBean;
import com.bw.movie.bean.XiaDanBean;
import com.bw.movie.bean.YingTingBean;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.bean.ZuoBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.ZuoPresenter;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class ZuoActivity extends BaseActivity<ZuoPresenter> implements HomeConteract.ZuoContreact.IView {
    private double fare;
    public static final String TAG = "ZuoActivity";
    @BindView(R.id.zuo_name)
    TextView zuoName;
    @BindView(R.id.zuo_pin)
    JCVideoPlayerStandard zuoPin;
    @BindView(R.id.zuo_xuan)
    RecyclerView zuoXuan;
    @BindView(R.id.zuo_ying)
    TextView zuoYing;
    @BindView(R.id.zuo_recy_ying)
    RecyclerView zuoRecyYing;
    @BindView(R.id.zuo_jia)
    TextView zuoJia;
    private String scheduleId;
    private String seat;
    private String hallId;
    private ZuoAdapter zuoAdapter;

    @Override
    protected ZuoPresenter providePresenter() {
        return new ZuoPresenter();
    }

    @Override
    protected void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        zuoRecyYing.setLayoutManager(linearLayoutManager);

    }

    @Override
    protected void initView() {
        mPresenter.getZuo("1");
        String cinemaId = getIntent().getStringExtra("yyid");
        SharedPreferences name = getSharedPreferences("users", Context.MODE_PRIVATE);
        String movieid = name.getString("movieId", "");
        mPresenter.getXQPresenter( movieid);
        Log.d(TAG, "movieId: " + movieid);
        Log.d(TAG, "cinemaId: " + cinemaId);
        mPresenter.getYingTingPresenter(movieid, cinemaId);

        zuoJia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sessionId = App.sharedPreferences.getString("sessionId", null);
                String userId = App.sharedPreferences.getString("userId", null);
                String md5 = MD5(userId + scheduleId + "movie");

               if (userId!=null &&sessionId!=null){
                   mPresenter.getXD(userId, sessionId, scheduleId, seat, md5);
               }else {
                   Toast.makeText(ZuoActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(ZuoActivity.this,MainActivity.class));
               }
            }
        });


        SharedPreferences sharedPreferences1 = getSharedPreferences("yyid", Context.MODE_PRIVATE);
        String id = sharedPreferences1.getString("id", null);
        String dyid = getIntent().getStringExtra("dyid");
        String usedd = getIntent().getStringExtra("usedd");
        if (dyid != null && id != null) {
            mPresenter.getYingTingPresenter(dyid, id);
            mPresenter.getXQPresenter( dyid);
        }

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_zuo;
    }

    @Override
    public void onXQSuccess(XQBean data) {
        Log.d(TAG, "onXQSuccess: " + data.getMessage());
        XQBean.ResultBean result = data.getResult();
        String videoUrl = result.getShortFilmList().get(0).getVideoUrl();
        zuoPin.setUp(videoUrl,JCVideoPlayer.SCREEN_LAYOUT_NORMAL,"");
        Glide.with(this).load(result.getShortFilmList().get(0).getImageUrl()).into(zuoPin.thumbImageView);
        String name = result.getName();
        if (name != null) {
            zuoName.setText(name);
        }
    }

    @Override
    public void onXQFailure(Throwable e) {

    }

    @Override
    public void onYingTingSuccess(YingTingBean data) {
        Log.d(TAG, "onYingTingSuccess: " + data.getMessage());
        List<YingTingBean.ResultBean> result = data.getResult();

        Log.d(TAG, "onYingTingSuccess: " + result);
        YingTingAdapter yingTingAdapter = new YingTingAdapter(this, result);
        zuoRecyYing.setAdapter(yingTingAdapter);
        yingTingAdapter.getListenter(new YingTingAdapter.onSetChange() {



            @Override
            public void getChange(String name,String id,double fare) {
                mPresenter.getZuo(name);
                hallId = name;
                scheduleId = id;
                fare = fare;
            }
        });

    }

    @Override
    public void onYingTingFailure(Throwable e) {

    }

    @Override
    public void onZuoSuccess(ZuoBean data) {
        Log.d(TAG, "onZuoSuccess: " + data.getMessage());
        List<ZuoBean.ResultBean> result = data.getResult();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 6);
        zuoXuan.setLayoutManager(gridLayoutManager);
        if (result != null) {
            zuoAdapter = new ZuoAdapter(this, result);
            zuoXuan.setAdapter(zuoAdapter);
            zuoAdapter.setListenter(new ZuoAdapter.setChange() {
                @Override
                public void getChange(String zuo,double anInt) {
                    Log.d(TAG, "zuo: "+zuo);
                    seat = zuo;
                    zuoJia.setText("支付"+anInt+"元");
                }
            });
        }

    }

    @Override
    public void onZuoFailure(Throwable e) {

    }

    //下单
    @Override
    public void onXDSuccess(XiaDanBean data) {
        String message = data.getMessage();
        if (data.getStatus().equals("0000")){
            String orderId = data.getOrderId();
            Dialog dialog = new Dialog(ZuoActivity.this, R.style.DialogTheme);
            View inflate = View.inflate(ZuoActivity.this, R.layout.weizhi_layout, null);
            RadioButton weixin = inflate.findViewById(R.id.weixin);
            RadioButton zhifubao = inflate.findViewById(R.id.zhifubao);
            dialog.setContentView(inflate);
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM);
            window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
            String s = String.valueOf(fare);
            zuoJia.setText(s);
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
                  if (userId!=null &&sessionId!=null){
                      mPresenter.getZF(userId,sessionId,"1",orderId);
                  }else {
                      Toast.makeText(ZuoActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                      startActivity(new Intent(ZuoActivity.this,MainActivity.class));
                  }
                   dialog.dismiss();
               }
           });

        }
    }

    @Override
    public void onXDFailure(Throwable e) {

    }

    @Override
    public void onZFSuccess(ZhiFuBean data) {

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

    public static String MD5(String sourceStr) {
        String result = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(sourceStr.getBytes());
            byte b[] = md.digest();
            int i;
            StringBuffer buf = new StringBuffer("");
            for (int offset = 0; offset < b.length; offset++) {
                i = b[offset];
                if (i < 0)
                    i += 256;
                if (i < 16)
                    buf.append("0");
                buf.append(Integer.toHexString(i));
            }
            result = buf.toString();
        } catch (NoSuchAlgorithmException e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
