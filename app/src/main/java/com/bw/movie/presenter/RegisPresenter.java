package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.app.Constant;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class RegisPresenter extends BasePresenter<HomeConteract.RegisterContract.IView> implements HomeConteract.RegisterContract.IPresenter {

    public static final String TAG = "RegisPresenter";
    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }


    @Override
    public void getRegisterPresenter(String nickName, String pwd, String email, String code) {
        loginModel.getRegisterModel(nickName, pwd, email, code, new HomeConteract.RegisterContract.IModel.IModelCallback() {
            @Override
            public void onSuccess(RegisterBean data) {
                if (isViewAttached()) {
                    Log.d(TAG, "onSuccess: " + data.getStatus());
                    if (data != null && data.getStatus().equals("0000")) {
                        Log.d(TAG, "onSuccess: " + data.getStatus());
                        getView().onSuccess(data);
                    }
                }
            }

            @Override
            public void onFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onFailure(e);
                }
            }
        });
    }

    @Override
    public void getEmailPresenter(String email) {
        loginModel.getEmailModel(email, new HomeConteract.RegisterContract.IModel.IEmailModelCallback() {
            @Override
            public void onSuccess(EmailBean data) {
                if (isViewAttached()) {
                    Log.d(TAG, "onSuccess: " + data.getMessage());
                    if (data != null && data.getStatus().equals("0000")) {
                        Log.d(TAG, "onSuccess1: " + data.getMessage());
                        getView().onEmailSuccess(data);
                    }
                }
            }

            @Override
            public void onFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onEmailFailure(e);
                }
            }
        });
    }
}
