package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/28.
 * Holle Android
 */

public class WxUserIdBean {
    /**
     * c : {"isnew":true,"nn":"韩","openid":"ofq8awup2x5jEiLKbLBcAPomD6yE","plants":0,"pn":"","purl":"http://wx.qlogo.cn/mmopen/vi_32/3kYpTtnjicicC649AoWcia98mSEMw992EXIdXMiahq7gPlkbtEjOvwCByq6uFr8Y90cewQI1o1ufYa3S5LnTcZwtRw/0","role":0,"token":"5XHsVJYwHAx7bag9dTasoZ_HPY3kBEZek2IR-ClWb21x1Iu7ZMdD-cvpMrxkS6Zy5THEPu-2lcZhXU3PaVm5Gg","uid":"87033941b633466d857f90ee2a46eb9e","unionId":"ogEsUwGuDyc3F-JP1K8Jn6H1j7Qc"}
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
         * nn : 韩
         * openid : ofq8awup2x5jEiLKbLBcAPomD6yE
         * plants : 0
         * pn :
         * purl : http://wx.qlogo.cn/mmopen/vi_32/3kYpTtnjicicC649AoWcia98mSEMw992EXIdXMiahq7gPlkbtEjOvwCByq6uFr8Y90cewQI1o1ufYa3S5LnTcZwtRw/0
         * role : 0
         * token : 5XHsVJYwHAx7bag9dTasoZ_HPY3kBEZek2IR-ClWb21x1Iu7ZMdD-cvpMrxkS6Zy5THEPu-2lcZhXU3PaVm5Gg
         * uid : 87033941b633466d857f90ee2a46eb9e
         * unionId : ogEsUwGuDyc3F-JP1K8Jn6H1j7Qc
         */

        private boolean isnew;
        private String nn;
        private String openid;
        private int plants;
        private String pn;
        private String purl;
        private int role;
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

        public int getRole() {
            return role;
        }

        public void setRole(int role) {
            this.role = role;
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
