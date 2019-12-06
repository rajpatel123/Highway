package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customer_diver_owner_Models_class.OngoingTrip;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter.VehicleOwnerOnGoingTripAdapter;

import java.util.ArrayList;
import java.util.List;


public class VehicleOnGoingFragment extends Fragment {
    List<OngoingTrip> ongoingTrips = new ArrayList<>();
    RecyclerView vehicleOngoingRecycler;
    DashBoardActivity dashBoardActivity;
    Context context;
    VehicleOwnerOnGoingTripAdapter vehicleOwnerOnGoingTripAdapter;




    public VehicleOnGoingFragment() { }


    public static VehicleOnGoingFragment newInstance() {
        VehicleOnGoingFragment fragment = new VehicleOnGoingFragment();
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
        View view =  inflater.inflate(R.layout.fragment_vehicle_on_going, container, false);
        vehicleOngoingRecycler  = view.findViewById(R.id.vehicleOnGoiingRecy);
        return view;
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


      public void vehicleOnGoingUpdateList(List<OngoingTrip>ongoingTrips){
        if (ongoingTrips!=null && ongoingTrips.size()>0){
            vehicleOwnerOnGoingTripAdapter = new VehicleOwnerOnGoingTripAdapter(ongoingTrips,getActivity());
            RecyclerView.LayoutManager layoutManager  = new LinearLayoutManager(getContext());
            vehicleOngoingRecycler.setLayoutManager(layoutManager);
            vehicleOngoingRecycler.setItemAnimator(new DefaultItemAnimator());
            vehicleOngoingRecycler.setAdapter(vehicleOwnerOnGoingTripAdapter);


        }
      }


}
