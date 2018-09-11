package com.tepia.photo_picker.adapter;

import android.support.v7.widget.RecyclerView;

import com.tepia.photo_picker.entity.Photo;
import com.tepia.photo_picker.entity.PhotoDirectory;
import com.tepia.photo_picker.event.Selectable;

import java.util.ArrayList;
import java.util.List;

/*************************************************************
 * Created by OCN.YAN                                        *
 * 主要功能:TOTO                                             *
 * 项目名:photopicker                                        *
 * 包名:com.tepia.photo_picker adapter                       *
 * 创建时间:2016年06月14日14:40                              *
 * 更新人:yanqiuqiu                                          *
 * 邮箱:yanqqkongmi@gmail.com                                *
 * QQ:2361167552                                             *
 * 更新时间:2017年09月14日14:40                              *
 * 版权:个人版权所有                                         *
 * 版本号:1.1.0                                              *
 *************************************************************/
public abstract class SelectableAdapter<VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> implements Selectable {

    private static final String TAG = SelectableAdapter.class.getSimpleName();
    public List<PhotoDirectory> photoDirectories;
    public List<String> selectedPhotos;
    protected int currentDirectoryIndex = 0;

    public SelectableAdapter() {
        photoDirectories = new ArrayList<>();
        selectedPhotos = new ArrayList<>();
    }

    @Override
    public boolean isSelected(Photo photo) {
        return getSelectedPhotos().contains(photo.getPath());
    }

    @Override
    public void toggleSelection(Photo photo) {
        if (selectedPhotos.contains(photo.getPath())) {
            selectedPhotos.remove(photo.getPath());
        } else {
            selectedPhotos.add(photo.getPath());
        }
    }


    @Override
    public void clearSelection() {
        selectedPhotos.clear();
    }


    /**
     * Count the selected items
     *
     * @return Selected items count
     */
    @Override
    public int getSelectedItemCount() {
        return selectedPhotos.size();
    }


    public void setCurrentDirectoryIndex(int currentDirectoryIndex) {
        this.currentDirectoryIndex = currentDirectoryIndex;
    }


    public List<Photo> getCurrentPhotos() {
        return photoDirectories.get(currentDirectoryIndex).getPhotos();
    }


    public List<String> getCurrentPhotoPaths() {
        List<String> currentPhotoPaths = new ArrayList<>(getCurrentPhotos().size());
        for (Photo photo : getCurrentPhotos()) {
            currentPhotoPaths.add(photo.getPath());
        }
        return currentPhotoPaths;
    }

    public List<String> getSelectedPhotos() {
        return selectedPhotos;
    }

}