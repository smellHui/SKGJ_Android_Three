package com.tepia.voice.xunfei;

/**
 * Created by Joeshould on 2017/10/20.
 */

public class AIUIResultBean {

    /**
     * answer : {"answerType":"openQA","emotion":"default","question":{"question":"我是谁","question_ws":"我/NP//  是/V_SHI//  谁/NP//"},"text":"我当然知道啊，你是我最在意的人呢。","topic":"32184208164607616","topicID":"32184208164607616","type":"T"}
     * man_intv :
     * no_nlu_result : 0
     * operation : ANSWER
     * rc : 0
     * service : openQA
     * status : 0
     * text : 我是谁
     * uuid : atn00b843db@ch9ef50d44ecd86f2a01
     * sid : atn00b843db@ch9ef50d44ecd86f2a01
     */

    private AnswerEntity answer;
    private String man_intv;
    private int no_nlu_result;
    private String operation;
    private int rc;
    private String service;
    private int status;
    private String text;
    private String uuid;
    private String sid;

    public void setAnswer(AnswerEntity answer) {
        this.answer = answer;
    }

    public void setMan_intv(String man_intv) {
        this.man_intv = man_intv;
    }

    public void setNo_nlu_result(int no_nlu_result) {
        this.no_nlu_result = no_nlu_result;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setRc(int rc) {
        this.rc = rc;
    }

    public void setService(String service) {
        this.service = service;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public AnswerEntity getAnswer() {
        return answer;
    }

    public String getMan_intv() {
        return man_intv;
    }

    public int getNo_nlu_result() {
        return no_nlu_result;
    }

    public String getOperation() {
        return operation;
    }

    public int getRc() {
        return rc;
    }

    public String getService() {
        return service;
    }

    public int getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }

    public String getUuid() {
        return uuid;
    }

    public String getSid() {
        return sid;
    }

    public static class AnswerEntity {
        /**
         * answerType : openQA
         * emotion : default
         * question : {"question":"我是谁","question_ws":"我/NP//  是/V_SHI//  谁/NP//"}
         * text : 我当然知道啊，你是我最在意的人呢。
         * topic : 32184208164607616
         * topicID : 32184208164607616
         * type : T
         */

        private String answerType;
        private String emotion;
        private QuestionEntity question;
        private String text;
        private String topic;
        private String topicID;
        private String type;

        public void setAnswerType(String answerType) {
            this.answerType = answerType;
        }

        public void setEmotion(String emotion) {
            this.emotion = emotion;
        }

        public void setQuestion(QuestionEntity question) {
            this.question = question;
        }

        public void setText(String text) {
            this.text = text;
        }

        public void setTopic(String topic) {
            this.topic = topic;
        }

        public void setTopicID(String topicID) {
            this.topicID = topicID;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getAnswerType() {
            return answerType;
        }

        public String getEmotion() {
            return emotion;
        }

        public QuestionEntity getQuestion() {
            return question;
        }

        public String getText() {
            return text;
        }

        public String getTopic() {
            return topic;
        }

        public String getTopicID() {
            return topicID;
        }

        public String getType() {
            return type;
        }

        public static class QuestionEntity {
            /**
             * question : 我是谁
             * question_ws : 我/NP//  是/V_SHI//  谁/NP//
             */

            private String question;
            private String question_ws;

            public void setQuestion(String question) {
                this.question = question;
            }

            public void setQuestion_ws(String question_ws) {
                this.question_ws = question_ws;
            }

            public String getQuestion() {
                return question;
            }

            public String getQuestion_ws() {
                return question_ws;
            }
        }
    }
}
