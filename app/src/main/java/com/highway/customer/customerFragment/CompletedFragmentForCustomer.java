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
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.CompletedTrip;
import com.highway.customer.customerActivity.CompletedTripDetailsForCustomersActivity;
import com.highway.customer.customerActivity.CustomerAllTripsActivity;
import com.highway.customer.customerAdapter.CompletedTripAdapterForCustomer;

import java.util.ArrayList;
import java.util.List;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class CompletedFragmentForCustomer extends Fragment {

    RecyclerView completedRecyclerforCustomer;
    //DashBoardActivity dashBoardActivity;
    CustomerAllTripsActivity dashBoardActivity;
    CompletedTripAdapterForCustomer completedTripAdapterForCustomer;
    List<CompletedTrip> completedTrips = new ArrayList<>();

    public CompletedFragmentForCustomer() {
        // Required empty public constructor
    }


    public static CompletedFragmentForCustomer newInstance() {
        CompletedFragmentForCustomer fragment = new CompletedFragmentForCustomer();
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
        View view = inflater.inflate(R.layout.fragment_customer_completed, container, false);
        completedRecyclerforCustomer = view.findViewById(R.id.completedRecyForCus);

        //completedTripDetailsForCustomer();
        return view;
    }

    private void completedTripDetailsForCustomer() {
        if (completedTrips != null && completedTrips.size() > 0) {
            if (completedTripAdapterForCustomer!=null) {
                completedTripAdapterForCustomer.setCompletedTripDetailsInterface(new CompletedTripAdapterForCustomer.CompletedTripDetailsInterface() {
                    @Override
                    public void tripDetailsListClick(String sourceLat, String sourceLong, String destinationLat, String destinationLong,
                                                     String name, String role, String vehicleName, String vehicleNumber, String fare,
                                                     String status, String tripType, String startDate, String endDate, String pickupTime,
                                                     String dropTime) {

                        Intent intent = new Intent(getActivity(), CompletedTripDetailsForCustomersActivity.class);
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

               /* intent.putExtra("SourceAddLatlog", completedTrips.get(0).getSourceLat());
                intent.putExtra("SourceAddLongitude",completedTrips.get(0).getSourceLong());
                intent.putExtra("DestAddLatlog", completedTrips.get(0).getSourceLat());
                intent.putExtra("DestAddLongitude", completedTrips.get(0).getDestinationLong());

                intent.putExtra("CompleteDate", completedTrips.get(0).getEndDate());
                intent.putExtra("PickupTime", completedTrips.get(0).getPickupTime());
                intent.putExtra("DropTime", completedTrips.get(0).getDropTime());
                intent.putExtra("VehicleName", completedTrips.get(0).getVehicleName());
                intent.putExtra("VehicleNumber", completedTrips.get(0).getVehicleNumber());
                intent.putExtra("FairCharge",completedTrips.get(0).getFare());
                intent.putExtra("UserName",completedTrips.get(0).getName());*/
                      getActivity().startActivity(intent);


                    }
                });
            }
        }
    }

    public void replaceFragment(Fragment fragment, String tag) {
        try {
            androidx.fragment.app.FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            if (fragmentManager != null) {
                androidx.fragment.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.nav_host_fragment, fragment, tag);
                fragmentTransaction.setTransition(androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                fragmentTransaction.commitAllowingStateLoss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      //  dashBoardActivity = (DashBoardActivity) getActivity();
        dashBoardActivity = (CustomerAllTripsActivity) getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void completedUpdatedTripListForCustomer(List<CompletedTrip> completedTrips) {
        // Log.d("OncreateView ", "cancelF");
        if (completedTrips != null && completedTrips.size() > 0) {
            completedTripAdapterForCustomer = new CompletedTripAdapterForCustomer(completedTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            completedRecyclerforCustomer.setLayoutManager(layoutManager);
            completedRecyclerforCustomer.setItemAnimator(new DefaultItemAnimator());
            completedRecyclerforCustomer.setAdapter(completedTripAdapterForCustomer);

        } else {
            Toast.makeText(dashBoardActivity, "No completed data  for customer !", Toast.LENGTH_SHORT).show();

        }
    }

}
