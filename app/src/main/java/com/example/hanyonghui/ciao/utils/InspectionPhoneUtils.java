package com.example.hanyonghui.ciao.utils;

import android.text.TextUtils;

/**
 * Created by hanyonghui on 2017/8/21.
 */

public class InspectionPhoneUtils {

    /**
     * 验证手机格式
     */
    public static boolean validateUserName(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }


    //校验密码
    public static boolean validatePassword(String password){
        String regex = "(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,8}";
        return password.matches(regex);
    }




}

