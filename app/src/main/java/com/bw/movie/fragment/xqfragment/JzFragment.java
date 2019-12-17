package com.bw.movie.fragment.xqfragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.JuZhaoAdapter;
import com.bw.movie.bean.GZDYBean;
import com.bw.movie.bean.QXDYGZBean;
import com.bw.movie.bean.XQBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.JzPresenter;

import java.util.List;

import butterknife.BindView;


public class JzFragment extends BaseFragment<JzPresenter> implements HomeConteract.XQContreact.IView {
    public static final String TAG = "JzFragment";
    @BindView(R.id.jz_recy)
    RecyclerView jzRecy;

    @Override
    protected JzPresenter providePresenter() {
        return new JzPresenter();
    }

    @Override
    protected void initData() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        jzRecy.setLayoutManager(gridLayoutManager);
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
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_jz;
    }

    @Override
    public void onXQSuccess(XQBean data) {
        Log.d(TAG, "onXQSuccess: " + data.getMessage());
        //posterList
        List<String> posterList = data.getResult().getPosterList();
       if (posterList!=null){
           jzRecy.setAdapter(new JuZhaoAdapter(getActivity(),posterList));
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
