package com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userRating;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpdateTripRatingByUserReq {

@SerializedName("userId")
@Expose
private String userId;
@SerializedName("tripId")
@Expose
private String tripId;
@SerializedName("ratingComment")
@Expose
private String ratingComment;
@SerializedName("ratingStatus")
@Expose
private String ratingStatus;
@SerializedName("ratingRate")
@Expose
private String ratingRate;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getTripId() {
return tripId;
}

public void setTripId(String tripId) {
this.tripId = tripId;
}

public String getRatingComment() {
return ratingComment;
}

public void setRatingComment(String ratingComment) {
this.ratingComment = ratingComment;
}

public String getRatingStatus() {
return ratingStatus;
}

public void setRatingStatus(String ratingStatus) {
this.ratingStatus = ratingStatus;
}

public String getRatingRate() {
return ratingRate;
}

public void setRatingRate(String ratingRate) {
this.ratingRate = ratingRate;
}

}