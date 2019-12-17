package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bw.movie.R;
import com.bw.movie.bean.ChaYuYueBean;
import com.bw.movie.bean.WDDYPBean;
import com.bw.movie.view.WdYpActivity;
import com.bw.movie.view.YuYueActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;

public class WDYPAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    private  List<WDDYPBean.ResultBean> list;
    private setqupiao setqupiao;
    public WDYPAdapter(WdYpActivity wdYpActivity, List<WDDYPBean.ResultBean> result) {
        this.context = wdYpActivity;
        this.list = result;
    }


    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.wdyp_layout, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyViewHolder){
                ((MyViewHolder) holder).textView.setText(list.get(position).getMovieName());
                String seat = list.get(position).getSeat();
                String[] split = seat.split("-");
                ((MyViewHolder) holder).gengduo_text2.setText(split[0]+"排"+split[1]+"座");
                ((MyViewHolder) holder).gengduo_text3.setText(list.get(position).getCinemaName());
                ((MyViewHolder) holder).gengduo_text4.setText(list.get(position).getScreeningHall());
                ((MyViewHolder) holder).ticket_sun.setText(list.get(position).getbeginTime()+"--"+list.get(position).getEndTime());
                long createTime = list.get(position).getCreateTime();
                String format = DateFormatUtil.format(createTime + "");
                ((MyViewHolder) holder).ticket_time.setText(format);
                ((MyViewHolder) holder).ticket_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = list.get(position).getId();
                        if (setqupiao!=null){
                            setqupiao.getQuPiao(id);
                        }
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends XRecyclerView.ViewHolder {

        private final Button ticket_btn;
        private final TextView textView,gengduo_text2,gengduo_text3,gengduo_text4,ticket_time,ticket_sun;


        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.ticket_name);
            gengduo_text2 = itemView.findViewById(R.id.ticket_seat);
            gengduo_text3 = itemView.findViewById(R.id.ticket_cinema);
            gengduo_text4 = itemView.findViewById(R.id.ticket_yt);
            ticket_time = itemView.findViewById(R.id.ticket_time);
            ticket_sun = itemView.findViewById(R.id.ticket_sun);
            ticket_btn = itemView.findViewById(R.id.ticket_btn);
        }
    }
    public void setLisenter(setqupiao setqupiao){
        this.setqupiao = setqupiao;
    }
    public interface setqupiao{
        void getQuPiao(String id);
    }
    public static class DateFormatUtil {
        public static String format(String date) {
            if (TextUtils.isEmpty(date))
                return null;
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Long time = new Long(date);
            return format.format(time);
        }
    }
}
