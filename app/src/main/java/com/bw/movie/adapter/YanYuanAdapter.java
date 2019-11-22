package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.XQBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class YanYuanAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    public static final String TAG="YanYuanAdapter";
    private Context context;
    List<XQBean.ResultBean.MovieActorBean> list;
    public YanYuanAdapter(FragmentActivity activity, List<XQBean.ResultBean.MovieActorBean> movieActor) {
        this.context = activity;
        this.list =  movieActor;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.yanyuan_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  MyHolder){
           if (list!=null){
               ((MyHolder) holder).name.setText(list.get(position).getName());
               Uri parse = Uri.parse(list.get(position).getPhoto());
               Log.d(TAG, "onBindViewHolder: "+parse);
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
            image = itemView.findViewById(R.id.yanyuan_image);
            name = itemView.findViewById(R.id.yanyuan_name);

        }
    }
}
