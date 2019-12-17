package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.DYPLBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WDDYPLAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG="WDDYPLAdapter";
    private Context context;
    List<DYPLBean.ResultBean> list;
    public WDDYPLAdapter(FragmentActivity activity, List<DYPLBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.wddypl_layout, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            Uri parse = Uri.parse(list.get(position).getImageUrl());
            ((ViewHolder) holder).chayingping_img.setImageURI(parse);
            ((ViewHolder) holder).chayingping_name.setText(list.get(position).getMovieName());
            ((ViewHolder) holder).chayingping_dizhi.setText("导演:     "+list.get(position).getDirector());
            ((ViewHolder) holder).chayingping_juli.setText("演员:      "+list.get(position).getStarring());
            ((ViewHolder) holder).chayingping_pinglun.setText(list.get(position).getMyCommentContent());
            Date date = new Date(list.get(position).getCommentTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd  hh:mm");
            ((ViewHolder) holder).chayingping_time.setText(simpleDateFormat.format(date));
            String movieScore = list.get(position).getMovieScore();
            Float aFloat = Float.valueOf(movieScore);
            Log.d(TAG, "onBindViewHolder: "+aFloat);
            ((ViewHolder) holder).rat_filmcinecism.setRating(aFloat);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView chayingping_img;
        private final RatingBar rat_filmcinecism;
        private final TextView chayingping_name,chayingping_dizhi,chayingping_juli,chayingping_pinglun,chayingping_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            chayingping_img = itemView.findViewById(R.id.chayingping_img);
            chayingping_name = itemView.findViewById(R.id.chayingping_name);
            chayingping_dizhi = itemView.findViewById(R.id.chayingping_dizhi);
            chayingping_juli = itemView.findViewById(R.id.chayingping_juli);
            chayingping_pinglun = itemView.findViewById(R.id.chayingping_pinglun);
            chayingping_time = itemView.findViewById(R.id.chayingping_time);
            rat_filmcinecism = itemView.findViewById(R.id.rat_filmcinecism);

        }
    }
}
