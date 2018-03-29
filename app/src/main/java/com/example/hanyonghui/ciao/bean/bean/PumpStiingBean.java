package com.example.hanyonghui.ciao.bean.bean;

/**
 * Created by hanyonghui on 2017/8/27.
 */

public class PumpStiingBean {


    public PumpStiingBean(String started,String end,int seekBar) {
        this.started = started;
        this.end = end;
    }

    private String started;
    private String end;

    public int getSeekBar() {
        return seekBar;
    }

    public void setSeekBar(int seekBar) {
        this.seekBar = seekBar;
    }

    private int seekBar;




    public String getStarted() {
        return started;
    }

    public String getEnd() {
        return end;
    }


    public void setStarted(String started) {
        this.started = started;
    }

    public void setEnd(String end) {
        this.end = end;
    }


}
