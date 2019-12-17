package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.GZDYBean;
import com.bw.movie.bean.QXDYGZBean;
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
        loginModel.getXQSDataModel(userId, sessionId, page, new HomeConteract.XQContreact.IModel.IModelXQSCallback() {
            @Override
            public void onTjyySuccess(XQBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onXQSSuccess(data);
                    }
                }
            }

            @Override
            public void onTjyyFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onXQSFailure(e);
                    }
            }
        });
    }

    @Override
    public void getGZDYPresenter(String userId, String sessionId, String movieId) {
       loginModel.getGZDYDataModel(userId, sessionId, movieId, new HomeConteract.XQContreact.IModel.IModelGZDYCallback() {
           @Override
           public void onGZDYSuccess(GZDYBean data) {
               if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onGZDYSuccess(data);
                    }
               }
           }

           @Override
           public void onGZDYFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onGZDYFailure(e);
                }
           }
       });
    }

    @Override
    public void getQXGZPresenter(String userId, String sessionId, String movieId) {
            loginModel.getQXDYGZDataModel(userId, sessionId, movieId, new HomeConteract.XQContreact.IModel.IModelQXDYGZCallback() {
                @Override
                public void onQXDYGZSuccess(QXDYGZBean data) {
                    if (isViewAttached()){
                        if (data!=null && data.getStatus().equals("0000")){
                            getView().onQXDYGZSuccess(data);
                        }
                    }
                }

                @Override
                public void onQXDYGZFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onQXDYGFailure(e);
                    }
                }
            });
    }
}
