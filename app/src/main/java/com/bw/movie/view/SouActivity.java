package com.bw.movie.view;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

public class SouActivity extends BaseActivity<SouPresenter> implements HomeConteract.SouContreact.IView {

    public static final String TAG="SouActivity";
    @BindView(R.id.sou_edit)
    EditText souEdit;
    @BindView(R.id.sou_recy)
    RecyclerView souRecy;

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
                if (s!=null){
                    mPresenter.getSouPresenter(s,"1","10");
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
        Log.d(TAG, "onSouSuccess: "+data.getMessage());
        String message = data.getMessage();
        List<SouBean.ResultBean> result = data.getResult();

            if (message.contains("未查到相关电影")){
                Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
            }else {
                souRecy.setLayoutManager(new LinearLayoutManager(this));
                souRecy.setAdapter(new souAdapter(this,result));
            }
    }

    @Override
    public void onSouFailure(Throwable e) {

    }


}
