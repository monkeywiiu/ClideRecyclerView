package com.example.cliderecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by Administrator on 2017/9/29 0029.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public List<FillCardBean> mDatas;
    public Context mContext;
    MyViewHolder lastHolder;

    public MyAdapter(Context context, List<FillCardBean> list) {
        mDatas = list;
        mContext = context;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public ImageView imageView;
        public HeartShapeView heartShapeView;
        public ForkShapeView forkShapeView;
        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.card_image);
            textView =  itemView.findViewById(R.id.card_text);
            heartShapeView = itemView.findViewById(R.id.like);
            forkShapeView = itemView.findViewById(R.id.not_like);
        }
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder;
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.main_view, parent, false);
        holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Glide.with(mContext).load(mDatas.get(position).url).into(holder.imageView);
        //Log.d("url", mDatas.get(position).url);
        holder.textView.setText(mDatas.get(position).name);
        if (position == mDatas.size() - 1) {
            setLastViewHolder(holder);
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setLastViewHolder(MyViewHolder holder) {
        lastHolder = holder;
    }

    public MyViewHolder getLastViewHolder(){
        return lastHolder;
    }
}
