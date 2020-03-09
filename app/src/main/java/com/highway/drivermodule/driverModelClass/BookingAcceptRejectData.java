package com.highway.drivermodule.driverModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingAcceptRejectData {

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    @SerializedName("driverId")
    @Expose
    private String driverId;
    @SerializedName("tripId")
    @Expose
    private String tripId;
    @SerializedName("TRIP_STATS")
    @Expose
    private String status;


    @SerializedName("updatedAt")
    @Expose
    private String updatedAt;



    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }


}
