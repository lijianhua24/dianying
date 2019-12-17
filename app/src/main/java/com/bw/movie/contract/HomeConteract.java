package com.bw.movie.contract;

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
import com.bw.movie.Base.IBaseView;
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
import com.bw.movie.fragment.yingyuanxiangqing.YYPJFragment;

import okhttp3.MultipartBody;

public interface HomeConteract {
    //登录
    interface LoginContreact {
        interface IModel {
            //登录
            void getLoginDataModel(String email, String pwd, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onSuccess(LoginBean data);

                void onFailure(Throwable e);
            }

            void getWXLoginDataModel(String code, IModelWXCallback callback);

            //model层中的接口回调
            interface IModelWXCallback {
                void onSuccess(WxBean data);

                void onFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onSuccess(LoginBean data);

            void onFailure(Throwable e);

            void onWXSuccess(WxBean data);

            void onWXFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getLoginPresenter(String email, String pwd);
            void getWXPresenter(String code);
        }
    }

    //注册
    interface RegisterContract {
        //model层   命名必须是IModel
        interface IModel {
            //注册
            void getRegisterModel(String nickName, String pwd, String email, String code, IModelCallback callback);

            //登录
            //model层中的接口回调
            interface IModelCallback {
                void onSuccess(RegisterBean data);

                void onFailure(Throwable e);
            }

            void getEmailModel(String email, IEmailModelCallback iEmailModelCallback);

            //model层中的接口回调
            interface IEmailModelCallback {
                void onSuccess(EmailBean data);

                void onFailure(Throwable e);
            }


        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onSuccess(RegisterBean data);

            void onFailure(Throwable e);

            void onEmailSuccess(EmailBean data);

            void onEmailFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {
            //注册
            void getRegisterPresenter(String nickName, String pwd, String email, String code);

            void getEmailPresenter(String email);

        }


    }

    interface Dianying {
        interface IView extends IBaseView {
            void onReSuccess(ReBean data);

            void onReFailure(Throwable e);

            void onChaSuccess(ChaBean data);

            void onChaFailure(Throwable e);

            void onJjuccess(JjBean data);

            void onJjFailure(Throwable e);

            void onBannerSuccess(BannerBean data);

            void onBannerFailure(Throwable e);

            void onYuYueSuccess(YuYueBean data);

            void onYuYueFailure(Throwable e);
        }

        //Model
        interface IModel {
            //注册
            void getReModel(String page, String count, IModelCallback callback);

            //登录
            //model层中的接口回调
            interface IModelCallback {
                void onReSuccess(ReBean data);

                void onReFailure(Throwable e);
            }

            void getYuYueModel(String userId, String sessionId, String movieId, IModelYuYueCallback callback);

            //登录
            //model层中的接口回调
            interface IModelYuYueCallback {
                void onYuYueSuccess(YuYueBean data);

                void onYuYueFailure(Throwable e);
            }

            void getChaModel(String page, String count, IChaModelCallback iEmailModelCallback);

            //model层中的接口回调
            interface IChaModelCallback {
                void onChaSuccess(ChaBean data);

                void onChaFailure(Throwable e);
            }

            void getJjModel(String userid, String sendid, String page, String count, IJjModelCallback JjModelCallback);

            //model层中的接口回调
            interface IJjModelCallback {
                void onJjuccess(JjBean data);

                void onJjFailure(Throwable e);
            }

            void getBannerModel(IBannerModelCallback bannerModelCallback);

            //model层中的接口回调
            interface IBannerModelCallback {
                void onBannerSuccess(BannerBean data);

                void onBannerFailure(Throwable e);
            }

        }

        interface IPresenter {
            //注册
            void getRePresenter(String page, String count);

            void getChaPresenter(String page, String count);

            void getJjPresenter(String userid, String sendid, String page, String count);

            void getBannerPresenter();

            void getYuYuePresenter(String userId, String sessionId, String movieId);


        }


    }

    //查询地区
    interface FindContreact {
        interface IModel {
            void getFindDataModel(IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onFindSuccess(FindBean data);

                void onFindFailure(Throwable e);
            }

