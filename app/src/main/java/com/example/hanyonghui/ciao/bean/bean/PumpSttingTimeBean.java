package com.example.hanyonghui.ciao.bean.bean;

/**
 * Created by hanyonghui on 2017/9/4.
 */

public class PumpSttingTimeBean {
    private String did; // 设备ID
    private String week; // 周 多个日期 1，3，7
    private String hours; // 时
    private String minute; // 分
    private String timeLength; // 持续时间

    private String lightness;
    public String getLightness() {
        return lightness;
    }

    public void setLightness(String lightness) {
        this.lightness = lightness;
    }



    public void setDid(String did) {
        this.did = did;
    }

    public void setWeek(String week) {
        this.week = week;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setMinute(String minute) {
        this.minute = minute;
    }

    public void setDuration(String timeLength) {
        this.timeLength = timeLength;
    }


}
