package com.example.administrator.day04_2.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.day04_2.R;
import com.example.administrator.day04_2.bean.MyBean;
import com.squareup.picasso.Picasso;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * author: 杨亮亮.
 * data: on 2018/3/6.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<MyBean.ResultBean.DataBean> mdata;
    private Context mcontext;
    private JIeKoe jiekou;
    int count = 0;
    public MyAdapter(List<MyBean.ResultBean.DataBean> mdata, Context mcontext) {
        this.mdata = mdata;
        this.mcontext = mcontext;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.rect_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, final int position) {


        if(TextUtils.isEmpty(mdata.get(position).getThumbnail_pic_s02())){
            holder.home_content.setText(mdata.get(position).getAuthor_name()+mdata.get(position).getCategory()+"/"+mdata.get(position).getDate());
            holder.home_title.setText(mdata.get(position).getTitle());
            Picasso.with(mcontext).load(mdata.get(position).getThumbnail_pic_s()).into(holder.img1);
            holder.img2.setVisibility(View.GONE);
            holder.img3.setVisibility(View.GONE);
            count+=1;
        }else if(TextUtils.isEmpty(mdata.get(position).getThumbnail_pic_s03())){
            holder.home_content.setText(mdata.get(position).getAuthor_name()+mdata.get(position).getCategory()+"/"+mdata.get(position).getDate());
            holder.home_title.setText(mdata.get(position).getTitle());
            Picasso.with(mcontext).load(mdata.get(position).getThumbnail_pic_s()).into(holder.img1);
            Picasso.with(mcontext).load(mdata.get(position).getThumbnail_pic_s02()).into(holder.img2);
            holder.img3.setVisibility(View.GONE);
            count+=2;
        }else{
            holder.home_content.setText(mdata.get(position).getAuthor_name()+mdata.get(position).getCategory()+"/"+mdata.get(position).getDate());
            holder.home_title.setText(mdata.get(position).getTitle());
            Picasso.with(mcontext).load(mdata.get(position).getThumbnail_pic_s()).into(holder.img1);
            Picasso.with(mcontext).load(mdata.get(position).getThumbnail_pic_s02()).into(holder.img2);
            Picasso.with(mcontext).load(mdata.get(position).getThumbnail_pic_s03()).into(holder.img3);
            count+=3;
        }

        if(position!=0){
            holder.banner.setVisibility(View.GONE);
        }else{
            List<String> images = new ArrayList<>();
            images.add("http://07.imgmini.eastday.com/mobile/20171109/20171109175525_4b706e469917a5184710455305391da5_4_mwpm_03200403.jpg");
            images.add("http://07.imgmini.eastday.com/mobile/20171109/20171109175525_cc33bfe071a38cc75687f5e62d840b96_5_mwpm_03200403.jpg");
            images.add("http://01.imgmini.eastday.com/mobile/20171109/20171109180324_8550569f222233a015e1cd7d70d2f2d4_2_mwpm_03200403.jpg");
            holder.banner.setImages(images).setImageLoader(new GlideImageLoader()).isAutoPlay(true).setDelayTime(1500).start();
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jiekou.onClick(position, count);
            }
        });
//0987654321`
        //wangwenting777777
    }
    public interface JIeKoe{
        void onClick(int position,int count);
        void train(List<MyBean.ResultBean.DataBean> mdata,int count);
    }
    public void setJIeKoe(JIeKoe jIeKoe){
        this.jiekou = jIeKoe;
    }
    @Override
    public int getItemCount() {
        return mdata.size();
    }
    public class GlideImageLoader extends ImageLoader {

        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {


            Picasso.with(context).load((String) path).into(imageView);

        }
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView home_title;
        private final TextView home_content;
        private final ImageView img1;
        private final ImageView img2;
        private final ImageView img3;
        private final Banner banner;

        public ViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
            home_title = itemView.findViewById(R.id.home_tv_title);
            home_content = itemView.findViewById(R.id.home_tv_content);
            img1 = itemView.findViewById(R.id.home_img1);
            img2 = itemView.findViewById(R.id.home_img2);
            img3 = itemView.findViewById(R.id.home_img3);
        }
    }
}
