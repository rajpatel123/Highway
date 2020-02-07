package com.highway.customer.customerModelClass.cancleTripModel.cancleReason;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CancelTripReasonRequest {

@SerializedName("userId")
@Expose
private String userId;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

}