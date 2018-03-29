package com.example.hanyonghui.ciao.bean.bean;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/27.
 * Holle Android
 */

public class WXTokenBean {
    /**
     * access_token : xFkD6H6YnO5cVhiIi7Fmod65vEspTa7gsPIQ26_rO8-EJdBcPaM14FOL5ZgDLkPKb8HIKNCa8o_pkZCxdju_Fg
     * expires_in : 7200
     * refresh_token : KHf6JZcg2Otez5yv0btTphTW2RnEubbehRK9vJLWIMyqBrn6jRFb5nN6cRD-cPr858NKBvu1IxrOSwiYEd1fZg
     * openid : ofq8awup2x5jEiLKbLBcAPomD6yE
     * scope : snsapi_userinfo
     * unionid : ogEsUwGuDyc3F-JP1K8Jn6H1j7Qc
     */

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
