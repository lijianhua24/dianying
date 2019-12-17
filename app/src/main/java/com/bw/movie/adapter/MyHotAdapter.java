package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.ChaBean;
import com.bw.movie.view.GouPiaoActivity;
import com.bw.movie.view.XQActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;


public class MyHotAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<ChaBean.ResultBean> list;
    private View inflate;

    public MyHotAdapter(Context activity, List<ChaBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.popularitem, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof  MyViewHolder){
                ((MyViewHolder) holder).textView.setText(list.get(position).getName());
                //Glide.with(context).load(list.get(position).getImageUrl()).into(((MyViewHolder) holder).imageView);
                Uri parse = Uri.parse(list.get(position).getImageUrl());
                ((MyViewHolder) holder).imageView.setImageURI(parse);
                ((MyViewHolder) holder).recy3_linear.setOnClickListener(new View.OnClickListener() {
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
                ((MyViewHolder) holder).recy3_but.setOnClickListener(new View.OnClickListener() {
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
        private final TextView textView;
        private final Button recy3_but;
        private final LinearLayout recy3_linear;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recy3_image2);
            textView = itemView.findViewById(R.id.recy3_text2);
            recy3_linear = itemView.findViewById(R.id.recy3_linear);
            recy3_but = itemView.findViewById(R.id.recy3_but);

        }
    }

}
