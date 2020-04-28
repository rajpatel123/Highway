package com.highway.ownerModule.vehileOwnerModelsClass.getAllDriver;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllDriverRequest {

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