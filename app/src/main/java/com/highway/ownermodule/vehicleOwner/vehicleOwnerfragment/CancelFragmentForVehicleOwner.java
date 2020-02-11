package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.CancelTrip;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter.CancleTripAdapterForVehicleOwner;

import java.util.ArrayList;
import java.util.List;


public class CancelFragmentForVehicleOwner extends Fragment {

    List<CancelTrip>cancelTrips = new ArrayList<>();
    RecyclerView cancleRecycler;
    DashBoardActivity dashBoardActivity;
    Context context;
   CancleTripAdapterForVehicleOwner cancleTripAdapterForVehicleOwner;


    public CancelFragmentForVehicleOwner() { }


    public static CancelFragmentForVehicleOwner newInstance() {
        CancelFragmentForVehicleOwner fragment = new CancelFragmentForVehicleOwner();
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
        View view = inflater.inflate(R.layout.fragment_vehicle_cancel, container, false);
          cancleRecycler = view.findViewById(R.id.vehicleOwnerCancelRecy);
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

    public  void vehicleCancleUpdatedTripList(List<CancelTrip> cancelTrips){
        if (cancelTrips !=null && cancelTrips.size()>0){
            cancleTripAdapterForVehicleOwner = new CancleTripAdapterForVehicleOwner(cancelTrips,getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            cancleRecycler.setLayoutManager(layoutManager);
            cancleRecycler.setItemAnimator(new DefaultItemAnimator());
            cancleRecycler.setAdapter(cancleTripAdapterForVehicleOwner);

        }else{
            Toast.makeText(dashBoardActivity, "Some thing is wrong", Toast.LENGTH_SHORT).show();
        }
    }


}
