package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * Created by hanyonghui on 2017/9/2.
 */

public class PlantsIdBean {
    /**
     * h : {"s":1}
     * m : []
     * c : {"typeNo":"f9e29102fbc242b68557a6e34bd125d1"}
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
         * typeNo : f9e29102fbc242b68557a6e34bd125d1
         */

        private String typeNo;

        public String getTypeNo() {
            return typeNo;
        }

        public void setTypeNo(String typeNo) {
            this.typeNo = typeNo;
        }
    }
}
