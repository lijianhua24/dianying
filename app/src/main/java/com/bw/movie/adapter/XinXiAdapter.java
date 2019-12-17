package com.bw.movie.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.XinXiBean;
import com.bw.movie.view.XinXiActivity;

import java.text.SimpleDateFormat;
import java.util.List;

public class XinXiAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<XinXiBean.ResultBean> list;
    public XinXiAdapter(XinXiActivity xinXiActivity, List<XinXiBean.ResultBean> result) {
        this.context = xinXiActivity;
        this.list = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.xinxi_layout, parent, false);
        return new XinXiHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof  XinXiHolder){
                ((XinXiHolder) holder).texts_name.setText(list.get(position).getTitle());
                ((XinXiHolder) holder).text_xianq.setText(list.get(position).getContent());
                ((XinXiHolder) holder).texts_time.setText(DateFormatUtil.format(list.get(position).getPushTime()+""));
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
     class XinXiHolder extends RecyclerView.ViewHolder {
        private final TextView texts_name,texts_time,text_xianq;
        public XinXiHolder(@NonNull View itemView) {
             super(itemView);
            texts_name = itemView.findViewById(R.id.texts_name);
            texts_time = itemView.findViewById(R.id.texts_time);
            text_xianq = itemView.findViewById(R.id.text_xianq);
         }
     }
    public static class DateFormatUtil{
        public static String format(String date){
            if (TextUtils.isEmpty(date))
                return null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日mm分ss秒");
            Long time = new Long(date);
            return format.format(time);
        }
    }
}
