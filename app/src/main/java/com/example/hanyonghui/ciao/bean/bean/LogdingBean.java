package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * Created by hanyonghui on 2017/8/20.
 */

public class LogdingBean {
    /**
     * c : {"isnew":true,"nn":"177****8669","openid":"","plants":0,"pn":"17710558669","purl":"","token":"","uid":"915c70af770848d78985bb8393361324","unionId":""}
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
         * isnew : true
         * nn : 177****8669
         * openid :
         * plants : 0
         * pn : 17710558669
         * purl :
         * token :
         * uid : 915c70af770848d78985bb8393361324
         * unionId :
         */


        private boolean isnew;
        private String nn;
        private String openid;
        private int plants;
        private String pn;
        private String purl;
        private String token;
        private String uid;
        private String unionId;



        public boolean isIsnew() {
            return isnew;
        }

        public void setIsnew(boolean isnew) {
            this.isnew = isnew;
        }

        public String getNn() {
            return nn;
        }

        public void setNn(String nn) {
            this.nn = nn;
        }

        public String getOpenid() {
            return openid;
        }

        public void setOpenid(String openid) {
            this.openid = openid;
        }

        public int getPlants() {
            return plants;
        }

        public void setPlants(int plants) {
            this.plants = plants;
        }

        public String getPn() {
            return pn;
        }

        public void setPn(String pn) {
            this.pn = pn;
        }

        public String getPurl() {
            return purl;
        }

        public void setPurl(String purl) {
            this.purl = purl;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getUnionId() {
            return unionId;
        }

        public void setUnionId(String unionId) {
            this.unionId = unionId;
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
