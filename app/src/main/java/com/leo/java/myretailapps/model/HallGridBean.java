package com.leo.java.myretailapps.model;

public class HallGridBean {
    private int img;
    private String text;

    public HallGridBean() {
    }

    public HallGridBean(int img, String text) {
        this.img = img;
        this.text = text;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
