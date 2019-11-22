package com.bw.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.ZuoBean;
import com.bw.movie.view.ZuoActivity;

import java.util.List;

public class ZuoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<ZuoBean.ResultBean> result;
    public ZuoAdapter(ZuoActivity zuoActivity, List<ZuoBean.ResultBean> result) {
        this.context = zuoActivity;
        this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.zuo_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof MyHolder){
            String row = result.get(position).getRow();
            String seat = result.get(position).getSeat();
            String status = result.get(position).getStatus();
            String s = row + seat;
            if (status.contains("1")){
                ((MyHolder) holder).zuo_che.setChecked(false);
            }else {
                ((MyHolder) holder).zuo_che.setChecked(true);
            }
            }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
            private CheckBox zuo_che;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            zuo_che = itemView.findViewById(R.id.zuo_che);
        }
    }

}
