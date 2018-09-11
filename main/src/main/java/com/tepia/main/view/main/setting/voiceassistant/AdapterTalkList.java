package com.tepia.main.view.main.setting.voiceassistant;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.tepia.main.R;
import com.tepia.voice.xunfei.TalkBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joeshould on 2017/11/7.
 */

public class AdapterTalkList extends RecyclerView.Adapter {
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private Context mContext;
    private LayoutInflater layoutInflate;
    private ArrayList<TalkBean> data;


    public AdapterTalkList(Context context) {
        this.mContext = context;
        layoutInflate = LayoutInflater.from(mContext);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == LEFT) {
            return new ViewLeftHolder(layoutInflate.inflate(R.layout.lv_item_talk_list_left, parent, false));
        } else {
            return new ViewRightHolder(layoutInflate.inflate(R.layout.lv_item_talk_list_right, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        if (data.size() == 0) {
            return;
        }
        TalkBean bean = data.get(data.size() - position - 1);
        if (bean == null) {
            return;
        }
        if (holder instanceof ViewLeftHolder) {
            ViewLeftHolder viewHolder = (ViewLeftHolder) holder;
            viewHolder.tvContent.setText(bean.getContext());
        } else if (holder instanceof ViewRightHolder) {
            ViewRightHolder viewHolder = (ViewRightHolder) holder;
            viewHolder.tvContent.setText(bean.getContext());
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (data == null) {
            return LEFT;
        }
        if (data.get(data.size() - position - 1).isLeft()) {
            return LEFT;
        } else {
            return RIGHT;
        }
    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        return data.size();
    }

    public void setData(List<TalkBean> data) {
        this.data = (ArrayList<TalkBean>) data;
        notifyDataSetChanged();
    }

    private class ViewLeftHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;

        public ViewLeftHolder(View view) {
            super(view);
            tvContent = view.findViewById(R.id.tv_content);
        }
    }

    private class ViewRightHolder extends RecyclerView.ViewHolder {
        private TextView tvContent;

        public ViewRightHolder(View view) {
            super(view);
            tvContent = view.findViewById(R.id.tv_content);
        }
    }
}
