package com.tepia.voice.xunfei;

import java.util.List;

/**
 * Created by Joeshould on 2017/10/20.
 */

public class AIUINEWSResultBean {


    /**
     * data : {"result":[{"category":"科技","content":"","description":"","id":"","imgUrl":"https://image.leting.io/671bca96-f92a-4340-ba5f-60af861a4f99.jpg","keyWords":"苹果","name":"","publishDateTime":"2017-12-28 15:56:16","source":"","time":"","title":"苹果公司在美遭集体诉讼，索赔金额近万亿美元","type":"1","url":"https://audio.leting.io/2ccfb2e0-cf56-46d5-8ff4-4eca47eac566.mp3"},{"category":"科技","content":"","description":"","id":"","imgUrl":"https://image.leting.io/198e86aa-7ea8-400e-9880-5f10daae9633.jpg","keyWords":"苹果","name":"","publishDateTime":"2017-12-28 15:56:15","source":"","time":"","title":"三季度苹果占全球手机利润较去年大幅下滑","type":"1","url":"https://audio.leting.io/b45b5f7d-9b24-4ce0-a6a1-b76c6b92c864.mp3"},{"category":"时政","content":"","description":"","id":"","imgUrl":"https://image.leting.io/90fe55b9-9ca6-4aef-b981-c4587c2b73d5.jpg","keyWords":"冰岛","name":"","publishDateTime":"2017-12-28 15:56:14","source":"","time":"","title":"载有中国游客大巴在冰岛发生车祸致1死多伤 使馆官员到医院慰问伤者","type":"1","url":"https://audio.leting.io/930d585e-131f-4dc7-8768-2b3a9ffc1aa5.mp3"},{"category":"社会","content":"","description":"","id":"","imgUrl":"https://image.leting.io/06ac9632-25ce-47db-9491-e9b6417f40e1.jpg","keyWords":"上海，交通","name":"","publishDateTime":"2017-12-28 15:56:13","source":"","time":"","title":"上海首批双开门公交线开通运行","type":"1","url":"https://audio.leting.io/a69f0b19-e29f-49f1-8911-d8adc53a9d92.mp3"},{"category":"时政","content":"","description":"","id":"","imgUrl":"https://image.leting.io/0dd38305-2657-4fb3-bb96-71b35f181145.jpg","keyWords":"医学","name":"","publishDateTime":"2017-12-28 15:50:14","source":"","time":"","title":"[新闻直播间]我国胚胎生殖细胞研究取得突破","type":"1","url":"https://audio.leting.io/31e657bf-afcc-47d5-9bd9-2c12d3951258.mp3"},{"category":"经济","content":"","description":"","id":"","imgUrl":"https://image.leting.io/02b6e3c6-2f5f-4304-956f-be9cfb0277c7.jpg","keyWords":"外贸","name":"","publishDateTime":"2017-12-28 15:50:13","source":"","time":"","title":"商务部：预计全年进出口再破4万亿美元","type":"1","url":"https://audio.leting.io/980b4730-3fe5-48f4-bcbb-bc6cd88422c6.mp3"},{"category":"时政","content":"","description":"","id":"","imgUrl":"https://image.leting.io/6b0e4394-9e4a-49d9-aea5-71a81c8fb884.jpg","keyWords":"冰岛，事故","name":"","publishDateTime":"2017-12-28 15:49:59","source":"","time":"","title":"冰岛：中国游客大巴发生车祸致1死多伤","type":"1","url":"https://audio.leting.io/c8c140e0-576a-4f25-abfe-5a3f08c1fe3e.mp3"},{"category":"经济","content":"","description":"","id":"","imgUrl":"https://image.leting.io/95dae43b-6e14-4175-81ed-196b9e6b29af.jpg","keyWords":"股市","name":"","publishDateTime":"2017-12-28 15:47:18","source":"","time":"","title":"徐传豹：盘中热点散乱，未来会有所分化","type":"1","url":"https://audio.leting.io/a3324ab1-c346-482f-8277-b2755df630a3.mp3"},{"category":"社会","content":"","description":"","id":"","imgUrl":"https://image.leting.io/d76a1ef8-2343-4e63-baa7-8f7e08b07bf1.jpg","keyWords":"江苏，镇江","name":"","publishDateTime":"2017-12-28 15:47:17","source":"","time":"","title":"江苏镇江 酒驾被查 辩称香烟中含酒精所致","type":"1","url":"https://audio.leting.io/59dde9a0-2222-4a15-84ad-b968f63ac62f.mp3"},{"category":"经济","content":"","description":"","id":"","imgUrl":"https://image.leting.io/3ebda16f-f784-40d9-bbf3-1cd915425b58.jpg","keyWords":"保千里","name":"","publishDateTime":"2017-12-28 15:47:15","source":"","time":"","title":"经营状况恶化，保千里29日将\u201c戴帽\u201d","type":"1","url":"https://audio.leting.io/32f49eb5-ad47-4e3a-938a-bb01e0fd1ea6.mp3"}]}
     * rc : 0
     * semantic : [{"intent":"PLAY","slots":[{"name":"datetime","value":"今天","normValue":"{\"datetime\":\"2017-12-28\",\"suggestDatetime\":\"2017-12-28\"}"}]}]
     * service : news
     * state : {"fg::news::default::default":{"state":"default"}}
     * text : 今天的新闻
     * uuid : atn0012cd3a@ch75750d9ff5146f2001
     * answer : {"text":"开始为您播放"}
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : cid6f1943cd@ch00510d9ff513040005
     */