            void getCinemaDataModel(String regionId, ICinemaModelCallback callback);

            //model层中的接口回调
            interface ICinemaModelCallback {
                void onCinemaSuccess(CinemaBean data);

                void onCinemaFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onFindSuccess(FindBean data);

            void onFindFailure(Throwable e);

            void onCinemaSuccess(CinemaBean data);

            void onCinemaFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getFindPresenter();

            void getLoginPresenter(String regionId);

        }


    }

    //查询推荐影院信息
    interface TjyyContreact {
        interface IModel {
            void getTjyyDataModel(String userId, String sessionId, Integer page, String count, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onTjyySuccess(TjyyBean data);

                void onTjyyFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onTjyySuccess(TjyyBean data);

            void onTjyyFailure(Throwable e);


        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getTjyyPresenter(String userId, String sessionId, Integer page, String count);

        }
    }

    //详情
    interface XQContreact {
        interface IModel {
            void getXQDataModel(String page, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onTjyySuccess(XQBean data);

                void onTjyyFailure(Throwable e);
            }

            void getXQSDataModel(String userId, String sessionId,String pageString , IModelXQSCallback callback);

            //model层中的接口回调
            interface IModelXQSCallback {
                void onTjyySuccess(XQBean data);

                void onTjyyFailure(Throwable e);
            }

            void getGZDYDataModel(String userId, String sessionId, String movieId, IModelGZDYCallback callback);

            //model层中的接口回调
            interface IModelGZDYCallback {
                void onGZDYSuccess(GZDYBean data);

                void onGZDYFailure(Throwable e);
            }

            void getQXDYGZDataModel(String userId, String sessionId, String movieId, IModelQXDYGZCallback callback);

            //model层中的接口回调
            interface IModelQXDYGZCallback {
                void onQXDYGZSuccess(QXDYGZBean data);

                void onQXDYGZFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onXQSuccess(XQBean data);

            void onXQFailure(Throwable e);

            void onXQSSuccess(XQBean data);

            void onXQSFailure(Throwable e);

            void onGZDYSuccess(GZDYBean data);

            void onGZDYFailure(Throwable e);

            void onQXDYGZSuccess(QXDYGZBean data);

            void onQXDYGFailure(Throwable e);

        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getXQPresenter(String page);

            void getXQSPresenter(String userId, String sessionId,String page);

            void getGZDYPresenter(String userId, String sessionId, String movieId);

            void getQXGZPresenter(String userId, String sessionId, String movieId);

        }
    }

    //详情
    interface FjyyContreact {
        interface IModel {
            void getFjyyDataModel(String userId, String sessionId, String longitude, String latitude, Integer page, String count, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onFjyySuccess(FjYyBean data);

                void onFjyyFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onFjyySuccess(FjYyBean data);

            void onFjyyFailure(Throwable e);


        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getFjyyPresenter(String userId, String sessionId, String longitude, String latitude, Integer page, String count);

        }
    }

    //根据电影的id查询电影评论
    interface PingLunContreact {
        interface IModel {
            void getPingLunDataModel( String movieId, Integer page, String count, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onPingLunSuccess(YingPingBean data);

                void onPingLunFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onPingLunSuccess(YingPingBean data);

            void onPingLunFailure(Throwable e);


        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getPingLunPresenter( String movieId, Integer page, String count);

        }
    }

    //查询电影信息明细
    interface YingYuanXqContreact {
        interface IModel {
            void getYingYuanXqDataModel(String userId, String sessionId, String movieId, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onYingYuanXqSuccess(YingYuanXQBean data);

                void onYingYuanXqFailure(Throwable e);
            }

            void getYYGZDataModel(String userId, String sessionId, String cinemaId, IModelYYGZCallback callback);

            //model层中的接口回调
            interface IModelYYGZCallback {
                void onYYGZXqSuccess(YYGZBean data);

                void onYYGZXqFailure(Throwable e);
            }

            void getQXYYGZDataModel(String userId, String sessionId, String cinemaId, IModelQXYYGZCallback callback);

