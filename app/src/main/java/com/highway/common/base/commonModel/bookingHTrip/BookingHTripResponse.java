package com.highway.common.base.commonModel.bookingHTrip;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BookingHTripResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("bookIdCode")
    @Expose
    private String bookIdCode;
    @SerializedName("bookId")
    @Expose
    private String bookId;

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

    public String getBookIdCode() {
        return bookIdCode;
    }

    public void setBookIdCode(String bookIdCode) {
        this.bookIdCode = bookIdCode;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

}