package com.bw.movie.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.YingTingBean;
import com.bw.movie.view.ZuoActivity;

import java.util.List;

public class YingTingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG="YingTingAdapter";
    private Context context;
    private List<YingTingBean.ResultBean> result;
    public YingTingAdapter(ZuoActivity zuoActivity, List<YingTingBean.ResultBean> result) {
        this.context = zuoActivity;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.yingting_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){
            Log.d(TAG, "onBindViewHolder: "+result.get(position).getBeginTime());
            ((MyHolder) holder).yingting_name.setText(result.get(position).getScreeningHall());
            ((MyHolder) holder).yingting_time1.setText(result.get(position).getBeginTime());
            ((MyHolder) holder).yingting_time2.setText(result.get(position).getEndTime());
            ((MyHolder) holder).yingting_linear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String hallId = result.get(position).getHallId();
                    String id = result.get(position).getId();
                    double fare = result.get(position).getFare();
                    if (onSetChange!=null){
                        onSetChange.getChange(hallId,id,fare);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
    private TextView yingting_time1,yingting_name,yingting_time2;
    private LinearLayout yingting_linear;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            yingting_name = itemView.findViewById(R.id.yingting_name);
            yingting_time1 = itemView.findViewById(R.id.yingting_time1);
            yingting_time2 = itemView.findViewById(R.id.yingting_time2);
            yingting_linear = itemView.findViewById(R.id.yingting_linear);
        }
    }
    onSetChange onSetChange;
    public void getListenter(onSetChange onSetChange){
        this.onSetChange = onSetChange;

    }
    public interface onSetChange{
        void getChange(String name,String id,double fare);
    }
}
