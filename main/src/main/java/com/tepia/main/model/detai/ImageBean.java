package com.tepia.main.model.detai;

import android.os.Parcel;
import android.os.Parcelable;

import com.tepia.base.http.BaseResponse;

import java.io.Serializable;
import java.util.List;

/**
 * 图像站
 * @author liying
 * @date 2018/7/29
 */

public class ImageBean extends BaseResponse{

    /**
     * data : {"fileServerUrl":"http://119.1.151.132:3003/","pictures":{"pageNum":1,"pageSize":10,"size":4,"startRow":1,"endRow":4,"total":4,"pages":1,"list":[{"stcd":"10170406","tm":"2018-04-11 00:03:49","pictm":"2018-04-11 00:00:00","picpath":"20180411\\0000170406-01-03-180411000000.jpg"},{"stcd":"10170406","tm":"2018-04-11 04:03:39","pictm":"2018-04-11 04:00:00","picpath":"20180411\\0000170406-01-03-180411040000.jpg"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean{
        /**
         * fileServerUrl : http://119.1.151.132:3003/
         * pictures : {"pageNum":1,"pageSize":10,"size":4,"startRow":1,"endRow":4,"total":4,"pages":1,"list":[{"stcd":"10170406","tm":"2018-04-11 00:03:49","pictm":"2018-04-11 00:00:00","picpath":"20180411\\0000170406-01-03-180411000000.jpg"},{"stcd":"10170406","tm":"2018-04-11 04:03:39","pictm":"2018-04-11 04:00:00","picpath":"20180411\\0000170406-01-03-180411040000.jpg"}],"prePage":0,"nextPage":0,"isFirstPage":true,"isLastPage":true,"hasPreviousPage":false,"hasNextPage":false,"navigatePages":8,"navigatepageNums":[1],"navigateFirstPage":1,"navigateLastPage":1,"firstPage":1,"lastPage":1}
         */

        private String fileServerUrl;
        private PicturesBean pictures;

        public String getFileServerUrl() {
            return fileServerUrl;
        }

        public void setFileServerUrl(String fileServerUrl) {
            this.fileServerUrl = fileServerUrl;
        }

        public PicturesBean getPictures() {
            return pictures;
        }

        public void setPictures(PicturesBean pictures) {
            this.pictures = pictures;
        }

        public static class PicturesBean{
            /**
             * pageNum : 1
             * pageSize : 10
             * size : 4
             * startRow : 1
             * endRow : 4
             * total : 4
             * pages : 1
             * list : [{"stcd":"10170406","tm":"2018-04-11 00:03:49","pictm":"2018-04-11 00:00:00","picpath":"20180411\\0000170406-01-03-180411000000.jpg"},{"stcd":"10170406","tm":"2018-04-11 04:03:39","pictm":"2018-04-11 04:00:00","picpath":"20180411\\0000170406-01-03-180411040000.jpg"}]
             * prePage : 0
             * nextPage : 0
             * isFirstPage : true
             * isLastPage : true
             * hasPreviousPage : false
             * hasNextPage : false
             * navigatePages : 8
             * navigatepageNums : [1]
             * navigateFirstPage : 1
             * navigateLastPage : 1
             * firstPage : 1
             * lastPage : 1
             */

            private int pageNum;
            private int pageSize;
            private int size;
            private int startRow;
            private int endRow;
            private int total;
            private int pages;
            private int prePage;
            private int nextPage;
            private boolean isFirstPage;
            private boolean isLastPage;
            private boolean hasPreviousPage;
            private boolean hasNextPage;
            private int navigatePages;
            private int navigateFirstPage;
            private int navigateLastPage;
            private int firstPage;
            private int lastPage;
            private List<ListBean> list;
            private List<Integer> navigatepageNums;

            public int getPageNum() {
                return pageNum;
            }

            public void setPageNum(int pageNum) {
                this.pageNum = pageNum;
            }

            public int getPageSize() {
                return pageSize;
            }

            public void setPageSize(int pageSize) {
                this.pageSize = pageSize;
            }

