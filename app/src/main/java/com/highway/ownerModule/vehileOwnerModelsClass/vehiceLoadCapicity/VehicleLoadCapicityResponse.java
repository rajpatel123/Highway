
package com.highway.ownerModule.vehileOwnerModelsClass.vehiceLoadCapicity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleLoadCapicityResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("loadCapicityData")
    @Expose
    private LoadCapicityData loadCapicityData;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LoadCapicityData getLoadCapicityData() {
        return loadCapicityData;
    }

    public void setLoadCapicityData(LoadCapicityData loadCapicityData) {
        this.loadCapicityData = loadCapicityData;
    }

}
