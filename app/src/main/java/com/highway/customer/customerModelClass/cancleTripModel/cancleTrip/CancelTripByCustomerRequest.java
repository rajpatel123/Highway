package com.highway.customer.customerModelClass.cancleTripModel.cancleTrip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelTripByCustomerRequest {

@SerializedName("userId")
@Expose
private String userId;
@SerializedName("cancelBookId")
@Expose
private String cancelBookId;
@SerializedName("cancelReasonId")
@Expose
private String cancelReasonId;
@SerializedName("cancelReasonComment")
@Expose
private String cancelReasonComment;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getCancelBookId() {
return cancelBookId;
}

public void setCancelBookId(String cancelBookId) {
this.cancelBookId = cancelBookId;
}

public String getCancelReasonId() {
return cancelReasonId;
}

public void setCancelReasonId(String cancelReasonId) {
this.cancelReasonId = cancelReasonId;
}

public String getCancelReasonComment() {
return cancelReasonComment;
}

public void setCancelReasonComment(String cancelReasonComment) {
this.cancelReasonComment = cancelReasonComment;
}

}