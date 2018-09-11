package com.tepia.voice.xunfei;

import java.util.List;

/**
 * Created by Joeshould on 2017/11/10.
 */

public class AIUICARBOXNumberResultBean {

    /**
     * category : CARBOX.number:2.0
     * intentType : custom
     * query : 第一百首
     * query_ws : 第/NM//  一百/NM//  首/AA//
     * rc : 0
     * nlis : true
     * service : CARBOX.number
     * uuid : atn00263fe4@ch74900d6098e46f2601
     * vendor : CARBOX
     * version : 2.0
     * semantic : [{"entrypoint":"ent","intent":"song_number","score":1,"slots":[{"begin":1,"end":3,"name":"number","normValue":"100","value":"一百"}]}]
     * state : null
     * sid : atn00263fe4@ch74900d6098e46f2601
     * text : 第一百首
     */

    private String category;
    private String intentType;
    private String query;
    private String query_ws;
    private int rc;
    private String nlis;
    private String service;
    private String uuid;
    private String vendor;
    private String version;
    private Object state;
    private String sid;
    private String text;
    private List<SemanticEntity> semantic;

    public void setCategory(String category) {
        this.category = category;
    }

    public void setIntentType(String intentType) {
        this.intentType = intentType;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public void setQuery_ws(String query_ws) {
        this.query_ws = query_ws;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public void setNlis(String nlis) {
        this.nlis = nlis;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setSemantic(List<SemanticEntity> semantic) {
        this.semantic = semantic;
    }

    public String getCategory() {
        return category;
    }

    public String getIntentType() {
        return intentType;
    }

    public String getQuery() {
        return query;
    }

    public String getQuery_ws() {
        return query_ws;
    }

    public int getRc() {
        return rc;
    }

    public String getNlis() {
        return nlis;
    }

    public String getService() {
        return service;
    }

    public String getUuid() {
        return uuid;
    }

    public String getVendor() {
        return vendor;
    }

    public String getVersion() {
        return version;
    }

    public Object getState() {
        return state;
    }

    public String getSid() {
        return sid;
    }

    public String getText() {
        return text;
    }

    public List<SemanticEntity> getSemantic() {
        return semantic;
    }

    public static class SemanticEntity {
        /**
         * entrypoint : ent
         * intent : song_number
         * score : 1
         * slots : [{"begin":1,"end":3,"name":"number","normValue":"100","value":"一百"}]
         */

        private String entrypoint;
        private String intent;
        private int score;
        private List<SlotsEntity> slots;

        public void setEntrypoint(String entrypoint) {
            this.entrypoint = entrypoint;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public void setSlots(List<SlotsEntity> slots) {
            this.slots = slots;
        }

        public String getEntrypoint() {
            return entrypoint;
        }

        public String getIntent() {
            return intent;
        }

        public int getScore() {
            return score;
        }

        public List<SlotsEntity> getSlots() {
            return slots;
        }

        public static class SlotsEntity {
            /**
             * begin : 1
             * end : 3
             * name : number
             * normValue : 100
             * value : 一百
             */

            private int begin;
            private int end;
            private String name;
            private String normValue;
            private String value;

            public void setBegin(int begin) {
                this.begin = begin;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setNormValue(String normValue) {
                this.normValue = normValue;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public int getBegin() {
                return begin;
            }

            public int getEnd() {
                return end;
            }

            public String getName() {
                return name;
            }

            public String getNormValue() {
                return normValue;
            }

            public String getValue() {
                return value;
            }
        }
    }
}
