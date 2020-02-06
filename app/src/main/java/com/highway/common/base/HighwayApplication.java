package com.highway.common.base;

import android.app.Application;

import com.highway.common.base.commonModel.bookingHTrip.BookingHTripRequest;

public class HighwayApplication extends Application {


    static HighwayApplication instance = null;
    public BookingHTripRequest getBookingHTripRequest() {
        return bookingHTripRequest;
    }

    public void setBookingHTripRequest(BookingHTripRequest bookingHTripRequest) {
        this.bookingHTripRequest = bookingHTripRequest;
    }

    BookingHTripRequest bookingHTripRequest = new BookingHTripRequest();


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static HighwayApplication getInstance(){
        return instance;
    }



    public BookingHTripRequest getBookingObject(){
        return bookingHTripRequest;
    }


}
