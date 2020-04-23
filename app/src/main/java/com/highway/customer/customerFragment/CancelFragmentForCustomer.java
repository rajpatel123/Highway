package com.highway.customer.customerFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.CancelTrip;
import com.highway.customer.customerActivity.CancelTripDetailsForCustomerActivity;
import com.highway.customer.customerActivity.CustomerAllTripsActivity;
import com.highway.customer.customerAdapter.CancelTripAdapterForCustomer;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class CancelFragmentForCustomer extends Fragment {
    public RecyclerView canRecyclerForCustomer;
 //   DashBoardActivity dashBoardActivity;
    CustomerAllTripsActivity dashBoardActivity;
    CancelTripAdapterForCustomer cancleTripAdapterForCustomer;
    List<CancelTrip>cancelTrips = new ArrayList<>();
    Context context;

    public CancelFragmentForCustomer() {
        // Required empty public constructor
    }


    public static CancelFragmentForCustomer newInstance(String param1, String param2) {
        CancelFragmentForCustomer fragment = new CancelFragmentForCustomer();
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
        View view= inflater.inflate(R.layout.fragment_customer_cancel, container, false);
        canRecyclerForCustomer = view.findViewById(R.id.CanRecyclerForCustomer);

        //cancelTripDetailsForCustomer();
        return view;
    }

    private void cancelTripDetailsForCustomer() {
        if (cancelTrips!=null && cancelTrips.size()>0) {
            if (cancleTripAdapterForCustomer != null) {

                cancleTripAdapterForCustomer.setCancelBookTripInterface(new CancelTripAdapterForCustomer.CancelBookTripInterface() {
                    @Override
                    public void cancelBookTripClick(String sourceLat, String sourceLong, String destinationLat, String destinationLong,
                                                    String name, String role, String vehicleName, String vehicleNumber, String fare,
                                                    String status, String tripType, String startDate, String endDate, String pickupTime,
                                                    String dropTime) {


                        Intent intent = new Intent(getActivity(), CancelTripDetailsForCustomerActivity.class);
                        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);

                        intent.putExtra("sourceLat", sourceLat);
                        intent.putExtra("sourceLong", sourceLong);
                        intent.putExtra("destinationLat", destinationLat);
                        intent.putExtra("destinationLong", destinationLong);
                        intent.putExtra("name", name);
                        intent.putExtra("role", role);
                        intent.putExtra("vehicleName", vehicleName);
                        intent.putExtra("vehicleNumber", vehicleNumber);
                        intent.putExtra("fare", fare);
                        intent.putExtra("status", status);
                        intent.putExtra("tripType", tripType);
                        intent.putExtra("startDate", startDate);
                        intent.putExtra("endDate", endDate);
                        intent.putExtra("pickupTime", pickupTime);
                        intent.putExtra("dropTime", dropTime);

                        getActivity().startActivity(intent);


                    }
                });

            }
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       // dashBoardActivity = (DashBoardActivity) getActivity();
        dashBoardActivity = (CustomerAllTripsActivity) getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void cancelUpdatedTripListForCustomer(List<CancelTrip>cancelTrips){
        if (cancelTrips!=null && cancelTrips.size()>0){
            cancleTripAdapterForCustomer = new CancelTripAdapterForCustomer(cancelTrips, getActivity());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            canRecyclerForCustomer.setLayoutManager(layoutManager);
            canRecyclerForCustomer.setItemAnimator(new DefaultItemAnimator());
            canRecyclerForCustomer.setAdapter(cancleTripAdapterForCustomer);
        }else{
            Toast.makeText(dashBoardActivity, "No cancel trip data for customer !", Toast.LENGTH_SHORT).show();
        }
    }


}