            //model层中的接口回调
            interface IModelQXYYGZCallback {
                void onQXYYGZXqSuccess(QXYYGZBean data);

                void onQXYYGZXqFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onYingYuanXqSuccess(YingYuanXQBean data);

            void onYingYuanXqFailure(Throwable e);

            void onYYGZSuccess(YYGZBean data);

            void onYYGZXqFailure(Throwable e);

            void onQXYYGZSuccess(QXYYGZBean data);

            void onQXYYGZXqFailure(Throwable e);


        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getYingYuanXqPresenter(String userId, String sessionId,String movieId);

            void getYYGZXqPresenter(String userId, String sessionId, String cinemaId);

            void getQXYYGZXqPresenter(String userId, String sessionId, String cinemaId);

        }
    }

    //查询影院用户评论列表
    interface YingYuanPJContreact {
        interface IModel {
            void getYingYuanPJDataModel( String cinemaId, Integer page, String count, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onYingYuanPJSuccess(YYPJBean data);

                void onYingYuanPJFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onYingYuanPJSuccess(YYPJBean data);

            void onYingYuanPJFailure(Throwable e);


        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getYingYuanPJPresenter( String cinemaId, Integer page, String count);

        }
    }

    //查询影院用户评论列表
    interface SouContreact {
        interface IModel {
            void getSouDataModel(String keyword, String page, String count, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onSouSuccess(SouBean data);

                void onSouFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onSouSuccess(SouBean data);

            void onSouFailure(Throwable e);


        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getSouPresenter(String keyword, String page, String count);

        }
    }

    //查询一周排期的时间
    interface GPContreact {
        interface IModel {
            void getXQDataModel( String page, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onTjyySuccess(XQBean data);

                void onTjyyFailure(Throwable e);
            }

            void getTimeDataModel(IModelCallbacks callback);

            //model层中的接口回调
            interface IModelCallbacks {
                void onTimeSuccess(TimeBean data);

                void onTimeFailure(Throwable e);
            }

            void getGjsjcyyDataModel(String movieId, String date, String page, String count, IModelGjsjcyyCallbacks callback);

            //model层中的接口回调
            interface IModelGjsjcyyCallbacks {
                void onGjsjcyySuccess(GjsjcyyBean data);

                void onGjsjcyyFailure(Throwable e);
            }

            void getDiQuDataModel(IModelDiQuCallback callback);

            //model层中的接口回调
            interface IModelDiQuCallback {
                void onDiQuSuccess(FindBean data);

                void onDiQuFailure(Throwable e);

            }

            void getDQYYDataModel(String movieId, String regionId, Integer page, String count, IModelDiDqyyCallback callback);

            //model层中的接口回调
            interface IModelDiDqyyCallback {
                void onDQYYSuccess(GjsjcyyBean data);

                void onDQYYFailure(Throwable e);

            }

            void getZuiDiDataModel(String movieId, String page, String count, IModelZuidiCallback callback);

            //model层中的接口回调
            interface IModelZuidiCallback {
                void onZuiDiSuccess(GjsjcyyBean data);

                void onZuiDiFailure(Throwable e);

            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onXQSuccess(XQBean data);

            void onXQFailure(Throwable e);

            void onZuiDiSuccess(GjsjcyyBean data);

            void onZuiDiFailure(Throwable e);

            void onTimeSuccess(TimeBean data);

            void onTimeFailure(Throwable e);

            void onGjsjcyySuccess(GjsjcyyBean data);

            void onGjsjcyyFailure(Throwable e);

            void onDiQuSuccess(FindBean data);

            void onDiQuyFailure(Throwable e);

            void onDQYYSuccess(GjsjcyyBean data);

            void onDQYYFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getXQPresenter( String page);

            void getTime();

            void getGjsjcyy(String movieId, String date, String page, String count);

            void getFindPresenter();

            void getDQYYPresenter(String movieId, String regionId, Integer page, String count);
            void getZuiDiPresenter(String movieId, String page, String count);
        }
    }

    //座位
    interface ZuoContreact {
        interface IModel {
            void getXQDataModel( String page, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onTjyySuccess(XQBean data);

                void onTjyyFailure(Throwable e);
            }

