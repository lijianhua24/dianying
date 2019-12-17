package com.bw.movie.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.bw.movie.R;
import com.bw.movie.utils.ActivityCollectorUtil;

public class TuiChuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tui_chu);
        ActivityCollectorUtil.addActivity(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollectorUtil.addActivity(this);
    }
}
