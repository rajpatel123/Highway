package com.highway.common.base.commonModel.customerDiverOwnerModelsClass;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllHighwayTripsRequest {

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