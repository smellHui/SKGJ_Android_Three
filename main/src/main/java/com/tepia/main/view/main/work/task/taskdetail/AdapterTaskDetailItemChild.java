package com.tepia.main.view.main.work.task.taskdetail;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tepia.main.R;
import com.tepia.main.model.task.bean.PositionNamesBean;
import com.tepia.main.model.task.bean.TaskItemBean;

import java.util.ArrayList;
import java.util.List;

import static com.tepia.base.utils.Utils.getContext;

/**
 * Created by Android Studio
 *
 * @author : Arthur
 * Date :    2019/12/24
 * Time :    9:25
 * Describe :
 */
public class AdapterTaskDetailItemChild extends RecyclerView.Adapter<AdapterTaskDetailItemChild.ViewHolder> {

    private Context mcontext;
    private List<TaskItemBean> mlist;

    private OnChildItemClickListener mlistener;

    public AdapterTaskDetailItemChild(Context context, List<TaskItemBean> list,OnChildItemClickListener listener) {
        mcontext = context;
        mlist = list;
        mlistener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_adaptertaskdetailitemchild, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskItemBean bean = mlist.get(position);
        holder.tvDescribe.setText(bean.getSuperviseItemName());
        String[] tittles = bean.getPositionTreeNames().split("/");
        if (tittles.length > 2) {
            holder.rvPositionTitle.setVisibility(View.VISIBLE);
            String tittle = "";
            for (int i = 2; i < tittles.length; i++) {
                tittle += tittles[i] + ">";
            }
            if (tittle.endsWith(">")) {
                tittle = tittle.substring(0, tittle.lastIndexOf(">"));
            }
            holder.rvPositionTitle.setText(tittle);
        } else {
            holder.rvPositionTitle.setVisibility(View.GONE);
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.onClick(bean);
            }
        });


        holder.tvTaskTime.setText(bean.getExcuteDate());

        if(bean.isCommitLocal()){
            holder.tvTaskPeople.setVisibility(View.VISIBLE);
        }else {
            holder.tvTaskPeople.setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(bean.getCompleteStatus()) && "0".equals(bean.getCompleteStatus())) {
            holder.ivIsOver.setVisibility(View.VISIBLE);
            holder.tvTaskTime.setVisibility(View.VISIBLE);
            if (bean.getExecuteResultType() != null && "1".equals(bean.getExecuteResultType())) {
                holder.ivIsOver.setImageResource(R.drawable.icon_status_finish_with_exp);
                holder.tvItemExp.setVisibility(View.VISIBLE);
            } else {
                holder.ivIsOver.setImageResource(R.drawable.icon_status_finish);
                holder.tvItemExp.setVisibility(View.GONE);
            }
            if (bean.isCommitLocal() && bean.getExecuteDate().compareTo(bean.getExcuteDate()) > 0) {
                holder.ivIsOver.setVisibility(View.VISIBLE);
                holder.tvTaskTime.setVisibility(View.VISIBLE);
                if (bean.getExResult() != null && "1".equals(bean.getExResult())) {
                    holder.ivIsOver.setImageResource(R.drawable.icon_status_finish_with_exp);
                    holder.tvItemExp.setVisibility(View.VISIBLE);
                } else {
                    holder.ivIsOver.setImageResource(R.drawable.icon_status_finish);
                    holder.tvItemExp.setVisibility(View.GONE);
                }
                holder.tvTaskTime.setText(bean.getExecuteDate());
            }
        } else {
            if (bean.isCommitLocal()) {
                holder.ivIsOver.setVisibility(View.VISIBLE);
                holder.tvTaskTime.setVisibility(View.VISIBLE);
                if (bean.getExResult() != null && "1".equals(bean.getExResult())) {
                    holder.ivIsOver.setImageResource(R.drawable.icon_status_finish_with_exp);
                    holder.tvItemExp.setVisibility(View.VISIBLE);
                } else {
                    holder.ivIsOver.setImageResource(R.drawable.icon_status_finish);
                    holder.tvItemExp.setVisibility(View.GONE);
                }
                holder.tvTaskTime.setText(bean.getExecuteDate());
            } else {
                holder.ivIsOver.setVisibility(View.GONE);
                holder.tvItemExp.setVisibility(View.GONE);
                holder.tvTaskTime.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView rvPositionTitle;
        private TextView tvDescribe;
        private TextView tvTaskPeople;
        private TextView tvTaskTime;
        private ImageView ivIsOver;
        private TextView tvItemExp;

        public ViewHolder(View itemView) {
            super(itemView);
            rvPositionTitle = itemView.findViewById(R.id.tv_tittle);
            tvDescribe = itemView.findViewById(R.id.tv_describe);
            tvTaskPeople = itemView.findViewById(R.id.tv_task_people);
            tvTaskTime = itemView.findViewById(R.id.tv_task_time);
            ivIsOver = itemView.findViewById(R.id.iv_is_over);
            tvItemExp = itemView.findViewById(R.id.tv_item_exp);
        }
    }

    public interface OnChildItemClickListener{
        void onClick(TaskItemBean bean);
    }
}
