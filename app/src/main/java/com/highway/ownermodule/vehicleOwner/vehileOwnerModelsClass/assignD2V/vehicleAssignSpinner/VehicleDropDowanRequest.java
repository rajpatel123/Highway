package com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleDropDowanRequest {

@SerializedName("user_id")
@Expose
private String userId;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

}