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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.OngoingTrip;
import com.highway.customer.customerAdapter.OnGoingTripAdapterForCustomer;

import java.util.ArrayList;
import java.util.List;


public class OnGoingFragmentForCustomer extends Fragment {
    private RecyclerView onGoingRecyclerForCustomer;
    DashBoardActivity dashBoardActivity;
    OnGoingTripAdapterForCustomer onGoingTripAdapterForCustomer;
    List<OngoingTrip>ongoingTrips = new ArrayList<>();

    public OnGoingFragmentForCustomer() {
        // Required empty public constructor
    }


    public static OnGoingFragmentForCustomer newInstance() {
        OnGoingFragmentForCustomer fragment = new OnGoingFragmentForCustomer();
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
        View view = inflater.inflate(R.layout.fragment_customer_on_going, container, false);
        onGoingRecyclerForCustomer = view.findViewById(R.id.onGoingRecyclerForCustomer);
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

    public void ongoingUpdatedTripListForCustomer(List<OngoingTrip> ongoingTrips) {
        // Log.d("OncreateView ", "cancelF");
        if (ongoingTrips != null && ongoingTrips.size() > 0) {
            onGoingTripAdapterForCustomer = new OnGoingTripAdapterForCustomer(ongoingTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            onGoingRecyclerForCustomer.setLayoutManager(layoutManager);
            onGoingRecyclerForCustomer.setItemAnimator(new DefaultItemAnimator());
            onGoingRecyclerForCustomer.setAdapter(onGoingTripAdapterForCustomer);

        } /*else {
            Toast.makeText(dashBoardActivity, "No ongoing data for customer !", Toast.LENGTH_SHORT).show();

        }*/
    }
}
