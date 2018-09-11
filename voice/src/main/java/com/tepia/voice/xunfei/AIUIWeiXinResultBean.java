package com.tepia.voice.xunfei;

import java.util.List;

/**
 * Created by Joeshould on 2017/10/23.
 */

class AIUIWeiXinResultBean {

    /**
     * rc : 0
     * semantic : [{"intent":"SEND","slots":[{"name":"content","value":"我错了"},{"name":"contentType","value":"text"},{"name":"receiver","value":"张珊"}]}]
     * service : weixin
     * state : {"fg::weixin::default::default":{"state":"default"}}
     * text : 发微信给张珊，我错了
     * uuid : atn00d3f270@ch24da0d48a2de6f2601
     * used_state : {"state_key":"fg::weixin::default::default","state":"default"}
     * sid : atn00d3f270@ch24da0d48a2de6f2601
     */

    private int rc;
    private String service;
    private String text;
    private String uuid;
    private UsedStateEntity used_state;
    private String sid;
    private List<SemanticEntity> semantic;

    public void setRc(int rc) {
        this.rc = rc;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setUsed_state(UsedStateEntity used_state) {
        this.used_state = used_state;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setSemantic(List<SemanticEntity> semantic) {
        this.semantic = semantic;
    }

    public int getRc() {
        return rc;
    }

    public String getService() {
        return service;
    }

    public String getText() {
        return text;
    }

    public String getUuid() {
        return uuid;
    }

    public UsedStateEntity getUsed_state() {
        return used_state;
    }

    public String getSid() {
        return sid;
    }

    public List<SemanticEntity> getSemantic() {
        return semantic;
    }

    public static class UsedStateEntity {
        /**
         * state_key : fg::weixin::default::default
         * state : default
         */

        private String state_key;
        private String state;

        public void setState_key(String state_key) {
            this.state_key = state_key;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getState_key() {
            return state_key;
        }

        public String getState() {
            return state;
        }
    }

    public static class SemanticEntity {
        /**
         * intent : SEND
         * slots : [{"name":"content","value":"我错了"},{"name":"contentType","value":"text"},{"name":"receiver","value":"张珊"}]
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
             * name : content
             * value : 我错了
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
