package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.CinemaBean;
import com.bw.movie.view.PaiQiActivity;
import com.bw.movie.view.YyXqActivity;

import java.util.List;

public class CinemaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private  List<CinemaBean.ResultBean> list;
    public CinemaAdapter(FragmentActivity activity, List<CinemaBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.cinema_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  MyHolder){
            ((MyHolder) holder).cinema_name.setText(list.get(position).getName());
            ((MyHolder) holder).cinema_name.setOnClickListener(new View.OnClickListener() {
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
    public class MyHolder extends RecyclerView.ViewHolder {
        private final TextView cinema_name;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            cinema_name = itemView.findViewById(R.id.cinema_name);
        }
    }
}
