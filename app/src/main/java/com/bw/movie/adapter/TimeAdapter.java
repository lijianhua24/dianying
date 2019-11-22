package com.bw.movie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bw.movie.R;
import com.bw.movie.view.GouPiaoActivity;

import java.util.List;

public class TimeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    List<String> result;
    private setChange setChange;
    public TimeAdapter(GouPiaoActivity gouPiaoActivity, List<String> result) {
    this.context = gouPiaoActivity;
    this.result = result;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.time_layout, parent, false);
        return new MyHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyHolder){
            if (result!=null){
                String name = result.get(position);
                ((MyHolder) holder).time_tx.setText(name);
                ((MyHolder) holder).time_tx.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (setChange!=null){
                            String name = result.get(position);
                            setChange.getChage(name);
                        }
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return result.size();
    }
    public class MyHolder extends RecyclerView.ViewHolder {
        private final TextView time_tx;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            time_tx = itemView.findViewById(R.id.time_tx);
        }
    }
    public void onListenter(setChange setChange){
            this.setChange = setChange;
    }
    public interface setChange{
        void getChage(String name);
    }
}
