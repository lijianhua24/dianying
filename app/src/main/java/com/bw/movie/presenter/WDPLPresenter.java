package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.DYPLBean;
import com.bw.movie.bean.YYPLBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class WDPLPresenter extends BasePresenter<HomeConteract.WDPLContreact.IView> implements HomeConteract.WDPLContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getWDDYPLPresenter(String userId, String sessionId, String page, String count) {
            loginModel.getWDDYPLDataModel(userId, sessionId, page, count, new HomeConteract.WDPLContreact.IModel.IModelWDDYPLCallback() {
                @Override
                public void onWDDYPLSuccess(DYPLBean data) {
                    if (isViewAttached()){
                        if (data!=null && data.getStatus().equals("0000")){
                            getView().onWDDYPLSuccess(data);
                        }
                    }
                }

                @Override
                public void onWDDYPLFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onWDDYPLFailure(e);
                    }
                }
            });
    }

    @Override
    public void getWDYYPLPresenter(String userId, String sessionId, String longitude, String latitude, String page, String count) {
            loginModel.getWDYYPLDataModel(userId, sessionId, longitude, latitude, page, count, new HomeConteract.WDPLContreact.IModel.IModelWYDYPLCallback() {
                @Override
                public void onWDYYPLSuccess(YYPLBean data) {
                    if (isViewAttached()){
                        if (data!=null && data.getStatus().equals("0000")){
                            getView().onWDYYPLSuccess(data);
                        }
                    }
                }

                @Override
                public void onWDYYPLFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onWDYYPLFailure(e);
                    }
                }
            });
    }
}
