package com.highway.drivermodule.drivermodels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TripStatus {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("Name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("mobile")
    @Expose
    private String mobile;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("emergency_contact1")
    @Expose
    private Object emergencyContact1;
    @SerializedName("emergency_contact2")
    @Expose
    private Object emergencyContact2;
    @SerializedName("bookingTripId")
    @Expose
    private String bookingTripId;
    @SerializedName("bookingTripCode")
    @Expose
    private String bookingTripCode;


    @SerializedName("bookingTripStatus")
    @Expose
    private String bookingTripStatus;

    @SerializedName("sourceLong")
    @Expose
    private String sourceLong;

    @SerializedName("dropLat")
    @Expose
    private String dropLat;

    @SerializedName("dropLong")
    @Expose
    private String dropLong;


    @SerializedName("sourceAddress")
    @Expose
    private String sourceAddress;

    @SerializedName("destinationAddress")
    @Expose
    private String destinationAddress;


    @SerializedName("sourceLat")
    @Expose
    private String sourceLat;

    @SerializedName("otp")
    @Expose
    private String otp;


    @SerializedName("vehicleType")
    @Expose
    private String vehicleType;
    @SerializedName("vehicleNumber")
    @Expose
    private String vehicleNumber;

    public String getVehicleType() { return vehicleType; }

    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getVehicleNumber() { return vehicleNumber; }

    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }


    public String getOtp() { return otp; }

    public void setOtp(String otp) { this.otp = otp; }

    public String getSourceLat() {
        return sourceLat;
    }

    public void setSourceLat(String sourceLat) {
        this.sourceLat = sourceLat;
    }

    public String getSourceLong() {
        return sourceLong;
    }

    public void setSourceLong(String sourceLong) {
        this.sourceLong = sourceLong;
    }

    public String getDropLat() {
        return dropLat;
    }

    public void setDropLat(String dropLat) {
        this.dropLat = dropLat;
    }

    public String getDropLong() {
        return dropLong;
    }

    public void setDropLong(String dropLong) {
        this.dropLong = dropLong;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }


    public String getCurrentTripStatus() {
        return currentTripStatus;
    }

    public void setCurrentTripStatus(String currentTripStatus) {
        this.currentTripStatus = currentTripStatus;
    }

    @SerializedName("currentTripStatus")
    @Expose
    private String currentTripStatus;


    public String getRatingStatus() {
        return ratingStatus;
    }

    public void setRatingStatus(String ratingStatus) {
        this.ratingStatus = ratingStatus;
    }

    @SerializedName("ratingStatus")
    @Expose
    private String ratingStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getEmergencyContact1() {
        return emergencyContact1;
    }

    public void setEmergencyContact1(Object emergencyContact1) {
        this.emergencyContact1 = emergencyContact1;
    }

    public Object getEmergencyContact2() {
        return emergencyContact2;
    }

    public void setEmergencyContact2(Object emergencyContact2) {
        this.emergencyContact2 = emergencyContact2;
    }

    public String getBookingTripId() {
        return bookingTripId;
    }

    public void setBookingTripId(String bookingTripId) {
        this.bookingTripId = bookingTripId;
    }

    public String getBookingTripCode() {
        return bookingTripCode;
    }

    public void setBookingTripCode(String bookingTripCode) {
        this.bookingTripCode = bookingTripCode;
    }

    public String getBookingTripStatus() {
        return bookingTripStatus;
    }

    public void setBookingTripStatus(String bookingTripStatus) {
        this.bookingTripStatus = bookingTripStatus;
    }


}