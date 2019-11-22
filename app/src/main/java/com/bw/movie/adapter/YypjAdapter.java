package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.YYPJBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class YypjAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<YYPJBean.ResultBean> list;
    public YypjAdapter(FragmentActivity activity, List<YYPJBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.yypj_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){
            if (list!=null){
               ((MyHolder) holder).name.setText(list.get(position).getCommentUserName());
               ((MyHolder) holder).dianzan.setText(list.get(position).getCommentUserId());
               ((MyHolder) holder).pinglun.setText(list.get(position).getCommentContent());
                Uri parse = Uri.parse(list.get(position).getCommentHeadPic());
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
        private final TextView dianzan,name,pinglun,time;

        public MyHolder( View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.yypj_image);
            dianzan = itemView.findViewById(R.id.yypj_dianzan);
            name = itemView.findViewById(R.id.yypj_name);
            pinglun = itemView.findViewById(R.id.yypj_pinglun);
            time = itemView.findViewById(R.id.yypjtime);

        }
    }
}
