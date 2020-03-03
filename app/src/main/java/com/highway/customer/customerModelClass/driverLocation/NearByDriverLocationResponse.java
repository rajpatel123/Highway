package com.highway.customer.customerModelClass.driverLocation;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NearByDriverLocationResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("nearByLocationData")
    @Expose
    private ArrayList<NearByDriverLocation> nearByLocationData;

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

    public ArrayList<NearByDriverLocation> getNearByLocationData() {
        return nearByLocationData;
    }

    public void setNearByLocationData(ArrayList<NearByDriverLocation> nearByLocationData) {
        this.nearByLocationData = nearByLocationData;
    }
}
