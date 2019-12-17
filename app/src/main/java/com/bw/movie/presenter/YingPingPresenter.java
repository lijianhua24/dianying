package com.bw.movie.presenter;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class YingPingPresenter extends BasePresenter<HomeConteract.PingLunContreact.IView> implements HomeConteract.PingLunContreact.IPresenter {

    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getPingLunPresenter( String movieId, Integer page, String count) {
        loginModel.getPingLunDataModel( movieId, page, count, new HomeConteract.PingLunContreact.IModel.IModelCallback() {
            @Override
            public void onPingLunSuccess(YingPingBean data) {
                if (data!=null){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onPingLunSuccess(data);
                    }
                }
            }

            @Override
            public void onPingLunFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onPingLunFailure(e);
                }
            }
        });
    }
}
