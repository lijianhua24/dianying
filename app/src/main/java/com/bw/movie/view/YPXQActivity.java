package com.bw.movie.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.bean.YingPiaoXingQing;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.DingDanPresenter;
import com.tencent.mm.opensdk.modelpay.PayReq;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class YPXQActivity extends BaseActivity<DingDanPresenter> implements HomeConteract.DingDanContreact.IView {


    @BindView(R.id.details_back)
    ImageView detailsBack;
    @BindView(R.id.movie_name_seat)
    TextView movieNameSeat;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.position)
    TextView position;
    @BindView(R.id.home)
    TextView home;
    @BindView(R.id.count)
    TextView count;
    @BindView(R.id.seat)
    TextView seat;
    @BindView(R.id.time)
    TextView time;
    @BindView(R.id.orderId)
    TextView orderId;
    @BindView(R.id.createtime)
    TextView createtime;
    @BindView(R.id.price)
    TextView price;
    @BindView(R.id.pay_btn)
    Button payBtn;
    private double price1;
    private String orderId1;

    @Override
    protected DingDanPresenter providePresenter() {
        return new DingDanPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);
        orderId1 = getIntent().getStringExtra("orderId");
        if (userId!=null && sessionId!=null){
            if (orderId1!=null){
                mPresenter.getDingDanPresenter(userId,sessionId,orderId1);
            }
        }else {
            Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_ypxq;
    }

    @Override
    public void onSuccess(YingPiaoXingQing data) {
        if (data.getStatus().equals("0000")) {
            if (data.getResult() == null) {
                finish();
                Toast.makeText(this, "空", Toast.LENGTH_SHORT).show();
                return;
            }
            YingPiaoXingQing.ResultBean result = data.getResult();
            name.setText(result.getMovieName());
            position.setText(result.getCinemaName());
            home.setText(result.getScreeningHall());
            count.setText(result.getAmount() + "张");
            seat.setText(result.getSeat());
            time.setText(result.getBeginTime() + "-" + result.getEndTime());
            orderId.setText(orderId1);
            Date date = new Date(result.getCreateTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            createtime.setText(simpleDateFormat.format(date));
            price.setText("￥" + result.getPrice()*result.getAmount());
            price1 = result.getPrice();
            payBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    initPopWindow(view);
                }
            });
        }else {

        }
    }

    @Override
    public void onFailure(Throwable e) {

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


    @OnClick(R.id.pay_btn)
    public void onViewClicked() {
    }
    private void initPopWindow(View v1) {
        Dialog dialog = new Dialog(this, R.style.DialogTheme);
        View inflate = View.inflate(this, R.layout.weizhi_layout, null);
        RadioButton weixin = inflate.findViewById(R.id.weixin);
        RadioButton zhifubao = inflate.findViewById(R.id.zhifubao);
        dialog.setContentView(inflate);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
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
                mPresenter.getZFs(userId, sessionId, "1", orderId1);
                dialog.dismiss();
            }
        });
    }
}
