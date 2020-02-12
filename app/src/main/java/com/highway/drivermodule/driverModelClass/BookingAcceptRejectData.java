package com.highway.drivermodule.driverModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingAcceptRejectData {

    @SerializedName("userId")
    @Expose
    private String userId;
    @SerializedName("tripId")
    @Expose
    private String tripId;
    @SerializedName("acceptReject")
    @Expose
    private String acceptReject;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getAcceptReject() {
        return acceptReject;
    }

    public void setAcceptReject(String acceptReject) {
        this.acceptReject = acceptReject;
    }
}
