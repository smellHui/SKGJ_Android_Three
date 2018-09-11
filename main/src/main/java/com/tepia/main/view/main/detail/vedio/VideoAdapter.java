package com.tepia.main.view.main.detail.vedio;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tepia.main.R;

import java.util.List;


public class VideoAdapter extends android.widget.BaseAdapter {
	private final Context context;
	private final List<VideoInfo> data;
	private int selectItem = -1;

	public VideoAdapter(Context context, List<VideoInfo> data) {
		this.context=context;
		this.data=data;
	}


	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	public void setSelectItem(int selectItem) {
		this.selectItem = selectItem;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView==null){
			convertView=View.inflate(context, R.layout.adapter_video_item, null);
		}
		ViewHolder holder = ViewHolder.getHolder(convertView);
        VideoInfo video = data.get(position);
        holder.tvName.setText(video.getChaName().trim());
        if(video.getChaStatus() != null && video.getChaStatus().equalsIgnoreCase("ok")) {
            holder.tvStatus.setText("连接状态:正常");
        }else {
            holder.tvStatus.setText("连接状态:不正常");
            holder.tvStatus.setTextColor(Color.RED);
        }
		if (position == selectItem) {// 如果当前的行就是ListView中选中的一行，就更改显示样式
			convertView.setBackgroundColor(Color.parseColor("#066FA5"));// 更改整行的背景色
			holder.tvStatus.setTextColor(Color.parseColor("#F7AD0A"));// 更改字体颜色
			holder.tvName.setTextColor(Color.parseColor("#F7AD0A"));// 更改字体颜色
		}else {
			convertView.setBackgroundColor(Color.parseColor("#E6E6E6"));// 更改整行的背景色
			holder.tvStatus.setTextColor(Color.BLACK);// 更改字体颜色
			holder.tvName.setTextColor(Color.BLACK);// 更改字体颜色
		}

		return convertView;

	}
	static class ViewHolder {
        private ImageView ivVideoIcon;
        private TextView tvName, tvStatus;

        public ViewHolder(View convertView) {
            ivVideoIcon = convertView.findViewById(R.id.iv_video_icon);
            tvName = convertView.findViewById(R.id.tv_video_channelname);
            tvStatus = convertView.findViewById(R.id.tv_video_channelStatus);
        }

        public static ViewHolder getHolder(View convertView) {
            ViewHolder holder = (ViewHolder) convertView.getTag();
            if (holder == null) {
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            }
            return holder;
        }
    }
}
