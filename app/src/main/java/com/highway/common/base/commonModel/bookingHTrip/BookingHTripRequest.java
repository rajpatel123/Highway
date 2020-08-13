package com.highway.common.base.commonModel.bookingHTrip;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingHTripRequest {

@SerializedName("User_id")
@Expose
private String userId;
@SerializedName("VehicleTypeId")
@Expose
private String vehicleTypeId;
@SerializedName("TripRecevirId")
@Expose
private String tripRecevirId;


@SerializedName("tripRecevirName")
@Expose
private String tripRecevirName;

@SerializedName("tripRecevirNumber")
@Expose
private String tripRecevirNumber;

    public String getTripRecevirName() {
        return tripRecevirName;
    }

    public void setTripRecevirName(String tripRecevirName) {
        this.tripRecevirName = tripRecevirName;
    }

    public String getTripRecevirNumber() {
        return tripRecevirNumber;
    }

    public void setTripRecevirNumber(String tripRecevirNumber) {
        this.tripRecevirNumber = tripRecevirNumber;
    }

    public String getTripPickupName() {
        return tripPickupName;
    }

    public void setTripPickupName(String tripPickupName) {
        this.tripPickupName = tripPickupName;
    }

    public String getTripPickupNumber() {
        return tripPickupNumber;
    }

    public void setTripPickupNumber(String tripPickupNumber) {
        this.tripPickupNumber = tripPickupNumber;
    }

    @SerializedName("tripPickupName")
@Expose
private String tripPickupName;

@SerializedName("tripPickupNumber")
@Expose
private String tripPickupNumber;

@SerializedName("GoodsTypeId")
@Expose
private String goodsTypeId;
@SerializedName("CouponId")
@Expose
private String couponId;
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
@SerializedName("sourceAddress")
@Expose
private String sourceAddress;

    public String getGoodsSize() {
        return goodsSize;
    }

    public void setGoodsSize(String goodsSize) {
        this.goodsSize = goodsSize;
    }

    @SerializedName("destAddress")
@Expose
private String destAddress;

@SerializedName("goodsSize")
@Expose
private String goodsSize;

public String getUserId() {
return userId;
}

public void setUserId(String userId) {
this.userId = userId;
}

public String getVehicleTypeId() {
return vehicleTypeId;
}

public void setVehicleTypeId(String vehicleTypeId) {
this.vehicleTypeId = vehicleTypeId;
}

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

}