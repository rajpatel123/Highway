
package com.highway.ownerModule.vehileOwnerModelsClass.getAllVehicle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllVehicleResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private DataVehicle data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public DataVehicle getData() {
        return data;
    }

    public void setData(DataVehicle data) {
        this.data = data;
    }

}
