package com.bw.movie.fragment;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.bw.movie.Base.BaseFragment;
import com.bw.movie.R;
import com.bw.movie.adapter.MyAdapter;
import com.bw.movie.app.App;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.ChaBean;
import com.bw.movie.bean.JjBean;
import com.bw.movie.bean.ReBean;
import com.bw.movie.bean.YuYueBean;
import com.bw.movie.contract.HomeConteract;
import com.bw.movie.presenter.DiYingPresenter;
import com.bw.movie.view.MainActivity;
import com.bw.movie.view.SouActivity;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class DianYingFragment extends BaseFragment<DiYingPresenter> implements HomeConteract.Dianying.IView {

    public static final String TAG = "DianYingFragment";
    @BindView(R.id.dianying_recy)
    RecyclerView dianyingRecy;
    @BindView(R.id.diangying_dingwei)
    ImageView diangyingDingwei;
    @BindView(R.id.dianying_weizhi)
    TextView dianyingWeizhi;
    @BindView(R.id.main_sou)
    ImageView mainSou;
    @BindView(R.id.meiyou_tu)
    ImageView meiyouTu;
    @BindView(R.id.meiyou_xinxi)
    TextView meiyouXinxi;
    @BindView(R.id.zong)
    LinearLayout zong;
    @BindView(R.id.dianying_shua)
    SmartRefreshLayout dianyingShua;

    private List<ReBean.ResultBean> relist;
    private List<ChaBean.ResultBean> cha;
    private List<JjBean.ResultBean> jj;
    private List<BannerBean.ResultBean> banner;
    private AMapLocationClient mlocationClient;
    private AMapLocationClientOption mLocationOption;
    private int GPS_REQUEST_CODE = 10;

    @Override
    protected DiYingPresenter providePresenter() {
        return new DiYingPresenter();
    }

    @Override
    protected void initData() {

        dianyingRecy.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    @Override
    protected void initView() {

        if (hasNetwork()) {
            String sessionId = getActivity().getIntent().getStringExtra("sessionId");
            String userId = getActivity().getIntent().getStringExtra("userId");
            mPresenter.getChaPresenter("1", "5");
            mPresenter.getJjPresenter(userId, sessionId, "1", "5");
            mPresenter.getRePresenter("1", "5");
            mPresenter.getBannerPresenter();
        } else {
            zong.setVisibility(View.VISIBLE);
            meiyouTu.setImageResource(R.mipmap.wuwang);
            meiyouXinxi.setText("暂无网络");
        }
        dianyingShua.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                String sessionId = getActivity().getIntent().getStringExtra("sessionId");
                String userId = getActivity().getIntent().getStringExtra("userId");
                mPresenter.getChaPresenter("1", "5");
                mPresenter.getJjPresenter(userId, sessionId, "1", "5");
                mPresenter.getRePresenter("1", "5");
                mPresenter.getBannerPresenter();
                dianyingShua.finishRefresh(2000);
            }
        });
       /* dianyingShua.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                dianyingShua.finishLoadMore(2000);
            }
        });*/

    }

    @Override
    protected int provideLayoutId() {
        return R.layout.fragment_movie;
    }

    @Override
    public void onReSuccess(ReBean data) {
        Log.d(TAG, "ReBean: " + data.getMessage());
        relist = data.getResult();
        MyAdapter myAdapter = new MyAdapter(getActivity(), relist, cha, jj, banner);
        dianyingRecy.setAdapter(myAdapter);

    }

    @Override
    public void onReFailure(Throwable e) {

    }

    @Override
    public void onChaSuccess(ChaBean data) {
        Log.d(TAG, "ChaBean: " + data.getMessage());
        cha = data.getResult();
        MyAdapter myAdapter = new MyAdapter(getActivity(), relist, cha, jj, banner);
        dianyingRecy.setAdapter(myAdapter);
        myAdapter.getLisenter(new MyAdapter.setChanges() {
            @Override
            public void getChanges(String movieid) {

                String sessionId = App.sharedPreferences.getString("sessionId", null);
                String userId = App.sharedPreferences.getString("userId", null);
                if (userId!=null && sessionId!=null){
                    mPresenter.getYuYuePresenter(userId, sessionId, movieid);
                }else {
                    Toast.makeText(getActivity(), "请先登录", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getActivity(), MainActivity.class));
                }
                myAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public void onChaFailure(Throwable e) {

    }

    @Override
    public void onJjuccess(JjBean data) {
        Log.d(TAG, "JjBean: " + data.getMessage());
        jj = data.getResult();
        MyAdapter myAdapter = new MyAdapter(getActivity(), relist, cha, jj, banner);
        dianyingRecy.setAdapter(myAdapter);
    }

    @Override
    public void onJjFailure(Throwable e) {

    }

    @Override
    public void onBannerSuccess(BannerBean data) {
        Log.d(TAG, "BannerBean: " + data.getMessage());
        banner = data.getResult();
        MyAdapter myAdapter = new MyAdapter(getActivity(), relist, cha, jj, banner);
        dianyingRecy.setAdapter(myAdapter);


    }

    @Override
    public void onBannerFailure(Throwable e) {

    }

    @Override
    public void onYuYueSuccess(YuYueBean data) {
        String message = data.getMessage();
    }

    @Override
    public void onYuYueFailure(Throwable e) {

    }

    @OnClick({R.id.diangying_dingwei, R.id.main_sou})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.diangying_dingwei:
                if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
                    //开启定位权限,200是标识码
                    ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);
                } else {
                    MyLocation(getActivity());//开始定位
                }
                break;
            case R.id.main_sou:
                startActivity(new Intent(getActivity(), SouActivity.class));
                break;
        }
    }

    /*@OnClick(R.id.main_sou)
    public void onViewClicked() {

    }*/
    /*
     * 定位 判断是否开启权限
     * 有 执行
     * 无 弹框提示进入设置开启权限
     * */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 200://刚才的识别码
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {//用户同意权限,执行我们的操作
                    MyLocation(getActivity());//开始定位
                } else {//用户拒绝之后,当然我们也可以弹出一个窗口,直接跳转到系统设置页面
                    Toast.makeText(getActivity(), "未开启定位权限,请手动到设置去开启权限", Toast.LENGTH_LONG).show();
                }
                break;
            default:
                break;
        }
    }

    /*
     * 定位成功回调信息，设置相关消息
     * */
    public void MyLocation(Context context) {
        mlocationClient = new AMapLocationClient(context);
        mLocationOption = new AMapLocationClientOption();
        mlocationClient.setLocationListener(new AMapLocationListener() {
            @Override
            public void onLocationChanged(AMapLocation amapLocation) {
                try {
                    if (amapLocation != null) {
                        if (amapLocation.getErrorCode() == 0) {
                            //定位成功回调信息，设置相关消息

                            //获取当前定位结果来源，如网络定位结果，详见定位类型表
                            Log.i("定位类型", amapLocation.getLocationType() + "");
                            Log.i("获取纬度", amapLocation.getLatitude() + "");
                            Log.i("获取经度", amapLocation.getLongitude() + "");
                            Log.i("获取精度信息", amapLocation.getAccuracy() + "");
                            //如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
                            Log.i("地址", amapLocation.getAddress());
                            Log.i("国家信息", amapLocation.getCountry());
                            Log.i("省信息", amapLocation.getProvince());
                            Log.i("城市信息", amapLocation.getCity());
                            Log.i("城区信息", amapLocation.getDistrict());
                            Log.i("街道信息", amapLocation.getStreet());
                            Log.i("街道门牌号信息", amapLocation.getStreetNum());
                            Log.i("城市编码", amapLocation.getCityCode());
                            Log.i("地区编码", amapLocation.getAdCode());
                            Log.i("获取当前定位点的AOI信息", amapLocation.getAoiName());
                            Log.i("获取当前室内定位的建筑物Id", amapLocation.getBuildingId());
                            Log.i("获取当前室内定位的楼层", amapLocation.getFloor());
                            Log.i("获取GPS的当前状态", amapLocation.getGpsAccuracyStatus() + "");
                            //获取定位时间
                            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                            Date date = new Date(amapLocation.getTime());
                            Log.i("获取定位时间", df.format(date));
                            dianyingWeizhi.setText(amapLocation.getDistrict());
                            // 停止定位
                            mlocationClient.stopLocation();
                        } else {
                            //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                            Log.e("AmapError", "location Error, ErrCode:"
                                    + amapLocation.getErrorCode() + ", errInfo:"
                                    + amapLocation.getErrorInfo());
                            Toast.makeText(getActivity(), "没有权限，请打开权限...", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(getActivity())
                                    .setTitle("定位服务未开启")
                                    .setMessage("请在系统设置中开启定位服务\n" +
                                            "以便为您提供更好的服务")
                                    .setPositiveButton("设置", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                                            startActivityForResult(intent, GPS_REQUEST_CODE);
                                        }
                                    })
                                    .show();
                        }
                    }
                } catch (Exception e) {
                }

            }
        });
        //设置定位模式为高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        mLocationOption.setInterval(5000);
        //设置定位参数
        mlocationClient.setLocationOption(mLocationOption);
        //启动定位
        mlocationClient.startLocation();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {//未开启定位权限
            //开启定位权限,200是标识码
            ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 200);

        } else {
            MyLocation(getActivity());//开始定位
            //Toast.makeText(getActivity(),"已开启定位权限",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        // 停止定位
        if (null != mlocationClient) {
            mlocationClient.stopLocation();
        }

    }

    //内存泄露和定位
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mlocationClient) {
            /**
             * 如果AMapLocationClient是在当前Activity实例化的，
             * 在Activity的onDestroy中一定要执行AMapLocationClient的onDestroy
             */
            mlocationClient.onDestroy();
            mlocationClient = null;
        }
    }
}
