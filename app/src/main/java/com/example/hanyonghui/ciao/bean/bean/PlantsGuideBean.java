package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * Created by hanyonghui on 2017/9/11.
 */

public class PlantsGuideBean {
    /**
     * c : {"gu":[{"circleNo":8,"content":"浸种:清水浸泡种子8到12小时","day":1,"guideurl":"","id":"35","ptypeid":"8","spray":3,"sprayName":"每月一次","title":"油葵","watering":2,"wateringName":"每周一次"},{"circleNo":8,"content":"上盆:将浸泡过的种子放在土表，覆薄土，厚度为种子2-3倍。浇透水。","day":2,"guideurl":"","id":"36","ptypeid":"8","spray":3,"sprayName":"每月一次","title":"油葵","watering":2,"wateringName":"每周一次"},{"circleNo":8,"content":"生长:生长期给予明亮散射光和充足的水分。可每日用喷壶浇水。","day":3,"guideurl":"","id":"37","ptypeid":"8","spray":3,"sprayName":"每月一次","title":"油葵","watering":2,"wateringName":"每周一次"},{"circleNo":8,"content":"收获:生长到适当高度时，平齐花盆边缘收割，尽快食用。","day":8,"guideurl":"","id":"38","ptypeid":"8","spray":3,"sprayName":"每月一次","title":"油葵","watering":2,"wateringName":"每周一次"}],"plifeCirle":"","ptypename":"油葵"}
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
         * gu : [{"circleNo":8,"content":"浸种:清水浸泡种子8到12小时","day":1,"guideurl":"","id":"35","ptypeid":"8","spray":3,"sprayName":"每月一次","title":"油葵","watering":2,"wateringName":"每周一次"},{"circleNo":8,"content":"上盆:将浸泡过的种子放在土表，覆薄土，厚度为种子2-3倍。浇透水。","day":2,"guideurl":"","id":"36","ptypeid":"8","spray":3,"sprayName":"每月一次","title":"油葵","watering":2,"wateringName":"每周一次"},{"circleNo":8,"content":"生长:生长期给予明亮散射光和充足的水分。可每日用喷壶浇水。","day":3,"guideurl":"","id":"37","ptypeid":"8","spray":3,"sprayName":"每月一次","title":"油葵","watering":2,"wateringName":"每周一次"},{"circleNo":8,"content":"收获:生长到适当高度时，平齐花盆边缘收割，尽快食用。","day":8,"guideurl":"","id":"38","ptypeid":"8","spray":3,"sprayName":"每月一次","title":"油葵","watering":2,"wateringName":"每周一次"}]
         * plifeCirle :
         * ptypename : 油葵
         */

        private String plifeCirle;
        private String ptypename;
        private List<GuBean> gu;

        public String getPlifeCirle() {
            return plifeCirle;
        }

        public void setPlifeCirle(String plifeCirle) {
            this.plifeCirle = plifeCirle;
        }

        public String getPtypename() {
            return ptypename;
        }

        public void setPtypename(String ptypename) {
            this.ptypename = ptypename;
        }

        public List<GuBean> getGu() {
            return gu;
        }

        public void setGu(List<GuBean> gu) {
            this.gu = gu;
        }

        public static class GuBean {
            /**
             * circleNo : 8
             * content : 浸种:清水浸泡种子8到12小时
             * day : 1
             * guideurl :
             * id : 35
             * ptypeid : 8
             * spray : 3
             * sprayName : 每月一次
             * title : 油葵
             * watering : 2
             * wateringName : 每周一次
             */

            private int circleNo;
            private String content;
            private int day;
            private String guideurl;
            private String id;
            private String ptypeid;
            private int spray;
            private String sprayName;
            private String title;
            private int watering;
            private String wateringName;

            public int getCircleNo() {
                return circleNo;
            }

            public void setCircleNo(int circleNo) {
                this.circleNo = circleNo;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public String getGuideurl() {
                return guideurl;
            }

            public void setGuideurl(String guideurl) {
                this.guideurl = guideurl;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getPtypeid() {
                return ptypeid;
            }

            public void setPtypeid(String ptypeid) {
                this.ptypeid = ptypeid;
            }

            public int getSpray() {
                return spray;
            }

            public void setSpray(int spray) {
                this.spray = spray;
            }

            public String getSprayName() {
                return sprayName;
            }

            public void setSprayName(String sprayName) {
                this.sprayName = sprayName;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public int getWatering() {
                return watering;
            }

            public void setWatering(int watering) {
                this.watering = watering;
            }

            public String getWateringName() {
                return wateringName;
            }

            public void setWateringName(String wateringName) {
                this.wateringName = wateringName;
            }
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
