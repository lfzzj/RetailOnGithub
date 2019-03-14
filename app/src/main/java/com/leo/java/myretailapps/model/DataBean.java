package com.leo.java.myretailapps.model;

import java.util.List;

public class DataBean {

    private List<HeadBean> head;

    public List<HeadBean> getHead() {
        return head;
    }

    public void setHead(List<HeadBean> head) {
        this.head = head;
    }

    public static class HeadBean {
        /**
         * img : http://wghy.wghy168.com/images/790x296-2.jpg
         * Url : http://www.jiandaopay.com/home/home?AppOnly=131889788097721838K155
         */

        private String img;
        private String Url;

        public HeadBean() {
        }

        public HeadBean(String img) {
            this.img = img;
        }

        public HeadBean(String img, String url) {
            this.img = img;
            Url = url;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getUrl() {
            return Url;
        }

        public void setUrl(String Url) {
            this.Url = Url;
        }
    }
}
