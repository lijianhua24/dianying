package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.TjyyBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class Tjyypresenter extends BasePresenter<HomeConteract.TjyyContreact.IView> implements HomeConteract.TjyyContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getTjyyPresenter(String userId, String sessionId, Integer page, String count) {
        loginModel.getTjyyDataModel(userId, sessionId, page, count, new HomeConteract.TjyyContreact.IModel.IModelCallback() {
            @Override
            public void onTjyySuccess(TjyyBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onTjyySuccess(data);
                    }
                }
            }

            @Override
            public void onTjyyFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onTjyyFailure(e);
                }
            }
        });
    }
}
