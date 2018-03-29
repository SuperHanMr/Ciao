package com.example.hanyonghui.ciao.bean.eventbusmodel;

import com.example.hanyonghui.ciao.bean.bean.UserBean;

/**
 * Created by hanyonghui on 2017/8/31.
 */

public class UserModel {

    private UserBean userBean;

    public UserModel(UserBean bean){
        this.userBean = bean;
    }

    public UserBean getUserBean(){
        return userBean;
    }

}