            void getXDDataModel(String userId, String sessionId, String scheduleId, String seat, String sign, IModelXDCallback callback);

            //model层中的接口回调
            interface IModelXDCallback {
                void onXDSuccess(XiaDanBean data);

                void onXDFailure(Throwable e);
            }

            void getYingTingDataModel(String movieId, String cinemaId, IModelYingTingCallback callback);

            //model层中的接口回调
            interface IModelYingTingCallback {
                void onTjyySuccess(YingTingBean data);

                void onTjyyFailure(Throwable e);
            }

            void getZuoDataModel(String hallId, IModelZuoCallback callback);

            //model层中的接口回调
            interface IModelZuoCallback {
                void onZuoSuccess(ZuoBean data);

                void onZuoFailure(Throwable e);
            }

            void getZFDataModel(String userId, String sessionId, String payType, String orderId, IModelZFCallback callback);

            //model层中的接口回调
            interface IModelZFCallback {
                void onZFSuccess(ZhiFuBean data);

                void onZFFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onXQSuccess(XQBean data);

            void onXQFailure(Throwable e);

            void onYingTingSuccess(YingTingBean data);

            void onYingTingFailure(Throwable e);

            void onZuoSuccess(ZuoBean data);

            void onZuoFailure(Throwable e);

            void onXDSuccess(XiaDanBean data);

            void onXDFailure(Throwable e);

            void onZFSuccess(ZhiFuBean data);

            void onZFFailure(Throwable e);

        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getXQPresenter( String page);

            void getYingTingPresenter(String movieId, String cinemaId);

            void getZuo(String hallId);

            void getXD(String userId, String sessionId, String scheduleId, String seat, String sign);

            void getZF(String userId, String sessionId, String payType, String orderId);

        }
    }

    //
    interface PaiQiContreact {
        interface IModel {
            //登录
            void getPaiQiDataModel(String cinemaId, Integer page, String count, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onPaiQiSuccess(YYPQBean data);

                void onPaiQiFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onPaiQiSuccess(YYPQBean data);

            void onPaiQiFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getPaiQiPresenter(String cinemaId, Integer page, String count);
        }
    }

    //一周时间
    interface TimeContract {
        interface IView extends IBaseView {
            void onTimeSuccess(TimeBean data);

            void onTimeFailure(Throwable e);
        }

        interface IModel {
            void getTimeSDataModel(IModelCallbacksS callback);

            //model层中的接口回调
            interface IModelCallbacksS {
                void onTimeSSuccess(TimeBean data);

                void onTimeFailure(Throwable e);
            }
        }

        interface IPresenter {
            void getTimeS();
        }
    }

    //添加用户对影片的评论
    interface XYPContract {
        interface IView extends IBaseView {
            void onXYPSuccess(XYPBean data);

            void onXYPFailure(Throwable e);
        }

        interface IModel {
            void getXYPDataModel(String userId, String sessionId, String movieId, String commentContent, String score, IModelXYPCallbacksS callback);

            //model层中的接口回调
            interface IModelXYPCallbacksS {
                void onXYPSuccess(XYPBean data);

                void onXYPFailure(Throwable e);
            }
        }

        interface IPresenter {
            void getXYP(String userId, String sessionId, String movieId, String commentContent, String score);
        }
    }

    //购票记录
    interface GPJLContreact {
        interface IModel {
            //登录
            void getGPJLDataModel(String userId, String sessionId, Integer page, String count, String status, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onGPJLSuccess(GPJLBean data);

                void onGPJLFailure(Throwable e);
            }

            void getZFDataModels(String userId, String sessionId, String payType, String orderId, IModelZFsCallback callback);

            //model层中的接口回调
            interface IModelZFsCallback {
                void onZFSuccess(ZhiFuBean data);

                void onZFFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onGPJLSuccess(GPJLBean data);

            void onGPJLFailure(Throwable e);

            void onZFSuccess(ZhiFuBean data);

            void onZFFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getGPJLPresenter(String userId, String sessionId, Integer page, String count, String status);
            void getZFs(String userId, String sessionId, String payType, String orderId);
        }
    }

