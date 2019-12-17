package com.bw.movie.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.bean.FanKuiBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.FanKuiPresenter;
import com.bw.movie.utils.ActivityCollectorUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FanKuiActivity extends BaseActivity<FanKuiPresenter> implements HomeConteract.FanKuiContreact.IView {

    @BindView(R.id.title_fanhui)
    ImageView fankuiTui;
    @BindView(R.id.dankui_edit)
    EditText dankuiEdit;
    @BindView(R.id.fankui_bt)
    Button fankuiBt;
    @BindView(R.id.title_biaoti)
    TextView titleBiaoti;

    @Override
    protected FanKuiPresenter providePresenter() {
        return new FanKuiPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
titleBiaoti.setText("意见反馈");
        ActivityCollectorUtil.addActivity(this);

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_fan_kui;
    }

    @Override
    public void onFanKuiSuccess(FanKuiBean data) {
        Toast.makeText(this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFanKuiFailure(Throwable e) {

    }


    @OnClick({R.id.title_fanhui, R.id.fankui_bt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_fanhui:
                finish();
                break;
            case R.id.fankui_bt:
                String sessionId = App.sharedPreferences.getString("sessionId", null);
                String userId = App.sharedPreferences.getString("userId", null);
                String s = dankuiEdit.getText().toString();
                if (userId!=null && sessionId!=null){
                    mPresenter.getFanKuiPresenter(userId, sessionId, s);
                }else {
                    Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(this,MainActivity.class));
                    finish();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
