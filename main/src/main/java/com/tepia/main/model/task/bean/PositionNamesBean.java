package com.tepia.main.model.task.bean;

import java.io.Serializable;

/**
 * Created by Joeshould on 2018/5/30.
 */

public class PositionNamesBean implements Serializable {
    public PositionNamesBean(String title) {
        this.title = title;
    }

    /**
     * title : 清底河水库
     */

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
