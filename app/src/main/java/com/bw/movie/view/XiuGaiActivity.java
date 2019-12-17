package com.bw.movie.view;

import android.content.Context;
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
import com.bw.movie.bean.XiuGaiBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.XiuGaiPresenter;
import com.bw.movie.utils.EncryptUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XiuGaiActivity extends BaseActivity<XiuGaiPresenter> implements HomeConteract.XiuGaiContreact.IView {


    @BindView(R.id.title_fanhui)
    ImageView titleFanhui;
    @BindView(R.id.title_biaoti)
    TextView titleBiaoti;
    @BindView(R.id.cz_pwd)
    EditText czPwd;
    @BindView(R.id.cz_pwd1)
    EditText czPwd1;
    @BindView(R.id.cz_pwd2)
    EditText czPwd2;
    @BindView(R.id.cz_qd)
    Button czQd;

    @Override
    protected XiuGaiPresenter providePresenter() {
        return new XiuGaiPresenter();
    }

    @Override
    protected void initData() {
        titleBiaoti.setText("重置密码");
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_xiu_gai;
    }

    @Override
    public void onXiuGaiSuccess(XiuGaiBean data) {
        String message = data.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        if (message.equals("密码修改成功")){

            finish();

        }
    }

    @Override
    public void onXiuGaiFailure(Throwable e) {

    }


    @OnClick({R.id.title_fanhui, R.id.cz_qd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_fanhui:
                finish();
                break;
            case R.id.cz_qd:
                SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                String sessionId = sharedPreferences.getString("sessionId", "");
                String userId = sharedPreferences.getString("userId", "");
                String pwd = czPwd.getText().toString();
                String pwd1 = czPwd1.getText().toString();
                String pwd2 = czPwd2.getText().toString();
                String encrypt = EncryptUtil.encrypt(pwd);
                String encrypt1 = EncryptUtil.encrypt(pwd1);
                String encrypt2 = EncryptUtil.encrypt(pwd2);
                mPresenter.getXiuGaiPresenter(userId,sessionId,encrypt,encrypt1,encrypt2);
                break;
        }
    }
}
