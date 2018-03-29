package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * Created by hanyonghui on 2017/7/23.
 */

public class LibraryListBean {

    /**
     * h : {"s":1}
     * m : []
     * c : {"total":15,"pages":2,"d":[{"id":"5","typename":"鸡冠花","scientificname":"","alias":"鸡髻花、老来红、芦花鸡冠、笔鸡冠、小头鸡冠、凤尾鸡冠","genera":"","geog":"中国","geogurl":"","complexity":1,"gurposes":"","populars":"1","places":"1","presents":"1","seasons":"1","geomancys":"1","imgurl":"http://123.56.204.69:8081/ciao-api/resource/plantImages/05866dff6828e1f8.jpg","lifecirle":"","ciaotype":2,"content":"鸡冠花（学名：Celosia cristata L.），中文别名：鸡髻花、老来红、芦花鸡冠、笔鸡冠、小头鸡冠、凤尾鸡冠、大鸡公花、鸡角根、红鸡冠。一年草本植物，夏秋季开花，花多为红色，呈鸡冠状，故称鸡冠花。原产非洲，美洲热带和印度。喜阳光充足、湿热，不耐霜冻。不耐瘠薄，喜疏松肥沃和排水良好的土壤。世界各地广为栽培，普通庭园植物。另外具有很高的药用价值。","typecontent":"","reference":"","plifeCirle":20},{"id":"4","typename":"薄荷","scientificname":"","alias":"野薄荷、夜息香、银丹草","genera":"","geog":"中国","geogurl":"","complexity":1,"gurposes":"","populars":"1","places":"1","presents":"1","seasons":"1","geomancys":"1","imgurl":"http://123.56.204.69:8081/ciao-api/resource/plantImages/0fa73e8a30dd43de.jpg","lifecirle":"","ciaotype":2,"content":"薄荷，土名叫\u201c银丹草\u201d，为唇形科植物，即同属其他干燥全草。多生于山野湿地河旁，根茎横生地下，多生于2100米海拔高度，但也可在3500米海拔上生长，是一种有特种经济价值的芳香作物。\r全株青气芳香。叶对生，花小淡紫色，唇形，花后结暗紫棕色的小粒果。","typecontent":"","reference":"","plifeCirle":20},{"id":"3","typename":"百里香","scientificname":"","alias":"地椒、地花椒、山椒、山胡椒、麝香草","genera":"","geog":"中国","geogurl":"","complexity":1,"gurposes":"","populars":"1","places":"1","presents":"1","seasons":"1","geomancys":"1","imgurl":"http://123.56.204.69:8081/ciao-api/resource/plantImages/9189924b8abab9e7.jpg","lifecirle":"","ciaotype":2,"content":"在中国称为地椒、地花椒、山椒、山胡椒、麝香草等，产于西北地区。元朝的《居家必用事类全集》中，记有用百里香加入驼峰驼蹄调味。李时珍《本草纲目》记载：\u201c味微辛，土人以煮羊肉食，香美。\u201d百里香原产于南欧，被作为一种美食的香料而广泛种植。","typecontent":"","reference":"","plifeCirle":20},{"id":"2","typename":"迷迭香","scientificname":"","alias":"海洋之露，艾菊","genera":"","geog":"中国","geogurl":"","complexity":1,"gurposes":"","populars":"1","places":"1","presents":"1","seasons":"1","geomancys":"1","imgurl":"http://123.56.204.69:8081/ciao-api/resource/plantImages/d3e823c70fdc9156.jpg","lifecirle":"","ciaotype":2,"content":"迷迭香，拉丁学名（Rosmarinus officinalis）唇形科灌木。性喜温暖气候，原产欧洲地区和非洲北部地中海沿岸。远在曹魏时期就曾引种中国。现在园林中偶有应用。","typecontent":"","reference":"","plifeCirle":20},{"id":"1","typename":"罗勒","scientificname":"","alias":"九层塔 金不换","genera":"","geog":"中国","geogurl":"","complexity":1,"gurposes":"","populars":"1","places":"1","presents":"1","seasons":"1","geomancys":"1","imgurl":"http://123.56.204.69:8081/ciao-api/resource/plantImages/11ba902726ab5130.jpg","lifecirle":"","ciaotype":2,"content":"罗勒为药食两用芳香植物，味似茴香，全株小巧，叶色翠绿，花色鲜艳，芳香四溢。原生于亚洲热带区，对寒冷非常敏感，在热和干燥的环境下生长得最好。具有强大、刺激、香的气味。","typecontent":"","reference":"","plifeCirle":20}]}
     */

