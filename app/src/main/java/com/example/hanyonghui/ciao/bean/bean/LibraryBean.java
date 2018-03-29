package com.example.hanyonghui.ciao.bean.bean;

import android.os.Parcelable;

import java.io.Serializable;
import java.util.List;

/**
 * Created by hanyonghui on 2017/7/22.
 */

public class LibraryBean implements Serializable {

    /**
     * c : [{"content":"菜","id":"1","modeltag":2,"name":"通俗大类","typename":"populars","url":"","value":"1"},{"content":"花","id":"2","modeltag":2,"name":"通俗大类","typename":"populars","url":"","value":"2"},{"content":"多肉","id":"3","modeltag":2,"name":"通俗大类","typename":"populars","url":"","value":"3"},{"content":"树木","id":"4","modeltag":2,"name":"通俗大类","typename":"populars","url":"","value":"4"},{"content":"水生","id":"5","modeltag":2,"name":"通俗大类","typename":"populars","url":"","value":"5"},{"content":"藤蔓","id":"6","modeltag":2,"name":"通俗大类","typename":"populars","url":"","value":"6"},{"content":"办公室","id":"7","modeltag":2,"name":"摆放位置","typename":"places","url":"","value":"1"},{"content":"阳台","id":"8","modeltag":2,"name":"摆放位置","typename":"places","url":"","value":"2"},{"content":"客厅","id":"9","modeltag":2,"name":"摆放位置","typename":"places","url":"","value":"3"},{"content":"厨房","id":"10","modeltag":2,"name":"摆放位置","typename":"places","url":"","value":"4"},{"content":"卧室","id":"12","modeltag":2,"name":"摆放位置","typename":"places","url":"","value":"6"},{"content":"庭院","id":"13","modeltag":2,"name":"摆放位置","typename":"places","url":"","value":"7"},{"content":"书房","id":"14","modeltag":2,"name":"摆放位置","typename":"places","url":"","value":"8"},{"content":"观花","id":"15","modeltag":2,"name":"功能用途","typename":"gurposes","url":"","value":"1"},{"content":"观果","id":"16","modeltag":2,"name":"功能用途","typename":"gurposes","url":"","value":"2"},{"content":"观叶","id":"17","modeltag":2,"name":"功能用途","typename":"gurposes","url":"","value":"3"},{"content":"食用","id":"18","modeltag":2,"name":"功能用途","typename":"gurposes","url":"","value":"4"},{"content":"药用","id":"19","modeltag":2,"name":"功能用途","typename":"gurposes","url":"","value":"5"},{"content":"净化空气","id":"20","modeltag":2,"name":"功能用途","typename":"gurposes","url":"","value":"6"},{"content":"除甲醛","id":"21","modeltag":2,"name":"功能用途","typename":"gurposes","url":"","value":"7"},{"content":"拜访亲友","id":"22","modeltag":2,"name":"礼品场合","typename":"presents","url":"","value":"1"},{"content":"新居乔迁","id":"23","modeltag":2,"name":"礼品场合","typename":"presents","url":"","value":"2"},{"content":"商务办公","id":"24","modeltag":2,"name":"礼品场合","typename":"presents","url":"","value":"3"},{"content":"柔情蜜意","id":"25","modeltag":2,"name":"礼品场合","typename":"presents","url":"","value":"4"},{"content":"春","id":"26","modeltag":2,"name":"季节","typename":"seasons","url":"","value":"1"},{"content":"夏","id":"27","modeltag":2,"name":"季节","typename":"seasons","url":"","value":"2"},{"content":"秋","id":"28","modeltag":2,"name":"季节","typename":"seasons","url":"","value":"3"},{"content":"冬","id":"29","modeltag":2,"name":"季节","typename":"seasons","url":"","value":"4"},{"content":"吉祥喜庆","id":"30","modeltag":2,"name":"风水","typename":"geomancys","url":"","value":"1"},{"content":"富贵生财","id":"31","modeltag":2,"name":"风水","typename":"geomancys","url":"","value":"2"},{"content":"平安长寿","id":"32","modeltag":2,"name":"风水","typename":"geomancys","url":"","value":"3"},{"content":"人丁兴旺","id":"33","modeltag":2,"name":"风水","typename":"geomancys","url":"","value":"4"},{"content":"护宅避院","id":"34","modeltag":2,"name":"风水","typename":"geomancys","url":"","value":"5"},{"content":"初级","id":"35","modeltag":2,"name":"难易程度","typename":"complexity","url":"","value":"1"},{"content":"中级","id":"36","modeltag":2,"name":"难易程度","typename":"complexity","url":"","value":"2"},{"content":"高级","id":"37","modeltag":2,"name":"难易程度","typename":"complexity","url":"","value":"3"}]
     * h : {"s":1}
     * m : []
     */

    private HBean h;
    private List<CBean> c;
    private List<?> m;

    public HBean getH() {
        return h;
    }

    public void setH(HBean h) {
        this.h = h;
    }

    public List<CBean> getC() {
        return c;
    }

    public void setC(List<CBean> c) {
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
         * content : 菜
         * id : 1
         * modeltag : 2
         * name : 通俗大类
         * typename : populars
         * url :
         * value : 1
         */
        private String content;
        private String id;
        private int modeltag;
        private String name;
        private String typename;
        private String url;
        private String value;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getModeltag() {
            return modeltag;
        }

        public void setModeltag(int modeltag) {
            this.modeltag = modeltag;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTypename() {
            return typename;
        }

        public void setTypename(String typename) {
            this.typename = typename;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
}
