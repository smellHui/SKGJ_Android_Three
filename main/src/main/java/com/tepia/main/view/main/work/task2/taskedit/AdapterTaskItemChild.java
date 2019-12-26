package com.tepia.main.view.main.work.task2.taskedit;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.tepia.main.R;
import com.tepia.main.model.task.bean.TaskItemBean;

import java.util.List;

/**
 * Created by Android Studio
 *
 * @author : Arthur
 * Date :    2019/12/23
 * Time :    14:29
 * Describe :
 */
public class AdapterTaskItemChild extends RecyclerView.Adapter<AdapterTaskItemChild.ViewHolder> {

    private Context mcontext;
    private List<TaskItemBean> mlist;

    public AdapterTaskItemChild(Context context, List<TaskItemBean> list) {
        mcontext = context;
        mlist = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(mcontext).inflate(R.layout.item_adaptertaskitemchild, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TaskItemBean bean = mlist.get(position);
        if (bean.isSelected()) {
            holder.cb.setChecked(true);
        } else {
            holder.cb.setChecked(false);
        }
        holder.tv_describe.setText(bean.getSuperviseItemName());
        String[] tittles = bean.getPositionTreeNames().split("/");
        if (tittles.length>2){
            holder.tittle.setVisibility(View.VISIBLE);
            String tittle = "";
            for (int i=2;i<tittles.length;i++){
                tittle += tittles[i]+">";
            }
            if (tittle.endsWith(">")) {
                tittle = tittle.substring(0, tittle.lastIndexOf(">"));
            }
            holder.tittle.setText(tittle);
        }else {
            holder.tittle.setVisibility(View.GONE);
        }
        holder.cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                mlist.get(position).setSelected(b);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlist.get(position).setSelected(!mlist.get(position).isSelected());
                notifyItemChanged(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mlist == null ? 0 : mlist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox cb;
        private TextView tittle,tv_describe;

        public ViewHolder(View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.cb_selected);
            tittle = itemView.findViewById(R.id.tv_tittle);
            tv_describe = itemView.findViewById(R.id.tv_describe);
        }
    }

}
