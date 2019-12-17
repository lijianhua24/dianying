package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.YYGZsBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class YYGZPresenter extends BasePresenter<HomeConteract.YYGZContreact.IView> implements HomeConteract.YYGZContreact.IPresenter {


    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getYYGZPresenter(String userId, String sessionId, Integer page, String count) {
        loginModel.getYYGZDataModel(userId, sessionId, page, count, new HomeConteract.YYGZContreact.IModel.IModelCallback() {
            @Override
            public void onYYGZSuccess(YYGZsBean data) {
                    if (isViewAttached()){
                        if (data!=null && data.getStatus().equals("0000")){
                            getView().onYYGZSuccess(data);
                        }
                    }
            }

            @Override
            public void onYYGZFailure(Throwable e) {
            if (isViewAttached()){
                getView().onYYGZFailure(e);
            }
            }
        });
    }
}
