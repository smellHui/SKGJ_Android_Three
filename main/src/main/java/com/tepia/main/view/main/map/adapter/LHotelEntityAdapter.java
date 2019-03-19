package com.tepia.main.view.main.map.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.tepia.main.R;

import java.util.ArrayList;


public class LHotelEntityAdapter extends SectionedRecyclerViewAdapter<HeaderHolder, DescHolder, RecyclerView.ViewHolder> {
    public SparseBooleanArray getmBooleanMap() {
        return mBooleanMap;
    }

    public ArrayList<LTntity> allTagList;
    private Context mContext;
    private LayoutInflater mInflater;

    private SparseBooleanArray mBooleanMap;

    private OnSetText onSetText;

    public void setOnSetText(LHotelEntityAdapter.OnSetText onSetText) {
        this.onSetText = onSetText;
    }

    public void setmOnRecyclerviewItemClickListener(OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener) {
        this.mOnRecyclerviewItemClickListener = mOnRecyclerviewItemClickListener;
    }

    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener = null;


    public LHotelEntityAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mBooleanMap = new SparseBooleanArray();
    }

    public void setData(ArrayList<LTntity> allTagList) {
        this.allTagList = allTagList;
        notifyDataSetChanged();
    }

    @Override
    protected int getSectionCount() {
        return (allTagList)==null||(allTagList).isEmpty() ? 0 : allTagList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = allTagList.get(section).list.size();
        if (count >= 4 && !mBooleanMap.get(section)) {
            count = 4;
        }
        return (allTagList.get(section).list)==null||(allTagList.get(section).list).isEmpty() ? 0 : count;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected HeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new HeaderHolder(mInflater.inflate(R.layout.hotel_title_item, parent, false));
    }


    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected DescHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.hotel_desc_item, parent, false);
        return new DescHolder(itemView);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(final HeaderHolder holder, final int section) {
   /*     holder.openView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isOpen = mBooleanMap.get(section);
                String text = isOpen ? "展开" : "关闭";
                mBooleanMap.put(section, !isOpen);
                holder.openView.setText(text);
                notifyDataSetChanged();
            }
        });*/
        holder.itemView.setOnClickListener(v -> {
            boolean isOpen = mBooleanMap.get(section);
            if (mBooleanMap.size()>0){
                for (int i = 0; i < mBooleanMap.size(); i++) {
                    if (i!=section){
                        mBooleanMap.put(i,false);
                    }
                }
            }
            String text = isOpen ? "展开" : "关闭";
            mBooleanMap.put(section, !isOpen);
            holder.openView.setText(text);
            notifyDataSetChanged();
        });

        holder.titleView.setText(allTagList.get(section).tagsName+"("+allTagList.get(section).list.size()+")");
        holder.openView.setText(mBooleanMap.get(section) ? "关闭" : "展开");
        holder.itemView.setTag(section);
    }


    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }




    @Override
    protected void onBindItemViewHolder(DescHolder holder, final int section, final int position) {
        //R.id.tag_groupItemIndex该id在strings.xml文件中
        holder.itemView.setTag(R.id.tag_groupItemIndex,section);
//        holder.descView.setText(allTagList.get(section).tagInfoList.get(position).tagName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnRecyclerviewItemClickListener.onItemClickListener(v,section,position);
            }
        });
        holder.descView.setText((position+1)+"     "+onSetText.setText(holder,section,position));
    }

    public interface OnSetText{
         String setText(DescHolder holder, int section, int position);
    }



}
