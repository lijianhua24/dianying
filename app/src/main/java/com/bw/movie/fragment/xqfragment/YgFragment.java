package com.bw.movie.fragment.xqfragment;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.YuGaoAdapter;
import com.bw.movie.bean.GZDYBean;
import com.bw.movie.bean.QXDYGZBean;
import com.bw.movie.bean.XQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.YuGaoPresenter;

import java.util.List;

import butterknife.BindView;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;


public class YgFragment extends BaseFragment<YuGaoPresenter> implements HomeConteract.XQContreact.IView {


    @BindView(R.id.yg_recy)
    RecyclerView ygRecy;

    @Override
    protected YuGaoPresenter providePresenter() {
        return new YuGaoPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        SharedPreferences name =getActivity().getSharedPreferences("users", Context.MODE_PRIVATE);
        String movieId = name.getString("movieId", "");
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        String sessionId = sharedPreferences.getString("sessionId", "");
        String userId = sharedPreferences.getString("userId", "");
        if (sessionId != null && userId != null && movieId != null) {
            mPresenter.getXQPresenter( movieId);
        }
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        ygRecy.setLayoutManager(linearLayoutManager);
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_yg;
    }

    @Override
    public void onXQSuccess(XQBean data) {
        //shortFilmList
        if (data!=null){
            List<XQBean.ResultBean.ShortFilmListBean> list = data.getResult().getShortFilmList();
            ygRecy.setAdapter(new YuGaoAdapter(getActivity(),list));
        }
    }

    @Override
    public void onXQFailure(Throwable e) {

    }

    @Override
    public void onXQSSuccess(XQBean data) {

    }

    @Override
    public void onXQSFailure(Throwable e) {

    }

    @Override
    public void onGZDYSuccess(GZDYBean data) {

    }

    @Override
    public void onGZDYFailure(Throwable e) {

    }

    @Override
    public void onQXDYGZSuccess(QXDYGZBean data) {

    }

    @Override
    public void onQXDYGFailure(Throwable e) {

    }

    @Override
    public void onPause() {
        super.onPause();
        JCVideoPlayer.releaseAllVideos();
    }
}
