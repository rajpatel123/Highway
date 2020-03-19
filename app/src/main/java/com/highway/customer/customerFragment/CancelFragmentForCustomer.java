package com.highway.customer.customerFragment;

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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.CancelTrip;
import com.highway.customer.customerAdapter.CancleTripAdapterForCustomer;

import java.util.ArrayList;
import java.util.List;


public class CancelFragmentForCustomer extends Fragment {
    public RecyclerView canRecyclerForCustomer;
    DashBoardActivity dashBoardActivity;
    CancleTripAdapterForCustomer cancleTripAdapterForCustomer;
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
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_customer_cancel, container, false);
        canRecyclerForCustomer = view.findViewById(R.id.CanRecyclerForCustomer);
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

    public void cancelUpdatedTripListForCustomer(List<CancelTrip>cancelTrips){
        if (cancelTrips!=null && cancelTrips.size()>0){
            cancleTripAdapterForCustomer = new CancleTripAdapterForCustomer(cancelTrips, getActivity());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            canRecyclerForCustomer.setLayoutManager(layoutManager);
            canRecyclerForCustomer.setItemAnimator(new DefaultItemAnimator());
            canRecyclerForCustomer.setAdapter(cancleTripAdapterForCustomer);
        }/*else{
            Toast.makeText(dashBoardActivity, "No cancel trip data for customer !", Toast.LENGTH_SHORT).show();
        }*/
    }


}
