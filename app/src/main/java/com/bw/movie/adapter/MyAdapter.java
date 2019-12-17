package com.bw.movie.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bw.movie.R;
import com.bw.movie.bean.BannerBean;
import com.bw.movie.bean.ChaBean;
import com.bw.movie.bean.JjBean;
import com.bw.movie.bean.ReBean;
import com.bw.movie.view.GengDuoActivity;
import com.bw.movie.view.XQActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.tabs.TabLayout;
import com.stx.xhb.xbanner.XBanner;
import com.stx.xhb.xbanner.entity.SimpleBannerInfo;
import com.xuezj.cardbanner.CardBanner;
import com.xuezj.cardbanner.ImageData;
import com.xuezj.cardbanner.imageloader.CardImageLoader;

import java.util.AbstractList;
import java.util.AbstractSequentialList;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<ReBean.ResultBean> relist;
    private List<ChaBean.ResultBean> cha;
    private List<JjBean.ResultBean> jj;
    private static List<BannerBean.ResultBean> banners;
    private Context context;
    public static final String TAG = "MyAdapter";
    private static setChanges setChanges;


    public MyAdapter(FragmentActivity activity, List<ReBean.ResultBean> relist, List<ChaBean.ResultBean> cha, List<JjBean.ResultBean> jj, List<BannerBean.ResultBean> banner) {
        this.context = activity;
        this.relist = relist;
        this.cha = cha;
        this.jj = jj;
        this.banners = banner;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.four_layout, parent, false);
            return new myFourHolder(inflate);
        } else if (viewType == 1) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.one_layout, parent, false);
            return new myOneHolder(inflate);
        } else if (viewType == 2) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.two_layout, parent, false);
            return new myTwoHolder(inflate);
        } else if (viewType == 3) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.three_layout, parent, false);
            return new myThreeHolder(inflate);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case 0:
                if (holder instanceof myFourHolder) {

                    if (banners != null) {
                        ((myFourHolder) holder).tab.setBannerData(R.layout.froure_layout, new AbstractList<SimpleBannerInfo>() {
                            @Override
                            public SimpleBannerInfo get(int index) {

                                return null;
                            }

                            @Override
                            public int size() {
                                return banners.size();
                            }
                        });
                        ((myFourHolder) holder).tab.loadImage(new XBanner.XBannerAdapter() {


                            @Override
                            public void loadBanner(XBanner banner, Object model, View view, int position) {
                                String imageUrl = banners.get(position).getImageUrl();
                                SimpleDraweeView simpleDraweeView = view.findViewById(R.id.simp);
                                DraweeController controller = Fresco.newDraweeControllerBuilder()
                                        .setUri(imageUrl)
                                        .setAutoPlayAnimations(true)
                                        .build();
                                simpleDraweeView.setController(controller);
                                simpleDraweeView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        String rank = banners.get(position).getJumpUrl();
                                        String[] split = rank.split("=");
                                        String s = split[1];
                                        Intent intent = new Intent();
                                        SharedPreferences name = context.getSharedPreferences("users", Context.MODE_PRIVATE);
                                        SharedPreferences.Editor edit = name.edit();
                                        edit.putString("movieId",s);
                                        edit.commit();
                                        intent.setClass(context, XQActivity.class);
                                        context.startActivity(intent);
                                    }
                                });
                            }

                        });


                    }

                }

                break;
            case 1:
                if (holder instanceof myOneHolder) {
                    if (relist != null) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                        ((myOneHolder) holder).onecy.setLayoutManager(linearLayoutManager);
                        MyYingAdapter myYingAdapter = new MyYingAdapter(context, relist);
                        ((myOneHolder) holder).onecy.setAdapter(myYingAdapter);

                        ((myOneHolder) holder).more.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context, GengDuoActivity.class));
                            }
                        });
                    }
                }
                break;
            case 2:
                if (holder instanceof myTwoHolder) {
                    if (jj != null) {
                        ((myTwoHolder) holder).twocy.setLayoutManager(new LinearLayoutManager(context));
                        MyJYingAdapter myJYingAdapter = new MyJYingAdapter(context, jj);
                        ((myTwoHolder) holder).twocy.setAdapter(myJYingAdapter);

                        ((myTwoHolder) holder).more2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context, GengDuoActivity.class));
                            }
                        });
                        myJYingAdapter.getLinsenter(new MyJYingAdapter.setChangeAdapter() {
                            @Override
                            public void getChang(String movie) {
                                Log.d(TAG, "getChang1: " + movie);
                                if (movie!=null){
                                        if (jj!=null){
                                            com.bw.movie.adapter.MyAdapter.setChanges.getChanges(movie);
                                            notifyDataSetChanged();
                                        }
                                }
                            }
                        });
                    }
                }
                break;
            case 3:
                if (holder instanceof myThreeHolder) {
                    if (cha != null) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
                        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
                        ((myThreeHolder) holder).threecy.setLayoutManager(linearLayoutManager);
                        MyHotAdapter myHotAdapter = new MyHotAdapter(context, cha);
                        ((myThreeHolder) holder).threecy.setAdapter(myHotAdapter);
                        if (banners != null) {
                            //  Glide.with(context).load().three_image);
                            Uri parse = Uri.parse(banners.get(0).getImageUrl());
                            ((myThreeHolder) holder).three_image.setImageURI(parse);
                        }
                        ((myThreeHolder) holder).more3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                context.startActivity(new Intent(context, GengDuoActivity.class));
                            }
                        });
                    }
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return 0;
        } else if (position == 1) {
            return 1;
        } else if (position == 2) {
            return 2;
        } else if (position == 3) {
            return 3;
        }
        return 0;
    }

    public class myOneHolder extends RecyclerView.ViewHolder {
        private final RecyclerView onecy;
        private final TextView more;

        public myOneHolder(@NonNull View itemView) {
            super(itemView);
            onecy = itemView.findViewById(R.id.one_recy);
            more = itemView.findViewById(R.id.more);
        }
    }

    public class myTwoHolder extends RecyclerView.ViewHolder {
        private final RecyclerView twocy;
        private final TextView more2;

        public myTwoHolder(@NonNull View itemView) {
            super(itemView);
            twocy = itemView.findViewById(R.id.two_recy);
            more2 = itemView.findViewById(R.id.more2);
        }
    }

    public class myThreeHolder extends RecyclerView.ViewHolder {

        private final RecyclerView threecy;
        private final SimpleDraweeView three_image;
        private final TextView more3;

        public myThreeHolder(@NonNull View itemView) {
            super(itemView);
            threecy = itemView.findViewById(R.id.three_recy);
            three_image = itemView.findViewById(R.id.three_image);
            more3 = itemView.findViewById(R.id.more3);
        }
    }

    public class myFourHolder extends RecyclerView.ViewHolder {

        private final XBanner tab;
        private final LinearLayout linear;

        public myFourHolder(@NonNull View itemView) {
            super(itemView);
            tab = itemView.findViewById(R.id.four_tab);
            linear = itemView.findViewById(R.id.linear);
        }
    }

    public void getLisenter(setChanges setChanges) {
        this.setChanges = setChanges;
    }

    public interface setChanges {
        void getChanges(String movieid);
    }
}
