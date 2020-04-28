package com.highway.customer.customerModelClass.bookingVehicleList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingVehicleListRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("startLat")
    @Expose
    private double startLat;
    @SerializedName("startLong")
    @Expose
    private double startLong;
    @SerializedName("endLat")
    @Expose
    private double endLat;
    @SerializedName("endLong")
    @Expose
    private double endLong;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getStartLat() {
        return startLat;
    }

    public void setStartLat(double startLat) {
        this.startLat = startLat;
    }

    public double getStartLong() {
        return startLong;
    }

    public void setStartLong(double startLong) {
        this.startLong = startLong;
    }

    public double getEndLat() {
        return endLat;
    }

    public void setEndLat(double endLat) {
        this.endLat = endLat;
    }

    public double getEndLong() {
        return endLong;
    }

    public void setEndLong(double endLong) {
        this.endLong = endLong;
    }




}