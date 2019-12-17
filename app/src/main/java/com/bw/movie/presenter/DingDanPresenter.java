package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.YingPiaoXingQing;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class DingDanPresenter extends BasePresenter<HomeConteract.DingDanContreact.IView> implements HomeConteract.DingDanContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getDingDanPresenter(String userId, String sessionId, String orderId) {
            loginModel.getDingDanDataModel(userId, sessionId, orderId, new HomeConteract.DingDanContreact.IModel.IModelCallback() {
                @Override
                public void onDingDanSuccess(YingPiaoXingQing data) {
                    if (isViewAttached()){
                       if (data!=null && data.getStatus().equals("0000")){
                           getView().onSuccess(data);

                       }
                    }
                }

                @Override
                public void onDingDanFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onFailure(e);
                    }
                }
            });
    }

    @Override
    public void getZFs(String userId, String sessionId, String payType, String orderId) {
        loginModel.getZhiFu(userId, sessionId, payType, orderId, new HomeConteract.DingDanContreact.IModel.IModelZhiFuCallback() {
            @Override
            public void onZFSuccess(ZhiFuBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onZFSuccess(data);

                    }
                }
            }

            @Override
            public void onZFFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onZFFailure(e);
                }
            }
        });
    }
}
