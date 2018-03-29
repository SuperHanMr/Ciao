package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * Created by hanyonghui on 2017/7/30.
 */

public class PlantDataBean {
    /**
     * c : {"breed":"扦插繁殖","fercontent":"芽苗菜期间不需要施肥。","ferrange":"0*10000","fertility":"500*4000","fostertimeurl":"","humcontent":"发芽需要温暖的环境，一般在15-25度之间。低于15度会声场生长。发芽需要温暖的环境，一般在15-25度之间。低于15度会生长迟缓。","humidity":"15*30","humrange":"-5*45","id":"13","illcontent":"需要光照，萌芽期可置于散射光下，后期逐渐增加光照强度，避免正午太阳直射光。","illrange":"0*10000","illumination":"2000*8000","insectcontent":"通风状态不佳可能感染病菌，引发霉变。","manure":"氮肥，磷肥","moicontent":"发芽期需水量较大，每天喷水两次，保持土壤稍稍积水的状态。忌水分过多，造成萎蔫。","moirange":"0*100","moisture":"20*70","preference":""}
     * h : {"s":1}
     * m : []
     */

    private CBean c;
    private HBean h;
    private List<?> m;

    public CBean getC() {
        return c;
    }

    public void setC(CBean c) {
        this.c = c;
    }

    public HBean getH() {
        return h;
    }

    public void setH(HBean h) {
        this.h = h;
    }

    public List<?> getM() {
        return m;
    }

    public void setM(List<?> m) {
        this.m = m;
    }

    public static class CBean {
        /**
         * breed : 扦插繁殖
         * fercontent : 芽苗菜期间不需要施肥。
         * ferrange : 0*10000
         * fertility : 500*4000
         * fostertimeurl :
         * humcontent : 发芽需要温暖的环境，一般在15-25度之间。低于15度会声场生长。发芽需要温暖的环境，一般在15-25度之间。低于15度会生长迟缓。
         * humidity : 15*30
         * humrange : -5*45
         * id : 13
         * illcontent : 需要光照，萌芽期可置于散射光下，后期逐渐增加光照强度，避免正午太阳直射光。
         * illrange : 0*10000
         * illumination : 2000*8000
         * insectcontent : 通风状态不佳可能感染病菌，引发霉变。
         * manure : 氮肥，磷肥
         * moicontent : 发芽期需水量较大，每天喷水两次，保持土壤稍稍积水的状态。忌水分过多，造成萎蔫。
         * moirange : 0*100
         * moisture : 20*70
         * preference :
         */

        private String breed;
        private String fercontent;
        private String ferrange;
        private String fertility;
        private String fostertimeurl;
        private String humcontent;
        private String humidity;
        private String humrange;
        private String id;
        private String illcontent;
        private String illrange;
        private String illumination;
        private String insectcontent;
        private String manure;
        private String moicontent;
        private String moirange;
        private String moisture;
        private String months;
        private String preference;


        public String getBreed() {
            return breed;
        }

        public void setBreed(String breed) {
            this.breed = breed;
        }

        public String getFercontent() {
            return fercontent;
        }

        public void setFercontent(String fercontent) {
            this.fercontent = fercontent;
        }

        public String getFerrange() {
            return ferrange;
        }

        public void setFerrange(String ferrange) {
            this.ferrange = ferrange;
        }

        public String getFertility() {
            return fertility;
        }

        public void setFertility(String fertility) {
            this.fertility = fertility;
        }

        public String getFostertimeurl() {
            return fostertimeurl;
        }

        public void setFostertimeurl(String fostertimeurl) {
            this.fostertimeurl = fostertimeurl;
        }

        public String getHumcontent() {
            return humcontent;
        }

        public void setHumcontent(String humcontent) {
            this.humcontent = humcontent;
        }

        public String getHumidity() {
            return humidity;
        }

        public void setHumidity(String humidity) {
            this.humidity = humidity;
        }

        public String getHumrange() {
            return humrange;
        }

        public void setHumrange(String humrange) {
            this.humrange = humrange;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getIllcontent() {
            return illcontent;
        }

        public void setIllcontent(String illcontent) {
            this.illcontent = illcontent;
        }

        public String getIllrange() {
            return illrange;
        }

        public void setIllrange(String illrange) {
            this.illrange = illrange;
        }

        public String getIllumination() {
            return illumination;
        }

        public void setIllumination(String illumination) {
            this.illumination = illumination;
        }

        public String getInsectcontent() {
            return insectcontent;
        }

        public void setInsectcontent(String insectcontent) {
            this.insectcontent = insectcontent;
        }

        public String getManure() {
            return manure;
        }

        public void setManure(String manure) {
            this.manure = manure;
        }

        public String getMoicontent() {
            return moicontent;
        }

        public void setMoicontent(String moicontent) {
            this.moicontent = moicontent;
        }

        public String getMoirange() {
            return moirange;
        }

        public void setMoirange(String moirange) {
            this.moirange = moirange;
        }

        public String getMoisture() {
            return moisture;
        }

        public void setMoisture(String moisture) {
            this.moisture = moisture;
        }

        public String getMonths(){
            return months;
        }

        public void setMonths(String months){
            this.months = months;
        }

        public String getPreference() {
            return preference;
        }

        public void setPreference(String preference) {
            this.preference = preference;
        }
    }

    public static class HBean {
        /**
         * s : 1
         */

        private int s;

        public int getS() {
            return s;
        }

        public void setS(int s) {
            this.s = s;
        }
    }
}
