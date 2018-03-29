package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * Created by hanyonghui on 2017/9/4.
 */

public class PumpBean {
    /**
     * h : {"s":1}
     * m : []
     * c : {"mac":"B0:F8:93:10:00:80","online":1,"version":"","devicetype":3,"defalutplant":"","did":"bea57e4c5d644925b7c01e0f24d74294","dn":"B0:F8:93:10:00:80","ps":""}
     */

    private HBean h;
    private CBean c;
    private List<?> m;

    public HBean getH() {
        return h;
    }

    public void setH(HBean h) {
        this.h = h;
    }

    public CBean getC() {
        return c;
    }

    public void setC(CBean c) {
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
         * mac : B0:F8:93:10:00:80
         * online : 1
         * version :
         * devicetype : 3
         * defalutplant :
         * did : bea57e4c5d644925b7c01e0f24d74294
         * dn : B0:F8:93:10:00:80
         * ps :
         */

        private String mac;
        private int online;
        private String version;
        private int devicetype;
        private String defalutplant;
        private String did;
        private String dn;
        private String ps;

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

        public int getDevicetype() {
            return devicetype;
        }

        public void setDevicetype(int devicetype) {
            this.devicetype = devicetype;
        }

        public String getDefalutplant() {
            return defalutplant;
        }

        public void setDefalutplant(String defalutplant) {
            this.defalutplant = defalutplant;
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

        public String getPs() {
            return ps;
        }

        public void setPs(String ps) {
            this.ps = ps;
        }
    }
}
