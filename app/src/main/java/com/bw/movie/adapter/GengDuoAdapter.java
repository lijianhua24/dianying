package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.ChaBean;
import com.bw.movie.bean.JjBean;
import com.bw.movie.bean.ReBean;
import com.bw.movie.view.GouPiaoActivity;
import com.bw.movie.view.XQActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class GengDuoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ReBean.ResultBean> re;
    List<ChaBean.ResultBean> cha;
    List<JjBean.ResultBean> jj;
    private View inflate;

    public GengDuoAdapter(FragmentActivity activity, List<ReBean.ResultBean> re, List<ChaBean.ResultBean> cha, List<JjBean.ResultBean> jj) {
        this.context = activity;
        this.re = re;
        this.cha = cha;
        this.jj = jj;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.gengduolayout, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            if (re != null) {
                ((MyViewHolder) holder).textView.setText(re.get(position).getName());
                ((MyViewHolder) holder).gengduo_text2.setText("导演:   "+re.get(position).getDirector());
                ((MyViewHolder) holder).gengduo_text3.setText("演员:   "+re.get(position).getStarring());
                ((MyViewHolder) holder).gengduo_text4.setText("评分:    "+re.get(position).getScore());
                Uri parse = Uri.parse(re.get(position).getImageUrl());
                ((MyViewHolder) holder).imageView.setImageURI(parse);
                ((MyViewHolder) holder).recy3_linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String movieId = re.get(position).getMovieId();
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
                        String movieId = re.get(position).getMovieId();
                        SharedPreferences name = context.getSharedPreferences("users", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = name.edit();
                        edit.putString("movieId",movieId);
                        edit.commit();
                        Intent intent = new Intent();
                        intent.setClass(context, GouPiaoActivity.class);
                        context.startActivity(intent);
                    }
                });
            } else if (jj != null) {
                ((MyViewHolder) holder).textView.setText(jj.get(position).getName());
                Uri parse = Uri.parse(jj.get(position).getImageUrl());
                ((MyViewHolder) holder).imageView.setImageURI(parse);
               ((MyViewHolder) holder).gengduo_text2.setText("上架时间:   "+jj.get(position).getReleaseTime());
                ((MyViewHolder) holder).recy3_linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String movieId = jj.get(position).getMovieId();
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

                    }
                });
            } else if (cha != null) {
                ((MyViewHolder) holder).textView.setText(cha.get(position).getName());
                Uri parse = Uri.parse(cha.get(position).getImageUrl());
                ((MyViewHolder) holder).imageView.setImageURI(parse);
                ((MyViewHolder) holder).gengduo_text2.setText("导演:   "+cha.get(position).getDirector());
                ((MyViewHolder) holder).gengduo_text3.setText("演员:   "+cha.get(position).getStarring());
                ((MyViewHolder) holder).gengduo_text4.setText("评分:    "+cha.get(position).getScore());
                ((MyViewHolder) holder).recy3_linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String movieId = cha.get(position).getMovieId();
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
                        String movieId = cha.get(position).getMovieId();
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
    }

    @Override
    public int getItemCount() {
        if (jj != null) {
            return jj.size();
        } else if (re != null) {

            return re.size();
        }

        return cha.size();

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView imageView;
        private final TextView textView,gengduo_text2,gengduo_text3,gengduo_text4;
        private final RelativeLayout recy3_linear;
        private final Button recy3_but;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.gengduo_image);
            textView = itemView.findViewById(R.id.gengduo_text1);
            gengduo_text2 = itemView.findViewById(R.id.gengduo_text2);
            gengduo_text3 = itemView.findViewById(R.id.gengduo_text3);
            gengduo_text4 = itemView.findViewById(R.id.gengduo_text4);
            recy3_linear = itemView.findViewById(R.id.gengduo_rela);
            recy3_but = itemView.findViewById(R.id.gengduo_but);

        }
    }

}

