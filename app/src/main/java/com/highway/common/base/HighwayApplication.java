package com.highway.common.base;

import android.app.Application;

import com.google.firebase.messaging.FirebaseMessaging;
import com.highway.common.base.commonModel.bookingHTrip.BookingHTripRequest;
import com.highway.customer.customerModelClass.customerCurrentTripStatus.CustomerTripStatus;
import com.highway.drivermodule.drivermodels.TripStatus;

public class HighwayApplication extends Application {


    static HighwayApplication instance = null;
    static String tripId;
    public BookingHTripRequest getBookingHTripRequest() {
        return bookingHTripRequest;
    }
    TripStatus tripStatus=null;
    CustomerTripStatus customerTripStatus=null;
    public void setBookingHTripRequest(BookingHTripRequest bookingHTripRequest) {
        this.bookingHTripRequest = bookingHTripRequest;
    }

    BookingHTripRequest bookingHTripRequest = new BookingHTripRequest();

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseMessaging.getInstance().subscribeToTopic("all");
        instance = this;
    }


    public static HighwayApplication getInstance(){
        return instance;
    }


    public void setCurrentTripId(String trip_id) {
        tripId = trip_id;
    }


    public String getCurrentTripId() {
        return tripId;
    }
    public TripStatus getTripStatus() {
        return tripStatus;
    }

    public void setUserDetails(TripStatus tripStatus) {
        this.tripStatus=tripStatus;
    }

    public void setUserDetails(CustomerTripStatus customerTripStatus) {
        this.customerTripStatus=customerTripStatus;
    }
}
