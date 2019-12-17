package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.SCTXBean;
import com.bw.movie.bean.ShengRiBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.bean.XiuGaiShouJIBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

import okhttp3.MultipartBody;

public class UserPresenter extends BasePresenter<HomeConteract.UserContreact.IView> implements HomeConteract.UserContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getUserPresenter(String userId, String sessionId) {
        loginModel.getUserDataModel(userId, sessionId, new HomeConteract.UserContreact.IModel.IModelUserCallback() {
            @Override
            public void onUserSuccess(UserBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onUserSuccess(data);
                    }
                }
            }

            @Override
            public void onUserFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onUserFailure(e);
                }
            }
        });
    }

    @Override
    public void getSCTXPresenter(String userId, String sessionId, MultipartBody.Part map) {
        loginModel.getSCTXDataModel(userId, sessionId,map, new HomeConteract.UserContreact.IModel.IModelSCTXCallback() {
            @Override
            public void onSCTXSuccess(SCTXBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onSCTXSuccess(data);
                    }
                }
            }

            @Override
            public void onSCTXFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onSCTXFailure(e);
                }
            }
        });
    }

    @Override
    public void getXGSJHPresenter(String userId, String sessionId, String phone) {
        loginModel.getXGSJHDataModel(userId, sessionId, phone, new HomeConteract.UserContreact.IModel.IModelXGSJHCallback() {
            @Override
            public void onXGSJHSuccess(XiuGaiShouJIBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onXGSJHSuccess(data);
                    }
                }
            }

            @Override
            public void onXGSJHFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onXGSJHFailure(e);
                }
            }
        });
    }

    @Override
    public void getXGSRPresenter(String userId, String sessionId,String birthday) {
        loginModel.getXGSRDataModel(userId,sessionId,birthday, new HomeConteract.UserContreact.IModel.IModelXGSRCallback() {
            @Override
            public void onXGSRSuccess(ShengRiBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onXGSRSuccess(data);
                    }
                }
            }

            @Override
            public void onXGSRFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onXGSRFailure(e);
                    }
            }
        });
    }

}
