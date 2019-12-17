package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.GPJLBean;
import com.bw.movie.view.GouPiaoActivity;
import com.bw.movie.view.GouPiaoJiLuActivity;
import com.bw.movie.view.YPXQActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class DFAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    private List<GPJLBean.ResultBean> list;
    private onorderId onorderId;
    public DFAdapter(FragmentActivity activity, List<GPJLBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.df_layout, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyViewHolder){
                ((MyViewHolder) holder).textView.setText(list.get(position).getMovieName());
                ((MyViewHolder) holder).gengduo_text2.setText("订单号:   "+list.get(position).getOrderId());
                ((MyViewHolder) holder).gengduo_text3.setText("创建时间:   "+ XinXiAdapter.DateFormatUtil.format(list.get(position).getCreateTime()+""));
                Uri parse = Uri.parse(list.get(position).getImageUrl());
                ((MyViewHolder) holder).imageView.setImageURI(parse);
                ((MyViewHolder) holder).df_but.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String orderId = list.get(position).getOrderId();
                        if (onorderId!=null){
                            onorderId.getorderId(orderId);
                        }
                    }
                });
                ((MyViewHolder) holder).imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String orderId = list.get(position).getOrderId();
                        Intent intent = new Intent();
                        intent.putExtra("orderId",orderId);
                        intent.setClass(context, YPXQActivity.class);
                        context.startActivity(intent);
                    }
                });
            }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends XRecyclerView.ViewHolder {

        private final SimpleDraweeView imageView;
        private final Button df_but;
        private final TextView textView,gengduo_text2,gengduo_text3,gengduo_text4;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.df_image);
            textView = itemView.findViewById(R.id.df_text1);
            gengduo_text2 = itemView.findViewById(R.id.df_text2);
            gengduo_text3 = itemView.findViewById(R.id.df_text3);
            gengduo_text4 = itemView.findViewById(R.id.df_text4);
            df_but = itemView.findViewById(R.id.df_but);
        }
    }
    public void getChange(onorderId onorderId){
        this.onorderId = onorderId;
    }
    public interface onorderId{
        void getorderId(String orderId);
    }
}
