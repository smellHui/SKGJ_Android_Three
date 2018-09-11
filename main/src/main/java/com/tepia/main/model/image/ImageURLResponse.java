package com.tepia.main.model.image;

import java.util.List;

/**
 * Created by Joeshould on 2018/5/30.
 */

public class ImageURLResponse {

    /**
     * total : 2
     * data : [{"businessType":"","isDelete":"0","middleId":"b4a30a98fa584013879944c563a706ff","objectId":"b5196388-9bb5-40a5-aebc-56d64ed5cbe1","picGroup":0,"picId":"90d1aa81ec6447b89cc96d064d8cc55c","picName":"IMG_20180530_093853.jpg","picPath":"http://192.168.30.220/2018/6/1/normal/4d467f4184ac41f28996ed9f92ce72bc.jpg?st111=kx4zCvG_sb8zJ2qh-2QYXA&e111=1527817371","picStorePhyPath":"2018\\6\\1\\normal\\4d467f4184ac41f28996ed9f92ce72bc.jpg","picType":"jpg","thumbnailPath":"2018\\6\\1\\normal\\4d467f4184ac41f28996ed9f92ce72bc_thumbnail.jpg"},{"businessType":"","isDelete":"0","middleId":"d685b006f0304028a5e51a3d6914f1ed","objectId":"b5196388-9bb5-40a5-aebc-56d64ed5cbe1","picGroup":1,"picId":"8abcbc5740d3473d865c067e06fe3b99","picName":"IMG_20180530_093853.jpg","picPath":"http://192.168.30.220/2018/6/1/normal/344785b5b5954a11aa35ff4dc98678e5.jpg?st111=crdbRiQTu_jijfpeMCPTow&e111=1527817371","picStorePhyPath":"2018\\6\\1\\normal\\344785b5b5954a11aa35ff4dc98678e5.jpg","picType":"jpg","thumbnailPath":"2018\\6\\1\\normal\\344785b5b5954a11aa35ff4dc98678e5_thumbnail.jpg"}]
     * success : true
     * beforePicViewList : {"total":1,"data":[{"businessType":"","isDelete":"0","middleId":"b4a30a98fa584013879944c563a706ff","objectId":"b5196388-9bb5-40a5-aebc-56d64ed5cbe1","picGroup":0,"picId":"90d1aa81ec6447b89cc96d064d8cc55c","picName":"IMG_20180530_093853.jpg","picPath":"http://192.168.30.220/2018/6/1/normal/4d467f4184ac41f28996ed9f92ce72bc.jpg?st111=kx4zCvG_sb8zJ2qh-2QYXA&e111=1527817371","picStorePhyPath":"2018\\6\\1\\normal\\4d467f4184ac41f28996ed9f92ce72bc.jpg","picType":"jpg","thumbnailPath":"2018\\6\\1\\normal\\4d467f4184ac41f28996ed9f92ce72bc_thumbnail.jpg"}],"success":true}
     * afterPicViewList : {"total":1,"data":[{"businessType":"","isDelete":"0","middleId":"d685b006f0304028a5e51a3d6914f1ed","objectId":"b5196388-9bb5-40a5-aebc-56d64ed5cbe1","picGroup":1,"picId":"8abcbc5740d3473d865c067e06fe3b99","picName":"IMG_20180530_093853.jpg","picPath":"http://192.168.30.220/2018/6/1/normal/344785b5b5954a11aa35ff4dc98678e5.jpg?st111=crdbRiQTu_jijfpeMCPTow&e111=1527817371","picStorePhyPath":"2018\\6\\1\\normal\\344785b5b5954a11aa35ff4dc98678e5.jpg","picType":"jpg","thumbnailPath":"2018\\6\\1\\normal\\344785b5b5954a11aa35ff4dc98678e5_thumbnail.jpg"}],"success":true}
     * SUCCESS : true
     * MESSAGE : 读取文件成功
     */

    private int total;
    private boolean success;
    private BeforePicViewListBean beforePicViewList;
    private AfterPicViewListBean afterPicViewList;
    private boolean SUCCESS;
    private String MESSAGE;
    private List<ImageInfoBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public BeforePicViewListBean getBeforePicViewList() {
        return beforePicViewList;
    }

    public void setBeforePicViewList(BeforePicViewListBean beforePicViewList) {
        this.beforePicViewList = beforePicViewList;
    }

    public AfterPicViewListBean getAfterPicViewList() {
        return afterPicViewList;
    }

    public void setAfterPicViewList(AfterPicViewListBean afterPicViewList) {
        this.afterPicViewList = afterPicViewList;
    }

    public boolean isSUCCESS() {
        return SUCCESS;
    }

    public void setSUCCESS(boolean SUCCESS) {
        this.SUCCESS = SUCCESS;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public List<ImageInfoBean> getData() {
        return data;
    }

    public void setData(List<ImageInfoBean> data) {
        this.data = data;
    }

    public static class BeforePicViewListBean {
        /**
         * total : 1
         * data : [{"businessType":"","isDelete":"0","middleId":"b4a30a98fa584013879944c563a706ff","objectId":"b5196388-9bb5-40a5-aebc-56d64ed5cbe1","picGroup":0,"picId":"90d1aa81ec6447b89cc96d064d8cc55c","picName":"IMG_20180530_093853.jpg","picPath":"http://192.168.30.220/2018/6/1/normal/4d467f4184ac41f28996ed9f92ce72bc.jpg?st111=kx4zCvG_sb8zJ2qh-2QYXA&e111=1527817371","picStorePhyPath":"2018\\6\\1\\normal\\4d467f4184ac41f28996ed9f92ce72bc.jpg","picType":"jpg","thumbnailPath":"2018\\6\\1\\normal\\4d467f4184ac41f28996ed9f92ce72bc_thumbnail.jpg"}]
         * success : true
         */

        private int total;
        private boolean success;
        private List<ImageInfoBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public List<ImageInfoBean> getData() {
            return data;
        }

        public void setData(List<ImageInfoBean> data) {
            this.data = data;
        }


    }

    public static class AfterPicViewListBean {
        /**
         * total : 1
         * data : [{"businessType":"","isDelete":"0","middleId":"d685b006f0304028a5e51a3d6914f1ed","objectId":"b5196388-9bb5-40a5-aebc-56d64ed5cbe1","picGroup":1,"picId":"8abcbc5740d3473d865c067e06fe3b99","picName":"IMG_20180530_093853.jpg","picPath":"http://192.168.30.220/2018/6/1/normal/344785b5b5954a11aa35ff4dc98678e5.jpg?st111=crdbRiQTu_jijfpeMCPTow&e111=1527817371","picStorePhyPath":"2018\\6\\1\\normal\\344785b5b5954a11aa35ff4dc98678e5.jpg","picType":"jpg","thumbnailPath":"2018\\6\\1\\normal\\344785b5b5954a11aa35ff4dc98678e5_thumbnail.jpg"}]
         * success : true
         */

        private int total;
        private boolean success;
        private List<ImageInfoBean> data;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public List<ImageInfoBean> getData() {
            return data;
        }

        public void setData(List<ImageInfoBean> data) {
            this.data = data;
        }

    }


}
