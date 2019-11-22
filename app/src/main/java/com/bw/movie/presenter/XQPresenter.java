package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.XQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class XQPresenter extends BasePresenter<HomeConteract.XQContreact.IView> implements HomeConteract.XQContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getXQPresenter(String userId, String sessionId, String page) {
            loginModel.getXQDataModel(userId, sessionId, page, new HomeConteract.XQContreact.IModel.IModelCallback() {
                @Override
                public void onTjyySuccess(XQBean data) {
                    if (isViewAttached()){
                        if (data!=null && data.getStatus().equals("0000")){
                            getView().onXQSuccess(data);
                        }
                    }
                }

                @Override
                public void onTjyyFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onXQFailure(e);
                    }
                }
            });
    }
}
