package com.tepia.voice.xunfei;

import java.util.List;
import java.util.Map;

/**
 * Created by Joeshould on 2018/8/7.
 */

public class AIUICustomResultBean {

    /**
     * category : JOESHOULD123.SKGJ
     * intentType : custom
     * rc : 0
     * semanticType : 0
     * service : JOESHOULD123.SKGJ
     * uuid : atn039a4cf9@dx00070ec4536ba10e01
     * vendor : JOESHOULD123
     * version : 5.0
     * semantic : [{"entrypoint":"ent","hazard":false,"intent":"open_wordorder_list","score":1,"slots":[{"begin":2,"end":4,"name":"workType","normValue":"巡检","value":"巡检"}],"template":"打开{workType}列表"}]
     * state : null
     * sessionIsEnd : true
     * shouldEndSession : true
     * answer : {"text":"正在为您打开工单列表页面","type":"T"}
     * data : {"result":[{"exeStatus":null,"status":200,"workType":"巡检"}]}
     * console : [{"data":"本次请求来自意图:open_wordorder_list","dataTime":"2018-08-07T10:43:55.421Z","level":"info"}]
     * sid : atn039a4cf9@dx00070ec4536ba10e01
     * text : 打开巡检列表
     */

    private String category;
    private String intentType;
    private int rc;
    private int semanticType;
    private String service;
    private String uuid;
    private String vendor;
    private String version;
    private Object state;
    private String sessionIsEnd;
    private boolean shouldEndSession;
    private AnswerBean answer;
    private DataBean data;
    private String sid;
    private String text;
    private List<SemanticBean> semantic;
    private List<ConsoleBean> console;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getIntentType() {
        return intentType;
    }

    public void setIntentType(String intentType) {
        this.intentType = intentType;
    }

    public int getRc() {
        return rc;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public int getSemanticType() {
        return semanticType;
    }

    public void setSemanticType(int semanticType) {
        this.semanticType = semanticType;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Object getState() {
        return state;
    }

    public void setState(Object state) {
        this.state = state;
    }

    public String getSessionIsEnd() {
        return sessionIsEnd;
    }

    public void setSessionIsEnd(String sessionIsEnd) {
        this.sessionIsEnd = sessionIsEnd;
    }

    public boolean isShouldEndSession() {
        return shouldEndSession;
    }

    public void setShouldEndSession(boolean shouldEndSession) {
        this.shouldEndSession = shouldEndSession;
    }

    public AnswerBean getAnswer() {
        return answer;
    }

    public void setAnswer(AnswerBean answer) {
        this.answer = answer;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<SemanticBean> getSemantic() {
        return semantic;
    }

    public void setSemantic(List<SemanticBean> semantic) {
        this.semantic = semantic;
    }

    public List<ConsoleBean> getConsole() {
        return console;
    }

    public void setConsole(List<ConsoleBean> console) {
        this.console = console;
    }

    public static class AnswerBean {
        /**
         * text : 正在为您打开工单列表页面
         * type : T
         */

        private String text;
        private String type;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }

    public static class DataBean {
        private List<Map<String,Object>> result;

        public List<Map<String,Object>> getResult() {
            return result;
        }

        public void setResult(List<Map<String,Object>> result) {
            this.result = result;
        }
    }

    public static class SemanticBean {
        /**
         * entrypoint : ent
         * hazard : false
         * intent : open_wordorder_list
         * score : 1
         * slots : [{"begin":2,"end":4,"name":"workType","normValue":"巡检","value":"巡检"}]
         * template : 打开{workType}列表
         */

        private String entrypoint;
        private boolean hazard;
        private String intent;
        private double score;
        private String template;
        private List<SlotsBean> slots;

        public String getEntrypoint() {
            return entrypoint;
        }

        public void setEntrypoint(String entrypoint) {
            this.entrypoint = entrypoint;
        }

        public boolean isHazard() {
            return hazard;
        }

        public void setHazard(boolean hazard) {
            this.hazard = hazard;
        }

        public String getIntent() {
            return intent;
        }

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }

        public String getTemplate() {
            return template;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public List<SlotsBean> getSlots() {
            return slots;
        }

        public void setSlots(List<SlotsBean> slots) {
            this.slots = slots;
        }

        public static class SlotsBean {
            /**
             * begin : 2
             * end : 4
             * name : workType
             * normValue : 巡检
             * value : 巡检
             */

            private int begin;
            private int end;
            private String name;
            private String normValue;
            private String value;

            public int getBegin() {
                return begin;
            }

            public void setBegin(int begin) {
                this.begin = begin;
            }

            public int getEnd() {
                return end;
            }

            public void setEnd(int end) {
                this.end = end;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getNormValue() {
                return normValue;
            }

            public void setNormValue(String normValue) {
                this.normValue = normValue;
            }

            public String getValue() {
                return value;
            }

            public void setValue(String value) {
                this.value = value;
            }
        }
    }

    public static class ConsoleBean {
    }
}
