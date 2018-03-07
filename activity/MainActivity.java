package com.example.administrator.day04_2.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.administrator.day04_2.R;

public class MainActivity extends AppCompatActivity {

    private ImageView main_img;
    private Handler handler =new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initTAsk();
    }

    private void initTAsk() {

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                    startActivity(new Intent(MainActivity.this,HomeActivity.class));
                    finish();
            }
        },3000);
    }

    private void initView() {
        main_img = (ImageView) findViewById(R.id.main_img);
    }
}
