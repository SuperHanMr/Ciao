package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * Created by hanyonghui on 2017/9/16.
 */

public class TaskListBena {
    /**
     * h : {"s":1}
     * m : []
     * c : [{"type":"B1","mac":"B0F893100080","week":"1","hours":9,"minute":50,"second":0,"timeLength":5,"lightness":0},{"type":"B1","mac":"B0F893100080","week":"2","hours":9,"minute":50,"second":0,"timeLength":5,"lightness":0},{"type":"B1","mac":"B0F893100080","week":"3","hours":9,"minute":50,"second":0,"timeLength":5,"lightness":0},{"type":"B1","mac":"B0F893100080","week":"4","hours":9,"minute":50,"second":0,"timeLength":5,"lightness":0}]
     */

    private HBean h;
    private List<?> m;
    private List<CBean> c;

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

    public List<CBean> getC() {
        return c;
    }

    public void setC(List<CBean> c) {
        this.c = c;
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
         * type : B1
         * mac : B0F893100080
         * week : 1
         * hours : 9
         * minute : 50
         * second : 0
         * timeLength : 5
         * lightness : 0
         */

        private String type;
        private String mac;
        private String week;
        private int hours;
        private int minute;
        private int second;
        private int timeLength;
        private int lightness;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getMac() {
            return mac;
        }

        public void setMac(String mac) {
            this.mac = mac;
        }

        public String getWeek() {
            return week;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public int getHours() {
            return hours;
        }

        public void setHours(int hours) {
            this.hours = hours;
        }

        public int getMinute() {
            return minute;
        }

        public void setMinute(int minute) {
            this.minute = minute;
        }

        public int getSecond() {
            return second;
        }

        public void setSecond(int second) {
            this.second = second;
        }

        public int getTimeLength() {
            return timeLength;
        }

        public void setTimeLength(int timeLength) {
            this.timeLength = timeLength;
        }

        public int getLightness() {
            return lightness;
        }

        public void setLightness(int lightness) {
            this.lightness = lightness;
        }
    }
}
