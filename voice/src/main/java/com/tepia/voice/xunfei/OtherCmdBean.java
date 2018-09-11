package com.tepia.voice.xunfei;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joeshould on 2018/7/27.
 */

public class OtherCmdBean {
    int code;
    List<String> cmdStriList = new ArrayList<>();

    public OtherCmdBean(int code, List<String> cmdStriList) {
        this.code = code;
        this.cmdStriList = cmdStriList;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<String> getCmdStriList() {
        return cmdStriList;
    }

    public void setCmdStriList(List<String> cmdStriList) {
        this.cmdStriList = cmdStriList;
    }
}
