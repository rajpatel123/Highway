package com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleOwnerModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleOwnerCompletedTripRequest {

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