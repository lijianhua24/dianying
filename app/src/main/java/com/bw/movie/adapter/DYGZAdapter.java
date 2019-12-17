package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.bw.movie.R;
import com.bw.movie.bean.DYGZBean;
import com.bw.movie.bean.GPJLBean;
import com.bw.movie.view.XQActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class DYGZAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    private List<DYGZBean.ResultBean> list;

    public DYGZAdapter(FragmentActivity activity, List<DYGZBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dygz_layout, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyViewHolder){
                ((MyViewHolder) holder).textView.setText(list.get(position).getName());
                ((MyViewHolder) holder).gengduo_text2.setText("导演:   "+list.get(position).getDirector());
                ((MyViewHolder) holder).gengduo_text3.setText("主演:   "+list.get(position).getStarring());
                ((MyViewHolder) holder).gengduo_text4.setText("评分:   "+list.get(position).getScore());
                Uri parse = Uri.parse(list.get(position).getImageUrl());
                ((MyViewHolder) holder).imageView.setImageURI(parse);
                ((MyViewHolder) holder).dygz_rela.setOnClickListener(new View.OnClickListener() {
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
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends XRecyclerView.ViewHolder {

        private final SimpleDraweeView imageView;
        private final RelativeLayout dygz_rela;
        private final TextView textView,gengduo_text2,gengduo_text3,gengduo_text4;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.dygz_image);
            textView = itemView.findViewById(R.id.dygz_text1);
            gengduo_text2 = itemView.findViewById(R.id.dygz_text2);
            gengduo_text3 = itemView.findViewById(R.id.dygz_text3);
            gengduo_text4 = itemView.findViewById(R.id.dygz_text4);
            dygz_rela = itemView.findViewById(R.id.dygz_rela);


        }
    }
}
