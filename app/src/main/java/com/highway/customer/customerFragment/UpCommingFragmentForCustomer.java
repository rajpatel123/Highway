package com.highway.customer.customerFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.type.LatLng;
import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.UpcomingTrip;
import com.highway.customer.customerActivity.BookingConformedActivity;
import com.highway.customer.customerActivity.TripTrackingByCustomerActivity;
import com.highway.customer.customerAdapter.UpComingTripAdapterForCustomer;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class UpCommingFragmentForCustomer extends Fragment {

    private RecyclerView upcomingRecyclerForCustomer;
    DashBoardActivity dashBoardActivity;
    UpComingTripAdapterForCustomer upComingTripAdapterForCustomer;
    List<UpcomingTrip> upcomingTrips = new ArrayList<>();
    UpcomingTrip upcomingTrip;

    public UpCommingFragmentForCustomer() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static UpCommingFragmentForCustomer newInstance() {
        UpCommingFragmentForCustomer fragment = new UpCommingFragmentForCustomer();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_customer_up_comming, container, false);
        upcomingRecyclerForCustomer = view.findViewById(R.id.upcomingRecyclerForCustomer);

        upcomingTripTracking();

        return view;
    }

    public void upcomingTripTracking() {
        upComingTripAdapterForCustomer.setUpComingBookTripInterface(new UpComingTripAdapterForCustomer.UpComingBookTripInterface() {
            @Override
            public void upComingBookTrip(String sourceLat, String sourceLong, String destinationLat, String destinationLong,
                                         String name, String role, String vehicleName, String vehicleNumber, String fare,
                                         String status, String tripType, String startDate, String endDate, String pickupTime,
                                         String dropTime) {

                Intent intent = new Intent(getActivity(), BookingConformedActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);

                intent.putExtra("sourceLat",sourceLat);
                intent.putExtra("sourceLong",sourceLong);
                intent.putExtra("destinationLat",destinationLat);
                intent.putExtra("destinationLong",destinationLong);
                intent.putExtra("name",name);
                intent.putExtra("role",  role);
                intent.putExtra("vehicleName",vehicleName);
                intent.putExtra("vehicleNumber",vehicleNumber);
                intent.putExtra("fare",fare);
                intent.putExtra("status",status);
                intent.putExtra("tripType",tripType);
                intent.putExtra("startDate",startDate);
                intent.putExtra("endDate",endDate);
                intent.putExtra("pickupTime",pickupTime);
                intent.putExtra("dropTime",dropTime);

                getActivity().startActivity(intent);

            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashBoardActivity = (DashBoardActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void upcomingUpdatedTripListForCustomer(List<UpcomingTrip> upcomingTrips) {
        // Log.d("OncreateView ", "cancelF");
        if (upcomingTrips != null && upcomingTrips.size() > 0) {
            upComingTripAdapterForCustomer = new UpComingTripAdapterForCustomer(upcomingTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            upcomingRecyclerForCustomer.setLayoutManager(layoutManager);
            upcomingRecyclerForCustomer.setItemAnimator(new DefaultItemAnimator());
            upcomingRecyclerForCustomer.setAdapter(upComingTripAdapterForCustomer);

        } /*else {
            Toast.makeText(dashBoardActivity, "No upcoming data for customer !", Toast.LENGTH_SHORT).show();

        }*/
    }

}
