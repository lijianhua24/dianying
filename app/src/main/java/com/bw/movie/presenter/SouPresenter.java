package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.SouBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class SouPresenter extends BasePresenter<HomeConteract.SouContreact.IView> implements HomeConteract.SouContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getSouPresenter(String keyword, String page, String count) {
        loginModel.getSouDataModel(keyword, page, count, new HomeConteract.SouContreact.IModel.IModelCallback() {
            @Override
            public void onSouSuccess(SouBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onSouSuccess(data);
                    }
                }
            }

            @Override
            public void onSouFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onSouFailure(e);
                }
            }
        });
    }
}
