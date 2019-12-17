package com.bw.movie.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.bean.YingPingBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


public class YingPingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Context context;
    List<YingPingBean.ResultBean>list;
    public YingPingAdapter(FragmentActivity activity, List<YingPingBean.ResultBean> result) {
        this.context = activity;
        this.list =  result;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.yingping_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof  MyHolder){
            if (list!=null){
                ((MyHolder) holder).name.setText(list.get(position).getCommentUserName());
                ((MyHolder) holder).pinglun.setText(list.get(position).getCommentContent());
                ((MyHolder) holder).dianzan.setText(list.get(position).getCommentUserId());
               /* Date date = new Date(list.get(position).getReleaseTime());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd");
                ((MyViewHolder) holder).textView1.setText(simpleDateFormat.format(date)+"上映");*/
                Date date = new Date(list.get(position).getCommentTime());
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy-MM-dd");
                ((MyHolder) holder).time.setText(simpleDateFormat.format(date));
                Uri parse = Uri.parse(list.get(position).getCommentHeadPic());
                ((MyHolder) holder).image.setImageURI(parse);
                double score = list.get(position).getScore();
                String s = String.valueOf(score);
                Float aFloat = Float.valueOf(s);
                ((MyHolder) holder).filmcinecism.setRating(aFloat);

            }
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder{

        private final SimpleDraweeView image;
        private final RatingBar filmcinecism;
        private final TextView dianzan,name,pinglun,time;

        public MyHolder( View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.yingping_image);
            dianzan = itemView.findViewById(R.id.yingping_dianzan);
            name = itemView.findViewById(R.id.yingping_name);
            pinglun = itemView.findViewById(R.id.yingping_pinglun);
            time = itemView.findViewById(R.id.yingpingtime);
            filmcinecism = itemView.findViewById(R.id.filmcinecism);

        }
    }
}
