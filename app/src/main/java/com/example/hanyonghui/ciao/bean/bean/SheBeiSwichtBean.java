package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/11/11.
 * Holle Android
 */

public class SheBeiSwichtBean {

    /**
     * c : {"aiopen":0,"autowater":"","defalutplant":"","devicetype":4,"did":"0ad3e91393954058b877618f689504f0","dn":"浇灌泵","mac":"B0:F8:93:10:61:98","online":0,"ps":[],"version":""}
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
         * aiopen : 0
         * autowater :
         * defalutplant :
         * devicetype : 4
         * did : 0ad3e91393954058b877618f689504f0
         * dn : 浇灌泵
         * mac : B0:F8:93:10:61:98
         * online : 0
         * ps : []
         * version :
         */

        private int aiopen;
        private String autowater;
        private String defalutplant;
        private int devicetype;
        private String did;
        private String dn;
        private String mac;
        private int online;
        private String version;
        private List<?> ps;

        public int getAiopen() {
            return aiopen;
        }

        public void setAiopen(int aiopen) {
            this.aiopen = aiopen;
        }

        public String getAutowater() {
            return autowater;
        }

        public void setAutowater(String autowater) {
            this.autowater = autowater;
        }

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
