package com.tepia.voice.xunfei;

import java.util.List;

/**
 * Created by Joeshould on 2017/10/23.
 */

class AIUITelephoneResultBean {

    /**
     * save_history : true
     * rc : 3
     * semantic : [{"intent":"DIAL","slots":[{"name":"code","value":"10086"},{"name":"name","value":"中国移动通信客服"}]}]
     * service : telephone
     * uuid : atn00d19f41@ch9ef50d489e606f2a01
     * text : 打电话给中国移动
     * state : {"fg::telephone::default::default":{"name":"1","operation":"1","state":"default"}}
     * used_state : {"name":"1","operation":"1","state":"default","state_key":"fg::telephone::default::default"}
     * answer : {"text":"确认拨打中国移动通信客服电话吗？号码是10086."}
     * dialog_stat : dataInvalid
     * sid : atn00d19f41@ch9ef50d489e606f2a01
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
         * name : 1
         * operation : 1
         * state : default
         * state_key : fg::telephone::default::default
         */

        private String name;
        private String operation;
        private String state;
        private String state_key;

        public void setName(String name) {
            this.name = name;
        }

        public void setOperation(String operation) {
            this.operation = operation;
        }

        public void setState(String state) {
            this.state = state;
        }

        public void setState_key(String state_key) {
            this.state_key = state_key;
        }

        public String getName() {
            return name;
        }

        public String getOperation() {
            return operation;
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
         * text : 确认拨打中国移动通信客服电话吗？号码是10086.
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
         * intent : DIAL
         * slots : [{"name":"code","value":"10086"},{"name":"name","value":"中国移动通信客服"}]
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
             * name : code
             * value : 10086
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
