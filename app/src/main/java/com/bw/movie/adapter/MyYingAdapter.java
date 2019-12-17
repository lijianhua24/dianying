package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.app.Constant;
import com.bw.movie.bean.ReBean;
import com.bw.movie.view.GouPiaoActivity;
import com.bw.movie.view.XQActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

/**
 * @author: 张恩
 * @description:
 * @date :2019/11/12 18:54
 */
public class MyYingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG="MyYingAdapter";
    private Context context;
    List<ReBean.ResultBean> list;
    private View inflate;

    public MyYingAdapter(Context activity, List<ReBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.nowshowingitem, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof  MyViewHolder){
                Log.d(TAG, "onBindViewHolder: "+list.get(position).getName());
                ((MyViewHolder) holder).recy_text1.setText(list.get(position).getName());
                ((MyViewHolder) holder).textView.setText(list.get(position).getScore()+"");

                Uri parse = Uri.parse(list.get(position).getImageUrl());
                ((MyViewHolder) holder).imageView.setImageURI(parse);
                ((MyViewHolder) holder).now_rela.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String movieId = list.get(position).getMovieId();
                        SharedPreferences name = context.getSharedPreferences("users", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = name.edit();
                        edit.putString("movieId",movieId);
                        edit.commit();
                        Intent intent = new Intent();
                        intent.setClass(context, XQActivity.class);
                        context.startActivity(intent);
                    }
                });
                ((MyViewHolder) holder).recy_but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String movieId = list.get(position).getMovieId();
                        SharedPreferences name = context.getSharedPreferences("users", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = name.edit();
                        edit.putString("movieId",movieId);
                        edit.commit();
                        Intent intent = new Intent();
                        intent.setClass(context, GouPiaoActivity.class);
                        context.startActivity(intent);
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView imageView;
        private final RelativeLayout now_rela;
        private final Button recy_but;
        private final TextView textView,recy_text1;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recy_image);
            textView = itemView.findViewById(R.id.recy_text1);
            recy_text1 = itemView.findViewById(R.id.recy_text2);
            now_rela = itemView.findViewById(R.id.now_rela);
            recy_but = itemView.findViewById(R.id.recy_but);
        }
    }
    public setChangeAdapter setChangeAdapter;
    public void getLinsenter(setChangeAdapter setChangeAdapter){
        this.setChangeAdapter = setChangeAdapter;
    }
    public interface setChangeAdapter{
        void getChang(String movie);
    }
}
