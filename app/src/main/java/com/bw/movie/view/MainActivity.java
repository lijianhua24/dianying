package com.bw.movie.view;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
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
import com.bw.movie.Utils.EncryptUtil;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.LoginPresenter;

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


    @Override
    protected LoginPresenter providePresenter() {
        return new LoginPresenter();
    }

    @Override
    protected void initData() {
    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(LoginBean data) {
        Log.d(TAG, "onSuccess: " + data.getMessage());
        if (data.getMessage().contains("登陆成功")) {
            Toast.makeText(this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
            String sessionId = data.getResult().getSessionId();
            String userId = data.getResult().getUserId();
            SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putString("sessionId",sessionId);
            edit.putString("userId",userId);
            Log.d(TAG, "onSuccess: "+userId+sessionId);
            edit.commit();
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            intent.putExtra("sessionId", sessionId);
            intent.putExtra("userId", userId);
            startActivity(intent);
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }


    @OnClick(R.id.deng)
    public void onViewClicked() {

    }


    @OnClick({R.id.zhuce, R.id.deng})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.zhuce:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;
            case R.id.deng:
                String pwd = mainPwd.getText().toString();
                String email = mainEmail.getText().toString();
                String pwds = EncryptUtil.encrypt(pwd);
                mPresenter.getLoginPresenter("896745795@qq.com", pwds);
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
