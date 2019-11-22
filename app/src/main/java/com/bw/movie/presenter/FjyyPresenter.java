package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.FjYyBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class FjyyPresenter extends BasePresenter<HomeConteract.FjyyContreact.IView> implements HomeConteract.FjyyContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getFjyyPresenter(String userId, String sessionId, String longitude, String latitude, Integer page, String count) {
        loginModel.getFjyyDataModel(userId, sessionId, longitude, latitude, page, count, new HomeConteract.FjyyContreact.IModel.IModelCallback() {
            @Override
            public void onFjyySuccess(FjYyBean data) {
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onFjyySuccess(data);
                    }
                }
            }

            @Override
            public void onFjyyFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onFjyyFailure(e);
                }
            }
        });
    }
}
