package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * Created by hanyonghui on 2017/7/30.
 */

public class PlanDataDescribeBean {
    /**
     * c : {"alias":"轮锋菊 松虫草","ciaotype":2,"complexity":1,"content":"蓝盆花又名轮锋菊，松虫草。川续断科山萝卜（蓝盆花）属。原产南欧地区。多年生草本，高30 ～50厘米，叶片披针形，边缘齿状。花序头状，花玫红色。花期4～5月","genera":"","geog":"中国","geogurl":"","geomancys":"1","gurposes":"","id":"07ceb9e5c82551197fecbd2e22058941","imgurl":"http://54.223.138.185:8088/ciao_cms/resources/Images/b10b9933c187667fe0ba3572bbc4eaf9.jpg","lifecirle":"","places":"1","plifeCirle":112,"populars":"1","presents":"1","reference":"","scientificname":"","seasons":"1","typecontent":"","typename":"蓝盆花"}
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
         * alias : 轮锋菊 松虫草
         * ciaotype : 2
         * complexity : 1
         * content : 蓝盆花又名轮锋菊，松虫草。川续断科山萝卜（蓝盆花）属。原产南欧地区。多年生草本，高30 ～50厘米，叶片披针形，边缘齿状。花序头状，花玫红色。花期4～5月
         * genera :
         * geog : 中国
         * geogurl :
         * geomancys : 1
         * gurposes :
         * id : 07ceb9e5c82551197fecbd2e22058941
         * imgurl : http://54.223.138.185:8088/ciao_cms/resources/Images/b10b9933c187667fe0ba3572bbc4eaf9.jpg
         * lifecirle :
         * places : 1
         * plifeCirle : 112
         * populars : 1
         * presents : 1
         * reference :
         * scientificname :
         * seasons : 1
         * typecontent :
         * typename : 蓝盆花
         */

        private String alias;
        private int ciaotype;
        private int complexity;
        private String content;
        private String genera;
        private String geog;
        private String geogurl;
        private String geomancys;
        private String gurposes;
        private String id;
        private String imgurl;
        private String lifecirle;
        private String places;
        private int plifeCirle;
        private String populars;
        private String presents;
        private String reference;
        private String scientificname;
        private String seasons;
        private String typecontent;
        private String typename;

        public String getAlias() {
            return alias;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public int getCiaotype() {
            return ciaotype;
        }

        public void setCiaotype(int ciaotype) {
            this.ciaotype = ciaotype;
        }

        public int getComplexity() {
            return complexity;
        }

        public void setComplexity(int complexity) {
            this.complexity = complexity;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getGenera() {
            return genera;
        }

        public void setGenera(String genera) {
            this.genera = genera;
        }

        public String getGeog() {
            return geog;
        }

        public void setGeog(String geog) {
            this.geog = geog;
        }

        public String getGeogurl() {
            return geogurl;
        }

        public void setGeogurl(String geogurl) {
            this.geogurl = geogurl;
        }

        public String getGeomancys() {
            return geomancys;
        }

        public void setGeomancys(String geomancys) {
            this.geomancys = geomancys;
        }

        public String getGurposes() {
            return gurposes;
        }

        public void setGurposes(String gurposes) {
            this.gurposes = gurposes;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getImgurl() {
            return imgurl;
        }

        public void setImgurl(String imgurl) {
            this.imgurl = imgurl;
        }

        public String getLifecirle() {
            return lifecirle;
        }

        public void setLifecirle(String lifecirle) {
            this.lifecirle = lifecirle;
        }

        public String getPlaces() {
            return places;
        }

        public void setPlaces(String places) {
            this.places = places;
        }

        public int getPlifeCirle() {
            return plifeCirle;
        }

        public void setPlifeCirle(int plifeCirle) {
            this.plifeCirle = plifeCirle;
        }

        public String getPopulars() {
            return populars;
        }

        public void setPopulars(String populars) {
            this.populars = populars;
        }

        public String getPresents() {
            return presents;
        }

        public void setPresents(String presents) {
            this.presents = presents;
        }

        public String getReference() {
            return reference;
        }

        public void setReference(String reference) {
            this.reference = reference;
        }

        public String getScientificname() {
            return scientificname;
        }

        public void setScientificname(String scientificname) {
            this.scientificname = scientificname;
        }

        public String getSeasons() {
            return seasons;
        }

        public void setSeasons(String seasons) {
            this.seasons = seasons;
        }

        public String getTypecontent() {
            return typecontent;
        }

        public void setTypecontent(String typecontent) {
            this.typecontent = typecontent;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
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
