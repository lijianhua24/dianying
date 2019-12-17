package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.XQBean;

import java.util.List;

import fm.jiecao.jcvideoplayer_lib.JCVideoPlayer;
import fm.jiecao.jcvideoplayer_lib.JCVideoPlayerStandard;

public class YuGaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    List<XQBean.ResultBean.ShortFilmListBean>list;
    public YuGaoAdapter(FragmentActivity activity, List<XQBean.ResultBean.ShortFilmListBean> list) {
        this.context = activity;
        this.list =  list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.yugao_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  MyHolder){
            if (list!=null){
                String videoUrl = list.get(position).getVideoUrl();
                ((MyHolder) holder).image.setUp(videoUrl, JCVideoPlayer.SCREEN_LAYOUT_NORMAL,"");
                Glide.with(context).load(list.get(position).getImageUrl()).into(((MyHolder) holder).image.thumbImageView);
            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder{

        private final JCVideoPlayerStandard image;

        public MyHolder( View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.yugao_vide_view);

        }
    }
}