    //查询用户关注电影列表
    interface DYGZContreact {
        interface IModel {
            //登录
            void getDYGZDataModel(String userId, String sessionId, Integer page, String count, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onDYGZSuccess(DYGZBean data);

                void onDYGZFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onDYGZSuccess(DYGZBean data);

            void onDYGZFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getDYGZPresenter(String userId, String sessionId, Integer page, String count);
        }
    }

    interface YYGZContreact {
        interface IModel {
            //登录
            void getYYGZDataModel(String userId, String sessionId, Integer page, String count, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onYYGZSuccess(YYGZsBean data);

                void onYYGZFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onYYGZSuccess(YYGZsBean data);

            void onYYGZFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getYYGZPresenter(String userId, String sessionId, Integer page, String count);
        }
    }

    //查询用户预约电影信息
    interface ChaYuYueContreact {
        interface IModel {
            //登录
            void getChaYuYueDataModel(String userId, String sessionId, IModelChaYuYueCallback callback);

            //model层中的接口回调
            interface IModelChaYuYueCallback {
                void onChaYuYueSuccess(ChaYuYueBean data);

                void onChaYuYueFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onChaYuYueSuccess(ChaYuYueBean data);

            void onChaYuYueFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getChaYuYuePresenter(String userId, String sessionId);
        }
    }

    //我的电影票
    interface WDDYPContreact {
        interface IModel {
            //登录
            void getWDDYPDataModel(String userId, String sessionId, IModelWDDYPCallback callback);

            //model层中的接口回调
            interface IModelWDDYPCallback {
                void onWDDYPSuccess(WDDYPBean data);

                void onWDDYPFailure(Throwable e);
            }

            void getQuPiaoDataModel(String userId, String sessionId, String orderId, IModelQuPiaoCallback callback);

            //model层中的接口回调
            interface IModelQuPiaoCallback {
                void onQuPiaoSuccess(QuPiaoBean data);

                void onQuPiaoFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onWDDYPSuccess(WDDYPBean data);

            void onWDDYPFailure(Throwable e);

            void onQuPiaoSuccess(QuPiaoBean data);

            void onQuPiaoFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getWDDYPPresenter(String userId, String sessionId);

            void getQuPiaoPresenter(String userId, String sessionId, String orderId);
        }
    }

    //查询看过的电影
    interface KanGuoContreact {
        interface IModel {
            //登录
            void getKanGuoDataModel(String userId, String sessionId, IModelKanGuoCallback callback);

            //model层中的接口回调
            interface IModelKanGuoCallback {
                void onKanGuoSuccess(KanGuoBean data);

                void onKanGuoFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onKanGuoSuccess(KanGuoBean data);

            void onKanGuoFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getKanGuoPresenter(String userId, String sessionId);
        }
    }

    //查询我对影院评论列表   查询我对电影的评论列表
    interface WDPLContreact {
        interface IModel {
            //登录
            void getWDDYPLDataModel(String userId, String sessionId, String page, String count, IModelWDDYPLCallback callback);

            //model层中的接口回调
            interface IModelWDDYPLCallback {
                void onWDDYPLSuccess(DYPLBean data);

                void onWDDYPLFailure(Throwable e);
            }

            void getWDYYPLDataModel(String userId, String sessionId, String longitude, String latitude, String page, String count, IModelWYDYPLCallback callback);

            //model层中的接口回调
            interface IModelWYDYPLCallback {
                void onWDYYPLSuccess(YYPLBean data);

                void onWDYYPLFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onWDDYPLSuccess(DYPLBean data);

            void onWDDYPLFailure(Throwable e);

            void onWDYYPLSuccess(YYPLBean data);

            void onWDYYPLFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getWDDYPLPresenter(String userId, String sessionId, String page, String count);

            void getWDYYPLPresenter(String userId, String sessionId, String longitude, String latitude, String page, String count);
        }
    }

    //意见反馈
    interface FanKuiContreact {
        interface IModel {
            //登录
            void getFanKuiDataModel(String userId, String sessionId, String content, IModelFanKuiCallback callback);

