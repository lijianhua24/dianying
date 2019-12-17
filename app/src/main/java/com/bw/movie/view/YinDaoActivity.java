package com.bw.movie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bw.movie.R;

import java.util.Timer;
import java.util.TimerTask;

public class YinDaoActivity extends AppCompatActivity {
    private Handler handler = new Handler(){
        @Override
        public void handleMessage( Message msg) {
            super.handleMessage(msg);
            Intent intent = new Intent(YinDaoActivity.this,HomeActivity.class);
            startActivity(intent);
            finish();
        }
    };
    Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what=0;
            handler.sendMessage(message);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yin_dao);
        timer.schedule(timerTask,2000);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }
}
