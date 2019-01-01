package com.videoapp.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VideoListResponse {

@SerializedName("status")
@Expose
private String status;
@SerializedName("message")
@Expose
private String message;
@SerializedName("video_list")
@Expose
private List<VideoList> videoList = null;

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public List<VideoList> getVideoList() {
return videoList;
}

public void setVideoList(List<VideoList> videoList) {
this.videoList = videoList;
}

}