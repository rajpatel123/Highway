
package com.highway.drivermodule.driverModelClass.driverInvoice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverInvoice {

    @SerializedName("bookingId")
    @Expose
    private String bookingId;
    @SerializedName("bookingTripCode")
    @Expose
    private String bookingTripCode;
    @SerializedName("startDate")
    @Expose
    private String startDate;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("totDistance")
    @Expose
    private String totDistance;

    @SerializedName("startTime")
    @Expose
    private Object startTime;
    @SerializedName("endTime")
    @Expose
    private Object endTime;

    @SerializedName("travelTime")
    @Expose
    private String travelTime;
    @SerializedName("basedFarefixed")
    @Expose
    private String basedFarefixed;
    @SerializedName("distancePrice")
    @Expose
    private String distancePrice;
    @SerializedName("peekHourCharges")
    @Expose
    private String peekHourCharges;
    @SerializedName("nightFare")
    @Expose
    private String nightFare;
    @SerializedName("tax")
    @Expose
    private String tax;
    @SerializedName("totalAmount")
    @Expose
    private String totalAmount;
    @SerializedName("walletDetection")
    @Expose
    private String walletDetection;
    @SerializedName("discount")
    @Expose
    private String discount;
    @SerializedName("paymentMode")
    @Expose
    private String paymentMode;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getBookingTripCode() {
        return bookingTripCode;
    }

    public void setBookingTripCode(String bookingTripCode) {
        this.bookingTripCode = bookingTripCode;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Object getStartTime() { return startTime; }

    public void setStartTime(Object startTime) { this.startTime = startTime; }

    public Object getEndTime() { return endTime; }

    public void setEndTime(Object endTime) { this.endTime = endTime; }

    public String getTotDistance() {
        return totDistance;
    }

    public void setTotDistance(String totDistance) {
        this.totDistance = totDistance;
    }

    public String getTravelTime() {
        return travelTime;
    }

    public void setTravelTime(String travelTime) {
        this.travelTime = travelTime;
    }

    public String getBasedFarefixed() {
        return basedFarefixed;
    }

    public void setBasedFarefixed(String basedFarefixed) {
        this.basedFarefixed = basedFarefixed;
    }

    public String getDistancePrice() {
        return distancePrice;
    }

    public void setDistancePrice(String distancePrice) {
        this.distancePrice = distancePrice;
    }

    public String getPeekHourCharges() {
        return peekHourCharges;
    }

    public void setPeekHourCharges(String peekHourCharges) {
        this.peekHourCharges = peekHourCharges;
    }

    public String getNightFare() {
        return nightFare;
    }

    public void setNightFare(String nightFare) {
        this.nightFare = nightFare;
    }

    public String getTax() {
        return tax;
    }

    public void setTax(String tax) {
        this.tax = tax;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getWalletDetection() {
        return walletDetection;
    }

    public void setWalletDetection(String walletDetection) {
        this.walletDetection = walletDetection;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

}
