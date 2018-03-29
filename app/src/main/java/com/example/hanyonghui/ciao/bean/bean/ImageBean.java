package com.example.hanyonghui.ciao.bean.bean;

/**
 * Created by hanyonghui on 2017/8/31.
 */

public class ImageBean {
    /**
     * c : resource/d9969e1b8f6a4b3c8a4daeffef33f52701.jpg
     * h : {"s":1}
     */

    private String c;
    private HBean h;

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public HBean getH() {
        return h;
    }

    public void setH(HBean h) {
        this.h = h;
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
