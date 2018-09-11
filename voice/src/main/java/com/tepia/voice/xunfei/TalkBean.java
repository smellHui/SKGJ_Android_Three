package com.tepia.voice.xunfei;

import org.litepal.crud.DataSupport;

public class TalkBean extends DataSupport {
    boolean isLeft;
    String context;

    public boolean isLeft() {
        return isLeft;
    }

    public void setLeft(boolean left) {
        isLeft = left;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }
}