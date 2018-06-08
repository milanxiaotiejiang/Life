package com.seabreeze.life.entity.music;

import java.util.List;

public class BannerListBean {


    /**
     * code : 1
     * data : [{"id":151,"res_cover":"http://up.wawa.fm/20,026ffccfb849d9","cut_cover":"http://up.wawa.fm/20,026ffccfb849d9?width=500","color":"#000000","link":"1269","category":2,"create_at":"2018-04-24T10:38:45+08:00","update_at":"2018-04-24T10:38:45+08:00","status":1,"Flag":0},{"id":150,"res_cover":"http://up.wawa.fm/20,025f62e1383951","cut_cover":"http://up.wawa.fm/20,025f62e1383951?width=500","color":"#000000","link":"1247","category":2,"create_at":"2018-04-19T14:11:02+08:00","update_at":"2018-04-19T14:11:02+08:00","status":1,"Flag":0},{"id":149,"res_cover":"http://up.wawa.fm/21,025c0f69568fde","cut_cover":"http://up.wawa.fm/21,025c0f69568fde?width=500","color":"#000000","link":"1239","category":2,"create_at":"2018-04-18T10:26:24+08:00","update_at":"2018-04-18T10:26:24+08:00","status":1,"Flag":0},{"id":148,"res_cover":"http://up.wawa.fm/17,025ac19e94cf77","cut_cover":"http://up.wawa.fm/17,025ac19e94cf77?width=500","color":"#000000","link":"1238","category":2,"create_at":"2018-04-17T12:09:34+08:00","update_at":"2018-04-17T14:20:57+08:00","status":1,"Flag":0},{"id":147,"res_cover":"http://up.wawa.fm/19,0252ee710194cf","cut_cover":"http://up.wawa.fm/19,0252ee710194cf?width=500","color":"#000000","link":"1232","category":2,"create_at":"2018-04-13T15:33:21+08:00","update_at":"2018-04-13T15:33:21+08:00","status":1,"Flag":0},{"id":145,"res_cover":"http://up.wawa.fm/18,024e7c2e9091a5","cut_cover":"http://up.wawa.fm/18,024e7c2e9091a5?width=500","color":"#000000","link":"1221","category":2,"create_at":"2018-04-12T10:16:16+08:00","update_at":"2018-04-12T10:16:16+08:00","status":1,"Flag":0}]
     * page : {"page":1,"total_count":6,"total_page":1}
     */

    private int code;
    private PageBean page;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public PageBean getPage() {
        return page;
    }

    public void setPage(PageBean page) {
        this.page = page;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class PageBean {
        /**
         * page : 1
         * total_count : 6
         * total_page : 1
         */

        private int page;
        private int total_count;
        private int total_page;

        public int getPage() {
            return page;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public int getTotal_count() {
            return total_count;
        }

        public void setTotal_count(int total_count) {
            this.total_count = total_count;
        }

        public int getTotal_page() {
            return total_page;
        }

        public void setTotal_page(int total_page) {
            this.total_page = total_page;
        }
    }

    public static class DataBean {
        /**
         * id : 151
         * res_cover : http://up.wawa.fm/20,026ffccfb849d9
         * cut_cover : http://up.wawa.fm/20,026ffccfb849d9?width=500
         * color : #000000
         * link : 1269
         * category : 2
         * create_at : 2018-04-24T10:38:45+08:00
         * update_at : 2018-04-24T10:38:45+08:00
         * status : 1
         * Flag : 0
         */

        private int id;
        private String res_cover;
        private String cut_cover;
        private String color;
        private String link;
        private int category;
        private String create_at;
        private String update_at;
        private int status;
        private int Flag;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getRes_cover() {
            return res_cover;
        }

        public void setRes_cover(String res_cover) {
            this.res_cover = res_cover;
        }

        public String getCut_cover() {
            return cut_cover;
        }

        public void setCut_cover(String cut_cover) {
            this.cut_cover = cut_cover;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public String getUpdate_at() {
            return update_at;
        }

        public void setUpdate_at(String update_at) {
            this.update_at = update_at;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public int getFlag() {
            return Flag;
        }

        public void setFlag(int Flag) {
            this.Flag = Flag;
        }
    }
}
