package com.tepia.main.view.maincommon.setting.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.view.main.jihua.adapter.PlanItemsAdapter;
import com.tepia.main.view.maincommon.setting.train.FileBean;

import java.util.ArrayList;

/**
 * Created by      Intellij IDEA
 *
 * @author :       wwj
 * Date    :       2018-10-08
 * Time    :       13:51
 * Version :       1.0
 * Company :       北京太比雅科技(武汉研发中心)
 **/
public class FileRecycleViewAdapter extends RecyclerView.Adapter<FileRecycleViewAdapter.FileViewHolder> {
    private Context mContext;
    private ArrayList<FileBean> filePaths = new ArrayList<>();

    public FileRecycleViewAdapter(Context mContext, ArrayList<FileBean> filePaths) {
        this.mContext = mContext;
        this.filePaths = filePaths;
    }

    private OnAddItemClickListener onItemClickListener;
    private OnItemDeleteClickListener onItemDeleteClickListener;


    public interface OnAddItemClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnAddItemClickListener(OnAddItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemDeleteClickListener{
        void onItemClick(View view, int position);
    }

    public void setOnAddDeleteClickListener(OnItemDeleteClickListener onItemClickListener) {
        this.onItemDeleteClickListener = onItemClickListener;
    }

    public final static int TYPE_ADD = 1;
    public final static int TYPE_FILE = 2;

    public static View inflate(int id) {
        return View.inflate(Utils.getContext(), id, null);
    }


    @Override
    public FileViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case TYPE_ADD:
                itemView = inflate(R.layout.common_add_office_layout);
                break;
            case TYPE_FILE:
                itemView = inflate(R.layout.common_office_delete_layout);
                break;
            default:
                break;
        }
        return new FileViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FileViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_FILE){
            holder.tvName.setText(filePaths.get(position).fileName);
            if (filePaths.get(position).fileName.endsWith(".doc") || filePaths.get(position).fileName.endsWith(".docx")) {
//                helper.setImageResource(R.id.officeIv,R.drawable.jianjie_word);
                holder.ivFile.setImageResource(R.drawable.jianjie_word);
            }else if (filePaths.get(position).fileName.endsWith(".xls") || filePaths.get(position).fileName.endsWith(".xlsx")) {
//                helper.setImageResource(R.id.officeIv,R.drawable.jianjie_excel);
                holder.ivFile.setImageResource(R.drawable.jianjie_excel);
            }else if (filePaths.get(position).fileName.endsWith(".ppt") || filePaths.get(position).fileName.endsWith(".pptx")) {
//                helper.setImageResource(R.id.officeIv,R.drawable.jianjie_ppt);
                holder.ivFile.setImageResource(R.drawable.jianjie_ppt);
            }else if (filePaths.get(position).fileName.endsWith(".pdf") ) {
//                helper.setImageResource(R.id.officeIv,R.drawable.jianjie_pdf);
                holder.ivFile.setImageResource(R.drawable.jianjie_ppt);
            }else if(filePaths.get(position).fileName.endsWith(".txt")){
//                helper.setImageResource(R.id.officeIv,R.drawable.jianjie_txt);
                holder.ivFile.setImageResource(R.drawable.jianjie_txt);
            }
            holder.itemView.setOnClickListener(v -> {
                if (onItemDeleteClickListener!=null){
                    onItemDeleteClickListener.onItemClick(v,position);
                }
            });
        }else {
            holder.itemView.setOnClickListener(v -> {
                if (onItemClickListener!=null){
                    onItemClickListener.onItemClick(v,position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int count = filePaths.size() + 1;
        return count;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == filePaths.size()) ? TYPE_ADD : TYPE_FILE;
    }

    public static class FileViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivFile;
        private TextView tvName;

        public FileViewHolder(View itemView) {
            super(itemView);
            ivFile = itemView.findViewById(R.id.officeIv);
            tvName = itemView.findViewById(R.id.officeTitleTv);
        }
    }
}
