package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.bw.movie.R;
import com.bw.movie.bean.GPJLBean;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class YFAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    private List<GPJLBean.ResultBean> list;
    public YFAdapter(FragmentActivity activity, List<GPJLBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.yf_layout, parent, false);
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
            imageView = itemView.findViewById(R.id.yf_image);
            textView = itemView.findViewById(R.id.yf_text1);
            gengduo_text2 = itemView.findViewById(R.id.yf_text2);
            gengduo_text3 = itemView.findViewById(R.id.yf_text3);
            gengduo_text4 = itemView.findViewById(R.id.yf_text4);


        }
    }
}
