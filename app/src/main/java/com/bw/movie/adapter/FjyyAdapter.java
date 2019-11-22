package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.FjYyBean;
import com.bw.movie.view.YyXqActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class FjyyAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    List<FjYyBean.ResultBean> list;
    public FjyyAdapter(FragmentActivity activity, List<FjYyBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.fjyy_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){
            if (list!=null){
                ((MyHolder) holder).tjyy_name.setText(list.get(position).getName());
                ((MyHolder) holder).tjyy_price.setText(list.get(position).getAddress());
                ((MyHolder) holder).fjyy_mi.setText(list.get(position).getCommentTotal()+"km");
                Uri parse = Uri.parse(list.get(position).getLogo());
                ((MyHolder) holder).tjyy_image.setImageURI(parse);
                ((MyHolder) holder).fjyy_linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = list.get(position).getId();
                        SharedPreferences sharedPreferences = context.getSharedPreferences("yyid", Context.MODE_PRIVATE);
                        SharedPreferences.Editor edit = sharedPreferences.edit();
                        edit.putString("id",id);
                        edit.commit();
                        Intent intent = new Intent();
                        intent.setClass(context, YyXqActivity.class);
                        context.startActivity(intent);
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyHolder extends XRecyclerView.ViewHolder{
        private final TextView tjyy_price, tjyy_name,fjyy_mi;
        private final SimpleDraweeView tjyy_image;
        private final LinearLayout fjyy_linear;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tjyy_price = itemView.findViewById(R.id.fjyy_price);
            tjyy_name = itemView.findViewById(R.id.fjyy_name);
            tjyy_image = itemView.findViewById(R.id.fjyy_image);
            fjyy_mi = itemView.findViewById(R.id.fjyy_mi);
            fjyy_linear = itemView.findViewById(R.id.fjyy_linear);
        }
    }
}
