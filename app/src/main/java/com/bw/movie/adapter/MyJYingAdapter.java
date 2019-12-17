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
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.JjBean;
import com.bw.movie.view.HomeActivity;
import com.bw.movie.view.XQActivity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: 张恩
 * @description:
 * @date :2019/11/12 18:54
 */
public class MyJYingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<JjBean.ResultBean> list;
    private View inflate;

    public MyJYingAdapter(Context activity, List<JjBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        inflate = LayoutInflater.from(context).inflate(R.layout.comingsoonitem, null);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof  MyViewHolder){
                ((MyViewHolder) holder).textView.setText(list.get(position).getName());
                Date date = new Date(list.get(position).getReleaseTime());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
              ((MyViewHolder) holder).textView1.setText(simpleDateFormat.format(date)+"上映");
                ((MyViewHolder) holder).textView2.setText(list.get(position).getWantSeeNum()+"人想看");
                String whetherReserve = list.get(position).getWhetherReserve();
                int i = Integer.parseInt(whetherReserve);
                if (i==1){
                    ((MyViewHolder) holder).recy2_but.setText("已预约");
                    ((MyViewHolder) holder).recy2_but.setBackgroundResource(R.drawable.but2);
                }
                // Glide.with(context).load(list.get(position).getImageUrl()).into(((MyViewHolder) holder).imageView);
                Uri parse = Uri.parse(list.get(position).getImageUrl());
                ((MyViewHolder) holder).imageView.setImageURI(parse);
                ((MyViewHolder) holder).recy2_rela.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        String movieId = list.get(position).getMovieId();
                        SharedPreferences name = context.getSharedPreferences("users", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = name.edit();
                        edit.putString("movieId",movieId);
                        edit.commit();
                        intent.setClass(context, XQActivity.class);
                        context.startActivity(intent);
                    }
                });
                ((MyViewHolder) holder).recy2_but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String movieId = list.get(position).getMovieId();
                                setChangeAdapter.getChang(movieId);
                                notifyDataSetChanged();
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
        private final RelativeLayout recy2_rela;
        private final TextView textView1;
        private final TextView textView2;
        private final Button recy2_but;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.recy2_image);
            textView = itemView.findViewById(R.id.recy2_text1);
            textView1 = itemView.findViewById(R.id.recy2_text2);
            textView2 = itemView.findViewById(R.id.recy2_text3);
            recy2_rela = itemView.findViewById(R.id.recy2_rela);
            recy2_but = itemView.findViewById(R.id.recy2_but);
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
