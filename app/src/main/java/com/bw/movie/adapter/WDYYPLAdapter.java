package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
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
import com.bw.movie.bean.YYPLBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WDYYPLAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<YYPLBean.ResultBean> list;

    public WDYYPLAdapter(FragmentActivity activity, List<YYPLBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.wdyypl_layout, null);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder){
            Uri parse = Uri.parse(list.get(position).getLogo());
            ((ViewHolder) holder).wdyypl_img.setImageURI(parse);
            ((ViewHolder) holder).wdyypl_name.setText(list.get(position).getCinemaName());
            ((ViewHolder) holder).wdyypl_dizhi.setText(list.get(position).getAddress());
            ((ViewHolder) holder).wdyypl_pinglun.setText(list.get(position).getMyCommentContent());
            Date date = new Date(list.get(position).getCommentTime());
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd  hh:mm");
            ((ViewHolder) holder).wdyypl_time.setText(simpleDateFormat.format(date));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        private final SimpleDraweeView wdyypl_img;
        private final RatingBar rat_filmcinecism;
        private final TextView wdyypl_name,wdyypl_dizhi,wdyypl_juli,wdyypl_pinglun,wdyypl_time;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            wdyypl_img = itemView.findViewById(R.id.wdyypl_img);
            wdyypl_name = itemView.findViewById(R.id.wdyypl_name);
            wdyypl_dizhi = itemView.findViewById(R.id.wdyypl_dizhi);
            wdyypl_juli = itemView.findViewById(R.id.wdyypl_juli);
            wdyypl_pinglun = itemView.findViewById(R.id.wdyypl_pinglun);
            wdyypl_time = itemView.findViewById(R.id.wdyypl_time);
            rat_filmcinecism = itemView.findViewById(R.id.rat_filmcinecism);

        }
    }
}
