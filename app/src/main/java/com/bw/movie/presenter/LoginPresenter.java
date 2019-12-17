package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.app.Constant;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.WxBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

import org.greenrobot.greendao.annotation.Id;

public class LoginPresenter extends BasePresenter<HomeConteract.LoginContreact.IView> implements HomeConteract.LoginContreact.IPresenter {
    public static final String TAG = "LoginPresenter";
    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getLoginPresenter(String email, String pwd) {
        loginModel.getLoginDataModel(email, pwd, new HomeConteract.LoginContreact.IModel.IModelCallback() {
            @Override
            public void onSuccess(LoginBean data) {
                LoginBean loginBean = (LoginBean) data;
                if (isViewAttached()) {
                    if (data != null && Constant.SUCCESS_CODE.equals(loginBean.getStatus())) {
                        Log.d(TAG, "sdsdsd: " + data.getMessage());
                        getView().onSuccess(data);
                    } else {
                        getView().onFailure(new Exception("服务器异常"));
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
    public void getWXPresenter(String code) {
        loginModel.getWXLoginDataModel(code, new HomeConteract.LoginContreact.IModel.IModelWXCallback() {
            @Override
            public void onSuccess(WxBean data) {
                if (isViewAttached()){
                    getView().onWXSuccess(data);
                }
            }

            @Override
            public void onFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onWXFailure(e);
                }
            }
        });
    }
}
