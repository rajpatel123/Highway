package com.videoapp.model;

public class Notification_Model {

    private String title,days,detail;
    private int img;

    public Notification_Model(String title, String days, String detail, int img) {
        this.title = title;
        this.days = days;
        this.detail = detail;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDays() {
        return days;
    }

    public void setDays(String days) {
        this.days = days;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
