package com.bw.movie.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Base.BaseFragment;
import com.bw.movie.Base.BasePresenter;
import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.bean.SCTXBean;
import com.bw.movie.bean.ShengRiBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.bean.XiuGaiShouJIBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.UserPresenter;
import com.bw.movie.view.FanKuiActivity;
import com.bw.movie.view.GouPiaoJiLuActivity;
import com.bw.movie.view.GuanZhuActivity;
import com.bw.movie.view.KanGuoActivity;
import com.bw.movie.view.MainActivity;
import com.bw.movie.view.SheZhiActivity;
import com.bw.movie.view.WDPLActivity;
import com.bw.movie.view.WdActivity;
import com.bw.movie.view.WdYpActivity;
import com.bw.movie.view.XinXiActivity;
import com.bw.movie.view.YuYueActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import butterknife.BindView;
import butterknife.OnClick;


public class WoDeFragment extends BaseFragment<UserPresenter> implements HomeConteract.UserContreact.IView {

    @BindView(R.id.wd_touxiang)
    SimpleDraweeView wdTouxiang;
    @BindView(R.id.img_sz)
    ImageView imgSz;
    @BindView(R.id.wd_nicheng)
    TextView wdNicheng;
    @BindView(R.id.wd_wdyp)
    LinearLayout wdWdyp;
    @BindView(R.id.wd_guanzhu)
    ImageView wdGuanzhu;
    @BindView(R.id.wd_yuyue)
    ImageView wdYuyue;
    @BindView(R.id.goupiao_jilu)
    ImageView goupiaoJilu;
    @BindView(R.id.wd_kanguo)
    ImageView wdKanguo;
    @BindView(R.id.wd_pinglun)
    ImageView wdPinglun;
    @BindView(R.id.wd_fankui)
    ImageView wdFankui;
    @BindView(R.id.wd_xinxi)
    ImageView wdXinxi;
    @BindView(R.id.wd_wdxx)
    SimpleDraweeView wdWdxx;


    @Override
    protected UserPresenter providePresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);
       if (userId!=null && sessionId!=null){
           mPresenter.getUserPresenter(userId,sessionId);
       }else {
           Toast.makeText(getActivity(), "点击头像,请先登录", Toast.LENGTH_SHORT).show();
       }
    }


    protected int provideLayoutId() {
        return R.layout.fragment_my;
    }

    @OnClick({R.id.wd_wdyp, R.id.wd_guanzhu, R.id.wd_yuyue, R.id.goupiao_jilu, R.id.wd_kanguo, R.id.wd_pinglun, R.id.wd_fankui, R.id.wd_xinxi, R.id.wd_wdxx,R.id.img_sz,R.id.wd_touxiang})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.wd_wdyp:
                startActivity(new Intent(getActivity(), WdYpActivity.class));
                break;
            case R.id.wd_guanzhu:
                startActivity(new Intent(getActivity(), GuanZhuActivity.class));
                break;
            case R.id.wd_yuyue:
                startActivity(new Intent(getActivity(), YuYueActivity.class));
                break;
            case R.id.goupiao_jilu:
                startActivity(new Intent(getActivity(), GouPiaoJiLuActivity.class));
                break;
            case R.id.wd_kanguo:
                startActivity(new Intent(getActivity(), KanGuoActivity.class));
                break;
            case R.id.wd_pinglun:
                startActivity(new Intent(getActivity(), WDPLActivity.class));
                break;
            case R.id.wd_fankui:
                startActivity(new Intent(getActivity(), FanKuiActivity.class));
                break;
            case R.id.wd_xinxi:
                startActivity(new Intent(getActivity(), XinXiActivity.class));
                break;
            case R.id.wd_wdxx:
                startActivity(new Intent(getActivity(), WdActivity.class));
                break;
                case R.id.img_sz:
                startActivity(new Intent(getActivity(), SheZhiActivity.class));
                break;
                case R.id.wd_touxiang:
                startActivity(new Intent(getActivity(), MainActivity.class));
                break;
        }
    }


    @Override
    public void onUserSuccess(UserBean data) {
        UserBean.ResultBean result = data.getResult();
        String headPic = result.getHeadPic();
        Uri parse = Uri.parse(headPic);
        wdTouxiang.setImageURI(parse);
        String nickName = result.getNickName();
        wdNicheng.setText(nickName);
    }

    @Override
    public void onUserFailure(Throwable e) {

    }

    @Override
    public void onSCTXSuccess(SCTXBean data) {

    }

    @Override
    public void onSCTXFailure(Throwable e) {

    }

    @Override
    public void onXGSJHSuccess(XiuGaiShouJIBean data) {

    }

    @Override
    public void onXGSJHFailure(Throwable e) {

    }

    @Override
    public void onXGSRSuccess(ShengRiBean data) {

    }

    @Override
    public void onXGSRFailure(Throwable e) {

    }
}
