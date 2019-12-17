package com.bw.movie.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.adapter.souAdapter;
import com.bw.movie.bean.SouBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.SouPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SouActivity extends BaseActivity<SouPresenter> implements HomeConteract.SouContreact.IView {

    public static final String TAG = "SouActivity";
    @BindView(R.id.sou_edit)
    EditText souEdit;
    @BindView(R.id.sou_recy)
    RecyclerView souRecy;
    @BindView(R.id.title_fanhui)
    ImageView titleFanhui;
    @BindView(R.id.title_biaoti)
    TextView titleBiaoti;
    @BindView(R.id.meiyou_tu)
    ImageView meiyouTu;
    @BindView(R.id.meiyou_xinxi)
    TextView meiyouXinxi;
    @BindView(R.id.zong)
    LinearLayout zong;

    @Override
    protected SouPresenter providePresenter() {
        return new SouPresenter();
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initView() {
        souEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = souEdit.getText().toString();
                if (s != null) {
                    mPresenter.getSouPresenter(s, "1", "10");
                }
            }
        });

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_sou;
    }

    @Override
    public void onSouSuccess(SouBean data) {
        Log.d(TAG, "onSouSuccess: " + data.getMessage());
        String message = data.getMessage();
        List<SouBean.ResultBean> result = data.getResult();

        if (message.contains("未查到相关电影")) {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.zanwujieguo);
            meiyouXinxi.setText("没有相关内容结果换个词试试吧");
            souRecy.setVisibility(View.GONE);
        }else {
            zong.setVisibility(View.GONE);
            souRecy.setVisibility(View.VISIBLE);
            souRecy.setLayoutManager(new LinearLayoutManager(this));
            souRecy.setAdapter(new souAdapter(this, result));
        }

    }

    @Override
    public void onSouFailure(Throwable e) {

    }




    @OnClick(R.id.title_fanhui)
    public void onViewClicked() {
        finish();
    }
}
