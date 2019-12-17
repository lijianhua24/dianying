package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.bw.movie.R;
import com.bw.movie.bean.ChaYuYueBean;
import com.bw.movie.bean.GPJLBean;
import com.bw.movie.view.YuYueActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class ChaYuYueAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    private  List<ChaYuYueBean.ResultBean> list;


    public ChaYuYueAdapter(YuYueActivity activity, List<ChaYuYueBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.chayuyue_layout, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyViewHolder){
                ((MyViewHolder) holder).textView.setText(list.get(position).getName());
                long releaseTime = list.get(position).getReleaseTime();
                String format = DateFormatUtil.format(releaseTime + "");
                ((MyViewHolder) holder).gengduo_text2.setText(format+"上映");
                ((MyViewHolder) holder).gengduo_text3.setText(list.get(position).getWantSeeNum()+"人想看");
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
            imageView = itemView.findViewById(R.id.chayuyue_image);
            textView = itemView.findViewById(R.id.chayuyue_text1);
            gengduo_text2 = itemView.findViewById(R.id.chayuyue_text2);
            gengduo_text3 = itemView.findViewById(R.id.chayuyue_text3);
            gengduo_text4 = itemView.findViewById(R.id.chayuyue_text4);

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
