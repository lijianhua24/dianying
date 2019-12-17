package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.XQBean;
import com.bw.movie.bean.XiaDanBean;
import com.bw.movie.bean.YingTingBean;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.bean.ZuoBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class ZuoPresenter extends BasePresenter<HomeConteract.ZuoContreact.IView> implements HomeConteract.ZuoContreact.IPresenter {

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
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onXQSuccess(data);
                    }
                }
            }

            @Override
            public void onTjyyFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onXQFailure(e);
                }
            }
        });
    }

    @Override
    public void getYingTingPresenter(String movieId, String cinemaId) {
        loginModel.getYingTingDataModel(movieId, cinemaId, new HomeConteract.ZuoContreact.IModel.IModelYingTingCallback() {
            @Override
            public void onTjyySuccess(YingTingBean data) {
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onYingTingSuccess(data);
                    }
                }
            }

            @Override
            public void onTjyyFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onYingTingFailure(e);
                }
            }
        });
    }

    @Override
    public void getZuo(String hallId) {
        loginModel.getZuoDataModel(hallId, new HomeConteract.ZuoContreact.IModel.IModelZuoCallback() {
            @Override
            public void onZuoSuccess(ZuoBean data) {
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onZuoSuccess(data);
                    }
                }
            }

            @Override
            public void onZuoFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onZuoFailure(e);
                }
            }
        });
    }

    @Override
    public void getXD(String userId, String sessionId, String scheduleId, String seat, String sign) {
        loginModel.getXDDataModel(userId, sessionId, scheduleId, seat, sign, new HomeConteract.ZuoContreact.IModel.IModelXDCallback() {
            @Override
            public void onXDSuccess(XiaDanBean data) {
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onXDSuccess(data);
                    }
                }
            }

            @Override
            public void onXDFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onXDFailure(e);
                }
            }
        });
    }

    @Override
    public void getZF(String userId, String sessionId, String payType, String orderId) {
        loginModel.getZFDataModel(userId, sessionId, payType, orderId, new HomeConteract.ZuoContreact.IModel.IModelZFCallback() {
            @Override
            public void onZFSuccess(ZhiFuBean data) {
                if (isViewAttached()){
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onZFSuccess(data);
                    }
                }
            }

            @Override
            public void onZFFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onZFFailure(e);
                }
            }
        });
    }
}
