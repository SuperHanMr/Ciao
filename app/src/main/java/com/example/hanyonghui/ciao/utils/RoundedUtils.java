package com.example.hanyonghui.ciao.utils;

import com.example.hanyonghui.ciao.bean.bean.ImageBean;
import com.google.gson.Gson;

import java.math.BigDecimal;

/**
 * Created by hanyonghui on 2017/7/30.
 */

public class RoundedUtils {


    // 设置水分
    public static String setMoisture(int moisture){
        int MOISTURE =100/5*moisture;
        return 20+"*"+MOISTURE;
    }

    // 设置光照
    public static String setLight(int light){
        int LIGHT = 10000/5*light;
        return 2000+"*"+LIGHT;
    }

    //设置化肥
    public static String setFertilizer(int fertilizer){
        int FERTILIZER = 10000/5*fertilizer;
        return 2000+"*"+FERTILIZER;


    }

    //获取水分
    public static int getMoisture(String s){
        if (s==null){
            return 1;
        }
        String substring = s.substring(s.indexOf("*") + 1);
        int Moisture = Integer.parseInt(substring);
        double mois =  Moisture/(100/5);
        BigDecimal decimal = new BigDecimal(mois).setScale(0,BigDecimal.ROUND_HALF_UP);
        return decimal.intValue();

    }

    // 获取光照
    public static int getLight(String s){
        if (s==null){
            return 1;
        }
        String substring = s.substring(s.indexOf("*") + 1);
        int Light = Integer.parseInt(substring);
        double light = Light/(10000/5);
        BigDecimal decimal = new BigDecimal(light).setScale(0,BigDecimal.ROUND_HALF_UP);
        return decimal.intValue();
    }

    // 获取化肥
    public static int getFertilizer(String s){
        if (s==null){
         return 1;
        }
        String substring = s.substring(s.indexOf("*") + 1);
        int Fertilizer = Integer.parseInt(substring);
        double fertilizer  = Fertilizer/(10000/5);
        BigDecimal decimal = new BigDecimal(fertilizer).setScale(0,BigDecimal.ROUND_HALF_UP);
        return decimal.intValue();
    }



    public static String getImageUrl(String s){
        Gson gson = new Gson();
        ImageBean imageBean = gson.fromJson(s, ImageBean.class);
        String imagUrl = imageBean.getC();
        return imagUrl;
    }

}
