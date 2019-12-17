package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.bw.movie.bean.YYPQBean;
import com.bw.movie.view.XQActivity;
import com.bw.movie.view.ZuoActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class TimesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;

    private View inflate;
    List<YYPQBean.ResultBean> result;
    public TimesAdapter(FragmentActivity activity, List<YYPQBean.ResultBean> result) {
        this.context = activity;
        this.result = result;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.times_layout, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
                ((MyViewHolder) holder).textView.setText(result.get(position).getName());
                ((MyViewHolder) holder).gengduo_text2.setText("导演:   "+result.get(position).getDirector());
                ((MyViewHolder) holder).gengduo_text3.setText("演员:   "+result.get(position).getStarring());
                ((MyViewHolder) holder).gengduo_text4.setText("评分:    "+result.get(position).getScore());
                Uri parse = Uri.parse(result.get(position).getImageUrl());
                ((MyViewHolder) holder).imageView.setImageURI(parse);

                ((MyViewHolder) holder).times_rela.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        String movieId = result.get(position).getMovieId();
                        intent.putExtra("dyid",movieId);
                        intent.putExtra("usedd","12");
                        intent.setClass(context, ZuoActivity.class);
                        context.startActivity(intent);
                    }
                });

        }
    }

    @Override
    public int getItemCount() {
       return result.size();

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView imageView;
        private final TextView textView,gengduo_text2,gengduo_text3,gengduo_text4;
        private final RelativeLayout times_rela;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.times_image);
            textView = itemView.findViewById(R.id.times_text1);
            gengduo_text2 = itemView.findViewById(R.id.times_text2);
            gengduo_text3 = itemView.findViewById(R.id.times_text3);
            gengduo_text4 = itemView.findViewById(R.id.times_text4);
            times_rela = itemView.findViewById(R.id.times_rela);

        }
    }

}

