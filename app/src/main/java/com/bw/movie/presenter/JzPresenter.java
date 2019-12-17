package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.XQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class JzPresenter extends BasePresenter<HomeConteract.XQContreact.IView> implements HomeConteract.XQContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getXQPresenter( String page) {
            loginModel.getXQDataModel( page, new HomeConteract.XQContreact.IModel.IModelCallback() {
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

    @Override
    public void getXQSPresenter(String userId, String sessionId, String page) {

    }

    @Override
    public void getGZDYPresenter(String userId, String sessionId, String movieId) {

    }

    @Override
    public void getQXGZPresenter(String userId, String sessionId, String movieId) {

    }
}
