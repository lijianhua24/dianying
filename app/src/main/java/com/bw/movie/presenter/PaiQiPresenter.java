package com.bw.movie.presenter;

import android.util.Log;

import com.bw.movie.Base.BasePresenter;
import com.bw.movie.bean.YYPQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.model.LoginModel;

public class PaiQiPresenter extends BasePresenter<HomeConteract.PaiQiContreact.IView> implements HomeConteract.PaiQiContreact.IPresenter {

    private LoginModel loginModel;
    public static final String TAG = "PaiQiPresenter";

    @Override
    protected void initModel() {
        loginModel = new LoginModel();
    }

    @Override
    public void getPaiQiPresenter(String cinemaId, Integer page, String count) {
        Log.d(TAG, "cinemaId: "+cinemaId+page+cinemaId);
        Log.d(TAG, "+page "+page);
        Log.d(TAG, "+cinemaId "+cinemaId);
        loginModel.getPaiQiDataModel(cinemaId, page, count, new HomeConteract.PaiQiContreact.IModel.IModelCallback() {
            @Override
            public void onPaiQiSuccess(YYPQBean data) {
                Log.d(TAG, "messge: "+data.getMessage());
                if (isViewAttached()) {
                    if (data != null && data.getStatus().equals("0000")) {
                        Log.d(TAG, "onPaiQiSuccess: " + data.getMessage());
                        getView().onPaiQiSuccess(data);
                    }
                }
            }

            @Override
            public void onPaiQiFailure(Throwable e) {
                if (isViewAttached()) {
                    getView().onPaiQiFailure(e);
                }
            }
        });
    }
}
