package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bw.movie.R;
import com.bw.movie.bean.KanGuoBean;
import com.bw.movie.view.KanGuoActivity;
import com.bw.movie.view.YuYueActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class KgAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    List<KanGuoBean.ResultBean> list;
    public KgAdapter(KanGuoActivity kanGuoActivity, List<KanGuoBean.ResultBean> result) {
        this.context = kanGuoActivity;
        this.list  = result;
    }


    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.kg_layout, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyViewHolder){
                ((MyViewHolder) holder).textView.setText(list.get(position).getMovieName());
                String endTime = list.get(position).getEndTime();
                String format = DateFormatUtil.format(endTime);
                ((MyViewHolder) holder).gengduo_text3.setText(format);
                Uri parse = Uri.parse(list.get(position).getImageUrl());
                ((MyViewHolder) holder).imageView.setImageURI(parse);
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends XRecyclerView.ViewHolder {

        private final SimpleDraweeView imageView;
        private final TextView textView,gengduo_text2,gengduo_text3,gengduo_text4;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.kg_image);
            textView = itemView.findViewById(R.id.kg_text1);
            gengduo_text2 = itemView.findViewById(R.id.kg_text2);
            gengduo_text3 = itemView.findViewById(R.id.kg_text3);
            gengduo_text4 = itemView.findViewById(R.id.kg_text4);


        }
    }
    public static class DateFormatUtil{
        public static String format(String date){
            if (TextUtils.isEmpty(date))
                return null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
            Long time = new Long(date);
            return format.format(time);
        }
    }
}
