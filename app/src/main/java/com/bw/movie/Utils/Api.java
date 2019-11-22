package com.bw.movie.Utils;

import com.bw.movie.bean.GjsjcyyBean;
import com.bw.movie.bean.SouBean;
import com.bw.movie.bean.TimeBean;
import com.bw.movie.bean.XYPBean;
import com.bw.movie.bean.XYingPingBean;
import com.bw.movie.bean.YYPJBean;
import com.bw.movie.bean.YYPQBean;
import com.bw.movie.bean.YingPingBean;
import com.bw.movie.bean.YingTingBean;
import com.bw.movie.bean.YingYuanXQBean;
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
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {
    //发送验证码
    //user/v2/sendOutEmailCode
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
    Observable<ChaBean> getCha(@Query("page") String page,@Query("count") String count);
    @GET("movie/v2/findHotMovieList")
    Observable<ReBean> getRe(@Query("page") String page, @Query("count") String count);
    @GET("movie/v2/findComingSoonMovieList")
    Observable<JjBean> getJj(@Header ("userId") String userId,@Header("sessionId") String sessionId,@Query("page") String page, @Query("count") String count);
    //地区
    @GET("tool/v2/findRegionList")
    Observable<FindBean> getfind();
    //http://172.17.8.100/movieApi/cinema/v1/findNearbyCinemas
    //根据地区查询影院
    @GET("cinema/v2/findCinemaByRegion")
    Observable<CinemaBean> getcinema(@Query("regionId") String regionId);
    @GET("cinema/v1/findRecommendCinemas")
    Observable<TjyyBean> getTjyy(@Header("userId") String userId , @Header("sessionId") String sessionId,
                                 @Query("page") Integer page, @Query("count") String count);
    //电影详情
    @GET("movie/v2/findMoviesDetail")
    Observable<XQBean> getXiangQ(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Query("movieId") String movieId);
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
    Observable<YingPingBean> getYingPing(@Header("userId") String userId,
                                         @Header("sessionId") String sessionId,
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
    Observable<YYPJBean> getYYPJ(@Header("userId") String userId,
                                 @Header("sessionId") String sessionId,
                                 @Query("cinemaId") String cinemaId,
                                 @Query("page") Integer page,
                                 @Query("count") String count);
    //String userId,String sessionId,String cinemaId,Integer page,String count
   /* @GET("cinema/v1/findCinemaInfo")*/
    //根据关键字查询电影信息
    @GET("movie/v2/findMovieByKeyword")
    Observable<SouBean> getSou(@Query("keyword") String keyword,@Query("page") String page,@Query("count") String count);
    //查询一周排期的时间
    @GET("tool/v2/findDateList")
    Observable<TimeBean> getTime();
    //根据电影id，时间 查询播放影院信息
    @GET("movie/v2/findCinemasInfoByDate")
    Observable<GjsjcyyBean> getGjsjcyy(@Query("movieId") String movieId,@Query("date") String date,@Query("page") String page,@Query("count") String count);
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

}
