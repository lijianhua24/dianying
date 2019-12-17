package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.XiuGaiBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class XiuGaiPresenter extends BasePresenter<HomeConteract.XiuGaiContreact.IView> implements HomeConteract.XiuGaiContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getXiuGaiPresenter(String userId, String sessionId, String oldPwd, String newPwd, String newPwd2) {
        loginModel.getXiuGaiDataModel(userId, sessionId, oldPwd, newPwd, newPwd2, new HomeConteract.XiuGaiContreact.IModel.IModelXiuGaiCallback() {
            @Override
            public void onXiuGaiSuccess(XiuGaiBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onXiuGaiSuccess(data);
                    }
                }
            }

            @Override
            public void onXiuGaiFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onXiuGaiFailure(e);
                    }
            }
        });
    }
}
