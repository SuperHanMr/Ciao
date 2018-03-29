package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/27.
 * Holle Android
 */

public class WXUserInfoBean {
    /**
     * city :
     * country : CN
     * headimgurl : http://wx.qlogo.cn/mmopen/vi_32/3kYpTtnjicicC649AoWcia98mSEMw992EXIdXMiahq7gPlkbtEjOvwCByq6uFr8Y90cewQI1o1ufYa3S5LnTcZwtRw/0
     * language : zh_CN
     * nickname : H
     * openid : ofq8awup2x5jEiLKbLBcAPomD6yE
     * privilege : []
     * province :
     * sex : 1
     * unionid : ogEsUwGuDyc3F-JP1K8Jn6H1j7Qc
     */

    private String city;
    private String country;
    private String headimgurl;
    private String language;
    private String nickname;
    private String openid;
    private String province;
    private int sex;
    private String unionid;
    private List<?> privilege;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public List<?> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<?> privilege) {
        this.privilege = privilege;
    }
}
