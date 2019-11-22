package com.bw.movie.contract;

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
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onSuccess(LoginBean data);

            void onFailure(Throwable e);
        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            //登录
            void getLoginPresenter(String email, String pwd);
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
            void getXQDataModel(String userId, String sessionId, String page, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onTjyySuccess(XQBean data);

                void onTjyyFailure(Throwable e);
            }

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onXQSuccess(XQBean data);

            void onXQFailure(Throwable e);


        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getXQPresenter(String userId, String sessionId, String page);

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
            void getPingLunDataModel(String userId, String sessionId, String movieId, Integer page, String count, IModelCallback callback);

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

            void getPingLunPresenter(String userId, String sessionId, String movieId, Integer page, String count);

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

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onYingYuanXqSuccess(YingYuanXQBean data);

            void onYingYuanXqFailure(Throwable e);


        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getYingYuanXqPresenter(String userId, String sessionId, String movieId);

        }
    }

    //查询影院用户评论列表
    interface YingYuanPJContreact {
        interface IModel {
            void getYingYuanPJDataModel(String userId, String sessionId, String cinemaId, Integer page, String count, IModelCallback callback);

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

            void getYingYuanPJPresenter(String userId, String sessionId, String cinemaId, Integer page, String count);

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
            void getXQDataModel(String userId, String sessionId, String page, IModelCallback callback);

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
        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onXQSuccess(XQBean data);

            void onXQFailure(Throwable e);

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

            void getXQPresenter(String userId, String sessionId, String page);

            void getTime();

            void getGjsjcyy(String movieId, String date, String page, String count);

            void getFindPresenter();

            void getDQYYPresenter(String movieId, String regionId, Integer page, String count);
        }
    }

    //座位
    interface ZuoContreact {
        interface IModel {
            void getXQDataModel(String userId, String sessionId, String page, IModelCallback callback);

            //model层中的接口回调
            interface IModelCallback {
                void onTjyySuccess(XQBean data);

                void onTjyyFailure(Throwable e);
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

        }

        //view层  命名必须是IView
        interface IView extends IBaseView {
            void onXQSuccess(XQBean data);

            void onXQFailure(Throwable e);

            void onYingTingSuccess(YingTingBean data);

            void onYingTingFailure(Throwable e);

            void onZuoSuccess(ZuoBean data);

            void onZuoFailure(Throwable e);


        }

        //presenter层   命名必须是IPresenter
        interface IPresenter {

            void getXQPresenter(String userId, String sessionId, String page);

            void getYingTingPresenter(String movieId, String cinemaId);

            void getZuo(String hallId);

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
            void getXYPDataModel(String userId,String sessionId,String movieId,String commentContent,String score,IModelXYPCallbacksS callback);

            //model层中的接口回调
            interface IModelXYPCallbacksS {
                void onXYPSuccess(XYPBean data);

                void onXYPFailure(Throwable e);
            }
        }

        interface IPresenter {
            void getXYP(String userId,String sessionId,String movieId,String commentContent,String score);
        }
    }
}


