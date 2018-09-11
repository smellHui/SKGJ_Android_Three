package com.tepia.voice.xunfei;

import java.util.List;

/**
 * Created by Joeshould on 2017/10/23.
 */

public class AIUIMusicResultBean {

    /**
     * save_history : true
     * rc : 0
     * semantic : [{"intent":"PLAY","slots":[{"name":"artist","value":"周杰伦"},{"name":"song","value":"烟花易冷"}]}]
     * service : musicX
     * uuid : atn00ce1b49@ch24da0d4898856f2601
     * text : 我要听周杰伦的烟花易冷
     * state : {"fg::musicX::default::playing":{"artist":"1","song":"1","state":"playing"}}
     * used_state : {"artist":"1","song":"1","state":"playing","state_key":"fg::musicX::default::playing"}
     * answer : {"text":"请欣赏周杰伦演唱的烟花易冷"}
     * dialog_stat : DataValid
     * sid : atn00ce1b49@ch24da0d4898856f2601
     */

    private boolean save_history;
    private int rc;
    private String service;
    private String uuid;
    private String text;
    private UsedStateEntity used_state;
    private AnswerEntity answer;
    private String dialog_stat;
    private String sid;
    private List<SemanticEntity> semantic;

    public void setSave_history(boolean save_history) {
        this.save_history = save_history;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUsed_state(UsedStateEntity used_state) {
        this.used_state = used_state;
    }

    public void setAnswer(AnswerEntity answer) {
        this.answer = answer;
    }

    public void setDialog_stat(String dialog_stat) {
        this.dialog_stat = dialog_stat;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setSemantic(List<SemanticEntity> semantic) {
        this.semantic = semantic;
    }

    public boolean getSave_history() {
        return save_history;
    }

    public int getRc() {
        return rc;
    }

    public String getService() {
        return service;
    }

    public String getUuid() {
        return uuid;
    }

    public String getText() {
        return text;
    }

    public UsedStateEntity getUsed_state() {
        return used_state;
    }

    public AnswerEntity getAnswer() {
        return answer;
    }

    public String getDialog_stat() {
        return dialog_stat;
    }

    public String getSid() {
        return sid;
    }

    public List<SemanticEntity> getSemantic() {
        return semantic;
    }

    public static class UsedStateEntity {
        /**
         * artist : 1
         * song : 1
         * state : playing
         * state_key : fg::musicX::default::playing
         */

        private String artist;
        private String song;
        private String state;
        private String state_key;

        public void setArtist(String artist) {
            this.artist = artist;
        }

        public void setSong(String song) {
            this.song = song;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setState_key(String state_key) {
            this.state_key = state_key;
        }

        public String getArtist() {
            return artist;
        }

        public String getSong() {
            return song;
        }

        public String getState() {
            return state;
        }

        public String getState_key() {
            return state_key;
        }
    }

    public static class AnswerEntity {
        /**
         * text : 请欣赏周杰伦演唱的烟花易冷
         */

        private String text;

        public void setText(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }
    }

    public static class SemanticEntity {
        /**
         * intent : PLAY
         * slots : [{"name":"artist","value":"周杰伦"},{"name":"song","value":"烟花易冷"}]
         */

        private String intent;
        private List<SlotsEntity> slots;

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public void setSlots(List<SlotsEntity> slots) {
            this.slots = slots;
        }

        public String getIntent() {
            return intent;
        }

        public List<SlotsEntity> getSlots() {
            return slots;
        }

        public static class SlotsEntity {
            /**
             * name : artist
             * value : 周杰伦
             */

            private String name;
            private String value;

            public void setName(String name) {
                this.name = name;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public String getName() {
                return name;
            }

            public String getValue() {
                return value;
            }
        }
    }
}
