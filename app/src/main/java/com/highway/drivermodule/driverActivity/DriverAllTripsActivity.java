package com.highway.drivermodule.driverActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.highway.R;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.CancelTrip;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.CompletedTrip;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.OngoingTrip;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.UpcomingTrip;

import java.util.ArrayList;
import java.util.List;

public class DriverAllTripsActivity extends AppCompatActivity {

    public List<CompletedTrip> getCompletedTrips() {
        return completedTrips;
    }

    public void setCompletedTrips(List<CompletedTrip> completedTrips) {
        this.completedTrips = completedTrips;
    }

    public List<OngoingTrip> getOngoingTrips() {
        return ongoingTrips;
    }

    public void setOngoingTrips(List<OngoingTrip> ongoingTrips) {
        this.ongoingTrips = ongoingTrips;
    }

    public List<UpcomingTrip> getUpcomingTrips() {
        return upcomingTrips;
    }

    public void setUpcomingTrips(List<UpcomingTrip> upcomingTrips) {
        this.upcomingTrips = upcomingTrips;
    }

    public List<CancelTrip> getCancelTrips() {
        return cancelTrips;
    }

    public void setCancelTrips(List<CancelTrip> cancelTrips) {
        this.cancelTrips = cancelTrips;
    }

    private List<CompletedTrip> completedTrips = new ArrayList<>();
    private List<OngoingTrip> ongoingTrips = new ArrayList<>();
    private List<UpcomingTrip> upcomingTrips = new ArrayList<>();
    private List<CancelTrip> cancelTrips = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_all_trips);
    }
}
