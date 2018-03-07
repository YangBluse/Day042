package com.example.administrator.day04_2.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.day04_2.R;
import com.example.administrator.day04_2.adapter.MyAdapter;
import com.example.administrator.day04_2.bean.MyBean;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ThirdActivity extends AppCompatActivity {

    private  static  List<MyBean.ResultBean.DataBean> mdatas;
    private static int counts;
    private TextView tv_exit;
    private TextView tv_share;
    private Banner banners;
    private TextView tv_pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        initView();
        initData();
    }

    private void initData() {
       // Toast.makeText(this, dm.getTitle(), Toast.LENGTH_SHORT).show();
        List<String> images = new ArrayList<>();
        List<String> titlelist = new ArrayList<>();
        for (MyBean.ResultBean.DataBean mybean :mdatas){

            if(TextUtils.isEmpty(mybean.getThumbnail_pic_s02())){
                images.add(mybean.getThumbnail_pic_s());
                titlelist.add(mybean.getTitle());
            }else if(TextUtils.isEmpty(mybean.getThumbnail_pic_s03())){
                images.add(mybean.getThumbnail_pic_s());
                images.add(mybean.getThumbnail_pic_s02());
                titlelist.add(mybean.getTitle());
                titlelist.add(mybean.getTitle()+"2");
            }else{
                images.add(mybean.getThumbnail_pic_s());
                images.add(mybean.getThumbnail_pic_s02());
                images.add(mybean.getThumbnail_pic_s03());
                titlelist.add(mybean.getTitle());
                titlelist.add(mybean.getTitle()+"2");
                titlelist.add(mybean.getTitle()+"3");
            }
        }


        banners.setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE);
        banners.setImages(images).setBannerTitles(titlelist).setImageLoader(new GlideImageLoader()).isAutoPlay(true).setDelayTime(1500).start();

        tv_pager.setText(1+"/"+counts);
    }
    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {


            Picasso.with(context).load((String) path).into(imageView);

        }
    }
    public static void setJieKoy() {
        HomeActivity.setJIeKoe(new MyAdapter.JIeKoe() {
            @Override
            public void onClick(int position,int count) {

            }

            @Override
            public void train(List<MyBean.ResultBean.DataBean> mdata,int count) {

                mdatas = mdata;
                counts = count;
            }
        });
    }

    private void initView() {
        tv_exit = (TextView) findViewById(R.id.tv_exit);
        tv_share = (TextView) findViewById(R.id.tv_share);
        banners = (Banner) findViewById(R.id.banners);
        tv_pager = (TextView) findViewById(R.id.tv_pager);
    }
}
