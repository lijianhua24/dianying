package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.YYPJBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class YYPJPresenter extends BasePresenter<HomeConteract.YingYuanPJContreact.IView> implements HomeConteract.YingYuanPJContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getYingYuanPJPresenter(String cinemaId, Integer page, String count) {
        loginModel.getYingYuanPJDataModel(cinemaId, page, count, new HomeConteract.YingYuanPJContreact.IModel.IModelCallback() {
            @Override
            public void onYingYuanPJSuccess(YYPJBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onYingYuanPJSuccess(data);
                    }
                }
            }

            @Override
            public void onYingYuanPJFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onYingYuanPJFailure(e);
                }
            }
        });
    }
}
