package com.leo.java.myretailapps.model;

import java.io.Serializable;

public class UserEntity implements Serializable{
    private String id;//id
    private String parent_id;//推荐人id
    private String icon_url;//头像
    private String nick;//昵称
    private String name;//用户名
    private String password;//密码
    private String phone;//电话
    private String zfb;//支付宝
    private String wchart;//微信
    private String adress;//地址

    public UserEntity() {
    }

    public UserEntity(String id, String parent_id, String icon_url, String nick, String name, String password, String phone, String zfb, String wchart, String adress) {
        this.id = id;
        this.parent_id = parent_id;
        this.icon_url = icon_url;
        this.nick = nick;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.zfb = zfb;
        this.wchart = wchart;
        this.adress = adress;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParent_id() {
        return parent_id;
    }

    public void setParent_id(String parent_id) {
        this.parent_id = parent_id;
    }

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getZfb() {
        return zfb;
    }

    public void setZfb(String zfb) {
        this.zfb = zfb;
    }

    public String getWchart() {
        return wchart;
    }

    public void setWchart(String wchart) {
        this.wchart = wchart;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}
