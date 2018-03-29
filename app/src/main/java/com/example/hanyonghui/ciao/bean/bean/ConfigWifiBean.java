package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * Created by hanyonghui on 2017/8/23.
 */

public class ConfigWifiBean {
    /**
     * c : {"defalutplant":"","devicetype":3,"did":"9b23bae5af904173bc6a7e5cb09babba","dn":"B0:F8:93:10:00:70","mac":"B0:F8:93:10:00:70","online":1,"ps":"","version":""}
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
         * defalutplant :
         * devicetype : 3
         * did : 9b23bae5af904173bc6a7e5cb09babba
         * dn : B0:F8:93:10:00:70
         * mac : B0:F8:93:10:00:70
         * online : 1
         * ps :
         * version :
         */

        private String defalutplant;
        private int devicetype;
        private String did;
        private String dn;
        private String mac;
        private int online;
        private String ps;
        private String version;

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

        public String getPs() {
            return ps;
        }

        public void setPs(String ps) {
            this.ps = ps;
        }

        public String getVersion() {
            return version;
        }

        public void setVersion(String version) {
            this.version = version;
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
