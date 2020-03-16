package com.highway.common.base.firebaseService;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class NotificationPushData implements Parcelable {

    @SerializedName("mobile")
    @Expose
    private String mobile;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("destination")
    @Expose
    private String destination;
    @SerializedName("tripId")
    @Expose
    private String tripId;
    @SerializedName("source")
    @Expose
    private String source;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("customer")
    @Expose
    private String customer;

    public NotificationPushData(Parcel in) {
        mobile = in.readString();
        message = in.readString();
        destination = in.readString();
        tripId = in.readString();
        source = in.readString();
        type = in.readString();
        customer = in.readString();
    }

    public static final Creator<NotificationPushData> CREATOR = new Creator<NotificationPushData>() {
        @Override
        public NotificationPushData createFromParcel(Parcel in) {
            return new NotificationPushData(in);
        }

        @Override
        public NotificationPushData[] newArray(int size) {
            return new NotificationPushData[size];
        }
    };

    public NotificationPushData() {

    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mobile);
        dest.writeString(message);
        dest.writeString(customer);
        dest.writeString(type);
        dest.writeString(source);
        dest.writeString(destination);
        dest.writeString(tripId);
    }

    public boolean getUser() {
        return false;
    }
}