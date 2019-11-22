package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.XYPBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class XYPPresenter extends BasePresenter<HomeConteract.XYPContract.IView> implements HomeConteract.XYPContract.IPresenter  {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getXYP(String userId, String sessionId, String movieId, String commentContent, String score) {
        loginModel.getXYPDataModel(userId, sessionId, movieId, commentContent, score, new HomeConteract.XYPContract.IModel.IModelXYPCallbacksS() {
            @Override
            public void onXYPSuccess(XYPBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onXYPSuccess(data);
                    }
                }
            }

            @Override
            public void onXYPFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onXYPFailure(e);
                }
            }
        });
    }
}
