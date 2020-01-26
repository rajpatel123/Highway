package com.highway.common.base.commonModel.bookingHTrip;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingHTripReq {

    @SerializedName("User_id")
    @Expose
    private String userId;
    @SerializedName("VehicleId")
    @Expose
    private String vahicalId;
    @SerializedName("SourceLat")
    @Expose
    private double sourceLat;
    @SerializedName("SourceLong")
    @Expose
    private double sourceLong;
    @SerializedName("DestLat")
    @Expose
    private double destLat;
    @SerializedName("DestLong")
    @Expose
    private double destLong;
    @SerializedName("TripFare")
    @Expose
    private String tripFare;
    @SerializedName("TripDistanc")
    @Expose
    private String tripDistanc;
    @SerializedName("STripTime")
    @Expose
    private String sTripTime;
    @SerializedName("STripDate")
    @Expose
    private String sTripDate;
    @SerializedName("ETripTime")
    @Expose
    private String eTripTime;

    @SerializedName("ETripDate")
    @Expose
    private String eTripDate;


    @SerializedName("sourceAddress")
    @Expose
    private String sourceAddress;

    @SerializedName("TripRecevirId")
    @Expose
    private String tripRecevirId;

    @SerializedName("GoodsTypeId")
    @Expose
    private String goodsTypeId;

    @SerializedName("CouponId")
    @Expose
    private String couponId;
    @SerializedName("destAddress")
    @Expose
    private String destAddress;




    public String getTripRecevirId() {
        return tripRecevirId;
    }

    public void setTripRecevirId(String tripRecevirId) {
        this.tripRecevirId = tripRecevirId;
    }

    public String getGoodsTypeId() {
        return goodsTypeId;
    }

    public void setGoodsTypeId(String goodsTypeId) {
        this.goodsTypeId = goodsTypeId;
    }

    public String getCouponId() {
        return couponId;
    }

    public void setCouponId(String couponId) {
        this.couponId = couponId;
    }

    public String getUserId() {
        return userId;
    }

    public String getsTripTime() {
        return sTripTime;
    }

    public void setsTripTime(String sTripTime) {
        this.sTripTime = sTripTime;
    }

    public String getsTripDate() {
        return sTripDate;
    }

    public void setsTripDate(String sTripDate) {
        this.sTripDate = sTripDate;
    }

    public String geteTripTime() {
        return eTripTime;
    }

    public void seteTripTime(String eTripTime) {
        this.eTripTime = eTripTime;
    }

    public String geteTripDate() {
        return eTripDate;
    }

    public void seteTripDate(String eTripDate) {
        this.eTripDate = eTripDate;
    }

    public String getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(String sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public String getDestAddress() {
        return destAddress;
    }

    public void setDestAddress(String destAddress) {
        this.destAddress = destAddress;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getVahicalId() {
        return vahicalId;
    }

    public void setVahicalId(String vahicalId) {
        this.vahicalId = vahicalId;
    }

    public double getSourceLat() {
        return sourceLat;
    }

    public void setSourceLat(double sourceLat) {
        this.sourceLat = sourceLat;
    }

    public double getSourceLong() {
        return sourceLong;
    }

    public void setSourceLong(double sourceLong) {
        this.sourceLong = sourceLong;
    }

    public double getDestLat() {
        return destLat;
    }

    public void setDestLat(double destLat) {
        this.destLat = destLat;
    }

    public double getDestLong() {
        return destLong;
    }

    public void setDestLong(double destLong) {
        this.destLong = destLong;
    }

    public String getTripFare() {
        return tripFare;
    }

    public void setTripFare(String tripFare) {
        this.tripFare = tripFare;
    }

    public String getTripDistanc() {
        return tripDistanc;
    }

    public void setTripDistanc(String tripDistanc) {
        this.tripDistanc = tripDistanc;
    }

    public String getSTripTime() {
        return sTripTime;
    }

    public void setSTripTime(String sTripTime) {
        this.sTripTime = sTripTime;
    }

    public String getSTripDate() {
        return sTripDate;
    }

    public void setSTripDate(String sTripDate) {
        this.sTripDate = sTripDate;
    }

    public String getETripTime() {
        return eTripTime;
    }

    public void setETripTime(String eTripTime) {
        this.eTripTime = eTripTime;
    }

    public String getETripDate() {
        return eTripDate;
    }

    public void setETripDate(String eTripDate) {
        this.eTripDate = eTripDate;
    }

}