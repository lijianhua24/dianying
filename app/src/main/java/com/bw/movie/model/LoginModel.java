package com.bw.movie.model;

import android.util.Log;

import com.bw.movie.bean.ChaYuYueBean;
import com.bw.movie.bean.DYGZBean;
import com.bw.movie.bean.DYPLBean;
import com.bw.movie.bean.FanKuiBean;
import com.bw.movie.bean.GPJLBean;
import com.bw.movie.bean.GZDYBean;
import com.bw.movie.bean.GjsjcyyBean;
import com.bw.movie.bean.KanGuoBean;
import com.bw.movie.bean.QXDYGZBean;
import com.bw.movie.bean.QXYYGZBean;
import com.bw.movie.bean.QuPiaoBean;
import com.bw.movie.bean.SCTXBean;
import com.bw.movie.bean.ShengRiBean;
import com.bw.movie.bean.SouBean;
import com.bw.movie.bean.TimeBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.bean.WDDYPBean;
import com.bw.movie.bean.WxBean;
import com.bw.movie.bean.XYPBean;
import com.bw.movie.bean.XiaDanBean;
import com.bw.movie.bean.XinXiBean;
import com.bw.movie.bean.XiuGaiBean;
import com.bw.movie.bean.XiuGaiShouJIBean;
import com.bw.movie.bean.YYGZBean;
import com.bw.movie.bean.YYGZsBean;
import com.bw.movie.bean.YYPJBean;
import com.bw.movie.bean.YYPLBean;
import com.bw.movie.bean.YYPQBean;
import com.bw.movie.bean.YingPiaoXingQing;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.bean.YingTingBean;
import com.bw.movie.bean.YingYuanXQBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.bean.ZhiFuBean;
import com.bw.movie.bean.ZuiDiBean;
import com.bw.movie.bean.ZuoBean;
import com.bw.movie.fragment.FindBean;
import com.bw.movie.utils.CommonObserver;
import com.bw.movie.utils.CommonSchedulers;
import com.bw.movie.utils.RequestNet;
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

import okhttp3.MultipartBody;

