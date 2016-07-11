package com.melody.joke.business.main;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.melody.base.util.DisplayUtility;
import com.melody.joke.R;
import com.melody.joke.bean.Demo;

import java.util.List;

/**
 * description: demo adapter
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
        TextView titleText, descriptionText;

        public ViewHolder(View itemView) {
            super(itemView);

            titleText = (TextView) itemView.findViewById(R.id.item_title);
            descriptionText = (TextView) itemView.findViewById(R.id.item_description);
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
                final ViewHolder holder = new ViewHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_main_demo, parent, false));
                holder.itemView.findViewById(R.id.item_layout).setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = getItem(holder.getAdapterPosition()).getIntent();
                                if (intent != null) mContext.startActivity(intent);
                            }
                        });
                return holder;

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
                    if (!TextUtils.isEmpty(mDemo.getTitle()))
                        holder.titleText.setText(mDemo.getTitle());

                    if (!TextUtils.isEmpty(mDemo.getDescription()))
                        holder.descriptionText.setText(mDemo.getDescription());
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
