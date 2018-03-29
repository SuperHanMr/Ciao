package com.example.hanyonghui.ciao.bean.eventbusmodel;

/**
 * Created by hanyonghui on 2017/9/20.
 */

public class PostDatailsModel {

    private int position;

    public PostDatailsModel(int position){
        this.position= position;
    }

   public int getPostItem(){
        return position;
    }

}
