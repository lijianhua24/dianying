package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.QXYYGZBean;
import com.bw.movie.bean.YYGZBean;
import com.bw.movie.bean.YingYuanXQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class YingYuanXQPresenter extends BasePresenter<HomeConteract.YingYuanXqContreact.IView> implements HomeConteract.YingYuanXqContreact.IPresenter {


    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getYingYuanXqPresenter( String userId, String sessionId,String movieId) {
        loginModel.getYingYuanXqDataModel( userId,sessionId,movieId, new HomeConteract.YingYuanXqContreact.IModel.IModelCallback() {
            @Override
            public void onYingYuanXqSuccess(YingYuanXQBean data) {
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onYingYuanXqSuccess(data);
                    }
                }
            }

            @Override
            public void onYingYuanXqFailure(Throwable e) {
                getView().onYingYuanXqFailure(e);
            }
        });
    }

    @Override
    public void getYYGZXqPresenter(String userId, String sessionId, String cinemaId) {
            loginModel.getYYGZDataModel(userId, sessionId, cinemaId, new HomeConteract.YingYuanXqContreact.IModel.IModelYYGZCallback() {
                @Override
                public void onYYGZXqSuccess(YYGZBean data) {
                    if (isViewAttached()){
                        if (data!=null && data.getStatus().equals("0000")){
                            getView().onYYGZSuccess(data);
                        }
                    }
                }

                @Override
                public void onYYGZXqFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onYYGZXqFailure(e);
                    }
                }
            });
    }

    @Override
        public void getQXYYGZXqPresenter(String userId, String sessionId, String cinemaId) {
            loginModel.getQXYYGZDataModel(userId, sessionId, cinemaId, new HomeConteract.YingYuanXqContreact.IModel.IModelQXYYGZCallback() {
                @Override
                public void onQXYYGZXqSuccess(QXYYGZBean data) {
                    if (isViewAttached()){
                        if (data!=null && data.getStatus().equals("0000")){
                            getView().onQXYYGZSuccess(data);
                        }
                    }
                }

                @Override
                public void onQXYYGZXqFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onQXYYGZXqFailure(e);
                    }
                }
            });
        }
}
