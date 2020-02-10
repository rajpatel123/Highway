
package com.highway.customer.customerModelClass.vehicleInfo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingVehicleInfoResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("vehicle_type_info")
    @Expose
    private List<VehicleTypeInfo> vehicleTypeInfo = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<VehicleTypeInfo> getVehicleTypeInfo() {
        return vehicleTypeInfo;
    }

    public void setVehicleTypeInfo(List<VehicleTypeInfo> vehicleTypeInfo) {
        this.vehicleTypeInfo = vehicleTypeInfo;
    }

}
