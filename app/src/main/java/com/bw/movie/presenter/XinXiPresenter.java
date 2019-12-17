package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.XinXiBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class XinXiPresenter extends BasePresenter<HomeConteract.XinXiContreact.IView> implements HomeConteract.XinXiContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getXinXiPresenter(String userId, String sessionId, String page, String count) {
            loginModel.getXinXiDataModel(userId, sessionId, page, count, new HomeConteract.XinXiContreact.IModel.IModelXinXiCallback() {
                @Override
                public void onXinXiSuccess(XinXiBean data) {
                    if (isViewAttached()){
                        if (data!=null && data.getStatus().equals("0000")){
                            getView().onXinXiSuccess(data);
                        }
                    }
                }

                @Override
                public void onXinXiFailure(Throwable e) {
                        if (isViewAttached()){
                            getView().onXinXiFailure(e);
                        }
                }
            });
    }
}
