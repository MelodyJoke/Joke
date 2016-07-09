package com.melody.joke.business.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.melody.base.util.DisplayUtility;
import com.melody.joke.R;
import com.melody.joke.bean.Demo;

import java.util.List;

import static android.R.attr.padding;

/**
 * description:
 * author: Melody
 * date: 2016/7/9
 * version: 0.0.0.1
 */
@SuppressWarnings("WeakerAccess, unused")
public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.ViewHolder> {

    public static final int DEMO_COMMON = 0, DEMO_DIVIDER = 1, DEMO_TITLE = 2;

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
    public int getItemViewType(int position) {
        return getItem(position).getType();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case DEMO_COMMON:
                return new ViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_main_demo, parent, false));

            case DEMO_DIVIDER:
                View divider = new View(mContext);
                divider.setMinimumHeight(DisplayUtility.getPxFromDp(8));
                return new ViewHolder(divider);

            case DEMO_TITLE:
                TextView title = new TextView(mContext);
                int paddingHorizontal = DisplayUtility.getPxFromDp(12);
                int paddingVertical = DisplayUtility.getPxFromDp(4);
                title.setPadding(paddingHorizontal,
                        paddingVertical,
                        paddingHorizontal,
                        paddingVertical);
                return new ViewHolder(title);

            default:
        }

        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final int mPosition = holder.getAdapterPosition();
        final Demo mDemo = getItem(mPosition);

        if (mDemo != null) {
            switch (getItemViewType(mPosition)) {
                case DEMO_COMMON:
                    break;

                case DEMO_DIVIDER:
                    break;

                case DEMO_TITLE:
                    ((TextView) holder.itemView).setText(mDemo.getTitle());
                    break;

                default:
            }
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public Demo getItem(int position) {
        return mList.get(position);
    }
}
