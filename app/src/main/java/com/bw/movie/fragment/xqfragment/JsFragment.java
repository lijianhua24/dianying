package com.bw.movie.fragment.xqfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.DYAdapter;
import com.bw.movie.adapter.YanYuanAdapter;
import com.bw.movie.bean.GZDYBean;
import com.bw.movie.bean.QXDYGZBean;
import com.bw.movie.bean.XQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.XQsPresenter;

import java.util.List;

import butterknife.BindView;


public class JsFragment extends BaseFragment<XQsPresenter> implements HomeConteract.XQContreact.IView {
    public static final String TAG = "JsFragment";
    @BindView(R.id.js_jian)
    TextView jsJian;
    @BindView(R.id.js_daoyan)
    TextView jsDaoyan;
    @BindView(R.id.js_recydy)
    RecyclerView jsRecydy;
    @BindView(R.id.js_yanyuan)
    TextView jsYanyuan;
    @BindView(R.id.js_recyyy)
    RecyclerView jsRecyyy;

    @Override
    protected XQsPresenter providePresenter() {
        return new XQsPresenter();
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
        Log.d(TAG, "initView: " + sessionId + userId + movieId);

        if (sessionId != null && userId != null && movieId != null) {
            mPresenter.getXQPresenter(movieId);
        }
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_js;
    }

    @Override
    public void onXQSuccess(XQBean data) {
        Log.d(TAG, "onXQSuccess: " + data.getMessage());
       if (data!=null){
           jsJian.setText(data.getResult().getSummary());
           List<XQBean.ResultBean.MovieActorBean> movieActor = data.getResult().getMovieActor();
           Log.d(TAG, "movieActor: "+movieActor);
           List<XQBean.ResultBean.MovieDirectorBean> movieDirector = data.getResult().getMovieDirector();
           jsDaoyan.setText("导演("+movieDirector.size()+")");

           LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
           linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
           jsRecydy.setLayoutManager(linearLayoutManager);
           LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
           linearLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
           jsRecyyy.setLayoutManager(linearLayoutManager1);
           jsRecydy.setAdapter(new DYAdapter(getActivity(),movieDirector));
           jsYanyuan.setText("演员:("+movieActor.size()+")");
           jsRecyyy.setAdapter(new YanYuanAdapter(getActivity(),movieActor));
       }else {
           Toast.makeText(getActivity(), "请检查网络", Toast.LENGTH_SHORT).show();
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
}
