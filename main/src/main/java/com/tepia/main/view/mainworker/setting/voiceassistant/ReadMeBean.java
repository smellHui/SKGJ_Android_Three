package com.tepia.main.view.mainworker.setting.voiceassistant;

public class ReadMeBean {
    private String title;
    private String content;
    private String desc;

    public ReadMeBean(String title, String content,String desc) {
        this.title = title;
        this.content = content;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
