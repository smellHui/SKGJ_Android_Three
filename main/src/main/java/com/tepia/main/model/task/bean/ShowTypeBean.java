package com.tepia.main.model.task.bean;

import java.io.Serializable;

/**
 * Created by Joeshould on 2018/5/30.
 */

public  class ShowTypeBean implements Serializable{
    /**
     * name : 无
     * value : 1
     * checked : true
     */

    private String name;
    private String value;
    private boolean checked;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
