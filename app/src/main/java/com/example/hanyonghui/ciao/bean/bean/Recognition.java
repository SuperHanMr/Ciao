package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/30.
 * Holle Android
 */

public class Recognition {
    /**
     * log_id : 379601174
     * result : [{"name":"蒜苗","score":0.32376033067703},{"name":"黄豆芽","score":0.061909299343824},{"name":"金针菇","score":0.046453788876534},{"name":"青葱","score":0.045085612684488},{"name":"鱼腥草","score":0.043358288705349}]
     */

    private int logid;
    private List<ResultBean> result;

    public int getLogid() {
        return logid;
    }

    public void setLogid(int logid) {
        this.logid = logid;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * name : 蒜苗
         * score : 0.32376033067703
         */

        private String name;
        private double score;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getScore() {
            return score;
        }

        public void setScore(double score) {
            this.score = score;
        }
    }
}
