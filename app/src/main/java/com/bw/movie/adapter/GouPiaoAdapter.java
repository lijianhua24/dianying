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

import com.bw.movie.R;
import com.bw.movie.bean.FjYyBean;
import com.bw.movie.bean.GjsjcyyBean;
import com.bw.movie.view.GouPiaoActivity;
import com.bw.movie.view.YyXqActivity;
import com.bw.movie.view.ZuoActivity;
import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.List;

public class GouPiaoAdapter extends XRecyclerView.Adapter<XRecyclerView.ViewHolder> {
    private Context context;
    List<GjsjcyyBean.ResultBean> list;

    public GouPiaoAdapter(GouPiaoActivity gouPiaoActivity, List<GjsjcyyBean.ResultBean> result) {
        this.context = gouPiaoActivity;
        this.list = result;
    }


    @NonNull
    @Override
    public XRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.goupiao_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull XRecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){
            if (list!=null){
                ((MyHolder) holder).tjyy_name.setText(list.get(position).getName());
                ((MyHolder) holder).tjyy_price.setText(list.get(position).getAddress());
                Uri parse = Uri.parse(list.get(position).getLogo());
                ((MyHolder) holder).tjyy_image.setImageURI(parse);
                ((MyHolder) holder).fjyy_linear.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent();
                        String yyid = list.get(position).getCinemaId();
                        intent.putExtra("yyid",yyid);
                        intent.putExtra("sss","111");
                        intent.setClass(context, ZuoActivity.class);
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
        private final TextView tjyy_price, tjyy_name;
        private final SimpleDraweeView tjyy_image;
        private final LinearLayout fjyy_linear;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tjyy_price = itemView.findViewById(R.id.goupiao_price);
            tjyy_name = itemView.findViewById(R.id.goupiao_name);
            tjyy_image = itemView.findViewById(R.id.goupiao_image);
            fjyy_linear = itemView.findViewById(R.id.goupiao_linear);
        }
    }
}
