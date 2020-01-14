
package com.highway.customer.customerModelClass.customerTrip;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerTripResponse {

    @SerializedName("Status")
    @Expose
    private Boolean status;
    @SerializedName("Message")
    @Expose
    private String message;
    @SerializedName("upcomingTrips")
    @Expose
    private List<Object> upcomingTrips = null;
    @SerializedName("ongoingTrips")
    @Expose
    private List<OngoingTrip> ongoingTrips = null;
    @SerializedName("completedTrips")
    @Expose
    private List<Object> completedTrips = null;
    @SerializedName("cancelTrips")
    @Expose
    private List<CancelTrip> cancelTrips = null;

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

    public List<Object> getUpcomingTrips() {
        return upcomingTrips;
    }

    public void setUpcomingTrips(List<Object> upcomingTrips) {
        this.upcomingTrips = upcomingTrips;
    }

    public List<OngoingTrip> getOngoingTrips() {
        return ongoingTrips;
    }

    public void setOngoingTrips(List<OngoingTrip> ongoingTrips) {
        this.ongoingTrips = ongoingTrips;
    }

    public List<Object> getCompletedTrips() {
        return completedTrips;
    }

    public void setCompletedTrips(List<Object> completedTrips) {
        this.completedTrips = completedTrips;
    }

    public List<CancelTrip> getCancelTrips() {
        return cancelTrips;
    }

    public void setCancelTrips(List<CancelTrip> cancelTrips) {
        this.cancelTrips = cancelTrips;
    }

}
