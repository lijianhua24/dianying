package com.bw.movie.view;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.fragment.FindBean;
import com.bw.movie.presenter.GouPiaoPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;

public class GouPiaoActivity extends BaseActivity<GouPiaoPresenter> implements HomeConteract.GPContreact.IView {
    @BindView(R.id.goupiao_dq_name)
    TextView goupiaoDqName;
    @BindView(R.id.goupiao_tite_time)
    TextView goupiaoTiteTime;
    private int a = 0;
    private int b = 0;
    @BindView(R.id.image_dq)
    ImageView imageDq;
    @BindView(R.id.image_time)
    ImageView imageTime;
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
    JCVideoPlayer goupiaoYugao;
    @BindView(R.id.goupiao_recy_dq)
    RecyclerView goupiaoRecyDq;
    @BindView(R.id.goupiao_recy_time)
    RecyclerView goupiaoRecyTime;
    @BindView(R.id.goupiao_recy_yy)
    RecyclerView goupiaoRecyYy;

    private String movieId;


    @Override
    protected GouPiaoPresenter providePresenter() {
        return new GouPiaoPresenter();
    }

    @Override
    protected void initData() {
        goupiaoRecyTime.setLayoutManager(new LinearLayoutManager(this));
        goupiaoRecyYy.setLayoutManager(new LinearLayoutManager(this));


    }

    @Override
    protected void initView() {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String sessionId = sharedPreferences.getString("sessionId", "");
        String userId = sharedPreferences.getString("userId", "");
        movieId = sharedPreferences.getString("movieId", "");
        Log.d(TAG, "goupiao " + sessionId + userId + movieId);

        if (sessionId != null && userId != null && movieId != null) {
            mPresenter.getXQPresenter(userId, sessionId, movieId);
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
            goupiaoYugao.setUp(imageUrl, null);
            Glide.with(this).load(result.getShortFilmList().get(0).getImageUrl()).into(goupiaoYugao.ivThumb);

        }
    }

    @Override
    public void onXQFailure(Throwable e) {

    }

    @Override
    public void onTimeSuccess(TimeBean data) {
        Log.d(TAG, "onTimeSuccess: " + data.getMessage());
        List<String> result = data.getResult();
        String sss = result.get(0);
        goupiaoTiteTime.setText(sss);
        TimeAdapter timeAdapter = new TimeAdapter(GouPiaoActivity.this, result);
        goupiaoRecyTime.setAdapter(timeAdapter);
        timeAdapter.onListenter(new TimeAdapter.setChange() {
            @Override
            public void getChage(String name) {
                if (name != null) {
                    a = 0;
                    goupiaoTiteTime.setText(name);
                    goupiaoRecyTime.setVisibility(View.INVISIBLE);
                    mPresenter.getGjsjcyy(movieId, name, "1", "10");
                }
            }
        });

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
        goupiaoRecyDq.setLayoutManager(new LinearLayoutManager(this));
        List<FindBean.ResultBean> result = data.getResult();
        DqAdapter dqAdapter = new DqAdapter(this, result);
        goupiaoRecyDq.setAdapter(dqAdapter);
        dqAdapter.setOnClickListenter(new DqAdapter.setChangListenter() {
            @Override
            public void getChang(String id, int postion) {
                String regionName = result.get(postion).getRegionName();
                goupiaoDqName.setText(regionName);
                if (id != null) {
                    b = 0;
                    Log.d(TAG, "getChang: " + id);
                    goupiaoRecyDq.setVisibility(View.INVISIBLE);
                    mPresenter.getDQYYPresenter(movieId, id, 1, "10");
                }
            }
        });
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

    @OnClick({R.id.image_dq, R.id.image_time})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.image_dq:
                mPresenter.getFindPresenter();
                if (b % 2 == 0) {
                    goupiaoRecyDq.setVisibility(View.VISIBLE);
                } else {
                    goupiaoRecyDq.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.image_time:
                mPresenter.getTime();
                if (a % 2 == 0) {
                    goupiaoRecyTime.setVisibility(View.VISIBLE);
                } else {
                    goupiaoRecyTime.setVisibility(View.INVISIBLE);
                }
                break;
        }
    }


}
