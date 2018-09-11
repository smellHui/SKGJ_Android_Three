package com.tepia.voice.xunfei;

import java.util.List;

/**
 * Created by Joeshould on 2017/10/20.
 */

class AIUIJokeResultBean {

    /**
     * data : {"result":[{"album":"微博冷笑话","albumUrl":"http://www.ximalaya.com/1000558/album/2677704","author":"笑话大王","category":"娱乐","id":72,"mp3Url":"http://audio.xmcdn.com/group3/M05/CD/85/wKgDsVLd05WDcBRDAAJ-elhe_ug152.mp3","mp4Url":"http://audio.xmcdn.com/group9/M07/09/A4/wKgDYlVfIdnx9t-zAAD7MI_mntI228.m4a","subCategory":"内涵段子","tag":"冷笑话;微博;笑话大王","title":"两位画家"}]}
     * rc : 0
     * semantic : [{"intent":"QUERY"}]
     * service : joke
     * text : 讲个笑话来听听
     * uuid : atn00bbd430@ch9ef50d4505c06f2a01
     * answer : {"text":"请听笑话,两位画家."}
     * dialog_stat : dataInvalid
     * save_history : true
     * sid : atn00bbd430@ch9ef50d4505c06f2a01
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
         * result : [{"album":"微博冷笑话","albumUrl":"http://www.ximalaya.com/1000558/album/2677704","author":"笑话大王","category":"娱乐","id":72,"mp3Url":"http://audio.xmcdn.com/group3/M05/CD/85/wKgDsVLd05WDcBRDAAJ-elhe_ug152.mp3","mp4Url":"http://audio.xmcdn.com/group9/M07/09/A4/wKgDYlVfIdnx9t-zAAD7MI_mntI228.m4a","subCategory":"内涵段子","tag":"冷笑话;微博;笑话大王","title":"两位画家"}]
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
             * album : 微博冷笑话
             * albumUrl : http://www.ximalaya.com/1000558/album/2677704
             * author : 笑话大王
             * category : 娱乐
             * id : 72
             * mp3Url : http://audio.xmcdn.com/group3/M05/CD/85/wKgDsVLd05WDcBRDAAJ-elhe_ug152.mp3
             * mp4Url : http://audio.xmcdn.com/group9/M07/09/A4/wKgDYlVfIdnx9t-zAAD7MI_mntI228.m4a
             * subCategory : 内涵段子
             * tag : 冷笑话;微博;笑话大王
             * title : 两位画家
             */

            private String album;
            private String albumUrl;
            private String author;
            private String category;
            private int id;
            private String mp3Url;
            private String mp4Url;
            private String subCategory;
            private String tag;
            private String title;

            public void setAlbum(String album) {
                this.album = album;
            }

            public void setAlbumUrl(String albumUrl) {
                this.albumUrl = albumUrl;
            }

            public void setAuthor(String author) {
                this.author = author;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setMp3Url(String mp3Url) {
                this.mp3Url = mp3Url;
            }

            public void setMp4Url(String mp4Url) {
                this.mp4Url = mp4Url;
            }

            public void setSubCategory(String subCategory) {
                this.subCategory = subCategory;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getAlbum() {
                return album;
            }

            public String getAlbumUrl() {
                return albumUrl;
            }

            public String getAuthor() {
                return author;
            }

            public String getCategory() {
                return category;
            }

            public int getId() {
                return id;
            }

            public String getMp3Url() {
                return mp3Url;
            }

            public String getMp4Url() {
                return mp4Url;
            }

            public String getSubCategory() {
                return subCategory;
            }

            public String getTag() {
                return tag;
            }

            public String getTitle() {
                return title;
            }
        }
    }

    public static class AnswerEntity {
        /**
         * text : 请听笑话,两位画家.
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
         * intent : QUERY
         */

        private String intent;

        public void setIntent(String intent) {
            this.intent = intent;
        }

        public String getIntent() {
            return intent;
        }
    }
}