            public int getSize() {
                return size;
            }

            public void setSize(int size) {
                this.size = size;
            }

            public int getStartRow() {
                return startRow;
            }

            public void setStartRow(int startRow) {
                this.startRow = startRow;
            }

            public int getEndRow() {
                return endRow;
            }

            public void setEndRow(int endRow) {
                this.endRow = endRow;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public int getPages() {
                return pages;
            }

            public void setPages(int pages) {
                this.pages = pages;
            }

            public int getPrePage() {
                return prePage;
            }

            public void setPrePage(int prePage) {
                this.prePage = prePage;
            }

            public int getNextPage() {
                return nextPage;
            }

            public void setNextPage(int nextPage) {
                this.nextPage = nextPage;
            }

            public boolean isIsFirstPage() {
                return isFirstPage;
            }

            public void setIsFirstPage(boolean isFirstPage) {
                this.isFirstPage = isFirstPage;
            }

            public boolean isIsLastPage() {
                return isLastPage;
            }

            public void setIsLastPage(boolean isLastPage) {
                this.isLastPage = isLastPage;
            }

            public boolean isHasPreviousPage() {
                return hasPreviousPage;
            }

            public void setHasPreviousPage(boolean hasPreviousPage) {
                this.hasPreviousPage = hasPreviousPage;
            }

            public boolean isHasNextPage() {
                return hasNextPage;
            }

            public void setHasNextPage(boolean hasNextPage) {
                this.hasNextPage = hasNextPage;
            }

            public int getNavigatePages() {
                return navigatePages;
            }

            public void setNavigatePages(int navigatePages) {
                this.navigatePages = navigatePages;
            }

            public int getNavigateFirstPage() {
                return navigateFirstPage;
            }

            public void setNavigateFirstPage(int navigateFirstPage) {
                this.navigateFirstPage = navigateFirstPage;
            }

            public int getNavigateLastPage() {
                return navigateLastPage;
            }

            public void setNavigateLastPage(int navigateLastPage) {
                this.navigateLastPage = navigateLastPage;
            }

            public int getFirstPage() {
                return firstPage;
            }

            public void setFirstPage(int firstPage) {
                this.firstPage = firstPage;
            }

            public int getLastPage() {
                return lastPage;
            }

            public void setLastPage(int lastPage) {
                this.lastPage = lastPage;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public List<Integer> getNavigatepageNums() {
                return navigatepageNums;
            }

            public void setNavigatepageNums(List<Integer> navigatepageNums) {
                this.navigatepageNums = navigatepageNums;
            }

            public static class ListBean implements Parcelable {
                /**
                 * stcd : 10170406
                 * tm : 2018-04-11 00:03:49
                 * pictm : 2018-04-11 00:00:00
                 * picpath : 20180411\0000170406-01-03-180411000000.jpg
                 */

                private String stcd;
                private String tm;
                private String pictm;
                private String picpath;

                protected ListBean(Parcel in) {
                    stcd = in.readString();
                    tm = in.readString();
                    pictm = in.readString();
                    picpath = in.readString();
                }

                public static final Creator<ListBean> CREATOR = new Creator<ListBean>() {
                    @Override
                    public ListBean createFromParcel(Parcel in) {
                        return new ListBean(in);
                    }

                    @Override
                    public ListBean[] newArray(int size) {
                        return new ListBean[size];
                    }
                };

                public String getStcd() {
                    return stcd;
                }

                public void setStcd(String stcd) {
                    this.stcd = stcd;
                }

                public String getTm() {
                    return tm;
                }

                public void setTm(String tm) {
                    this.tm = tm;
                }

                public String getPictm() {
                    return pictm;
                }

                public void setPictm(String pictm) {
                    this.pictm = pictm;
                }

                public String getPicpath() {
                    return picpath;
                }

                public void setPicpath(String picpath) {
                    this.picpath = picpath;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int i) {
                    dest.writeString(stcd);
                    dest.writeString(tm);
                    dest.writeString(pictm);
                    dest.writeString(picpath);
                }
            }
        }
    }
}
