package com.highway.drivermodule.driverModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingAcceptRejectResponse {


    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("driverVehicleDetails")
    @Expose
    private DriverVehicleDetails driverVehicleDetails;

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

    public DriverVehicleDetails getDriverVehicleDetails() {
        return driverVehicleDetails;
    }

    public void setDriverVehicleDetails(DriverVehicleDetails driverVehicleDetails) {
        this.driverVehicleDetails = driverVehicleDetails;
    }
}
