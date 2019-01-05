package com.videoapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoList {

//    @SerializedName("list_id")
//    @Expose
//    private String listId;
//    @SerializedName("user_name")
//    @Expose
//    private String userName;
    @SerializedName("video")
    @Expose
    private String video;


    private String thumbs;
    private String strDesc;
    private String profilePics;


    public VideoList() {
    }

    public VideoList(String thumbs, String strDesc, String profilePics, String video) {
        this.thumbs = thumbs;
        this.strDesc = strDesc;
        this.profilePics = profilePics;
        this.video = video;
    }



    public String getThumbs() {
        return thumbs;
    }

    public void setThumbs(String thumbs) {
        this.thumbs = thumbs;
    }

    public String getStrDesc() {
        return strDesc;
    }

    public void setStrDesc(String strDesc) {
        this.strDesc = strDesc;
    }

    public String getProfilePics() {
        return profilePics;
    }

    public void setProfilePics(String profilePics) {
        this.profilePics = profilePics;
    }

    //    public String getListId() {
//        return listId;
//    }
//
//    public void setListId(String listId) {
//        this.listId = listId;
//    }
//
//    public String getUserName() {
//        return userName;
//    }
//
//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
//
    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

}
