package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.QuPiaoBean;
import com.bw.movie.bean.WDDYPBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class WDYPPresenter extends BasePresenter<HomeConteract.WDDYPContreact.IView> implements HomeConteract.WDDYPContreact.IPresenter {
    public static final String TAG="WDYPPresenter";
    private LoginModel loginModel;

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getWDDYPPresenter(String userId, String sessionId) {
        loginModel.getWDDYPDataModel(userId, sessionId, new HomeConteract.WDDYPContreact.IModel.IModelWDDYPCallback() {
            @Override
            public void onWDDYPSuccess(WDDYPBean data) {
                if (isViewAttached()){
                    if (data!=null && data.getStatus().equals("0000")){
                        getView().onWDDYPSuccess(data);
                    }
                }
            }

            @Override
            public void onWDDYPFailure(Throwable e) {
                if (isViewAttached()){
                    getView().onWDDYPFailure(e);
                }
            }
        });
    }

    @Override
    public void getQuPiaoPresenter(String userId, String sessionId, String orderId) {

       loginModel.getQuPiaoDataModel(userId, sessionId, orderId, new HomeConteract.WDDYPContreact.IModel.IModelQuPiaoCallback() {
           @Override
           public void onQuPiaoSuccess(QuPiaoBean data) {
               if (isViewAttached()){
                   if (data!=null && data.getStatus().equals("0000")){
                       Log.d(TAG, "onQuPiaoSuccess: "+data.getMessage());
                       getView().onQuPiaoSuccess(data);
                   }
               }
           }

           @Override
           public void onQuPiaoFailure(Throwable e) {
              if (isViewAttached()){
                  getView().onQuPiaoFailure(e);
              }
           }
       });
    }
}
