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
import com.bw.movie.bean.SouBean;
import com.bw.movie.view.GouPiaoActivity;
import com.bw.movie.view.SouActivity;
import com.bw.movie.view.XQActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class souAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<SouBean.ResultBean> result;
    private View inflate;

    public souAdapter(SouActivity souActivity, List<SouBean.ResultBean> result) {
        this.context = souActivity;
        this.result = result;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.soulayout, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder) {
            if (result != null) {
                ((MyViewHolder) holder).textView.setText(result.get(position).getName());
                ((MyViewHolder) holder).gengduo_text2.setText("导演:   "+result.get(position).getDirector());
                ((MyViewHolder) holder).gengduo_text3.setText("演员:   "+result.get(position).getStarring());
                ((MyViewHolder) holder).gengduo_text4.setText("评分:    "+result.get(position).getScore());
                Uri parse = Uri.parse(result.get(position).getImageUrl());
                ((MyViewHolder) holder).imageView.setImageURI(parse);
                ((MyViewHolder) holder).sou_rela.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String movieId = result.get(position).getMovieId();
                        SharedPreferences name = context.getSharedPreferences("users", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = name.edit();
                        edit.putString("movieId",movieId);
                        edit.commit();
                        Intent intent = new Intent();
                        intent.setClass(context, XQActivity.class);
                        context.startActivity(intent);
                    }
                });
                ((MyViewHolder) holder).sou_but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String movieId = result.get(position).getMovieId();
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
        return result.size();

    }

    static class MyViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView imageView;
        private final RelativeLayout sou_rela;
        private final Button sou_but;
        private final TextView textView,gengduo_text2,gengduo_text3,gengduo_text4;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.sou_image);
            textView = itemView.findViewById(R.id.sou_text1);
            gengduo_text2 = itemView.findViewById(R.id.sou_text2);
            gengduo_text3 = itemView.findViewById(R.id.sou_text3);
            gengduo_text4 = itemView.findViewById(R.id.sou_text4);
            sou_rela = itemView.findViewById(R.id.sou_rela);
            sou_but = itemView.findViewById(R.id.sou_but);

        }
    }

}

