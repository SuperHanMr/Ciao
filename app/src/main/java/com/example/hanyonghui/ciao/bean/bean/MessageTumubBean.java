package com.example.hanyonghui.ciao.bean.bean;

import java.util.List;

/**
 * 程序员：韩永辉
 * 创建日期：on 2017/9/26.
 * Holle Android
 */

public class MessageTumubBean {

    /**
     * h : {"s":1}
     * m : []
     * c : {"total":4,"pages":1,"d":[{"id":"32c84982799741b09bbbcae8c360aa87","twitterid":"58ff333f0c454712abb67a933a788aca","twitter":{"id":"58ff333f0c454712abb67a933a788aca","userid":"","user":"","plantid":"","createtime":"","body":"666","praises":0,"comments":0,"ispraise":"","label":"","imgs":"","commonts":""},"userid":"915c70af770848d78985bb8393361324","user":{"isnew":false,"token":"","unionId":"","openid":"","role":0,"plants":0,"uid":"915c70af770848d78985bb8393361324","nn":"💔韩永辉","pn":"","purl":"http://123.56.204.69:8081/ciao/resource/f8b2389077b74defbbbf6845853e388b01.jpg"},"body":"","iscomment":2,"pareteid":"","createtime":"2017-09-25 16:40:06"},{"id":"64587047be9a4cf4a7def842c8db7374","twitterid":"e6af0b358cf942cfb210fe59867cb8e3","twitter":{"id":"e6af0b358cf942cfb210fe59867cb8e3","userid":"","user":"","plantid":"","createtime":"","body":"88888","praises":0,"comments":0,"ispraise":"","label":"","imgs":"","commonts":""},"userid":"915c70af770848d78985bb8393361324","user":{"isnew":false,"token":"","unionId":"","openid":"","role":0,"plants":0,"uid":"915c70af770848d78985bb8393361324","nn":"💔韩永辉","pn":"","purl":"http://123.56.204.69:8081/ciao/resource/f8b2389077b74defbbbf6845853e388b01.jpg"},"body":"","iscomment":2,"pareteid":"","createtime":"2017-09-25 16:36:43"},{"id":"4871a7a3898a483ca4dfac28e5849046","twitterid":"0cf07130936843118e62c60d44fffa7d","twitter":{"id":"0cf07130936843118e62c60d44fffa7d","userid":"","user":"","plantid":"","createtime":"","body":"555","praises":0,"comments":0,"ispraise":"","label":"","imgs":"","commonts":""},"userid":"915c70af770848d78985bb8393361324","user":{"isnew":false,"token":"","unionId":"","openid":"","role":0,"plants":0,"uid":"915c70af770848d78985bb8393361324","nn":"💔韩永辉","pn":"","purl":"http://123.56.204.69:8081/ciao/resource/f8b2389077b74defbbbf6845853e388b01.jpg"},"body":"","iscomment":2,"pareteid":"","createtime":"2017-09-25 14:44:17"},{"id":"b84cf0c2cc69437f953097cc425b4fae","twitterid":"23198faf034945d898b18ed0638c440d","twitter":{"id":"23198faf034945d898b18ed0638c440d","userid":"","user":"","plantid":"","createtime":"","body":"555555","praises":0,"comments":0,"ispraise":"","label":"","imgs":"","commonts":""},"userid":"915c70af770848d78985bb8393361324","user":{"isnew":false,"token":"","unionId":"","openid":"","role":0,"plants":0,"uid":"915c70af770848d78985bb8393361324","nn":"💔韩永辉","pn":"","purl":"http://123.56.204.69:8081/ciao/resource/f8b2389077b74defbbbf6845853e388b01.jpg"},"body":"","iscomment":2,"pareteid":"","createtime":"2017-09-25 13:54:01"}]}
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
         * total : 4
         * pages : 1
         * d : [{"id":"32c84982799741b09bbbcae8c360aa87","twitterid":"58ff333f0c454712abb67a933a788aca","twitter":{"id":"58ff333f0c454712abb67a933a788aca","userid":"","user":"","plantid":"","createtime":"","body":"666","praises":0,"comments":0,"ispraise":"","label":"","imgs":"","commonts":""},"userid":"915c70af770848d78985bb8393361324","user":{"isnew":false,"token":"","unionId":"","openid":"","role":0,"plants":0,"uid":"915c70af770848d78985bb8393361324","nn":"💔韩永辉","pn":"","purl":"http://123.56.204.69:8081/ciao/resource/f8b2389077b74defbbbf6845853e388b01.jpg"},"body":"","iscomment":2,"pareteid":"","createtime":"2017-09-25 16:40:06"},{"id":"64587047be9a4cf4a7def842c8db7374","twitterid":"e6af0b358cf942cfb210fe59867cb8e3","twitter":{"id":"e6af0b358cf942cfb210fe59867cb8e3","userid":"","user":"","plantid":"","createtime":"","body":"88888","praises":0,"comments":0,"ispraise":"","label":"","imgs":"","commonts":""},"userid":"915c70af770848d78985bb8393361324","user":{"isnew":false,"token":"","unionId":"","openid":"","role":0,"plants":0,"uid":"915c70af770848d78985bb8393361324","nn":"💔韩永辉","pn":"","purl":"http://123.56.204.69:8081/ciao/resource/f8b2389077b74defbbbf6845853e388b01.jpg"},"body":"","iscomment":2,"pareteid":"","createtime":"2017-09-25 16:36:43"},{"id":"4871a7a3898a483ca4dfac28e5849046","twitterid":"0cf07130936843118e62c60d44fffa7d","twitter":{"id":"0cf07130936843118e62c60d44fffa7d","userid":"","user":"","plantid":"","createtime":"","body":"555","praises":0,"comments":0,"ispraise":"","label":"","imgs":"","commonts":""},"userid":"915c70af770848d78985bb8393361324","user":{"isnew":false,"token":"","unionId":"","openid":"","role":0,"plants":0,"uid":"915c70af770848d78985bb8393361324","nn":"💔韩永辉","pn":"","purl":"http://123.56.204.69:8081/ciao/resource/f8b2389077b74defbbbf6845853e388b01.jpg"},"body":"","iscomment":2,"pareteid":"","createtime":"2017-09-25 14:44:17"},{"id":"b84cf0c2cc69437f953097cc425b4fae","twitterid":"23198faf034945d898b18ed0638c440d","twitter":{"id":"23198faf034945d898b18ed0638c440d","userid":"","user":"","plantid":"","createtime":"","body":"555555","praises":0,"comments":0,"ispraise":"","label":"","imgs":"","commonts":""},"userid":"915c70af770848d78985bb8393361324","user":{"isnew":false,"token":"","unionId":"","openid":"","role":0,"plants":0,"uid":"915c70af770848d78985bb8393361324","nn":"💔韩永辉","pn":"","purl":"http://123.56.204.69:8081/ciao/resource/f8b2389077b74defbbbf6845853e388b01.jpg"},"body":"","iscomment":2,"pareteid":"","createtime":"2017-09-25 13:54:01"}]
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
             * id : 32c84982799741b09bbbcae8c360aa87
             * twitterid : 58ff333f0c454712abb67a933a788aca
             * twitter : {"id":"58ff333f0c454712abb67a933a788aca","userid":"","user":"","plantid":"","createtime":"","body":"666","praises":0,"comments":0,"ispraise":"","label":"","imgs":"","commonts":""}
             * userid : 915c70af770848d78985bb8393361324
             * user : {"isnew":false,"token":"","unionId":"","openid":"","role":0,"plants":0,"uid":"915c70af770848d78985bb8393361324","nn":"💔韩永辉","pn":"","purl":"http://123.56.204.69:8081/ciao/resource/f8b2389077b74defbbbf6845853e388b01.jpg"}
             * body :
             * iscomment : 2
             * pareteid :
             * createtime : 2017-09-25 16:40:06
             */

