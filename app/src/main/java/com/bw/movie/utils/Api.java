package com.bw.movie.utils;

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

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface Api {
    //发送验证码
    @FormUrlEncoded
    @POST("user/v2/sendOutEmailCode")
    Observable<EmailBean> getEmail(@Field("email") String email);

    //登录
    @FormUrlEncoded
    @POST("user/v2/login")
    Observable<LoginBean> getLogin(@Field("email") String email,//String email,String pwd
                                   @Field("pwd") String pwd);

    //注册
    @FormUrlEncoded
    @POST("user/v2/register")
    Observable<RegisterBean> getRegister(@Field("nickName") String nickName,
                                         @Field("pwd") String pwd,
                                         @Field("email") String email,
                                         @Field("code") String code);

    //String nickName,String pwd,String email,String code
    @GET("tool/v2/banner")
    Observable<BannerBean> getBanner();

    @GET("movie/v2/findReleaseMovieList")
    Observable<ChaBean> getCha(@Query("page") String page,
                               @Query("count") String count);

    @GET("movie/v2/findHotMovieList")
    Observable<ReBean> getRe(@Query("page") String page,
                             @Query("count") String count);

    @GET("movie/v2/findComingSoonMovieList")
    Observable<JjBean> getJj(@Header("userId") String userId,
                             @Header("sessionId") String sessionId,
                             @Query("page") String page,
                             @Query("count") String count);

    //地区
    @GET("tool/v2/findRegionList")
    Observable<FindBean> getfind();

    //http://172.17.8.100/movieApi/cinema/v1/findNearbyCinemas
    //根据地区查询影院
    @GET("cinema/v2/findCinemaByRegion")
    Observable<CinemaBean> getcinema(@Query("regionId") String regionId);

    @GET("cinema/v1/findRecommendCinemas")
    Observable<TjyyBean> getTjyy(@Header("userId") String userId, @Header("sessionId") String sessionId,
                                 @Query("page") Integer page, @Query("count") String count);

    //电影详情
    @GET("movie/v2/findMoviesDetail")
    Observable<XQBean> getXiangQ(@Query("movieId") String movieId);

    @GET("movie/v2/findMoviesDetail")
    Observable<XQBean> getXQ(@Header("userId") String userId, @Header("sessionId") String sessionId,@Query("movieId") String movieId);

    //查询附近影院
    @GET("cinema/v1/findNearbyCinemas")
    Observable<FjYyBean> getFjyy(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Query("longitude") String longitude,
                                 @Query("latitude") String latitude,
                                 @Query("page") Integer page,
                                 @Query("count") String count);

    //String longitude,String latitude, String page,String count
    //根据电影的id查询电影评论
    @GET("movie/v2/findAllMovieComment")
    Observable<YingPingBean> getYingPing(
                                         @Query("movieId") String movieId,
                                         @Query("page") Integer page,
                                         @Query("count") String count);

    //
    @GET("cinema/v1/findCinemaInfo")
    Observable<YingYuanXQBean> getYingYuanXingqing(@Header("userId") String userId,
                                                   @Header("sessionId") String sessionId,
                                                   @Query("cinemaId") String cinemaId);

    //查询影院用户评论列表
    @GET("cinema/v1/findAllCinemaComment")
    Observable<YYPJBean> getYYPJ(@Query("cinemaId") String cinemaId,
                                 @Query("page") Integer page,
                                 @Query("count") String count);

    //String userId,String sessionId,String cinemaId,Integer page,String count
    /* @GET("cinema/v1/findCinemaInfo")*/
    //根据关键字查询电影信息
    @GET("movie/v2/findMovieByKeyword")
    Observable<SouBean> getSou(@Query("keyword") String keyword,
                               @Query("page") String page,
                               @Query("count") String count);

    //查询一周排期的时间
    @GET("tool/v2/findDateList")
    Observable<TimeBean> getTime();

    //根据电影id，时间 查询播放影院信息
    @GET("movie/v2/findCinemasInfoByDate")
    Observable<GjsjcyyBean> getGjsjcyy(@Query("movieId") String movieId,
                                       @Query("date") String date,
                                       @Query("page") String page,
                                       @Query("count") String count);

    //根据电影id,区域id 查询播放影院信息
    @GET("movie/v2/findCinemasInfoByRegion")
    Observable<GjsjcyyBean> getDQYYS(@Query("movieId") String movieId,
                                     @Query("regionId") String regionId,
                                     @Query("page") Integer page,
                                     @Query("count") String count);

    //根据电影ID和影院ID查询电影排期列表
    @GET("movie/v2/findMovieSchedule")
    Observable<YingTingBean> getYingTing(@Query("movieId") String movieId,
                                         @Query("cinemaId") String cinemaId);

    //
    //根据影厅id 查询座位信息
    @GET("movie/v2/findSeatInfo")
    Observable<ZuoBean> getZuo(@Query("hallId") String hallId);

    //查询影院下的电影排期
    @GET("cinema/v2/findCinemaScheduleList")
    Observable<YYPQBean> getYyQq(@Query("cinemaId") String cinemaId,
                                 @Query("page") Integer page,
                                 @Query("count") String count);

    //添加用户对影片的评论
    @FormUrlEncoded
    @POST("movie/v1/verify/movieComment")
    Observable<XYPBean> getXYP(@Header("userId") String userId,
                               @Header("sessionId") String sessionId,
                               @Field("movieId") String movieId,
                               @Field("commentContent") String commentContent,
                               @Field("score") String score);

    //String userId,String sessionId,String movieId,String commentContent,Integer score,
    //购票下单
    @FormUrlEncoded
    @POST("movie/v2/verify/buyMovieTickets")
    Observable<XiaDanBean> getXiaDan(@Header("userId") String userId,
                                     @Header("sessionId") String sessionId,
                                     @Field("scheduleId") String scheduleId,
                                     @Field("seat") String seat,
                                     @Field("sign") String sign);

    //支付
    @FormUrlEncoded
    @POST("movie/v2/verify/pay")
    Observable<ZhiFuBean> getZhiFu(@Header("userId") String userId,
                                   @Header("sessionId") String sessionId,
                                   @Field("payType") String payType,
                                   @Field("orderId") String orderId);

    //购票记录
    @GET("user/v2/verify/findUserBuyTicketRecord")
    Observable<GPJLBean> getGPJL(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Query("page") Integer page,
                                 @Query("count") String count,
                                 @Query("status") String status);

    //关注电影

    @GET("movie/v1/verify/followMovie")
    Observable<GZDYBean> getGZDY(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Query("movieId") String movieId);

    //取消关注电影
    @GET("movie/v1/verify/cancelFollowMovie")
    Observable<QXDYGZBean> getQXDYGZ(@Header("userId") String userId,
                                     @Header("sessionId") String sessionId,
                                     @Query("movieId") String movieId);

    //关注影院
    @GET("cinema/v1/verify/followCinema")
    Observable<YYGZBean> getYYGZ(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Query("cinemaId") String cinemaId);

    //取消关注影院
    @GET("cinema/v1/verify/cancelFollowCinema")
    Observable<QXYYGZBean> getQXYYGZ(@Header("userId") String userId,
                                     @Header("sessionId") String sessionId,
                                     @Query("cinemaId") String cinemaId);

    //查询用户关注电影列表
    @GET("user/v2/verify/findUserFollowMovieList")
        Observable<DYGZBean> getDYGZ(@Header("userId") String userId,
                                     @Header("sessionId") String sessionId,
                                     @Query("page") Integer page,
                                     @Query("count") String count);

    //查询用户关注影院列表
    @GET("user/v2/verify/findUserFollowCinemaList")
    Observable<YYGZsBean> getYYGZ(@Header("userId") String userId,
                                  @Header("sessionId") String sessionId,
                                  @Query("page") Integer page,
                                  @Query("count") String count);

    //预约
    @FormUrlEncoded
    @POST("movie/v2/verify/reserve")
    Observable<YuYueBean> getYuYue(@Header("userId") String userId,
                                   @Header("sessionId") String sessionId,
                                   @Field("movieId") String movieId);

    //查询用户预约电影信息
    @GET("user/v2/verify/findUserReserve")
    Observable<ChaYuYueBean> getChaYuYue(@Header("userId") String userId,
                                         @Header("sessionId") String sessionId);

    //我的电影票
    @GET("user/v2/verify/findMyMovieTicket")
    Observable<WDDYPBean> getwddyp(@Header("userId") String userId,
                                   @Header("sessionId") String sessionId);
    //取票
    @GET("user/v2/verify/findExchangeCode")
    Observable<QuPiaoBean> getQuPiao(@Header("userId") String userId,
                                     @Header("sessionId") String sessionId,
                                     @Query("recordId") String recordId);

    //查询看过的电影
    @GET("user/v2/verify/findSeenMovie")
    Observable<KanGuoBean> getKanGuo(@Header("userId") String userId,
                                     @Header("sessionId") String sessionId);
    //查询我对电影的评论列表
    @GET("user/v2/verify/findMyMovieCommentList")
    Observable<DYPLBean> getDYPL(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Query("page") String page,
                                 @Query("count") String count);

    //查询我对影院评论列表
    @GET("user/v2/verify/findMyCinemaCommentList")
    Observable<YYPLBean> getYYPL(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Query("longitude") String longitude,
                                 @Query("latitude") String latitude,
                                 @Query("page") String page,
                                 @Query("count") String count);
    //意见反馈
    @FormUrlEncoded
    @POST("tool/v1/verify/recordFeedBack")
    Observable<FanKuiBean> getFanKui(@Header("userId") String userId,
                                     @Header("sessionId") String sessionId,
                                     @Field("content") String content
                                     );

    //查询系统消息列表
    @GET("tool/v1/verify/findAllSysMsgList")
    Observable<XinXiBean> getXinXi(@Header("userId") String userId,
                                   @Header("sessionId") String sessionId,
                                   @Query("page") String page,
                                   @Query("count") String count);

    //微信登录
    @FormUrlEncoded
    @POST("user/v1/weChatBindingLogin")
    Observable<WxBean> getWx(@Field("code") String code);

    //根据用户ID查询用户信息
    @GET("user/v1/verify/getUserInfoByUserId")
    Observable<UserBean> getUser(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId);

    //修改密码
    @FormUrlEncoded
    @POST("user/v1/verify/modifyUserPwd")
    Observable<XiuGaiBean> getXiuGai(@Header("userId") String userId,
                                     @Header("sessionId") String sessionId,
                                     @Field("oldPwd") String oldPwd,
                                     @Field("newPwd") String newPwd,
                                     @Field("newPwd2") String newPwd2);

    //上传用户头像
    @Multipart
    @POST("user/v1/verify/uploadHeadPic")
    Observable<SCTXBean> getSCTX(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Part MultipartBody.Part map);
    //修改用户手机号
    @FormUrlEncoded
    @POST("user/v2/verify/updateUserPhone")
    Observable<XiuGaiShouJIBean> getXiuGaiShouJi(@Header("userId") String userId,
                                                 @Header("sessionId") String sessionId,
                                                 @Field("phone") String phone);

    //修改用户生日
    @FormUrlEncoded
    @POST("user/v2/verify/updateUserBirthday")
    Observable<ShengRiBean> getShengRi(@Header("userId") String userId,
                                       @Header("sessionId") String sessionId,
                                       @Field("birthday") String birthday);

    //查看订单详情
    @GET("user/v2/verify/findBuyTicketRecordByOrderId")
    Observable<YingPiaoXingQing> getDingDan(@Header("userId") String userId,
                                            @Header("sessionId") String sessionId,
                                            @Query("orderId") String orderId);

    //根据电影价格查询播放影院信息

    @GET("movie/v2/findCinemasInfoByPrice")
    Observable<GjsjcyyBean> getZuiDi(@Query("movieId") String movieId,
                                     @Query("page") String page,
                                     @Query("count") String count);

}
