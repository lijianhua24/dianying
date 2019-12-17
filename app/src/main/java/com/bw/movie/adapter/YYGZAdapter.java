package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.bw.movie.R;
import com.bw.movie.bean.YYGZsBean;
import com.bw.movie.view.YyXqActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class YYGZAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    private List<YYGZsBean.ResultBean> list;
    

    public YYGZAdapter(FragmentActivity activity, List<YYGZsBean.ResultBean> result) {
        this.context = activity;
        this.list = result;
    }

    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.yygz_layout, parent, false);
        return new MyViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder holder, int position) {
            if (holder instanceof MyViewHolder){
                ((MyViewHolder) holder).textView.setText(list.get(position).getName());
                ((MyViewHolder) holder).gengduo_text3.setText("地址:   "+list.get(position).getAddress());
                Uri parse = Uri.parse(list.get(position).getLogo());
                ((MyViewHolder) holder).imageView.setImageURI(parse);
                /**/
                ((MyViewHolder) holder).yygz_rela.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String id = list.get(position).getCinemaId();
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

    public class MyViewHolder extends XRecyclerView.ViewHolder {

        private final SimpleDraweeView imageView;
        private final RelativeLayout yygz_rela;
        private final TextView textView,gengduo_text2,gengduo_text3,gengduo_text4;


        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.yygz_image);
            textView = itemView.findViewById(R.id.yygz_text1);
            gengduo_text2 = itemView.findViewById(R.id.yygz_text2);
            gengduo_text3 = itemView.findViewById(R.id.yygz_text3);
            gengduo_text4 = itemView.findViewById(R.id.yygz_text4);
            yygz_rela = itemView.findViewById(R.id.yygz_rela);


        }
    }
}
