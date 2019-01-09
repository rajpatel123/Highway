package com.videoapp.model;

public class Itemlistobject {
    private String name;
    private String desc;
    private String photo;

    public Itemlistobject(String name) {
        this.name = name;
//        this.desc = desc;
//        this.photo = photo;
    }

    public Itemlistobject() {
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
//    public String getDesc() {
//        return desc;
//    }
//    public void setDesc(String desc) {
//        this.desc = desc;
//    }
//
//    public String getPhoto() {
//        return photo;
//    }
//    public void setPhoto(String photo) {
//        this.photo = photo;
//    }

}
