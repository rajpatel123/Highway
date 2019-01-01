package com.videoapp.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoList {

@SerializedName("list_id")
@Expose
private String listId;
@SerializedName("user_name")
@Expose
private String userName;
@SerializedName("video")
@Expose
private String video;

public String getListId() {
return listId;
}

public void setListId(String listId) {
this.listId = listId;
}

public String getUserName() {
return userName;
}

public void setUserName(String userName) {
this.userName = userName;
}

public String getVideo() {
return video;
}

public void setVideo(String video) {
this.video = video;
}

}
