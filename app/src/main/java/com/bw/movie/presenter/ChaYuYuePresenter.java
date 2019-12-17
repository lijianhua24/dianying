package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.ChaYuYueBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class ChaYuYuePresenter extends BasePresenter<HomeConteract.ChaYuYueContreact.IView> implements HomeConteract.ChaYuYueContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getChaYuYuePresenter(String userId, String sessionId) {
            loginModel.getChaYuYueDataModel(userId, sessionId, new HomeConteract.ChaYuYueContreact.IModel.IModelChaYuYueCallback() {
                @Override
                public void onChaYuYueSuccess(ChaYuYueBean data) {
                    if (isViewAttached()){
                        if (data!=null && data.getStatus().equals("0000")){
                            getView().onChaYuYueSuccess(data);
                        }
                    }
                }

                @Override
                public void onChaYuYueFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onChaYuYueFailure(e);
                    }
                }
            });
    }
}
