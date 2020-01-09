package com.highway.customer.customerModelClass.customerTrip;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerTripRequest {

@SerializedName("User_Id")
@Expose
private String userId;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

}