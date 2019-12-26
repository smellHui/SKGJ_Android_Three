package com.tepia.main.common.image;

import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.tepia.base.utils.LogUtil;
import com.tepia.base.utils.ResUtils;
import com.tepia.base.utils.ToastUtils;
import com.tepia.base.utils.Utils;
import com.tepia.main.R;
import com.tepia.main.common.pickview.AndroidLifecycleUtils;
import com.tepia.main.common.pickview.OnItemClickListener;
import com.tepia.main.model.image.ImageInfoBean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:图片选择适配                                       *
 * 项目名:贵州水务                                           *
 * 包名:com.elegant.river_system.adapter                    *
 * 创建时间:2017年10月12日11:16                               *
 * 更新时间:2017年10月24日11:16                               *
 * 版本号:1.1.0                                              *
 *************************************************************/
public class PhotoSelectAdapter extends RecyclerView.Adapter<PhotoSelectAdapter.PhotoViewHolder> {

    private Context mContext;
    private ArrayList<String> photoPaths;
    public final static int TYPE_ADD = 1;
    public final static int TYPE_PHOTO = 2;
    public final static int MAX = 5;

    private OnItemClickListener onItemClickListener;
    private ArrayList<String> localData;
    private List<ImageInfoBean> netData;
    private ArrayList<String> netDataUrl = new ArrayList<>();
    private DeleteListener deleteListener;
    private boolean showType = false;

    public void setPhotoPaths(ArrayList<String> photoPaths) {
        this.photoPaths = photoPaths;
    }

    public ArrayList<String> getPhotoPaths() {
        return photoPaths;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * 加载布局文件
     */
    public static View inflate(int id) {
        return View.inflate(Utils.getContext(), id, null);
    }

    public PhotoSelectAdapter(Context mContext) {
        this.photoPaths = new ArrayList<>();
        this.mContext = mContext;

    }

    @Override
    public PhotoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        switch (viewType) {
            case TYPE_ADD:
                itemView = inflate(R.layout.item_add);
                break;
            case TYPE_PHOTO:
                itemView = inflate(R.layout.picker_select_photo);
                break;
            default:
                break;
        }
        return new PhotoViewHolder(itemView);
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final PhotoViewHolder holder, final int position) {
        if (getItemViewType(position) == TYPE_PHOTO) {
            if (photoPaths.get(position).contains("http:")) {
                Glide.with(mContext).
                        load(photoPaths.get(position)).
                        thumbnail(0.5f)
                        .apply(new RequestOptions()
                                .centerCrop()
                                .placeholder(R.drawable.icon_empty)
                                .error(R.drawable.icon_empty)
                                .priority(Priority.HIGH)
                                .diskCacheStrategy(DiskCacheStrategy.NONE))
                        .into(holder.ivPhoto);
                LogUtil.e("图片地址---------：" + photoPaths.get(position));
                if (!showType) {
                    holder.vSelected.setVisibility(View.VISIBLE);
                }
                holder.vSelected.setImageResource(R.drawable.icon_x);
                holder.vSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (deleteListener != null) {
                            deleteListener.ondelete(netData.get(position));
                        }
                    }
                });
            } else {
                Uri uri = Uri.fromFile(new File(photoPaths.get(position)));
                boolean canLoadImage = AndroidLifecycleUtils.canLoadImage(holder.ivPhoto.getContext());
                if (canLoadImage) {
                    Glide.with(mContext)
                            .load(uri)
                            .into(holder.ivPhoto);
                }
                if (!showType) {
                    holder.vSelected.setVisibility(View.VISIBLE);
                }
                holder.vSelected.setImageResource(R.drawable.icon_x);
                holder.vSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = photoPaths.get(position);
                        for (int i=0;i<localData.size();i++){
                            if (url.equals(localData.get(i))){
                                localData.remove(i);
                                break;
                            }
                        }
                        photoPaths.remove(position);
                        notifyDataSetChanged();
                        if (deleteListener != null) {
                            deleteListener.ondelete(photoPaths == null ? 0 : photoPaths.size());
                        }
                    }
                });
            }

//            holder.vSelected.setImageResource(R.drawable.ic_next_grren_24dp);
        }

        holder.itemView.setOnClickListener((v) -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(v, position);
            }
        });
    }


    @Override
    public int getItemCount() {
        if (!showType) {
            int count = photoPaths.size() + 1;
            if (count > MAX) {
                count = MAX;
            }
            return count;
        } else {
            int count = photoPaths.size();
            if (count > MAX) {
                count = MAX;
            }
            return count;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (!showType) {
            return (position == photoPaths.size() && position != MAX) ? TYPE_ADD : TYPE_PHOTO;
        } else {
            return TYPE_PHOTO;
        }
    }

    public void setLocalData(ArrayList<String> localData) {
        this.localData = localData;
        photoPaths.clear();
        if (netDataUrl != null && netDataUrl.size() > 0) {
            photoPaths.addAll(netDataUrl);
        }
        if (localData != null && localData.size() > 0) {
            photoPaths.addAll(localData);
        }
        notifyDataSetChanged();
    }

    public void setNetData(List<ImageInfoBean> data) {
        this.netData = data;
        if (data == null) {
            return;
        }

        if (netDataUrl == null || netDataUrl.size() == 0) {
            this.netDataUrl = new ArrayList<String>();
            for (ImageInfoBean bean : data) {
                netDataUrl.add(bean.getFilePath());
            }
            photoPaths.clear();
            if (netDataUrl != null && netDataUrl.size() > 0) {
                photoPaths.addAll(netDataUrl);
            }
            if (localData != null && localData.size() > 0) {
                photoPaths.addAll(localData);
            }
            notifyDataSetChanged();
        } else {
            if (netDataUrl.size() == data.size()) {
                boolean isEq = true;
                for (int i = 0; i < data.size(); i++) {
                    if (netDataUrl.get(i).equals(data.get(i).getFilePath())) {
                        isEq = false;
                        break;
                    }
                }
                if (!isEq) {
                    netDataUrl.clear();
                    for (ImageInfoBean bean : data) {
                        netDataUrl.add(bean.getFilePath());
                    }
                    photoPaths.clear();
                    if (netDataUrl != null && netDataUrl.size() > 0) {
                        photoPaths.addAll(netDataUrl);
                    }
                    if (localData != null && localData.size() > 0) {
                        photoPaths.addAll(localData);
                    }
                    notifyDataSetChanged();
                }
            } else {
                netDataUrl.clear();
                for (ImageInfoBean bean : data) {
                    netDataUrl.add(bean.getFilePath());
                }
                photoPaths.clear();
                if (netDataUrl != null && netDataUrl.size() > 0) {
                    photoPaths.addAll(netDataUrl);
                }
                if (localData != null && localData.size() > 0) {
                    photoPaths.addAll(localData);
                }
                notifyDataSetChanged();
            }
        }


    }

    public void setShowType(boolean showType) {
        this.showType = showType;
        notifyDataSetChanged();
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder {
        private ImageView ivPhoto;
        private ImageView vSelected;

        public PhotoViewHolder(View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.iv_photo);
            vSelected = itemView.findViewById(R.id.v_selected);
            if (vSelected != null) {
                vSelected.setVisibility(View.GONE);
            }

        }
    }

    public interface DeleteListener {
        void ondelete(ImageInfoBean imageInfoBean);

        void ondelete(int num);
    }

    public void setDeleteListener(DeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }
}
