package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.GjsjcyyBean;
import com.bw.movie.bean.TimeBean;
import com.bw.movie.bean.XQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.fragment.FindBean;
import com.bw.movie.model.LoginModel;

import org.greenrobot.greendao.annotation.Id;

public class GouPiaoPresenter extends BasePresenter<HomeConteract.GPContreact.IView> implements HomeConteract.GPContreact.IPresenter {


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

    @Override
    public void getTime() {
        loginModel.getTimeDataModel(new HomeConteract.GPContreact.IModel.IModelCallbacks() {
            @Override
            public void onTimeSuccess(TimeBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                    getView().onTimeSuccess(data);
                    }
                }
            }

            @Override
            public void onTimeFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onTimeFailure(e);
                }
            }
        });
    }

    @Override
    public void getGjsjcyy(String movieId, String date, String page, String count) {
        loginModel.getGjsjcyyDataModel(movieId,date, page, count, new HomeConteract.GPContreact.IModel.IModelGjsjcyyCallbacks() {
            @Override
            public void onGjsjcyySuccess(GjsjcyyBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onGjsjcyySuccess(data);
                    }
                }
            }

            @Override
            public void onGjsjcyyFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onGjsjcyyFailure(e);
                }
            }
        });
    }

    @Override
    public void getFindPresenter() {
        loginModel.getFindDataModel(new HomeConteract.FindContreact.IModel.IModelCallback() {
            @Override
            public void onFindSuccess(FindBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onDiQuSuccess(data);
                    }
                }
            }

            @Override
            public void onFindFailure(Throwable e) {
                if (isViewAttached()){
                   getView().onDiQuyFailure(e);
                }
            }
        });
    }

    @Override
    public void getDQYYPresenter(String movieId, String regionId, Integer page, String count) {
        loginModel.getDQYYDataModel(movieId, regionId, page, count, new HomeConteract.GPContreact.IModel.IModelDiDqyyCallback() {
            @Override
            public void onDQYYSuccess(GjsjcyyBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onDQYYSuccess(data);
                    }
                }
            }

            @Override
            public void onDQYYFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onDQYYFailure(e);
                    }
            }
        });
    }


}
