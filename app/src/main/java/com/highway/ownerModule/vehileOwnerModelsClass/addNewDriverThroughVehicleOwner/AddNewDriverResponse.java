package com.highway.ownerModule.vehileOwnerModelsClass.addNewDriverThroughVehicleOwner;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddNewDriverResponse {

@SerializedName("status")
@Expose
private Boolean status;
@SerializedName("message")
@Expose
private String message;
@SerializedName("id")
@Expose
private Integer id;

public Boolean getStatus() {
return status;
}

public void setStatus(Boolean status) {
this.status = status;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

}