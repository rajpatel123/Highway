package com.highway.ownerModule.vehileOwnerModelsClass.assignD2V.driverAssignSpinner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverDropDownRequest {

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