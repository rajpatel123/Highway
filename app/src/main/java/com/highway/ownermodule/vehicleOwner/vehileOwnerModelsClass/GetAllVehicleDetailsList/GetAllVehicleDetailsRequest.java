package com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllVehicleDetailsList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllVehicleDetailsRequest {

@SerializedName("ownerId")
@Expose
private String ownerId;

public String getOwnerId() {
return ownerId;
}

public void setOwnerId(String ownerId) {
this.ownerId = ownerId;
}

}