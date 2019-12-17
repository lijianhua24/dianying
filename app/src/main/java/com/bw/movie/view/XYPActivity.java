package com.bw.movie.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.bean.XYPBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.XYPPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class XYPActivity extends BaseActivity<XYPPresenter> implements HomeConteract.XYPContract.IView {
    public static final String TAG="XYPActivity";
    @BindView(R.id.xyp_name)
    TextView xypName;
    @BindView(R.id.xyp_fen)
    RatingBar xypFen;
    @BindView(R.id.xyp_xyp)
    TextView xypXyp;
    @BindView(R.id.xyp_tijiao)
    TextView xypTijiao;
    private String resultName;

    private String sessionId;
    private String userId;

    @Override

    protected XYPPresenter providePresenter() {
        return new XYPPresenter();
    }

    @Override
    protected void initData() {
        if (resultName!=null){
            xypName.setText(resultName);
        }
    }

    @Override
    protected void initView() {
        SharedPreferences sharedPreferences2 = getSharedPreferences("Suser", Context.MODE_PRIVATE);

        resultName = sharedPreferences2.getString("resultName", null);
        sessionId = App.sharedPreferences.getString("sessionId", null);
        userId = App.sharedPreferences.getString("userId", "");
        String stringExtra = getIntent().getStringExtra("ssss");
        xypTijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "sessionId: "+sessionId);
                Log.d(TAG, "userId: "+userId);
                if (userId != null && sessionId != null) {
                    String s = xypXyp.getText().toString();
                    float rating = xypFen.getRating();
                    Log.d(TAG, "onViewClicked: " +stringExtra);
                    mPresenter.getXYP(userId, sessionId, stringExtra, s, rating + "");
                    startActivity(new Intent(XYPActivity.this,XQActivity.class));
                }else {
                    Toast.makeText(XYPActivity.this, "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(XYPActivity.this,MainActivity.class));
                }
            }
        });
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_xyp;
    }

    @Override
    public void onXYPSuccess(XYPBean data) {
        String message = data.getMessage();
        Log.d(TAG, "onXYPSuccess: "+message);

        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onXYPFailure(Throwable e) {

    }



}
