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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.OngoingTrip;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.UpcomingTrip;
import com.highway.customer.customerAdapter.OnGoingTripAdapterForCustomer;
import com.highway.customer.customerAdapter.UpComingTripAdapterForCustomer;

import java.util.ArrayList;
import java.util.List;


public class UpCommingFragmentForCustomer extends Fragment {

    private RecyclerView upcomingRecyclerForCustomer;
    DashBoardActivity dashBoardActivity;
    UpComingTripAdapterForCustomer upComingTripAdapterForCustomer;
    List<UpcomingTrip> upcomingTrips = new ArrayList<>();

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


    public void upcomingUpdatedTripListForCustomer(List<UpcomingTrip> upcomingTrips) {
        // Log.d("OncreateView ", "cancelF");
        if (upcomingTrips != null && upcomingTrips.size() > 0) {
            upComingTripAdapterForCustomer = new UpComingTripAdapterForCustomer(upcomingTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            upcomingRecyclerForCustomer.setLayoutManager(layoutManager);
            upcomingRecyclerForCustomer.setItemAnimator(new DefaultItemAnimator());
            upcomingRecyclerForCustomer.setAdapter(upComingTripAdapterForCustomer);

        } else {
            Toast.makeText(dashBoardActivity, "No upcoming data for customer !", Toast.LENGTH_SHORT).show();

        }
    }

}
