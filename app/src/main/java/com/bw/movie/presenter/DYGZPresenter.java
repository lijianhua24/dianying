package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class DYGZPresenter extends BasePresenter<HomeConteract.DYGZContreact.IView> implements HomeConteract.DYGZContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getDYGZPresenter(String userId, String sessionId, Integer page, String count) {
            loginModel.getDYGZDataModel(userId, sessionId, page, count, new HomeConteract.DYGZContreact.IModel.IModelCallback() {
                @Override
                public void onDYGZSuccess(com.bw.movie.bean.DYGZBean data) {
                    if (isViewAttached()){
                        if (data!=null && data.getStatus().equals("0000")){
                            getView().onDYGZSuccess(data);
                        }
                    }
                }

                @Override
                public void onDYGZFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onDYGZFailure(e);
                    }
                }
            });
    }
}
