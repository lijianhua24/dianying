package com.bw.movie.wxapi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.bw.movie.R;
import com.bw.movie.app.App;
import com.bw.movie.view.ZuoActivity;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends AppCompatActivity implements IWXAPIEventHandler {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
        App.api= WXAPIFactory.createWXAPI(this,"wxb3852e6a6b7d9516");
        App.api.handleIntent(getIntent(),this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
//        Logger.e("WXPayEntryActivity回调微信支付的结果errCode = " + resp.errCode);
       // Log.e("yaya", "WXPayEntryActivity回调微信支付的结果errCode=" + baseResp.errCode);
        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int errCode = baseResp.errCode;
            if (errCode == -1) {/*支付失败*/
                Toast.makeText(this, "支付失败", Toast.LENGTH_LONG).show();
                overridePendingTransition(0, 0);
            } else if (errCode == 0) {/*支付成功*/
                Toast.makeText(this, "支付成功", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(WXPayEntryActivity.this, ZuoActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("types", "1");
                bundle.putString("state", "支付");
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
                overridePendingTransition(0, 0);
            } else if (errCode == -2) {/*取消支付*/
                Toast.makeText(this, "取消支付", Toast.LENGTH_LONG).show();
                overridePendingTransition(0, 0);
            }
            finish();
        }
    }
}
