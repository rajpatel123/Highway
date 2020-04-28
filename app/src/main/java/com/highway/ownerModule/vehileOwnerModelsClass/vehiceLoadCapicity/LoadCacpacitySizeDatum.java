
package com.highway.ownerModule.vehileOwnerModelsClass.vehiceLoadCapicity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoadCacpacitySizeDatum {

    @SerializedName("vehicleLoadCapacityId")
    @Expose
    private String vehicleLoadCapacityId;
    @SerializedName("vehicleLoadingCapacity")
    @Expose
    private String vehicleLoadingCapacity;

    public String getVehicleLoadCapacityId() {
        return vehicleLoadCapacityId;
    }

    public void setVehicleLoadCapacityId(String vehicleLoadCapacityId) {
        this.vehicleLoadCapacityId = vehicleLoadCapacityId;
    }

    public String getVehicleLoadingCapacity() {
        return vehicleLoadingCapacity;
    }

    public void setVehicleLoadingCapacity(String vehicleLoadingCapacity) {
        this.vehicleLoadingCapacity = vehicleLoadingCapacity;
    }

}
