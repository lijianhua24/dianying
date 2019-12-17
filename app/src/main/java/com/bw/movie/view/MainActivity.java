package com.bw.movie.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.WxBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.LoginPresenter;
import com.bw.movie.utils.ActivityCollectorUtil;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.utils.SendAuth;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity<LoginPresenter> implements HomeConteract.LoginContreact.IView {
    public static final String TAG = "MainActivity";
    @BindView(R.id.name)
    EditText mainEmail;
    @BindView(R.id.pass)
    EditText mainPwd;
    @BindView(R.id.deng)
    Button mainDeng;
    @BindView(R.id.zhuce)
    TextView mainRegister;
    @BindView(R.id.main_weideng)
    ImageView mainWeideng;


    @Override
    protected LoginPresenter providePresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {
        ActivityCollectorUtil.addActivity(this);
        String code = getIntent().getStringExtra("code");
        if (code!=null){
            mPresenter.getWXPresenter(code);
        }
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(LoginBean data) {
        Toast.makeText(this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
        if (data.getMessage().contains("登陆成功")) {
            String sessionId = data.getResult().getSessionId();
            String userId = data.getResult().getUserId();
            LoginBean.ResultBean.UserInfoBean userInfo = data.getResult().getUserInfo();
            String nickName = userInfo.getNickName();
            String headPic = userInfo.getHeadPic();
            String email = userInfo.getEmail();
            String sex = userInfo.getSex();
            String lastLogStringime = userInfo.getLastLogStringime();

            SharedPreferences.Editor edit2 = App.sharedPreferences.edit();
            edit2.putString("sessionId", sessionId);
            edit2.putString("userId", userId);
            edit2.commit();

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("sessionId", sessionId);
            intent.putExtra("userId", userId);
            startActivity(intent);
            finish();
        }else {
            Toast.makeText(this, "登陆失败,账号或密码错误", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    public void onWXSuccess(WxBean data) {
        String message = data.message;
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        if (data!=null){
            WxBean.ResultBean result = data.result;
            int userId = result.userId;
            String sessionId = result.sessionId;
            String s = String.valueOf(userId);
            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("sessionId", sessionId);
            edit.putString("userId", s);
            edit.commit();
            SharedPreferences.Editor edit1 = App.sharedPreferences.edit();
            edit1.putString("sessionId", sessionId);
            edit1.putString("userId", s);
            edit1.commit();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("sessionId", sessionId);
            intent.putExtra("userId", s);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void onWXFailure(Throwable e) {

    }


    @OnClick(R.id.deng)
    public void onViewClicked() {

    }


    @OnClick({R.id.zhuce, R.id.deng, R.id.main_weideng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuce:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            case R.id.main_weideng:
                SendAuth.Req req = new SendAuth.Req();
                req.scope = "snsapi_userinfo";
                req.state = "wechat_sdk_demo_test";
                App.api.sendReq(req);
                break;
            case R.id.deng:
                String pwd = mainPwd.getText().toString();
                String email = mainEmail.getText().toString();
                String pwds = EncryptUtil.encrypt(pwd);
                Log.d(TAG, "onViewClicked: "+pwds);
                mPresenter.getLoginPresenter(email, pwds);
                break;
        }
    }



}
