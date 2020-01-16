
package com.highway.customer.customerModelClass.vehicleInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("vehicle_info")
    @Expose
    private List<VehicleInfo> vehicleInfo = null;

    public List<VehicleInfo> getVehicleInfo() {
        return vehicleInfo;
    }

    public void setVehicleInfo(List<VehicleInfo> vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

}