            private String id;
            private String twitterid;
            private TwitterBean twitter;
            private String userid;
            private UserBean user;
            private String body;
            private int iscomment;
            private String pareteid;
            private String createtime;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTwitterid() {
                return twitterid;
            }

            public void setTwitterid(String twitterid) {
                this.twitterid = twitterid;
            }

            public TwitterBean getTwitter() {
                return twitter;
            }

            public void setTwitter(TwitterBean twitter) {
                this.twitter = twitter;
            }

            public String getUserid() {
                return userid;
            }

            public void setUserid(String userid) {
                this.userid = userid;
            }

            public UserBean getUser() {
                return user;
            }

            public void setUser(UserBean user) {
                this.user = user;
            }

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
            }

            public int getIscomment() {
                return iscomment;
            }

            public void setIscomment(int iscomment) {
                this.iscomment = iscomment;
            }

            public String getPareteid() {
                return pareteid;
            }

            public void setPareteid(String pareteid) {
                this.pareteid = pareteid;
            }

            public String getCreatetime() {
                return createtime;
            }

            public void setCreatetime(String createtime) {
                this.createtime = createtime;
            }

            public static class TwitterBean {
                /**
                 * id : 58ff333f0c454712abb67a933a788aca
                 * userid :
                 * user :
                 * plantid :
                 * createtime :
                 * body : 666
                 * praises : 0
                 * comments : 0
                 * ispraise :
                 * label :
                 * imgs :
                 * commonts :
                 */

                private String id;
                private String userid;
                private String user;
                private String plantid;
                private String createtime;
                private String body;
                private int praises;
                private int comments;
                private String ispraise;
                private String label;
                private String imgs;
                private String commonts;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getUserid() {
                    return userid;
                }

                public void setUserid(String userid) {
                    this.userid = userid;
                }

                public String getUser() {
                    return user;
                }

                public void setUser(String user) {
                    this.user = user;
                }

                public String getPlantid() {
                    return plantid;
                }

                public void setPlantid(String plantid) {
                    this.plantid = plantid;
                }

                public String getCreatetime() {
                    return createtime;
                }

                public void setCreatetime(String createtime) {
                    this.createtime = createtime;
                }

                public String getBody() {
                    return body;
                }

                public void setBody(String body) {
                    this.body = body;
                }

                public int getPraises() {
                    return praises;
                }

                public void setPraises(int praises) {
                    this.praises = praises;
                }

                public int getComments() {
                    return comments;
                }

                public void setComments(int comments) {
                    this.comments = comments;
                }

                public String getIspraise() {
                    return ispraise;
                }

                public void setIspraise(String ispraise) {
                    this.ispraise = ispraise;
                }

                public String getLabel() {
                    return label;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public String getImgs() {
                    return imgs;
                }

                public void setImgs(String imgs) {
                    this.imgs = imgs;
                }

                public String getCommonts() {
                    return commonts;
                }

                public void setCommonts(String commonts) {
                    this.commonts = commonts;
                }
            }

            public static class UserBean {
                /**
                 * isnew : false
                 * token :
                 * unionId :
                 * openid :
                 * role : 0
                 * plants : 0
                 * uid : 915c70af770848d78985bb8393361324
                 * nn : 💔韩永辉
                 * pn :
                 * purl : http://123.56.204.69:8081/ciao/resource/f8b2389077b74defbbbf6845853e388b01.jpg
                 */

                private boolean isnew;
                private String token;
                private String unionId;
                private String openid;
                private int role;
                private int plants;
                private String uid;
                private String nn;
                private String pn;
                private String purl;

                public boolean isIsnew() {
                    return isnew;
                }

                public void setIsnew(boolean isnew) {
                    this.isnew = isnew;
                }

                public String getToken() {
                    return token;
                }

                public void setToken(String token) {
                    this.token = token;
                }

                public String getUnionId() {
                    return unionId;
                }

                public void setUnionId(String unionId) {
                    this.unionId = unionId;
                }

                public String getOpenid() {
                    return openid;
                }

                public void setOpenid(String openid) {
                    this.openid = openid;
                }

                public int getRole() {
                    return role;
                }

                public void setRole(int role) {
                    this.role = role;
                }

                public int getPlants() {
                    return plants;
                }

                public void setPlants(int plants) {
                    this.plants = plants;
                }

                public String getUid() {
                    return uid;
                }

                public void setUid(String uid) {
                    this.uid = uid;
                }

                public String getNn() {
                    return nn;
                }

                public void setNn(String nn) {
                    this.nn = nn;
                }

                public String getPn() {
                    return pn;
                }

                public void setPn(String pn) {
                    this.pn = pn;
                }

                public String getPurl() {
                    return purl;
                }

                public void setPurl(String purl) {
                    this.purl = purl;
                }
            }
        }
    }
}
