package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.FanKuiBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class FanKuiPresenter extends BasePresenter<HomeConteract.FanKuiContreact.IView> implements HomeConteract.FanKuiContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getFanKuiPresenter(String userId, String sessionId, String content) {
        loginModel.getFanKuiDataModel(userId, sessionId, content, new HomeConteract.FanKuiContreact.IModel.IModelFanKuiCallback() {
            @Override
            public void onFanKuiSuccess(FanKuiBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onFanKuiSuccess(data);
                    }
                }
            }

            @Override
            public void onFanKuiFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onFanKuiFailure(e);
                }
            }
        });
    }
}
