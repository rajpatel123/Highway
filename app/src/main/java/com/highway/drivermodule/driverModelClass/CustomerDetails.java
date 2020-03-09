package com.highway.drivermodule.driverModelClass;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerDetails {

    @SerializedName("customerId")
    @Expose
    private String customerId;
    @SerializedName("customerName")
    @Expose
    private String customerName;
    @SerializedName("customerMobile")
    @Expose
    private String customerMobile;
    @SerializedName("tripAcceptStatus")
    @Expose
    private String tripAcceptStatus;
    @SerializedName("startTripLat")
    @Expose
    private String startTripLat;
    @SerializedName("startTripLong")
    @Expose
    private String startTripLong;
    @SerializedName("endTripLat")
    @Expose
    private String endTripLat;
    @SerializedName("endTripLong")
    @Expose
    private String endTripLong;
    @SerializedName("bookingTripId")
    @Expose
    private String bookingTripId;
    @SerializedName("tripId")
    @Expose
    private String tripId;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerMobile() {
        return customerMobile;
    }

    public void setCustomerMobile(String customerMobile) {
        this.customerMobile = customerMobile;
    }

    public String getTripAcceptStatus() {
        return tripAcceptStatus;
    }

    public void setTripAcceptStatus(String tripAcceptStatus) {
        this.tripAcceptStatus = tripAcceptStatus;
    }

    public String getStartTripLat() {
        return startTripLat;
    }

    public void setStartTripLat(String startTripLat) {
        this.startTripLat = startTripLat;
    }

    public String getStartTripLong() {
        return startTripLong;
    }

    public void setStartTripLong(String startTripLong) {
        this.startTripLong = startTripLong;
    }

    public String getEndTripLat() {
        return endTripLat;
    }

    public void setEndTripLat(String endTripLat) {
        this.endTripLat = endTripLat;
    }

    public String getEndTripLong() {
        return endTripLong;
    }

    public void setEndTripLong(String endTripLong) {
        this.endTripLong = endTripLong;
    }

    public String getBookingTripId() {
        return bookingTripId;
    }

    public void setBookingTripId(String bookingTripId) {
        this.bookingTripId = bookingTripId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

}