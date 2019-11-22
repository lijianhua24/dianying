package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.TjyyBean;
import com.bw.movie.view.YyXqActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class TjyyAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    private List<TjyyBean.ResultBean> list;

    public TjyyAdapter(FragmentActivity activity, List<TjyyBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.tjyy_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder) {
            ((MyHolder) holder).tjyy_name.setText(list.get(position).getName());
            ((MyHolder) holder).tjyy_price.setText(list.get(position).getAddress());
            //Glide.with(context).load(list.get(position).getLogo()).into(((MyHolder) holder).tjyy_image);
            Uri parse = Uri.parse(list.get(position).getLogo());
            ((MyHolder) holder).tjyy_image.setImageURI(parse);
            ((MyHolder) holder).tjyy_linear.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyHolder extends XRecyclerView.ViewHolder {
        private final TextView tjyy_price, tjyy_name;
        private final SimpleDraweeView tjyy_image;
        private final LinearLayout tjyy_linear;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tjyy_price = itemView.findViewById(R.id.tjyy_price);
            tjyy_name = itemView.findViewById(R.id.tjyy_name);
            tjyy_image = itemView.findViewById(R.id.tjyy_image);
            tjyy_linear = itemView.findViewById(R.id.tjyy_linear);
        }
    }
}
