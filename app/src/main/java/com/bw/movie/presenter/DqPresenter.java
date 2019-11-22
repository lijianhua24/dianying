package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.fragment.FindBean;
import com.bw.movie.bean.CinemaBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class DqPresenter extends BasePresenter<HomeConteract.FindContreact.IView> implements HomeConteract.FindContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getFindPresenter() {
        loginModel.getFindDataModel(new HomeConteract.FindContreact.IModel.IModelCallback() {
            @Override
            public void onFindSuccess(FindBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onFindSuccess(data);
                    }
                }
            }

            @Override
            public void onFindFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onFindFailure(e);
                }
            }
        });
    }

    @Override
    public void getLoginPresenter(String regionId) {
        loginModel.getCinemaDataModel(regionId, new HomeConteract.FindContreact.IModel.ICinemaModelCallback() {
            @Override
            public void onCinemaSuccess(CinemaBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onCinemaSuccess(data);
                    }
                }
            }

            @Override
            public void onCinemaFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onFindFailure(e);
                }
            }
        });
    }
}
