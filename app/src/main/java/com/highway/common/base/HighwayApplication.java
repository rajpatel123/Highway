package com.highway.common.base;

import android.app.Application;

import com.google.firebase.messaging.FirebaseMessaging;
import com.highway.common.base.commonModel.bookingHTrip.BookingHTripRequest;
import com.highway.customer.customerModelClass.customerCurrentTripStatus.CustomerTripStatus;
import com.highway.drivermodule.drivermodels.TripStatus;

public class HighwayApplication extends Application {


    static HighwayApplication instance = null;
    static String tripId;
    private String vehicleType;
    private String vehicleNumber;

    public BookingHTripRequest getBookingHTripRequest() {
        return bookingHTripRequest;
    }
    TripStatus tripStatus=null;

    public void setBookingHTripRequest(BookingHTripRequest bookingHTripRequest) {
        this.bookingHTripRequest = bookingHTripRequest;
    }

    BookingHTripRequest bookingHTripRequest = null;

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

    public String getVehicleType() { return vehicleType; }

    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public String getVehicleNumber() { return vehicleNumber; }

    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }



    public void setUserDetails(TripStatus tripStatus) {
        this.tripStatus=tripStatus;
    }


}
