
package com.highway.customer.customerModelClass.bookingVehicleList;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VehicleData {

    @SerializedName("vehicle_list")
    @Expose
    private List<VehicleList> vehicleList = null;

    public List<VehicleList> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<VehicleList> vehicleList) {
        this.vehicleList = vehicleList;
    }

}
