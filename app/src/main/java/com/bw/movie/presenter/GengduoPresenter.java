package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.ChaBean;
import com.bw.movie.bean.JjBean;
import com.bw.movie.bean.ReBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class GengduoPresenter extends BasePresenter<HomeConteract.Dianying.IView> implements HomeConteract.Dianying.IPresenter {

    private LoginModel loginModel;
    public static final String TAG="DiYingPresenter";
    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getRePresenter(String page, String count) {
        Log.d(TAG, "getRePresenter: "+page+count);
        loginModel.getReModel(page, count, new HomeConteract.Dianying.IModel.IModelCallback() {
            @Override
            public void onReSuccess(ReBean data) {
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onReSuccess(data);
                    }
                }
            }

            @Override
            public void onReFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onReFailure(e);
                }
            }
        });
    }

    @Override
    public void getChaPresenter(String page, String count) {
        loginModel.getChaModel(page, count, new HomeConteract.Dianying.IModel.IChaModelCallback() {
            @Override
            public void onChaSuccess(ChaBean data) {
                Log.d(TAG, "onChaSuccess: "+data.getMessage());
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onChaSuccess(data);
                    }
                }
            }

            @Override
            public void onChaFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onChaFailure(e);
                }
            }
        });

    }

    @Override
    public void getJjPresenter(String userid,String sendid,String page, String count) {
        loginModel.getJjModel(userid,sendid,page, count, new HomeConteract.Dianying.IModel.IJjModelCallback() {
            @Override
            public void onJjuccess(JjBean data) {
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onJjuccess(data);
                    }
                }
            }

            @Override
            public void onJjFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onChaFailure(e);
                }
            }
        });
    }

    @Override
    public void getBannerPresenter() {
        loginModel.getBannerModel(new HomeConteract.Dianying.IModel.IBannerModelCallback() {
            @Override
            public void onBannerSuccess(BannerBean data) {
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        getView().onBannerSuccess(data);
                    }
                }
            }

            @Override
            public void onBannerFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onBannerFailure(e);
                }
            }
        });
    }

    @Override
    public void getYuYuePresenter(String userId, String sessionId, String movieId) {

    }
}
