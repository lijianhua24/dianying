package com.bw.movie.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.ZuoBean;
import com.bw.movie.view.ZuoActivity;

import java.util.ArrayList;
import java.util.List;

public class ZuoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<ZuoBean.ResultBean> result;
    private setChange setChange;
    private double anInt;
    private ArrayList<Object> list;


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
            String status = result.get(position).getStatus();
            if (status.contains("1")){
                ((MyHolder) holder).zuo_che.setChecked(false);
            }else if (status.contains("3")){
                ((MyHolder) holder).zuo_che.setChecked(true);
            }else if (status.contains("2")){
                ((MyHolder) holder).zuo_che.setBackgroundResource(R.drawable.myyy_shape);
            }
            list = new ArrayList<>();
            ((MyHolder) holder).zuo_che.setOnClickListener(new View.OnClickListener() {

                private String seat;
                private String row;

                @Override
                public void onClick(View v) {
                    row = result.get(position).getRow();
                    seat = result.get(position).getSeat();
                    if (((MyHolder) holder).zuo_che.isChecked()){
                        anInt+=0.01;
                        String s = row +"-"+ seat;
                        list.add(s);
                        String join = TextUtils.join(",", list);
                        if (setChange!=null){
                            setChange.getChange(join,anInt);
                        }
                    }else {
                        double v1 = anInt - 0.01;
                        String s = row +"-"+ seat;
                        list.remove(s);
                        String join = TextUtils.join(",", list);
                        if (setChange!=null){
                            setChange.getChange(join,v1);
                        }
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
            private CheckBox zuo_che;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            zuo_che = itemView.findViewById(R.id.zuo_che);
        }
    }

    public void setListenter(setChange setChange){
        this.setChange = setChange;
    }
    public interface setChange{
        void getChange(String zuo,double anInt);
    }
}
