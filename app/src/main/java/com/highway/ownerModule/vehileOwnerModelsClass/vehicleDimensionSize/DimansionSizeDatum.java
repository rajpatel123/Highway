
package com.highway.ownerModule.vehileOwnerModelsClass.vehicleDimensionSize;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DimansionSizeDatum {

    @SerializedName("vehicleDimensionSizeID")
    @Expose
    private String vehicleDimensionSizeID;
    @SerializedName("vehicleDimensionSize")
    @Expose
    private String vehicleDimensionSize;

    public String getVehicleDimensionSizeID() {
        return vehicleDimensionSizeID;
    }

    public void setVehicleDimensionSizeID(String vehicleDimensionSizeID) {
        this.vehicleDimensionSizeID = vehicleDimensionSizeID;
    }

    public String getVehicleDimensionSize() {
        return vehicleDimensionSize;
    }

    public void setVehicleDimensionSize(String vehicleDimensionSize) {
        this.vehicleDimensionSize = vehicleDimensionSize;
    }

}