    private DataEntity data;
    private int rc;
    private String service;
    private String text;
    private String uuid;
    private AnswerEntity answer;
    private String dialog_stat;
    private boolean save_history;
    private String sid;
    private List<SemanticEntity> semantic;

    public void setData(DataEntity data) {
        this.data = data;
    }

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

    public void setAnswer(AnswerEntity answer) {
        this.answer = answer;
    }

    public void setDialog_stat(String dialog_stat) {
        this.dialog_stat = dialog_stat;
    }

    public void setSave_history(boolean save_history) {
        this.save_history = save_history;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setSemantic(List<SemanticEntity> semantic) {
        this.semantic = semantic;
    }

    public DataEntity getData() {
        return data;
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

    public AnswerEntity getAnswer() {
        return answer;
    }

    public String getDialog_stat() {
        return dialog_stat;
    }

    public boolean getSave_history() {
        return save_history;
    }

    public String getSid() {
        return sid;
    }

    public List<SemanticEntity> getSemantic() {
        return semantic;
    }

    public static class DataEntity {
        /**
         * result : [{"category":"科技","content":"","description":"","id":"","imgUrl":"https://image.leting.io/671bca96-f92a-4340-ba5f-60af861a4f99.jpg","keyWords":"苹果","name":"","publishDateTime":"2017-12-28 15:56:16","source":"","time":"","title":"苹果公司在美遭集体诉讼，索赔金额近万亿美元","type":"1","url":"https://audio.leting.io/2ccfb2e0-cf56-46d5-8ff4-4eca47eac566.mp3"},{"category":"科技","content":"","description":"","id":"","imgUrl":"https://image.leting.io/198e86aa-7ea8-400e-9880-5f10daae9633.jpg","keyWords":"苹果","name":"","publishDateTime":"2017-12-28 15:56:15","source":"","time":"","title":"三季度苹果占全球手机利润较去年大幅下滑","type":"1","url":"https://audio.leting.io/b45b5f7d-9b24-4ce0-a6a1-b76c6b92c864.mp3"},{"category":"时政","content":"","description":"","id":"","imgUrl":"https://image.leting.io/90fe55b9-9ca6-4aef-b981-c4587c2b73d5.jpg","keyWords":"冰岛","name":"","publishDateTime":"2017-12-28 15:56:14","source":"","time":"","title":"载有中国游客大巴在冰岛发生车祸致1死多伤 使馆官员到医院慰问伤者","type":"1","url":"https://audio.leting.io/930d585e-131f-4dc7-8768-2b3a9ffc1aa5.mp3"},{"category":"社会","content":"","description":"","id":"","imgUrl":"https://image.leting.io/06ac9632-25ce-47db-9491-e9b6417f40e1.jpg","keyWords":"上海，交通","name":"","publishDateTime":"2017-12-28 15:56:13","source":"","time":"","title":"上海首批双开门公交线开通运行","type":"1","url":"https://audio.leting.io/a69f0b19-e29f-49f1-8911-d8adc53a9d92.mp3"},{"category":"时政","content":"","description":"","id":"","imgUrl":"https://image.leting.io/0dd38305-2657-4fb3-bb96-71b35f181145.jpg","keyWords":"医学","name":"","publishDateTime":"2017-12-28 15:50:14","source":"","time":"","title":"[新闻直播间]我国胚胎生殖细胞研究取得突破","type":"1","url":"https://audio.leting.io/31e657bf-afcc-47d5-9bd9-2c12d3951258.mp3"},{"category":"经济","content":"","description":"","id":"","imgUrl":"https://image.leting.io/02b6e3c6-2f5f-4304-956f-be9cfb0277c7.jpg","keyWords":"外贸","name":"","publishDateTime":"2017-12-28 15:50:13","source":"","time":"","title":"商务部：预计全年进出口再破4万亿美元","type":"1","url":"https://audio.leting.io/980b4730-3fe5-48f4-bcbb-bc6cd88422c6.mp3"},{"category":"时政","content":"","description":"","id":"","imgUrl":"https://image.leting.io/6b0e4394-9e4a-49d9-aea5-71a81c8fb884.jpg","keyWords":"冰岛，事故","name":"","publishDateTime":"2017-12-28 15:49:59","source":"","time":"","title":"冰岛：中国游客大巴发生车祸致1死多伤","type":"1","url":"https://audio.leting.io/c8c140e0-576a-4f25-abfe-5a3f08c1fe3e.mp3"},{"category":"经济","content":"","description":"","id":"","imgUrl":"https://image.leting.io/95dae43b-6e14-4175-81ed-196b9e6b29af.jpg","keyWords":"股市","name":"","publishDateTime":"2017-12-28 15:47:18","source":"","time":"","title":"徐传豹：盘中热点散乱，未来会有所分化","type":"1","url":"https://audio.leting.io/a3324ab1-c346-482f-8277-b2755df630a3.mp3"},{"category":"社会","content":"","description":"","id":"","imgUrl":"https://image.leting.io/d76a1ef8-2343-4e63-baa7-8f7e08b07bf1.jpg","keyWords":"江苏，镇江","name":"","publishDateTime":"2017-12-28 15:47:17","source":"","time":"","title":"江苏镇江 酒驾被查 辩称香烟中含酒精所致","type":"1","url":"https://audio.leting.io/59dde9a0-2222-4a15-84ad-b968f63ac62f.mp3"},{"category":"经济","content":"","description":"","id":"","imgUrl":"https://image.leting.io/3ebda16f-f784-40d9-bbf3-1cd915425b58.jpg","keyWords":"保千里","name":"","publishDateTime":"2017-12-28 15:47:15","source":"","time":"","title":"经营状况恶化，保千里29日将\u201c戴帽\u201d","type":"1","url":"https://audio.leting.io/32f49eb5-ad47-4e3a-938a-bb01e0fd1ea6.mp3"}]
         */

        private List<ResultEntity> result;

        public void setResult(List<ResultEntity> result) {
            this.result = result;
        }

        public List<ResultEntity> getResult() {
            return result;
        }

        public static class ResultEntity {
            /**
             * category : 科技
             * content :
             * description :
             * id :
             * imgUrl : https://image.leting.io/671bca96-f92a-4340-ba5f-60af861a4f99.jpg
             * keyWords : 苹果
             * name :
             * publishDateTime : 2017-12-28 15:56:16
             * source :
             * time :
             * title : 苹果公司在美遭集体诉讼，索赔金额近万亿美元
             * type : 1
             * url : https://audio.leting.io/2ccfb2e0-cf56-46d5-8ff4-4eca47eac566.mp3
             */

            private String category;
            private String content;
            private String description;
            private String id;
            private String imgUrl;
            private String keyWords;
            private String name;
            private String publishDateTime;
            private String source;
            private String time;
            private String title;
            private String type;
            private String url;

            public void setCategory(String category) {
                this.category = category;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public void setId(String id) {
                this.id = id;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public void setKeyWords(String keyWords) {
                this.keyWords = keyWords;
            }

            public void setName(String name) {
                this.name = name;
            }

            public void setPublishDateTime(String publishDateTime) {
                this.publishDateTime = publishDateTime;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getCategory() {
                return category;
            }

            public String getContent() {
                return content;
            }

            public String getDescription() {
                return description;
            }

            public String getId() {
                return id;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public String getKeyWords() {
                return keyWords;
            }

            public String getName() {
                return name;
            }

            public String getPublishDateTime() {
                return publishDateTime;
            }

            public String getSource() {
                return source;
            }

            public String getTime() {
                return time;
            }

            public String getTitle() {
                return title;
            }

            public String getType() {
                return type;
            }

            public String getUrl() {
                return url;
            }
        }
    }

    public static class AnswerEntity {
        /**
         * text : 开始为您播放
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
         * slots : [{"name":"datetime","value":"今天","normValue":"{\"datetime\":\"2017-12-28\",\"suggestDatetime\":\"2017-12-28\"}"}]
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
             * name : datetime
             * value : 今天
             * normValue : {"datetime":"2017-12-28","suggestDatetime":"2017-12-28"}
             */

            private String name;
            private String value;
            private String normValue;

            public void setName(String name) {
                this.name = name;
            }

            public void setValue(String value) {
                this.value = value;
            }

            public void setNormValue(String normValue) {
                this.normValue = normValue;
            }

            public String getName() {
                return name;
            }

            public String getValue() {
                return value;
            }

            public String getNormValue() {
                return normValue;
            }
        }
    }
}
