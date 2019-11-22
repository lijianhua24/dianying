package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.XQBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class JuZhaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    List<String> list;
    public JuZhaoAdapter(FragmentActivity activity, List<String> posterList) {
        this.context = activity;
        this.list =  posterList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.juzhao_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  MyHolder){
            if (list!=null){
                Uri parse = Uri.parse(list.get(position));
                ((MyHolder) holder).image.setImageURI(parse);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView image;

        public MyHolder( View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.juzhao_image);

        }
    }
}
