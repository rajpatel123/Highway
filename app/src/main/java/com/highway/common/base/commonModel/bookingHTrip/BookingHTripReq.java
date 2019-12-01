package com.highway.common.base.commonModel.bookingHTrip;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingHTripReq {

@SerializedName("User_Id")
@Expose
private String userId;
@SerializedName("Vahical_Id")
@Expose
private String vahicalId;
@SerializedName("SourceLat")
@Expose
private String sourceLat;
@SerializedName("SourceLong")
@Expose
private String sourceLong;
@SerializedName("DestLat")
@Expose
private String destLat;
@SerializedName("DestLong")
@Expose
private String destLong;
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

public String getUserId() {
return userId;
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

public String getDestLat() {
return destLat;
}

public void setDestLat(String destLat) {
this.destLat = destLat;
}

public String getDestLong() {
return destLong;
}

public void setDestLong(String destLong) {
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