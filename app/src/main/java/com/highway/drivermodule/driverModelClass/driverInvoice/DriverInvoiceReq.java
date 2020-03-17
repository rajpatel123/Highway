package com.highway.drivermodule.driverModelClass.driverInvoice;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DriverInvoiceReq {

@SerializedName("driverId")
@Expose
private String driverId;
@SerializedName("bookingId")
@Expose
private String bookingId;

public String getDriverId() {
return driverId;
}

public void setDriverId(String driverId) {
this.driverId = driverId;
}

public String getBookingId() {
return bookingId;
}

public void setBookingId(String bookingId) {
this.bookingId = bookingId;
}

}