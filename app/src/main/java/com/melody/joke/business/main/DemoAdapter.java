package com.melody.joke.business.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.melody.joke.bean.Demo;

import java.util.List;

/**
 * description:
 * author: Melody
 * date: 2016/7/9
 * version: 0.0.0.1
 */

public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

    private Context mContext;

    private List<Demo> mList;

    public DemoAdapter(Context mContext, List<Demo> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public Demo getItem(int position) {
        return mList.get(position);
    }
}
