package com.example.hanyonghui.ciao.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by hanyonghui on 2017/9/17.
 */

public class GetNumUtlis {

    public static int[] getNum(String s){
        String[] split = s.split(",");
        int num[] = new int[split.length];
        for (int i = 0; i < split.length; i++) {
            LogUtils.e(split[i]);
            num[i] = Integer.parseInt(split[i]);

        }
        return num;
    }
}
