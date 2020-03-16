
package com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetAllTripByUserIdResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("upcomingTrips")
    @Expose
    private List<UpcomingTrip> upcomingTrips = null;
    @SerializedName("ongoingTrips")
    @Expose
    private List<OngoingTrip> ongoingTrips = null;
    @SerializedName("completedTrips")
    @Expose
    private List<CompletedTrip> completedTrips = null;
    @SerializedName("cancelTrips")
    @Expose
    private List<CancelTrip> cancelTrips = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<UpcomingTrip> getUpcomingTrips() {
        return upcomingTrips;
    }

    public void setUpcomingTrips(List<UpcomingTrip> upcomingTrips) {
        this.upcomingTrips = upcomingTrips;
    }

    public List<OngoingTrip> getOngoingTrips() {
        return ongoingTrips;
    }

    public void setOngoingTrips(List<OngoingTrip> ongoingTrips) {
        this.ongoingTrips = ongoingTrips;
    }

    public List<CompletedTrip> getCompletedTrips() {
        return completedTrips;
    }

    public void setCompletedTrips(List<CompletedTrip> completedTrips) {
        this.completedTrips = completedTrips;
    }

    public List<CancelTrip> getCancelTrips() {
        return cancelTrips;
    }

    public void setCancelTrips(List<CancelTrip> cancelTrips) {
        this.cancelTrips = cancelTrips;
    }

}
