package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.XQBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class DYAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<XQBean.ResultBean.MovieDirectorBean> list;
    public DYAdapter(FragmentActivity activity, List<XQBean.ResultBean.MovieDirectorBean> movieDirector) {
        this.context = activity;
        this.list =  movieDirector;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.daoyan_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  MyHolder){
          if (list!=null){
              ((MyHolder) holder).name.setText(list.get(position).getName());
              Uri parse = Uri.parse(list.get(position).getPhoto());
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
        private final TextView name;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.daoyan_image);
            name = itemView.findViewById(R.id.daoyan_name);

        }
    }
}