    private HBean h;
    private CBean c;
    private List<?> m;
    public HBean getH() {
        return h;
    }

    public void setH(HBean h) {
        this.h = h;
    }

    public CBean getC() {
        return c;
    }

    public void setC(CBean c) {
        this.c = c;
    }

    public List<?> getM() {
        return m;
    }

    public void setM(List<?> m) {
        this.m = m;
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

    public static class CBean {
        /**
         * total : 15
         * pages : 2
         * d : [{"id":"5","typename":"鸡冠花","scientificname":"","alias":"鸡髻花、老来红、芦花鸡冠、笔鸡冠、小头鸡冠、凤尾鸡冠","genera":"","geog":"中国","geogurl":"","complexity":1,"gurposes":"","populars":"1","places":"1","presents":"1","seasons":"1","geomancys":"1","imgurl":"http://123.56.204.69:8081/ciao-api/resource/plantImages/05866dff6828e1f8.jpg","lifecirle":"","ciaotype":2,"content":"鸡冠花（学名：Celosia cristata L.），中文别名：鸡髻花、老来红、芦花鸡冠、笔鸡冠、小头鸡冠、凤尾鸡冠、大鸡公花、鸡角根、红鸡冠。一年草本植物，夏秋季开花，花多为红色，呈鸡冠状，故称鸡冠花。原产非洲，美洲热带和印度。喜阳光充足、湿热，不耐霜冻。不耐瘠薄，喜疏松肥沃和排水良好的土壤。世界各地广为栽培，普通庭园植物。另外具有很高的药用价值。","typecontent":"","reference":"","plifeCirle":20},{"id":"4","typename":"薄荷","scientificname":"","alias":"野薄荷、夜息香、银丹草","genera":"","geog":"中国","geogurl":"","complexity":1,"gurposes":"","populars":"1","places":"1","presents":"1","seasons":"1","geomancys":"1","imgurl":"http://123.56.204.69:8081/ciao-api/resource/plantImages/0fa73e8a30dd43de.jpg","lifecirle":"","ciaotype":2,"content":"薄荷，土名叫\u201c银丹草\u201d，为唇形科植物，即同属其他干燥全草。多生于山野湿地河旁，根茎横生地下，多生于2100米海拔高度，但也可在3500米海拔上生长，是一种有特种经济价值的芳香作物。\r全株青气芳香。叶对生，花小淡紫色，唇形，花后结暗紫棕色的小粒果。","typecontent":"","reference":"","plifeCirle":20},{"id":"3","typename":"百里香","scientificname":"","alias":"地椒、地花椒、山椒、山胡椒、麝香草","genera":"","geog":"中国","geogurl":"","complexity":1,"gurposes":"","populars":"1","places":"1","presents":"1","seasons":"1","geomancys":"1","imgurl":"http://123.56.204.69:8081/ciao-api/resource/plantImages/9189924b8abab9e7.jpg","lifecirle":"","ciaotype":2,"content":"在中国称为地椒、地花椒、山椒、山胡椒、麝香草等，产于西北地区。元朝的《居家必用事类全集》中，记有用百里香加入驼峰驼蹄调味。李时珍《本草纲目》记载：\u201c味微辛，土人以煮羊肉食，香美。\u201d百里香原产于南欧，被作为一种美食的香料而广泛种植。","typecontent":"","reference":"","plifeCirle":20},{"id":"2","typename":"迷迭香","scientificname":"","alias":"海洋之露，艾菊","genera":"","geog":"中国","geogurl":"","complexity":1,"gurposes":"","populars":"1","places":"1","presents":"1","seasons":"1","geomancys":"1","imgurl":"http://123.56.204.69:8081/ciao-api/resource/plantImages/d3e823c70fdc9156.jpg","lifecirle":"","ciaotype":2,"content":"迷迭香，拉丁学名（Rosmarinus officinalis）唇形科灌木。性喜温暖气候，原产欧洲地区和非洲北部地中海沿岸。远在曹魏时期就曾引种中国。现在园林中偶有应用。","typecontent":"","reference":"","plifeCirle":20},{"id":"1","typename":"罗勒","scientificname":"","alias":"九层塔 金不换","genera":"","geog":"中国","geogurl":"","complexity":1,"gurposes":"","populars":"1","places":"1","presents":"1","seasons":"1","geomancys":"1","imgurl":"http://123.56.204.69:8081/ciao-api/resource/plantImages/11ba902726ab5130.jpg","lifecirle":"","ciaotype":2,"content":"罗勒为药食两用芳香植物，味似茴香，全株小巧，叶色翠绿，花色鲜艳，芳香四溢。原生于亚洲热带区，对寒冷非常敏感，在热和干燥的环境下生长得最好。具有强大、刺激、香的气味。","typecontent":"","reference":"","plifeCirle":20}]
         */

        private int total;
        private int pages;
        private List<DBean> d;

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public int getPages() {
            return pages;
        }

        public void setPages(int pages) {
            this.pages = pages;
        }

        public List<DBean> getD() {
            return d;
        }

        public void setD(List<DBean> d) {
            this.d = d;
        }

        public static class DBean {
            /**
             * id : 5
             * typename : 鸡冠花
             * scientificname :
             * alias : 鸡髻花、老来红、芦花鸡冠、笔鸡冠、小头鸡冠、凤尾鸡冠
             * genera :
             * geog : 中国
             * geogurl :
             * complexity : 1
             * gurposes :
             * populars : 1
             * places : 1
             * presents : 1
             * seasons : 1
             * geomancys : 1
             * imgurl : http://123.56.204.69:8081/ciao-api/resource/plantImages/05866dff6828e1f8.jpg
             * lifecirle :
             * ciaotype : 2
             * content : 鸡冠花（学名：Celosia cristata L.），中文别名：鸡髻花、老来红、芦花鸡冠、笔鸡冠、小头鸡冠、凤尾鸡冠、大鸡公花、鸡角根、红鸡冠。一年草本植物，夏秋季开花，花多为红色，呈鸡冠状，故称鸡冠花。原产非洲，美洲热带和印度。喜阳光充足、湿热，不耐霜冻。不耐瘠薄，喜疏松肥沃和排水良好的土壤。世界各地广为栽培，普通庭园植物。另外具有很高的药用价值。
             * typecontent :
             * reference :
             * plifeCirle : 20
             */

            private String id;
            private String typename;
            private String scientificname;
            private String alias;
            private String genera;
            private String geog;
            private String geogurl;
            private int complexity;
            private String gurposes;
            private String populars;
            private String places;
            private String presents;
            private String seasons;
            private String geomancys;
            private String imgurl;
            private String lifecirle;
            private int ciaotype;
            private String content;
            private String typecontent;
            private String reference;
            private int plifeCirle;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTypename() {
                return typename;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public String getScientificname() {
                return scientificname;
            }

            public void setScientificname(String scientificname) {
                this.scientificname = scientificname;
            }

            public String getAlias() {
                return alias;
            }

            public void setAlias(String alias) {
                this.alias = alias;
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

            public int getComplexity() {
                return complexity;
            }

            public void setComplexity(int complexity) {
                this.complexity = complexity;
            }

            public String getGurposes() {
                return gurposes;
            }

            public void setGurposes(String gurposes) {
                this.gurposes = gurposes;
            }

            public String getPopulars() {
                return populars;
            }

            public void setPopulars(String populars) {
                this.populars = populars;
            }

            public String getPlaces() {
                return places;
            }

            public void setPlaces(String places) {
                this.places = places;
            }

            public String getPresents() {
                return presents;
            }

            public void setPresents(String presents) {
                this.presents = presents;
            }

            public String getSeasons() {
                return seasons;
            }

            public void setSeasons(String seasons) {
                this.seasons = seasons;
            }

            public String getGeomancys() {
                return geomancys;
            }

            public void setGeomancys(String geomancys) {
                this.geomancys = geomancys;
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

            public int getCiaotype() {
                return ciaotype;
            }

            public void setCiaotype(int ciaotype) {
                this.ciaotype = ciaotype;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getTypecontent() {
                return typecontent;
            }

            public void setTypecontent(String typecontent) {
                this.typecontent = typecontent;
            }

            public String getReference() {
                return reference;
            }

            public void setReference(String reference) {
                this.reference = reference;
            }

            public int getPlifeCirle() {
                return plifeCirle;
            }

            public void setPlifeCirle(int plifeCirle) {
                this.plifeCirle = plifeCirle;
            }
        }
    }
}
