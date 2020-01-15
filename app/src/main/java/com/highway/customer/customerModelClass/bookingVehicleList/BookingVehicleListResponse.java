
package com.highway.customer.customerModelClass.bookingVehicleList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingVehicleListResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("vehicleData")
    @Expose
    private VehicleData vehicleData;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public VehicleData getVehicleData() {
        return vehicleData;
    }

    public void setVehicleData(VehicleData vehicleData) {
        this.vehicleData = vehicleData;
    }

}
