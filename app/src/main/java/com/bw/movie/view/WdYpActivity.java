package com.bw.movie.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.adapter.WDYPAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.QuPiaoBean;
import com.bw.movie.bean.WDDYPBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.WDYPPresenter;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WdYpActivity extends BaseActivity<WDYPPresenter> implements HomeConteract.WDDYPContreact.IView {

    private  Context context;
    @BindView(R.id.wdyp_recy)
    RecyclerView wdypRecy;
    private SimpleDraweeView wdyp_image;
    public static final String TAG = "WdYpActivity";

    @BindView(R.id.title_biaoti)
    TextView titleBiaoti;
    @BindView(R.id.title_fanhui)
    ImageView titleFanhui;
    @BindView(R.id.meiyou_tu)
    ImageView meiyouTu;
    @BindView(R.id.meiyou_xinxi)
    TextView meiyouXinxi;
    @BindView(R.id.zong)
    LinearLayout zong;

    private String sessionId;
    private String userId;

    @Override
    protected WDYPPresenter providePresenter() {
        return new WDYPPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

        sessionId = App.sharedPreferences.getString("sessionId", null);
        userId = App.sharedPreferences.getString("userId", null);

        if (userId !=null && sessionId!=null){
            mPresenter.getWDDYPPresenter(userId, sessionId);
        }else {
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this,MainActivity.class));
            finish();
        }
        wdypRecy.setLayoutManager(new LinearLayoutManager(this));
        titleBiaoti.setText("我的影票");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_wd_yp;
    }

    @Override
    public void onWDDYPSuccess(WDDYPBean data) {
        List<WDDYPBean.ResultBean> result = data.getResult();
        if (userId!=null && sessionId!=null){
            if (result != null) {
                WDYPAdapter wdypAdapter = new WDYPAdapter(this, result);
                wdypRecy.setAdapter(wdypAdapter);
                wdypAdapter.setLisenter(new WDYPAdapter.setqupiao() {

                    private Button wdyp_qx;


                    @Override
                    public void getQuPiao(String id) {

                        Log.d(TAG, "id: " + id);
                        Log.d(TAG, "userId: " + userId);
                        Log.d(TAG, "sessionId: " + sessionId);
                        if (id != null) {
                            mPresenter.getQuPiaoPresenter(userId, sessionId, id);
                            View view = LayoutInflater.from(WdYpActivity.this).inflate(R.layout.qupiao_layout, null, false);
                            wdyp_image = view.findViewById(R.id.wdyp_image);
                            wdyp_qx = (Button) view.findViewById(R.id.wdyp_qx);
                            //1.构造一个PopupWindow，参数依次是加载的View，宽高
                            final PopupWindow popWindow = new PopupWindow(view,
                                    ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT, true);
                            popWindow.setTouchable(true);
                            popWindow.setTouchInterceptor(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    return false;
                                    // 这里如果返回true的话，touch事件将被拦截
                                    // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                                }
                            });
                            popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效

                            //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
                            popWindow.showAsDropDown(view, -50, 0);
                            popWindow.setOnDismissListener(new PopupWindow.OnDismissListener(){
                                @Override
                                public void onDismiss() {
                                    WindowManager.LayoutParams lp=getWindow().getAttributes();
                                    lp.alpha=1.0f;
                                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                                    getWindow().setAttributes(lp);
                                }
                            });
                            popWindow.showAtLocation(view, Gravity.BOTTOM, 0, 0);
                            WindowManager.LayoutParams lp=getWindow().getAttributes();
                            lp.alpha=0.3f;
                            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                            getWindow().setAttributes(lp);
                            wdyp_qx.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    popWindow.dismiss();
                                }
                            });
                        }
                    }
                });
            }else {
                zong.setVisibility(View.VISIBLE);
                meiyouTu.setImageResource(R.mipmap.zanwuguanzhu);
                meiyouXinxi.setText("暂无影票");
            }
        }else {
            Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(context,MainActivity.class));
        }
    }

    @Override
    public void onWDDYPFailure(Throwable e) {

    }

    @Override
    public void onQuPiaoSuccess(QuPiaoBean data) {
        String exchangeCode = data.getResult().getExchangeCode();
        Log.d(TAG, "onQuPiaoSuccess: " + exchangeCode);
        Uri parse = Uri.parse(exchangeCode);
        wdyp_image.setImageURI(parse);

    }

    @Override
    public void onQuPiaoFailure(Throwable e) {

    }



    @OnClick({R.id.title_fanhui})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_fanhui:
                finish();
                break;
        }
    }

}
