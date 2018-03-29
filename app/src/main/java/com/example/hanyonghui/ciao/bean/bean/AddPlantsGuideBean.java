package com.example.hanyonghui.ciao.bean.bean;

/**
 * Created by hanyonghui on 2017/9/6.
 */

public class AddPlantsGuideBean {


    private String ptypeid;  // 植物品类号
    private String title;    // 标题或生长阶段名称
    private String day;      // 生长天数

    public String getPtypeid() {
        return ptypeid;
    }

    public String getTitle() {
        return title;
    }

    public String getDay() {
        return day;
    }

    public String getContent() {
        return content;
    }

    public String getGuideUrl() {
        return guideUrl;
    }

    public String getCircleNo() {
        return circleNo;
    }

    public String getWatering() {
        return watering;
    }

    public String getSpray() {
        return spray;
    }

    private String content;  // 描述
    private String guideUrl; // 植物周期对应的图片Url
    private String circleNo; // 生长周期序号
    private String watering; // 浇水 时间周期 1	每天一次，2每周一次，3 每月一次
    private String spray;    // 喷雾 时间周期 1	每天一次，2每周一次，3 每月一次


    public void setPtypeid(String ptypeid) {
        this.ptypeid = ptypeid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setGuideUrl(String guideUrl) {
        this.guideUrl = guideUrl;
    }

    public void setCircleNo(String circleNo) {
        this.circleNo = circleNo;
    }

    public void setWatering(String watering) {
        this.watering = watering;
    }

    public void setSpray(String spray) {
        this.spray = spray;
    }

    @Override
    public String toString() {
        return "AddPlantsGuideBean{" +
                "ptypeid='" + ptypeid + '\'' +
                ", title='" + title + '\'' +
                ", day='" + day + '\'' +
                ", content='" + content + '\'' +
                ", guideUrl='" + guideUrl + '\'' +
                ", circleNo='" + circleNo + '\'' +
                ", watering='" + watering + '\'' +
                ", spray='" + spray + '\'' +
                '}';
    }
}
