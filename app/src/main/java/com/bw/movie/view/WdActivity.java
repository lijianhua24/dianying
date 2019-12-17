package com.bw.movie.view;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.TimePickerView;
import com.bw.movie.Base.BaseActivity;
import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.bean.SCTXBean;
import com.bw.movie.bean.ShengRiBean;
import com.bw.movie.bean.UserBean;
import com.bw.movie.bean.XiuGaiShouJIBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.UserPresenter;
import com.bw.movie.utils.ImageUtil;
import com.facebook.drawee.view.SimpleDraweeView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public class WdActivity extends BaseActivity<UserPresenter> implements HomeConteract.UserContreact.IView {
    public static final String TAG = "WdActivity";

    @BindView(R.id.title_fanhui)
    ImageView titleFanhui;
    @BindView(R.id.title_biaoti)
    TextView titleBiaoti;
    @BindView(R.id.sView_user_mine)
    SimpleDraweeView sViewUserMine;
    @BindView(R.id.tv_name_user_mine)
    TextView tvNameUserMine;
    @BindView(R.id.tv_sex_user_mine)
    TextView tvSexUserMine;
    @BindView(R.id.tv_data_user_mine)
    TextView tvDataUserMine;
    @BindView(R.id.tv_email_user_mine)
    TextView tvEmailUserMine;
    @BindView(R.id.tv_email_user_phone)
    TextView tvEmailUserPhone;
    @BindView(R.id.shoujihao)
    LinearLayout shoujihao;
    private Dialog dialog;


    @Override
    protected UserPresenter providePresenter() {
        return new UserPresenter();
    }

    @Override
    protected void initData() {
        String sessionId = App.sharedPreferences.getString("sessionId", null);
        String userId = App.sharedPreferences.getString("userId", null);
       if (userId!=null && sessionId!=null){
           mPresenter.getUserPresenter(userId, sessionId);
       }else {
           Toast.makeText(this, "请先登录", Toast.LENGTH_SHORT).show();
           startActivity(new Intent(this,MainActivity.class));
           finish();
       }
    }

    @Override
    protected void initView() {

        titleBiaoti.setText("我的");
    }

    @Override
    protected int provideLayoutId() {
        return R.layout.activity_wd;
    }


    @Override
    public void onUserSuccess(UserBean data) {
        UserBean.ResultBean result = data.getResult();
        String email = result.getEmail();
        String headPic = result.getHeadPic();
        long lastLoginTime = result.getLastLoginTime();
        String nickName = result.getNickName();
        int sex = result.getSex();
        Uri parse = Uri.parse(headPic);
        sViewUserMine.setImageURI(parse);
        tvNameUserMine.setText(nickName);
        if (sex == 1) {
            tvSexUserMine.setText("男");
        } else {
            tvSexUserMine.setText("女");
        }
        String s = String.valueOf(lastLoginTime);
        tvDataUserMine.setText(DateFormatUtil.format(s));
        tvEmailUserMine.setText(email);
        String phone = result.getPhone();
        if (phone!=null){
            tvEmailUserPhone.setText(phone);
        }
    }

    @Override
    public void onUserFailure(Throwable e) {

    }

    @Override
    public void onSCTXSuccess(SCTXBean data) {
        Log.d(TAG, "onSCTXSuccess: " + data.getMessage());
        Toast.makeText(this, "" + data.getMessage(), Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String sessionId = sharedPreferences.getString("sessionId", "");
        String userId = sharedPreferences.getString("userId", "");
        mPresenter.getUserPresenter(userId, sessionId);
    }

    @Override
    public void onSCTXFailure(Throwable e) {
        Log.d(TAG, "onSCTXFailure: " + e);
    }

    @Override
    public void onXGSJHSuccess(XiuGaiShouJIBean data) {
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String sessionId = sharedPreferences.getString("sessionId", "");
        String userId = sharedPreferences.getString("userId", "");
        mPresenter.getUserPresenter(userId, sessionId);
        dialog.dismiss();

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


    @OnClick({R.id.title_fanhui, R.id.sView_user_mine, R.id.shoujihao,R.id.tv_data_user_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_fanhui:
                finish();
                break;
            case R.id.tv_data_user_mine:
                TimePickerView timePickerView = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY);
                // 设置是否循环
                timePickerView.setCyclic(true);
                timePickerView.setTime(new Date());
                timePickerView.setCyclic(false);
                timePickerView.setCancelable(true);
                //时间选择后回调
                timePickerView.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

                    @Override
                    public void onTimeSelect(Date date) {

                        String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
                        tvDataUserMine.setText(dateStr);
                        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                        String sessionId = sharedPreferences.getString("sessionId", "");
                        String userId = sharedPreferences.getString("userId", "");
                        mPresenter.getXGSRPresenter(userId,sessionId,dateStr);
                    }
                });
                timePickerView.show();
                break;
            case R.id.sView_user_mine:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 10);
                break;
            case R.id.shoujihao:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);

                // 创建一个view，并且将布局加入view中
                View inflate = LayoutInflater.from(this).inflate(
                        R.layout.shoujihao_layout, null, false);
                // 将view添加到builder中
                builder.setView(inflate);
                // 创建dialog
                dialog = builder.create();
                // 初始化控件，注意这里是通过view.findViewById
                final EditText edt = (EditText) inflate.findViewById(R.id.edt);
                Button confirm = (Button) inflate.findViewById(R.id.confirm);
                Button cancel = (Button) inflate.findViewById(R.id.cancel);
                // 设置button的点击事件及获取editview中的文本内容
                confirm.setOnClickListener(new android.view.View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        // TODO Auto-generated method stub
                        String str = edt.getText() == null ? "" : edt.getText()
                                .toString();
                        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                        String sessionId = sharedPreferences.getString("sessionId", "");
                        String userId = sharedPreferences.getString("userId", "");
                        mPresenter.getXGSJHPresenter(userId,sessionId,str);
                    }
                });
                // 取消按钮
                cancel.setOnClickListener(new android.view.View.OnClickListener() {

                    @Override
                    public void onClick(View arg0) {

                        dialog.dismiss();
                    }
                });
                dialog.show();
                break;
        }
    }


    public static class DateFormatUtil {
        public static String format(String date) {
            if (TextUtils.isEmpty(date))
                return null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Long time = new Long(date);
            return format.format(time);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 & resultCode == RESULT_OK) {
            Uri uri = data.getData();
            if (uri != null) {
                //用一个工具类获取图片的绝对路径,我会粘到下方
                String path = ImageUtil.getPath(this, uri);
                if (path != null) {
                    //转换为file类型
                    File file1 = new File(path);
                    //进行类型转换,因为在RetrofitService定义的是@Part MultipartBody.Part,所以要转成这样的格式
                    RequestBody requestBody1 = RequestBody.create(MediaType.parse("multipart/form-data"), file1);
                    MultipartBody.Part image1 = MultipartBody.Part.createFormData("image", file1.getName(), requestBody1);
                    //调用P层
                    SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
                    String sessionId = sharedPreferences.getString("sessionId", "");
                    String userId = sharedPreferences.getString("userId", "");
                    mPresenter.getSCTXPresenter(userId, sessionId, image1);
                }
            }
        }
    }
}
