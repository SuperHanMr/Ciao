package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * Created by hanyonghui on 2017/9/8.
 */

public class EqipmentListBean {
    /**
     * c : [[{"defalutplant":"","devicetype":2,"did":"ee25284ce63342caa37ad5a336c9595b","dn":"芽苗菜","mac":"AAAAAA","online":1,"ps":[],"version":"1.0"}],[{"defalutplant":"8c4e5b5fcbf340afb16834267f851d49","devicetype":3,"did":"bea57e4c5d644925b7c01e0f24d74294","dn":"B0:F8:93:10:00:80","mac":"B0:F8:93:10:00:80","online":1,"ps":[],"version":""},{"defalutplant":"ad4766e3cff74f9580b2cd84e9ad073c","devicetype":3,"did":"9b23bae5af904173bc6a7e5cb09babba","dn":"B0:F8:93:10:00:70","mac":"B0:F8:93:10:00:70","online":1,"ps":[],"version":""},{"defalutplant":"","devicetype":3,"did":"61c79eee07d241f788b4064116a96454","dn":"B0:F8:93:10:02:23","mac":"B0:F8:93:10:02:23","online":1,"ps":[],"version":""}],[{"defalutplant":"","devicetype":4,"did":"4b4216a7919c46c19a09c7bc96638b9b","dn":"浇灌阀","mac":"B:444:90:90:15","online":1,"ps":[],"version":""},{"defalutplant":"","devicetype":4,"did":"6c8bc4207d084abe8f7e6a20e8358a07","dn":"浇灌泵","mac":"B:444:80:80:15","online":1,"ps":[],"version":""}]]
     * h : {"s":1}
     * m : []
     */

    private HBean h;
    private List<List<CBean>> c;
    private List<?> m;

    public HBean getH() {
        return h;
    }

    public void setH(HBean h) {
        this.h = h;
    }

    public List<List<CBean>> getC() {
        return c;
    }

    public void setC(List<List<CBean>> c) {
        this.c = c;
    }

    public List<?> getM() {
        return m;
    }

    public void setM(List<?> m) {
        this.m = m;
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

    public static class CBean {
        /**
         * defalutplant :
         * devicetype : 2
         * did : ee25284ce63342caa37ad5a336c9595b
         * dn : 芽苗菜
         * mac : AAAAAA
         * online : 1
         * ps : []
         * version : 1.0
         */

        private String defalutplant;
        private int devicetype;
        private String did;
        private String dn;
        private String mac;
        private int online;
        private String version;
        private List<?> ps;

        public String getDefalutplant() {
            return defalutplant;
        }

        public void setDefalutplant(String defalutplant) {
            this.defalutplant = defalutplant;
        }

        public int getDevicetype() {
            return devicetype;
        }

        public void setDevicetype(int devicetype) {
            this.devicetype = devicetype;
        }

        public String getDid() {
            return did;
        }

        public void setDid(String did) {
            this.did = did;
        }

        public String getDn() {
            return dn;
        }

        public void setDn(String dn) {
            this.dn = dn;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public int getOnline() {
            return online;
        }

        public void setOnline(int online) {
            this.online = online;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
        }

        public List<?> getPs() {
            return ps;
        }

        public void setPs(List<?> ps) {
            this.ps = ps;
        }
    }
}
