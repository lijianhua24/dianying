package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.GPJLBean;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class DFPresenter extends BasePresenter<HomeConteract.GPJLContreact.IView> implements HomeConteract.GPJLContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getGPJLPresenter(String userId, String sessionId, Integer page, String count, String status) {
        loginModel.getGPJLDataModel(userId, sessionId, page, count, status, new HomeConteract.GPJLContreact.IModel.IModelCallback() {
            @Override
            public void onGPJLSuccess(GPJLBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onGPJLSuccess(data);
                    }
                }
            }

            @Override
            public void onGPJLFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onGPJLFailure(e);
                }
            }
        });
    }

    @Override
    public void getZFs(String userId, String sessionId, String payType, String orderId) {
        loginModel.getZhiFu(userId, sessionId, payType, orderId, new HomeConteract.DingDanContreact.IModel.IModelZhiFuCallback() {
            @Override
            public void onZFSuccess(ZhiFuBean data) {
                if (data!=null && data.getStatus().equals("0000")){
                    getView().onZFSuccess(data);
                }
            }

            @Override
            public void onZFFailure(Throwable e) {
                getView().onZFFailure(e);
            }
        });

    }
}
