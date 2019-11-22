package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.TimeBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class TimePresenter extends BasePresenter<HomeConteract.TimeContract.IView> implements HomeConteract.TimeContract.IPresenter{

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getTimeS() {
        loginModel.getTimeSDataModel(new HomeConteract.TimeContract.IModel.IModelCallbacksS() {
            @Override
            public void onTimeSSuccess(TimeBean data) {
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
}
