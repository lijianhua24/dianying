package com.bw.movie.view;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.utils.EncryptUtil;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.RegisPresenter;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity<RegisPresenter> implements HomeConteract.RegisterContract.IView {

    public static final String TAG = "RegisterActivity";
    @BindView(R.id.register_name)
    EditText resName;
    @BindView(R.id.register_email)
    EditText resEmail01;
    @BindView(R.id.register_pwd)
    EditText resPwd01;
    @BindView(R.id.register_verification)
    EditText resYan;
    @BindView(R.id.register_huoqu)
    Button resYanbt;

    @BindView(R.id.register)
    Button resRegister;
    @BindView(R.id.jump_login)
    TextView resLogin;

    @Override
    protected RegisPresenter providePresenter() {
        return new RegisPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_zhu_ce;
    }

    @Override
    public void onSuccess(RegisterBean data) {
        Toast.makeText(this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
        String message = data.getMessage();
        Log.d(TAG, "onSuccess: " + message);
        if (message.contains("注册成功")) {
            startActivity(new Intent(RegisterActivity.this, HomeActivity.class));
            finish();
        }
    }

    @Override
    public void onFailure(Throwable e) {

    }

    @Override
    public void onEmailSuccess(EmailBean data) {
        Log.d(TAG, "onEmailSuccess: " + data.getMessage());
        Toast.makeText(this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEmailFailure(Throwable e) {

    }


    @OnClick({R.id.register_huoqu, R.id.register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.register_huoqu:
                String email = resEmail01.getText().toString();
                if (email != null) {
                    mPresenter.getEmailPresenter(email);
                } else {
                    Toast.makeText(this, "请输入正确的邮箱", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.register:
                String name = resName.getText().toString();
                String pwd = resPwd01.getText().toString();
                String yan = resYan.getText().toString();
                String pwds = EncryptUtil.encrypt(pwd);
                String emis = resEmail01.getText().toString();
                if (!name.isEmpty() && !pwds.isEmpty() && !yan.isEmpty() && !emis.isEmpty()) {
                    mPresenter.getRegisterPresenter(name, pwds, emis, yan);
                } else {
                    Toast.makeText(this, "请填写正确的格式", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }


    @OnClick(R.id.jump_login)
    public void onViewClicked() {
        startActivity(new Intent(RegisterActivity.this,MainActivity.class));
        finish();
    }
}
