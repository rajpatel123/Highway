package com.highway.customer.customerModelClass.customerInvoice;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerInvoiceReq {

@SerializedName("customerId")
@Expose
private String customerId;
@SerializedName("bookingId")
@Expose
private String bookingId;

public String getCustomerId() {
return customerId;
}

public void setCustomerId(String customerId) {
this.customerId = customerId;
}

public String getBookingId() {
return bookingId;
}

public void setBookingId(String bookingId) {
this.bookingId = bookingId;
}

}