package com.bw.movie.wxapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.view.MainActivity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {
    public static final String TAG="WXEntryActivity";
    private String CODE;
    public static boolean isOK = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        App.api= WXAPIFactory.createWXAPI(this,"wxb3852e6a6b7d9516");
        App.api.handleIntent(getIntent(),this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        Log.d(TAG, "onResp: 123"+baseResp.errCode);
        int errOk = BaseResp.ErrCode.ERR_OK;
        Log.d(TAG, "onResp: 1234"+errOk);

        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                SendAuth.Resp sendResp= (SendAuth.Resp) baseResp;
                CODE = sendResp.code;
                isOK=true;
//              WXEntryActivity.this.finish();
                Log.d(TAG, "onResp11: "+CODE);
                Intent intent = new Intent(WXEntryActivity.this, MainActivity.class);
                intent.putExtra("code",CODE);
                startActivity(intent);
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                Toast.makeText(WXEntryActivity.this,"用户拒绝授权",Toast.LENGTH_SHORT).show();
                isOK=false;
                WXEntryActivity.this.finish();
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                Toast.makeText(this, "用户取消授权", Toast.LENGTH_SHORT).show();
                WXEntryActivity.this.finish();
                break;
            default:
                isOK=false;
                WXEntryActivity.this.finish();
                break;
        }
    }
}

