package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.KanGuoBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class KanGuoPresenter extends BasePresenter<HomeConteract.KanGuoContreact.IView> implements HomeConteract.KanGuoContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getKanGuoPresenter(String userId, String sessionId) {
        loginModel.getKanGuoDataModel(userId, sessionId, new HomeConteract.KanGuoContreact.IModel.IModelKanGuoCallback() {
            @Override
            public void onKanGuoSuccess(KanGuoBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onKanGuoSuccess(data);
                    }
                }
            }

            @Override
            public void onKanGuoFailure(Throwable e) {
                    if (isViewAttached()){
                        getView().onKanGuoFailure(e);
                    }
            }
        });
    }
}
