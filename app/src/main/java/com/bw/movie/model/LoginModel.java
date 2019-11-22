package com.bw.movie.model;

import android.util.Log;

import com.bw.movie.bean.GjsjcyyBean;
import com.bw.movie.bean.SouBean;
import com.bw.movie.bean.TimeBean;
import com.bw.movie.bean.XYPBean;
import com.bw.movie.bean.YYPJBean;
import com.bw.movie.bean.YYPQBean;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.bean.YingTingBean;
import com.bw.movie.bean.YingYuanXQBean;
import com.bw.movie.bean.ZuoBean;
import com.bw.movie.fragment.FindBean;
import com.bw.movie.Utils.CommonObserver;
import com.bw.movie.Utils.CommonSchedulers;
import com.bw.movie.Utils.RequestNet;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.ChaBean;
import com.bw.movie.bean.CinemaBean;
import com.bw.movie.bean.EmailBean;
import com.bw.movie.bean.FjYyBean;
import com.bw.movie.bean.JjBean;
import com.bw.movie.bean.LoginBean;
import com.bw.movie.bean.ReBean;
import com.bw.movie.bean.RegisterBean;
import com.bw.movie.bean.TjyyBean;
import com.bw.movie.bean.XQBean;
import com.bw.movie.contract.HomeConteract;

