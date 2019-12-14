
package com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.VehicleDropDown_Spinners;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Data {

    @SerializedName("vehicle_data")
    @Expose
    private List<VehicleDatum> vehicleData = null;

    public List<VehicleDatum> getVehicleData() {
        return vehicleData;
    }

    public void setVehicleData(List<VehicleDatum> vehicleData) {
        this.vehicleData = vehicleData;
    }

}