public class LoginModel implements HomeConteract.LoginContreact.IModel, HomeConteract.RegisterContract.IModel, HomeConteract.Dianying.IModel
        , HomeConteract.FindContreact.IModel, HomeConteract.TjyyContreact.IModel, HomeConteract.PingLunContreact.IModel,
        HomeConteract.FjyyContreact.IModel, HomeConteract.XQContreact.IModel, HomeConteract.YingYuanXqContreact.IModel
        , HomeConteract.YingYuanPJContreact.IModel, HomeConteract.SouContreact.IModel, HomeConteract.GPContreact.IModel,
        HomeConteract.PaiQiContreact.IModel, HomeConteract.ZuoContreact.IModel,HomeConteract.TimeContract.IModel,
        HomeConteract.XYPContract.IModel,HomeConteract.GPJLContreact.IModel,HomeConteract.DYGZContreact.IModel,
        HomeConteract.YYGZContreact.IModel,HomeConteract.ChaYuYueContreact.IModel,HomeConteract.WDDYPContreact.IModel,
        HomeConteract.KanGuoContreact.IModel,HomeConteract.WDPLContreact.IModel,HomeConteract.FanKuiContreact.IModel,
        HomeConteract.XinXiContreact.IModel,HomeConteract.UserContreact.IModel,HomeConteract.XiuGaiContreact.IModel
        ,HomeConteract.DingDanContreact.IModel
{
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
    public void getWXLoginDataModel(String code, IModelWXCallback callback) {
        RequestNet.getInstance().create().getWx(code)

                .compose(CommonSchedulers.<WxBean>io2main())
                .subscribe(new CommonObserver<WxBean>() {
                    @Override
                    public void onNext(WxBean loginBean) {

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
    public void getYuYueModel(String userId, String sessionId, String movieId, IModelYuYueCallback callback) {
        RequestNet.getInstance().create().getYuYue(userId, sessionId,movieId)
                .compose(CommonSchedulers.<YuYueBean>io2main())
                .subscribe(new CommonObserver<YuYueBean>() {
                    @Override
                    public void onNext(YuYueBean emailBean) {
                        callback.onYuYueSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onYuYueFailure(e);
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
    public void getXQDataModel( String page, HomeConteract.XQContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getXiangQ( page)
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
    public void getXQSDataModel(String userId, String sessionId, String pageString, IModelXQSCallback callback) {
        RequestNet.getInstance().create().getXQ(userId,sessionId, pageString)
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
    public void getGZDYDataModel(String userId, String sessionId, String movieId, IModelGZDYCallback callback) {
        RequestNet.getInstance().create().getGZDY(userId, sessionId, movieId)
                .compose(CommonSchedulers.<GZDYBean>io2main())
                .subscribe(new CommonObserver<GZDYBean>() {
                    @Override
                    public void onNext(GZDYBean emailBean) {
                        callback.onGZDYSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGZDYFailure(e);
                    }
                });
    }

    @Override
    public void getQXDYGZDataModel(String userId, String sessionId, String movieId, IModelQXDYGZCallback callback) {
        RequestNet.getInstance().create().getQXDYGZ(userId, sessionId, movieId)
                .compose(CommonSchedulers.<QXDYGZBean>io2main())
                .subscribe(new CommonObserver<QXDYGZBean>() {
                    @Override
                    public void onNext(QXDYGZBean emailBean) {
                        callback.onQXDYGZSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onQXDYGZFailure(e);
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
    public void getPingLunDataModel( String movieId, Integer page, String count, HomeConteract.PingLunContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getYingPing( movieId, page, count)
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
    public void getYingYuanXqDataModel( String userId, String sessionId,String movieId, HomeConteract.YingYuanXqContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getYingYuanXingqing(userId,sessionId, movieId)
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
    public void getYYGZDataModel(String userId, String sessionId, String cinemaId, IModelYYGZCallback callback) {
        RequestNet.getInstance().create().getYYGZ(userId, sessionId, cinemaId)
                .compose(CommonSchedulers.<YYGZBean>io2main())
                .subscribe(new CommonObserver<YYGZBean>() {
                    @Override
                    public void onNext(YYGZBean emailBean) {
                        callback.onYYGZXqSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onYYGZXqFailure(e);
                    }
                });
    }

    @Override
    public void getQXYYGZDataModel(String userId, String sessionId, String cinemaId, IModelQXYYGZCallback callback) {
        RequestNet.getInstance().create().getQXYYGZ(userId, sessionId, cinemaId)
                .compose(CommonSchedulers.<QXYYGZBean>io2main())
                .subscribe(new CommonObserver<QXYYGZBean>() {
                    @Override
                    public void onNext(QXYYGZBean emailBean) {
                        callback.onQXYYGZXqSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onQXYYGZXqFailure(e);
                    }
                });
    }

    @Override
    public void getYingYuanPJDataModel( String cinemaId, Integer page, String count, HomeConteract.YingYuanPJContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getYYPJ( cinemaId, page, count)
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
    public void getXQDataModel(String page, HomeConteract.GPContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getXiangQ( page)
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
    public void getZuiDiDataModel(String movieId, String page, String count, IModelZuidiCallback callback) {
        RequestNet.getInstance().create().getZuiDi(movieId, page, count)
                .compose(CommonSchedulers.<GjsjcyyBean>io2main())
                .subscribe(new CommonObserver<GjsjcyyBean>() {
                    @Override
                    public void onNext(GjsjcyyBean emailBean) {
                        callback.onZuiDiSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onZuiDiFailure(e);
                    }
                });
    }


    @Override
    public void getXQDataModel( String page, HomeConteract.ZuoContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getXiangQ( page)
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
    public void getXDDataModel(String userId, String sessionId, String scheduleId, String seat, String sign, IModelXDCallback callback) {
        RequestNet.getInstance().create().getXiaDan(userId, sessionId, scheduleId,seat,sign)
                .compose(CommonSchedulers.<XiaDanBean>io2main())
                .subscribe(new CommonObserver<XiaDanBean>() {
                    @Override
                    public void onNext(XiaDanBean emailBean) {
                        callback.onXDSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onXDFailure(e);
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
    public void getZFDataModel(String userId, String sessionId, String payType, String orderId, IModelZFCallback callback) {
        RequestNet.getInstance().create().getZhiFu(userId,sessionId,payType,orderId)
                .compose(CommonSchedulers.<ZhiFuBean>io2main())
                .subscribe(new CommonObserver<ZhiFuBean>() {
                    @Override
                    public void onNext(ZhiFuBean emailBean) {
                        callback.onZFSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onZFFailure(e);
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

    @Override
    public void getGPJLDataModel(String userId, String sessionId, Integer page, String count, String status, HomeConteract.GPJLContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getGPJL(userId,sessionId,page,count,status)
                .compose(CommonSchedulers.<GPJLBean>io2main())
                .subscribe(new CommonObserver<GPJLBean>() {
                    @Override
                    public void onNext(GPJLBean emailBean) {
                        callback.onGPJLSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onGPJLFailure(e);
                    }
                });
    }

    @Override
    public void getZFDataModels(String userId, String sessionId, String payType, String orderId, IModelZFsCallback callback) {
        RequestNet.getInstance().create().getZhiFu(userId,sessionId,payType,orderId)
                .compose(CommonSchedulers.<ZhiFuBean>io2main())
                .subscribe(new CommonObserver<ZhiFuBean>() {
                    @Override
                    public void onNext(ZhiFuBean emailBean) {
                        callback.onZFSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onZFFailure(e);
                    }
                });
    }

    @Override
    public void getDYGZDataModel(String userId, String sessionId, Integer page, String count, HomeConteract.DYGZContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getDYGZ(userId,sessionId,page,count)
                .compose(CommonSchedulers.<DYGZBean>io2main())
                .subscribe(new CommonObserver<DYGZBean>() {
                    @Override
                    public void onNext(DYGZBean emailBean) {
                        callback.onDYGZSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDYGZFailure(e);
                    }
                });
    }

    @Override
    public void getYYGZDataModel(String userId, String sessionId, Integer page, String count, HomeConteract.YYGZContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getYYGZ(userId,sessionId,page,count)
                .compose(CommonSchedulers.<YYGZsBean>io2main())
                .subscribe(new CommonObserver<YYGZsBean>() {
                    @Override
                    public void onNext(YYGZsBean emailBean) {
                        callback.onYYGZSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onYYGZFailure(e);
                    }
                });
    }

    @Override
    public void getChaYuYueDataModel(String userId, String sessionId, IModelChaYuYueCallback callback) {
        RequestNet.getInstance().create().getChaYuYue(userId,sessionId)
                .compose(CommonSchedulers.<ChaYuYueBean>io2main())
                .subscribe(new CommonObserver<ChaYuYueBean>() {
                    @Override
                    public void onNext(ChaYuYueBean emailBean) {
                        callback.onChaYuYueSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onChaYuYueFailure(e);
                    }
                });
    }

    @Override
    public void getWDDYPDataModel(String userId, String sessionId, IModelWDDYPCallback callback) {
        RequestNet.getInstance().create().getwddyp(userId,sessionId)
                .compose(CommonSchedulers.<WDDYPBean>io2main())
                .subscribe(new CommonObserver<WDDYPBean>() {
                    @Override
                    public void onNext(WDDYPBean emailBean) {
                        callback.onWDDYPSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onWDDYPFailure(e);
                    }
                });
    }

    @Override
    public void getQuPiaoDataModel(String userId, String sessionId, String orderId, IModelQuPiaoCallback callback) {
        RequestNet.getInstance().create().getQuPiao(userId,sessionId,orderId)
                .compose(CommonSchedulers.<QuPiaoBean>io2main())
                .subscribe(new CommonObserver<QuPiaoBean>() {
                    @Override
                    public void onNext(QuPiaoBean emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onQuPiaoSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onQuPiaoFailure(e);
                    }
                });
    }

    @Override
    public void getKanGuoDataModel(String userId, String sessionId, IModelKanGuoCallback callback) {
        RequestNet.getInstance().create().getKanGuo(userId,sessionId)
                .compose(CommonSchedulers.<KanGuoBean>io2main())
                .subscribe(new CommonObserver<KanGuoBean>() {
                    @Override
                    public void onNext(KanGuoBean emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onKanGuoSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onKanGuoFailure(e);
                    }
                });
    }

    @Override
    public void getWDDYPLDataModel(String userId, String sessionId, String page, String count, IModelWDDYPLCallback callback) {
        RequestNet.getInstance().create().getDYPL(userId,sessionId,page,count)
                .compose(CommonSchedulers.<DYPLBean>io2main())
                .subscribe(new CommonObserver<DYPLBean>() {
                    @Override
                    public void onNext(DYPLBean emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onWDDYPLSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onWDDYPLFailure(e);
                    }
                });
    }

    @Override
    public void getWDYYPLDataModel(String userId, String sessionId, String longitude, String latitude, String page, String count, IModelWYDYPLCallback callback) {
        RequestNet.getInstance().create().getYYPL(userId,sessionId,longitude,latitude,page,count)
                .compose(CommonSchedulers.<YYPLBean>io2main())
                .subscribe(new CommonObserver<YYPLBean>() {
                    @Override
                    public void onNext(YYPLBean emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onWDYYPLSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onWDYYPLFailure(e);
                    }
                });
    }

    @Override
    public void getFanKuiDataModel(String userId, String sessionId, String content, IModelFanKuiCallback callback) {
        RequestNet.getInstance().create().getFanKui(userId,sessionId,content)
                .compose(CommonSchedulers.<FanKuiBean>io2main())
                .subscribe(new CommonObserver<FanKuiBean>() {
                    @Override
                    public void onNext(FanKuiBean emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onFanKuiSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onFanKuiFailure(e);
                    }
                });
    }

    @Override
    public void getXinXiDataModel(String userId, String sessionId, String page, String count, IModelXinXiCallback callback) {
        RequestNet.getInstance().create().getXinXi(userId,sessionId,page,count)
                .compose(CommonSchedulers.<XinXiBean>io2main())
                .subscribe(new CommonObserver<XinXiBean>() {
                    @Override
                    public void onNext(XinXiBean emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onXinXiSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onXinXiFailure(e);
                    }
                });
    }

    @Override
    public void getUserDataModel(String userId, String sessionId, IModelUserCallback callback) {
        RequestNet.getInstance().create().getUser(userId,sessionId)
                .compose(CommonSchedulers.<UserBean>io2main())
                .subscribe(new CommonObserver<UserBean>() {
                    @Override
                    public void onNext(UserBean emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onUserSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onUserFailure(e);
                    }
                });
    }

    @Override
    public void getSCTXDataModel(String userId, String sessionId, MultipartBody.Part map, IModelSCTXCallback callback) {
        RequestNet.getInstance().create().getSCTX(userId,sessionId,map)
                .compose(CommonSchedulers.<SCTXBean>io2main())
                .subscribe(new CommonObserver<SCTXBean>() {
                    @Override
                    public void onNext(SCTXBean emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onSCTXSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onSCTXFailure(e);
                    }
                });
    }

    @Override
    public void getXGSJHDataModel(String userId, String sessionId, String phone, IModelXGSJHCallback callback) {
        RequestNet.getInstance().create().getXiuGaiShouJi(userId,sessionId,phone)
                .compose(CommonSchedulers.<XiuGaiShouJIBean>io2main())
                .subscribe(new CommonObserver<XiuGaiShouJIBean>() {
                    @Override
                    public void onNext(XiuGaiShouJIBean emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onXGSJHSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onXGSJHFailure(e);
                    }
                });
    }

    @Override
    public void getXGSRDataModel(String userId, String sessionId,String birthday, IModelXGSRCallback callback) {
        RequestNet.getInstance().create().getShengRi(userId,sessionId,birthday)
                .compose(CommonSchedulers.<ShengRiBean>io2main())
                .subscribe(new CommonObserver<ShengRiBean>() {
                    @Override
                    public void onNext(ShengRiBean emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onXGSRSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onXGSRFailure(e);
                    }
                });
    }


    @Override
    public void getXiuGaiDataModel(String userId, String sessionId, String oldPwd, String newPwd, String newPwd2, IModelXiuGaiCallback callback) {
        RequestNet.getInstance().create().getXiuGai(userId,sessionId,oldPwd,newPwd,newPwd2)
                .compose(CommonSchedulers.<XiuGaiBean>io2main())
                .subscribe(new CommonObserver<XiuGaiBean>() {
                    @Override
                    public void onNext(XiuGaiBean emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onXiuGaiSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onXiuGaiFailure(e);
                    }
                });
    }

    @Override
    public void getDingDanDataModel(String userId, String sessionId, String orderId, HomeConteract.DingDanContreact.IModel.IModelCallback callback) {
        RequestNet.getInstance().create().getDingDan(userId,sessionId,orderId)
                .compose(CommonSchedulers.<YingPiaoXingQing>io2main())
                .subscribe(new CommonObserver<YingPiaoXingQing>() {
                    @Override
                    public void onNext(YingPiaoXingQing emailBean) {
                        Log.d(TAG, "emailBean: "+emailBean.getMessage());
                        callback.onDingDanSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDingDanFailure(e);
                    }
                });
    }

    @Override
    public void getZhiFu(String userId, String sessionId, String payType, String orderId, IModelZhiFuCallback callback) {
        RequestNet.getInstance().create().getZhiFu(userId,sessionId,payType,orderId)
                .compose(CommonSchedulers.<ZhiFuBean>io2main())
                .subscribe(new CommonObserver<ZhiFuBean>() {
                    @Override
                    public void onNext(ZhiFuBean emailBean) {
                        callback.onZFSuccess(emailBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onZFFailure(e);
                    }
                });
    }
}