public class LoginModel implements HomeConteract.LoginContreact.IModel, HomeConteract.RegisterContract.IModel, HomeConteract.Dianying.IModel
        , HomeConteract.FindContreact.IModel, HomeConteract.TjyyContreact.IModel, HomeConteract.PingLunContreact.IModel,
        HomeConteract.FjyyContreact.IModel, HomeConteract.XQContreact.IModel, HomeConteract.YingYuanXqContreact.IModel
        , HomeConteract.YingYuanPJContreact.IModel, HomeConteract.SouContreact.IModel, HomeConteract.GPContreact.IModel, HomeConteract.PaiQiContreact.IModel
        , HomeConteract.ZuoContreact.IModel,HomeConteract.TimeContract.IModel,HomeConteract.XYPContract.IModel {
    public static final String TAG = "LoginModel";

    //
    @Override
    public void getLoginDataModel(String email, String pwd, HomeConteract.LoginContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getLogin(email, pwd)

                .compose(CommonSchedulers.<LoginBean>io2main())
                .subscribe(new CommonObserver<LoginBean>() {
                    @Override
                    public void onNext(LoginBean loginBean) {
                        String message = loginBean.getMessage();
                        Log.i("123123", message);
                        callback.onSuccess(loginBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getRegisterModel(String nickName, String pwd, String email, String code, HomeConteract.RegisterContract.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getRegister(nickName, pwd, email, code)
                .compose(CommonSchedulers.<RegisterBean>io2main())
                .subscribe(new CommonObserver<RegisterBean>() {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        callback.onSuccess(registerBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFailure(e);
                    }
                });
    }

    @Override
    public void getEmailModel(String email, IEmailModelCallback iEmailModelCallback) {
        RequestNet.getInstance().create().getEmail(email)
                .compose(CommonSchedulers.<EmailBean>io2main())
                .subscribe(new CommonObserver<EmailBean>() {
                    @Override
                    public void onNext(EmailBean emailBean) {
                        iEmailModelCallback.onSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iEmailModelCallback.onFailure(e);
                    }
                });
    }

    @Override
    public void getReModel(String page, String count, HomeConteract.Dianying.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getRe(page, count)
                .compose(CommonSchedulers.<ReBean>io2main())
                .subscribe(new CommonObserver<ReBean>() {
                    @Override
                    public void onNext(ReBean emailBean) {
                        callback.onReSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onReFailure(e);
                    }
                });
    }

    @Override
    public void getChaModel(String page, String count, IChaModelCallback iEmailModelCallback) {
        Log.d(TAG, "getChaModel: " + page + count);
        RequestNet.getInstance().create().getCha(page, count)
                .compose(CommonSchedulers.<ChaBean>io2main())
                .subscribe(new CommonObserver<ChaBean>() {
                    @Override
                    public void onNext(ChaBean emailBean) {
                        Log.d(TAG, "onNext: " + emailBean.getMessage());
                        iEmailModelCallback.onChaSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        iEmailModelCallback.onChaFailure(e);
                    }
                });
    }

    @Override
    public void getJjModel(String userid, String sendid, String page, String count, IJjModelCallback JjModelCallback) {
        RequestNet.getInstance().create().getJj(userid, sendid, page, count)
                .compose(CommonSchedulers.<JjBean>io2main())
                .subscribe(new CommonObserver<JjBean>() {
                    @Override
                    public void onNext(JjBean emailBean) {
                        JjModelCallback.onJjuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        JjModelCallback.onJjFailure(e);
                    }
                });
    }

    @Override
    public void getBannerModel(IBannerModelCallback bannerModelCallback) {
        RequestNet.getInstance().create().getBanner()
                .compose(CommonSchedulers.<BannerBean>io2main())
                .subscribe(new CommonObserver<BannerBean>() {
                    @Override
                    public void onNext(BannerBean emailBean) {
                        bannerModelCallback.onBannerSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        bannerModelCallback.onBannerFailure(e);
                    }
                });
    }


    @Override
    public void getFindDataModel(HomeConteract.FindContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getfind()
                .compose(CommonSchedulers.<FindBean>io2main())
                .subscribe(new CommonObserver<FindBean>() {
                    @Override
                    public void onNext(FindBean emailBean) {
                        callback.onFindSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFindFailure(e);
                    }
                });
    }

    @Override
    public void getCinemaDataModel(String regionId, ICinemaModelCallback callback) {
        RequestNet.getInstance().create().getcinema(regionId)
                .compose(CommonSchedulers.<CinemaBean>io2main())
                .subscribe(new CommonObserver<CinemaBean>() {
                    @Override
                    public void onNext(CinemaBean emailBean) {
                        callback.onCinemaSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onCinemaFailure(e);
                    }
                });
    }

    @Override
    public void getTjyyDataModel(String userId, String sessionId, Integer page, String count, HomeConteract.TjyyContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getTjyy(userId, sessionId, page, count)
                .compose(CommonSchedulers.<TjyyBean>io2main())
                .subscribe(new CommonObserver<TjyyBean>() {
                    @Override
                    public void onNext(TjyyBean emailBean) {
                        callback.onTjyySuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onTjyyFailure(e);
                    }
                });
    }

    @Override
    public void getXQDataModel(String userId, String sessionId, String page, HomeConteract.XQContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getXiangQ(userId, sessionId, page)
                .compose(CommonSchedulers.<XQBean>io2main())
                .subscribe(new CommonObserver<XQBean>() {
                    @Override
                    public void onNext(XQBean emailBean) {
                        callback.onTjyySuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onTjyyFailure(e);
                    }
                });
    }

    @Override
    public void getFjyyDataModel(String userId, String sessionId, String longitude, String latitude, Integer page, String count, HomeConteract.FjyyContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getFjyy(userId, sessionId, longitude, latitude, page, count)
                .compose(CommonSchedulers.<FjYyBean>io2main())
                .subscribe(new CommonObserver<FjYyBean>() {
                    @Override
                    public void onNext(FjYyBean emailBean) {
                        callback.onFjyySuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFjyyFailure(e);
                    }
                });
    }


    @Override
    public void getPingLunDataModel(String userId, String sessionId, String movieId, Integer page, String count, HomeConteract.PingLunContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getYingPing(userId, sessionId, movieId, page, count)
                .compose(CommonSchedulers.<YingPingBean>io2main())
                .subscribe(new CommonObserver<YingPingBean>() {
                    @Override
                    public void onNext(YingPingBean emailBean) {
                        callback.onPingLunSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onPingLunFailure(e);
                    }
                });
    }

    @Override
    public void getYingYuanXqDataModel(String userId, String sessionId, String movieId, HomeConteract.YingYuanXqContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getYingYuanXingqing(userId, sessionId, movieId)
                .compose(CommonSchedulers.<YingYuanXQBean>io2main())
                .subscribe(new CommonObserver<YingYuanXQBean>() {
                    @Override
                    public void onNext(YingYuanXQBean emailBean) {
                        callback.onYingYuanXqSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onYingYuanXqFailure(e);
                    }
                });
    }

    @Override
    public void getYingYuanPJDataModel(String userId, String sessionId, String cinemaId, Integer page, String count, HomeConteract.YingYuanPJContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getYYPJ(userId, sessionId, cinemaId, page, count)
                .compose(CommonSchedulers.<YYPJBean>io2main())
                .subscribe(new CommonObserver<YYPJBean>() {
                    @Override
                    public void onNext(YYPJBean emailBean) {

                        callback.onYingYuanPJSuccess(emailBean);

                    }

                    @Override
                    public void onError(Throwable e) {

                        callback.onYingYuanPJFailure(e);

                    }
                });
    }

    @Override
    public void getSouDataModel(String keyword, String page, String count, HomeConteract.SouContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getSou(keyword, page, count)
                .compose(CommonSchedulers.<SouBean>io2main())
                .subscribe(new CommonObserver<SouBean>() {
                    @Override
                    public void onNext(SouBean emailBean) {

                        callback.onSouSuccess(emailBean);

                    }

                    @Override
                    public void onError(Throwable e) {

                        callback.onSouFailure(e);

                    }
                });
    }

    @Override
    public void getXQDataModel(String userId, String sessionId, String page, HomeConteract.GPContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getXiangQ(userId, sessionId, page)
                .compose(CommonSchedulers.<XQBean>io2main())
                .subscribe(new CommonObserver<XQBean>() {
                    @Override
                    public void onNext(XQBean emailBean) {
                        callback.onTjyySuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onTjyyFailure(e);
                    }
                });
    }

    @Override
    public void getTimeDataModel(IModelCallbacks callback) {
        RequestNet.getInstance().create().getTime()
                .compose(CommonSchedulers.<TimeBean>io2main())
                .subscribe(new CommonObserver<TimeBean>() {
                    @Override
                    public void onNext(TimeBean emailBean) {
                        callback.onTimeSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onTimeFailure(e);
                    }
                });
    }

    @Override
    public void getGjsjcyyDataModel(String movieId, String date, String page, String count, IModelGjsjcyyCallbacks callback) {
        RequestNet.getInstance().create().getGjsjcyy(movieId, date, page, count)
                .compose(CommonSchedulers.<GjsjcyyBean>io2main())
                .subscribe(new CommonObserver<GjsjcyyBean>() {
                    @Override
                    public void onNext(GjsjcyyBean emailBean) {
                        callback.onGjsjcyySuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGjsjcyyFailure(e);
                    }
                });
    }

    @Override
    public void getDiQuDataModel(IModelDiQuCallback callback) {
        RequestNet.getInstance().create().getfind()
                .compose(CommonSchedulers.<FindBean>io2main())
                .subscribe(new CommonObserver<FindBean>() {
                    @Override
                    public void onNext(FindBean emailBean) {
                        callback.onDiQuSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDiQuFailure(e);
                    }
                });
    }

    @Override
    public void getDQYYDataModel(String movieId, String regionId, Integer page, String count, IModelDiDqyyCallback callback) {
        RequestNet.getInstance().create().getDQYYS(movieId, regionId, page, count)
                .compose(CommonSchedulers.<GjsjcyyBean>io2main())
                .subscribe(new CommonObserver<GjsjcyyBean>() {
                    @Override
                    public void onNext(GjsjcyyBean emailBean) {
                        callback.onDQYYSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDQYYFailure(e);
                    }
                });
    }


    @Override
    public void getXQDataModel(String userId, String sessionId, String page, HomeConteract.ZuoContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getXiangQ(userId, sessionId, page)
                .compose(CommonSchedulers.<XQBean>io2main())
                .subscribe(new CommonObserver<XQBean>() {
                    @Override
                    public void onNext(XQBean emailBean) {
                        callback.onTjyySuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onTjyyFailure(e);
                    }
                });
    }

    @Override
    public void getYingTingDataModel(String movieId, String cinemaId, IModelYingTingCallback callback) {
        RequestNet.getInstance().create().getYingTing(movieId, cinemaId)
                .compose(CommonSchedulers.<YingTingBean>io2main())
                .subscribe(new CommonObserver<YingTingBean>() {
                    @Override
                    public void onNext(YingTingBean emailBean) {
                        callback.onTjyySuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onTjyyFailure(e);
                    }
                });
    }

    @Override
    public void getZuoDataModel(String hallId, IModelZuoCallback callback) {
        RequestNet.getInstance().create().getZuo(hallId)
                .compose(CommonSchedulers.<ZuoBean>io2main())
                .subscribe(new CommonObserver<ZuoBean>() {
                    @Override
                    public void onNext(ZuoBean emailBean) {
                        callback.onZuoSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onZuoFailure(e);
                    }
                });
    }


    @Override
    public void getPaiQiDataModel(String cinemaId, Integer page, String count, HomeConteract.PaiQiContreact.IModel.IModelCallback callback) {
        Log.d(TAG, "getPaiQiDataModel: "+cinemaId+page+count);
        RequestNet.getInstance().create().getYyQq(cinemaId, page, count)
                .compose(CommonSchedulers.<YYPQBean>io2main())
                .subscribe(new CommonObserver<YYPQBean>() {
                    @Override
                    public void onNext(YYPQBean emailBean) {
                        Log.d(TAG, "yyqq: "+emailBean.getMessage());
                        callback.onPaiQiSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onPaiQiFailure(e);
                    }
                });
    }

    @Override
    public void getTimeSDataModel(IModelCallbacksS callback) {
        RequestNet.getInstance().create().getTime()
                .compose(CommonSchedulers.<TimeBean>io2main())
                .subscribe(new CommonObserver<TimeBean>() {
                    @Override
                    public void onNext(TimeBean emailBean) {
                        Log.d(TAG, "yyqq: "+emailBean.getMessage());
                        callback.onTimeSSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onTimeFailure(e);
                    }
                });
    }


    @Override
    public void getXYPDataModel(String userId, String sessionId, String movieId, String commentContent, String score, IModelXYPCallbacksS callback) {
        RequestNet.getInstance().create().getXYP(userId,sessionId,movieId,commentContent,score)
                .compose(CommonSchedulers.<XYPBean>io2main())
                .subscribe(new CommonObserver<XYPBean>() {
                    @Override
                    public void onNext(XYPBean emailBean) {
                        Log.d(TAG, "yyqq: "+emailBean.getMessage());
                        callback.onXYPSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onXYPFailure(e);
                    }
                });
    }
}



