package com.bw.movie.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.adapter.DqAdapter;
import com.bw.movie.adapter.GouPiaoAdapter;
import com.bw.movie.adapter.TimeAdapter;
import com.bw.movie.bean.GjsjcyyBean;
import com.bw.movie.bean.TimeBean;
import com.bw.movie.bean.XQBean;
import com.bw.movie.bean.ZuiDiBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.fragment.FindBean;
import com.bw.movie.presenter.GouPiaoPresenter;
import com.bw.movie.utils.ActivityCollectorUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class GouPiaoActivity extends BaseActivity<GouPiaoPresenter> implements HomeConteract.GPContreact.IView {
    @BindView(R.id.goupiao_dq_name)
    TextView goupiaoDqName;
    @BindView(R.id.goupiao_tite_time)
    TextView goupiaoTiteTime;
    @BindView(R.id.goupaio_dq)
    RelativeLayout goupaioDq;

    @BindView(R.id.goupiao_recytime)
    RelativeLayout goupiaoRecytime;
    @BindView(R.id.image_dq)
    ImageView imageDq;
    @BindView(R.id.image_time)
    ImageView imageTime;
    @BindView(R.id.goupaio_zuidi)
    TextView goupaioZuidi;
    private int i = 0;
    public static final String TAG = "GouPiaoActivity";
    @BindView(R.id.goupiao_name)
    TextView goupiaoName;
    @BindView(R.id.goupiao_daoyan)
    TextView goupiaoDaoyan;
    @BindView(R.id.goupiao_pingfen)
    TextView goupiaoPingfen;
    @BindView(R.id.goupiao_time)
    TextView goupiaoTime;
    @BindView(R.id.goupiao_yugao)
    JCVideoPlayerStandard goupiaoYugao;

    @BindView(R.id.goupiao_recy_yy)
    RecyclerView goupiaoRecyYy;
    private PopupWindow popWindow;

    private String movieId;
    private RecyclerView dq_recy, shijian_shijian;


    @Override
    protected GouPiaoPresenter providePresenter() {
        return new GouPiaoPresenter();
    }

    @Override
    protected void initData() {
        goupiaoRecyYy.setLayoutManager(new LinearLayoutManager(this));
        goupaioDq.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                mPresenter.getFindPresenter();
                View inflate = LayoutInflater.from(GouPiaoActivity.this).inflate(R.layout.dp_layout, null, false);
                dq_recy = inflate.findViewById(R.id.dq_recy);
                popWindow = new PopupWindow(inflate,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popWindow.setTouchable(true);
                popWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                        // 这里如果返回true的话，touch事件将被拦截
                        // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                    }
                });
                popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效

                //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
                popWindow.showAsDropDown(goupaioDq, -20, 0);
                dq_recy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popWindow.dismiss();
                    }
                });
            }
        });
        goupiaoRecytime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.getTime();
                View inflate = LayoutInflater.from(GouPiaoActivity.this).inflate(R.layout.shijian_layout, null, false);
                shijian_shijian = inflate.findViewById(R.id.shijian_shijian);
                final PopupWindow popWindow = new PopupWindow(inflate,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popWindow.setTouchable(true);
                popWindow.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                        // 这里如果返回true的话，touch事件将被拦截
                        // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
                    }
                });
                popWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));    //要为popWindow设置一个背景才有效

                //设置popupWindow显示的位置，参数依次是参照View，x轴的偏移量，y轴的偏移量
                popWindow.showAsDropDown(goupiaoRecytime, -20, 0);
                shijian_shijian.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popWindow.dismiss();
                    }
                });
            }
        });
    }

    @Override
    protected void initView() {
        ActivityCollectorUtil.addActivity(this);
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String sessionId = sharedPreferences.getString("sessionId", "");
        String userId = sharedPreferences.getString("userId", "");
        SharedPreferences name = getSharedPreferences("users", Context.MODE_PRIVATE);
        movieId = name.getString("movieId", "");
        Log.d(TAG, "sessionId: " + sessionId);
        Log.d(TAG, "userId: " + userId);
        Log.d(TAG, "movieId: " + movieId);

        if (sessionId != null && userId != null && movieId != null) {
            mPresenter.getXQPresenter( movieId);
        }
        mPresenter.getTime();
        mPresenter.getDQYYPresenter(movieId, "1", 1, "10");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_gou_piao;
    }

    @Override
    public void onXQSuccess(XQBean data) {
        if (data != null) {
            XQBean.ResultBean result = data.getResult();
            goupiaoName.setText(result.getName());
            goupiaoTime.setText(result.getDuration());
            List<XQBean.ResultBean.MovieDirectorBean> movieDirector = result.getMovieDirector();
            for (int i = 0; i < movieDirector.size(); i++) {
                goupiaoDaoyan.setText(movieDirector.get(i).getName());
            }
            goupiaoPingfen.setText(result.getScore() + "分");
            String imageUrl = result.getShortFilmList().get(0).getVideoUrl();
            goupiaoYugao.setUp(imageUrl, JCVideoPlayer.SCREEN_LAYOUT_NORMAL, "");
            Glide.with(this).load(result.getShortFilmList().get(0).getImageUrl()).into(goupiaoYugao.thumbImageView);

        }
    }

    @Override
    public void onXQFailure(Throwable e) {

    }

    @Override
    public void onZuiDiSuccess(GjsjcyyBean data) {
        String message = data.getMessage();
        Log.d(TAG, "onZuiDiSuccess: "+message);
        List<GjsjcyyBean.ResultBean> result = data.getResult();
        if (result != null) {
            goupiaoRecyYy.setAdapter(new GouPiaoAdapter(this, result));
        }
    }

    @Override
    public void onZuiDiFailure(Throwable e) {

    }

    @Override
    public void onTimeSuccess(TimeBean data) {
        Log.d(TAG, "onTimeSuccess: " + data.getMessage());
        List<String> result = data.getResult();
        String sss = result.get(0);
        goupiaoTiteTime.setText(sss);
        TimeAdapter timeAdapter = new TimeAdapter(GouPiaoActivity.this, result);
        if (shijian_shijian != null) {
            shijian_shijian.setLayoutManager(new LinearLayoutManager(this));

            shijian_shijian.setAdapter(timeAdapter);
            timeAdapter.onListenter(new TimeAdapter.setChange() {
                @Override
                public void getChage(String name) {
                    if (name != null) {
                        goupiaoTiteTime.setText(name);
                        shijian_shijian.setVisibility(View.INVISIBLE);
                        mPresenter.getGjsjcyy(movieId, name, "1", "10");
                    }
                }
            });
        }

    }

    @Override
    public void onTimeFailure(Throwable e) {

    }

    @Override
    public void onGjsjcyySuccess(GjsjcyyBean data) {
        Log.d(TAG, "onGjsjcyySuccess: " + data.getMessage());
        List<GjsjcyyBean.ResultBean> result = data.getResult();
        if (result != null) {
            goupiaoRecyYy.setAdapter(new GouPiaoAdapter(this, result));
        }
    }

    @Override
    public void onGjsjcyyFailure(Throwable e) {

    }

    @Override
    public void onDiQuSuccess(FindBean data) {
        if (dq_recy != null) {
            dq_recy.setLayoutManager(new LinearLayoutManager(this));
            List<FindBean.ResultBean> result = data.getResult();
            DqAdapter dqAdapter = new DqAdapter(this, result);
            dq_recy.setAdapter(dqAdapter);
            dqAdapter.setOnClickListenter(new DqAdapter.setChangListenter() {
                @Override
                public void getChang(String id, int postion) {
                    String regionName = result.get(postion).getRegionName();
                    goupiaoDqName.setText(regionName);
                    if (id != null) {
                        popWindow.dismiss();
                        mPresenter.getDQYYPresenter(movieId, id, 1, "10");
                    }
                }
            });
        }
    }

    @Override
    public void onDiQuyFailure(Throwable e) {

    }

    @Override
    public void onDQYYSuccess(GjsjcyyBean data) {

        List<GjsjcyyBean.ResultBean> result = data.getResult();
        if (result != null) {
            goupiaoRecyYy.setAdapter(new GouPiaoAdapter(this, result));

        }
        Log.d(TAG, "onDQYYSuccess: " + data);
    }

    @Override
    public void onDQYYFailure(Throwable e) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }


    @OnClick(R.id.goupaio_zuidi)
    public void onViewClicked() {
        mPresenter.getZuiDiPresenter(movieId,"1","10");
    }
}
