package com.example.administrator.day04_2.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.administrator.day04_2.R;
import com.example.administrator.day04_2.adapter.MyAdapter;
import com.example.administrator.day04_2.bean.MyBean;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HomeActivity extends AppCompatActivity {

    private RecyclerView recy_view;
    private List<MyBean.ResultBean.DataBean> data;
    public static MyAdapter.JIeKoe jiekou;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
        initBanner();
        initRecy();
    }

    private void initRecy() {

        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://172.16.54.12:8080/h/train/data.json").build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                String jsonstr = response.body().string();
                MyBean myBean = new Gson().fromJson(jsonstr, MyBean.class);
                data = myBean.getResult().getData();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        initAdapter();
                    }
                });
            }
        });
    }


    private void initAdapter() {

        Toast.makeText(this, data.size()+"", Toast.LENGTH_SHORT).show();
        MyAdapter myAdapter = new MyAdapter(data, HomeActivity.this);
        LinearLayoutManager manager = new LinearLayoutManager(HomeActivity.this);
        recy_view.setLayoutManager(manager);
        recy_view.setAdapter(myAdapter);
        initListener(myAdapter);
    }

    private void initListener(MyAdapter myAdapter) {
        myAdapter.setJIeKoe(new MyAdapter.JIeKoe() {
            @Override
            public void onClick(int position,int count) {
                startActivity(new Intent(HomeActivity.this,ThirdActivity.class));
                ThirdActivity.setJieKoy();
                jiekou.train(data,count);
            }

            @Override
            public void train(List<MyBean.ResultBean.DataBean> mdata,int count) {

            }
        });
    }

    public static void setJIeKoe(MyAdapter.JIeKoe jIeKoe){
        jiekou = jIeKoe;

    }
    private void initBanner() {



    }


    private void initView() {
        recy_view = (RecyclerView) findViewById(R.id.recy_view);

    }
}
