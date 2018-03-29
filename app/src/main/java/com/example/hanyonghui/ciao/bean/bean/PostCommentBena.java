package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * Created by hanyonghui on 2017/9/19.
 */

public class PostCommentBena {
    /**
     * c : {"body":"111","createtime":"2017-09-19 14:18:30","id":"c9d6fa5b89794d9881b3146ce6ed8f1d","iscomment":1,"pareteid":"87033941b633466d857f90ee2a46eb9e","twitter":"","twitterid":"04e2b9c4a7764139bbac24fce5b67d68","user":"","userid":"208b5db76fd84f28a5883739db7d4784"}
     * h : {"s":1}
     * m : []
     */

    private CBean c;
    private HBean h;
    private List<?> m;

    public CBean getC() {
        return c;
    }

    public void setC(CBean c) {
        this.c = c;
    }

    public HBean getH() {
        return h;
    }

    public void setH(HBean h) {
        this.h = h;
    }

    public List<?> getM() {
        return m;
    }

    public void setM(List<?> m) {
        this.m = m;
    }

    public static class CBean {
        /**
         * body : 111
         * createtime : 2017-09-19 14:18:30
         * id : c9d6fa5b89794d9881b3146ce6ed8f1d
         * iscomment : 1
         * pareteid : 87033941b633466d857f90ee2a46eb9e
         * twitter :
         * twitterid : 04e2b9c4a7764139bbac24fce5b67d68
         * user :
         * userid : 208b5db76fd84f28a5883739db7d4784
         */

        private String body;
        private String createtime;
        private String id;
        private int iscomment;
        private String pareteid;
        private String twitter;
        private String twitterid;
        private String user;
        private String userid;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getIscomment() {
            return iscomment;
        }

        public void setIscomment(int iscomment) {
            this.iscomment = iscomment;
        }

        public String getPareteid() {
            return pareteid;
        }

        public void setPareteid(String pareteid) {
            this.pareteid = pareteid;
        }

        public String getTwitter() {
            return twitter;
        }

        public void setTwitter(String twitter) {
            this.twitter = twitter;
        }

        public String getTwitterid() {
            return twitterid;
        }

        public void setTwitterid(String twitterid) {
            this.twitterid = twitterid;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }

        public String getUserid() {
            return userid;
        }

        public void setUserid(String userid) {
            this.userid = userid;
        }
    }

    public static class HBean {
        /**
         * s : 1
         */

        private int s;

        public int getS() {
            return s;
        }

        public void setS(int s) {
            this.s = s;
        }
    }
}