            //model层中的接口回调
            interface IModelFanKuiCallback {
                void onFanKuiSuccess(FanKuiBean data);

                void onFanKuiFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onFanKuiSuccess(FanKuiBean data);

            void onFanKuiFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getFanKuiPresenter(String userId, String sessionId, String content);
        }
    }

    //查询系统消息列表
    interface XinXiContreact {
        interface IModel {
            //登录
            void getXinXiDataModel(String userId, String sessionId, String page, String count, IModelXinXiCallback callback);

            //model层中的接口回调
            interface IModelXinXiCallback {
                void onXinXiSuccess(XinXiBean data);

                void onXinXiFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onXinXiSuccess(XinXiBean data);

            void onXinXiFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getXinXiPresenter(String userId, String sessionId, String page, String count);
        }
    }

    //个人信息
    interface UserContreact {
        interface IModel {
            //登录
            void getUserDataModel(String userId, String sessionId, IModelUserCallback callback);

            //model层中的接口回调
            interface IModelUserCallback {
                void onUserSuccess(UserBean data);

                void onUserFailure(Throwable e);
            }

            void getSCTXDataModel(String userId, String sessionId,MultipartBody.Part map, IModelSCTXCallback callback);

            //model层中的接口回调
            interface IModelSCTXCallback {
                void onSCTXSuccess(SCTXBean data);

                void onSCTXFailure(Throwable e);
            }

            void getXGSJHDataModel(String userId, String sessionId,String phone, IModelXGSJHCallback callback);

            //model层中的接口回调
            interface IModelXGSJHCallback {
                void onXGSJHSuccess(XiuGaiShouJIBean data);

                void onXGSJHFailure(Throwable e);
            }

            void getXGSRDataModel(String userId, String sessionId,String birthday, IModelXGSRCallback callback);

            //model层中的接口回调
            interface IModelXGSRCallback {
                void onXGSRSuccess(ShengRiBean data);

                void onXGSRFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onUserSuccess(UserBean data);

            void onUserFailure(Throwable e);

            void onSCTXSuccess(SCTXBean data);

            void onSCTXFailure(Throwable e);

            void onXGSJHSuccess(XiuGaiShouJIBean data);

            void onXGSJHFailure(Throwable e);

            void onXGSRSuccess(ShengRiBean data);

            void onXGSRFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getUserPresenter(String userId, String sessionId);
            void getSCTXPresenter(String userId, String sessionId, MultipartBody.Part map);
            void getXGSJHPresenter(String userId, String sessionId,String phone);
            void getXGSRPresenter(String userId, String sessionId,String birthday);
        }
    }

    //修改密码
    interface XiuGaiContreact {
        interface IModel {
            //登录
            void getXiuGaiDataModel(String userId, String sessionId, String oldPwd, String newPwd, String newPwd2, IModelXiuGaiCallback callback);

            //model层中的接口回调
            interface IModelXiuGaiCallback {
                void onXiuGaiSuccess(XiuGaiBean data);

                void onXiuGaiFailure(Throwable e);
            }
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onXiuGaiSuccess(XiuGaiBean data);

            void onXiuGaiFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getXiuGaiPresenter(String userId, String sessionId, String oldPwd, String newPwd, String newPwd2);

        }
    }

    //登录
    interface DingDanContreact {
        interface IModel {
            //登录
            void getDingDanDataModel(String userId, String sessionId,String orderId, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onDingDanSuccess(YingPiaoXingQing data);

                void onDingDanFailure(Throwable e);
            }
            void getZhiFu(String userId, String sessionId, String payType, String orderId, IModelZhiFuCallback callback);

            //model层中的接口回调
            interface IModelZhiFuCallback {
                void onZFSuccess(ZhiFuBean data);

                void onZFFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onSuccess(YingPiaoXingQing data);

            void onFailure(Throwable e);

            void onZFSuccess(ZhiFuBean data);

            void onZFFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getDingDanPresenter(String userId, String sessionId,String orderId);

            void getZFs(String userId, String sessionId, String payType, String orderId);
        }
    }



}


