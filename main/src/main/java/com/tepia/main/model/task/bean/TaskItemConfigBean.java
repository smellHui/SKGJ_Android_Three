package com.tepia.main.model.task.bean;

import java.util.List;

/**
 * Created by Android Studio
 *
 * @author : Arthur
 * Date :    2019/12/23
 * Time :    13:57
 * Describe :
 */
public class TaskItemConfigBean {

    private List<TaskItemBean> list;

    private String tittle;

    public List<TaskItemBean> getList() {
        return list;
    }

    public void setList(List<TaskItemBean> list) {
        this.list = list;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
}
