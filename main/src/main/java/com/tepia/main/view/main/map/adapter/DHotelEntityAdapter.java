package com.tepia.main.view.main.map.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.tepia.main.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * @author 44822
 */
public class DHotelEntityAdapter extends SectionedRecyclerViewAdapter<DHeaderHolder, DDescHolder, RecyclerView.ViewHolder> {


    public ArrayList<LTntity> allTagList;
    private Context mContext;
    private LayoutInflater mInflater;

    private SparseBooleanArray mBooleanMap;

    private HashMap<Integer, SparseBooleanArray> mCheckBooleanMap;
    private SparseBooleanArray mCheckList;

    private OnSetText onSetText;
    private ArrayList<SparseBooleanArray> sparseBooleanArrays;

    public void setOnSetText(DHotelEntityAdapter.OnSetText onSetText) {
        this.onSetText = onSetText;
    }

    public void setmOnRecyclerviewItemClickListener(OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener) {
        this.mOnRecyclerviewItemClickListener = mOnRecyclerviewItemClickListener;
    }

    //声明自定义的监听接口
    private OnRecyclerviewItemClickListener mOnRecyclerviewItemClickListener = null;


    public DHotelEntityAdapter(Context context) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    public void setData(ArrayList<LTntity> allTagList) {
        this.allTagList = allTagList;
        //解决chectbox复用的问题
        mBooleanMap = new SparseBooleanArray();
        sparseBooleanArrays = new ArrayList<>();
        mCheckBooleanMap = new HashMap<Integer, SparseBooleanArray>();
        for (int i = 0; i < allTagList.size(); i++) {
            mCheckList = new SparseBooleanArray();
            sparseBooleanArrays.add(mCheckList);
            mCheckBooleanMap.put(i, sparseBooleanArrays.get(i));
        }
        //默认选中事件
        sparseBooleanArrays.get(0).put(0, true);
        //默认展开第一个
        mBooleanMap.put(0, true);
        notifyDataSetChanged();
    }

    //返回集合给MainActivity
    public Map<Integer, SparseBooleanArray> getMap() {
        return mCheckBooleanMap;
    }

    public void setItemNotChecked(int section,int position){
        mCheckBooleanMap.get(section).put(position,false);
        notifyDataSetChanged();
    }

    public void setItemChecked(int section,int position){
        mCheckBooleanMap.get(section).put(position,true);
        notifyDataSetChanged();
    }

    public void setAllNotChecked() {
        for (int i = 0; i < mCheckBooleanMap.size(); i++) {
            for (int j = 0; j < mCheckBooleanMap.get(i).size(); j++) {
                mCheckBooleanMap.get(i).put(j, false);
            }
        }
        notifyDataSetChanged();
    }

    @Override
    protected int getSectionCount() {
        return (allTagList == null || allTagList.isEmpty()) ? 0 : allTagList.size();
    }

    @Override
    protected int getItemCountForSection(int section) {
        int count = allTagList.get(section).list.size();
        if (count >= 0 && !mBooleanMap.get(section)) {
            count = 0;
        }
        return ((allTagList.get(section).list)==null||(allTagList.get(section).list).isEmpty()) ? 0 : count;
    }

    //是否有footer布局
    @Override
    protected boolean hasFooterInSection(int section) {
        return false;
    }

    @Override
    protected DHeaderHolder onCreateSectionHeaderViewHolder(ViewGroup parent, int viewType) {
        return new DHeaderHolder(mInflater.inflate(R.layout.group, parent, false));
    }


    @Override
    protected RecyclerView.ViewHolder onCreateSectionFooterViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected DDescHolder onCreateItemViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.item, parent, false);
        return new DDescHolder(itemView);
    }

    @Override
    protected void onBindSectionHeaderViewHolder(final DHeaderHolder holder, final int section) {
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean isOpen = mBooleanMap.get(section);
//                mBooleanMap.put(section, !isOpen);
//                holder.iv_more.setImageDrawable(!isOpen ? ContextCompat.getDrawable(mContext, R.mipmap.icon_more) : ContextCompat.getDrawable(mContext, R.mipmap.icon_arrow_down));
//                notifyDataSetChanged();
//            }
//        });
//
//        holder.titleView.setText(allTagList.get(section).tagsName);
//        holder.iv_more.setImageDrawable(!mBooleanMap.get(section) ? ContextCompat.getDrawable(mContext, R.mipmap.icon_more) : ContextCompat.getDrawable(mContext, R.mipmap.icon_arrow_down));

    }


    @Override
    protected void onBindSectionFooterViewHolder(RecyclerView.ViewHolder holder, int section) {

    }


    @Override
    protected void onBindItemViewHolder(final DDescHolder holder, final int section, final int position) {
        holder.descView.setText(allTagList.get(section).list.get(position));
//        holder.checkBox.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                boolean checked = holder.checkBox.isChecked();
//                sparseBooleanArrays.get(section).put(position, checked);
//                mCheckBooleanMap.put(section, sparseBooleanArrays.get(section));
////                Toast.makeText(mContext, "sub item " + String.valueOf(section) + " in group item " +
////                        String.valueOf(position), Toast.LENGTH_SHORT).show();
////                onSetItem.setCheckBoxItemOnClick(holder, section, position);
//            }
//        });
        //默认选中水质站，排污口，雨量站
        mCheckBooleanMap.put(0, sparseBooleanArrays.get(section));
        boolean b = mCheckBooleanMap.get(section).get(position);
//        Log.i("map",mCheckBooleanMap.toString());
        holder.checkBox.setChecked(b);
        holder.itemView.setOnClickListener(v -> {
            mOnRecyclerviewItemClickListener.onItemClickListener(v, section, position);
        });
    }

    public interface OnSetText {
        String setText(DDescHolder holder, int section, int position);
    }

    private OnSetItem onSetItem;

    public void setOnSetItem(OnSetItem onSetItem) {
        this.onSetItem = onSetItem;
    }

    public interface OnSetItem {
        //设置item数据
        void setCheckBoxItemOnClick(RecyclerView.ViewHolder holder, int groupItemIndex, int subItemIndex);
    }

}
