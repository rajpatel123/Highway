package com.highway.common.base;

import android.app.Application;

import com.highway.common.base.commonModel.bookingHTrip.BookingHTripReq;

public class HighwayApplication extends Application {


    static HighwayApplication instance = null;
    public BookingHTripReq getBookingHTripReq() {
        return bookingHTripReq;
    }

    public void setBookingHTripReq(BookingHTripReq bookingHTripReq) {
        this.bookingHTripReq = bookingHTripReq;
    }

    BookingHTripReq bookingHTripReq = new BookingHTripReq();


    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }


    public static HighwayApplication getInstance(){
        return instance;
    }



    public BookingHTripReq getBookingObject(){
        return bookingHTripReq;
    }


}
