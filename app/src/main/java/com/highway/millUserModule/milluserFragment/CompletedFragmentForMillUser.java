package com.highway.millUserModule.milluserFragment;

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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.CompletedTrip;
import com.highway.millUserModule.milluserActivity.MillUserAllTripActivity;
import com.highway.millUserModule.milluserAdapter.CompletedTripAdapterForMillUser;

import java.util.ArrayList;
import java.util.List;


public class CompletedFragmentForMillUser extends Fragment {
    DashBoardActivity dashBoardActivity;
    MillUserAllTripActivity millUserAllTripActivity;
    public RecyclerView completedRecycForMiller;
    CompletedTripAdapterForMillUser completedTripAdapterForMillUser;
    DashBoardFragmentForMillUser dashBoardFragmentForMillUser;
    List<CompletedTrip> completedTrips = new ArrayList<>();

    public CompletedFragmentForMillUser() {
        // Required empty public constructor
    }

    public static CompletedFragmentForMillUser newInstance() {
        CompletedFragmentForMillUser fragment = new CompletedFragmentForMillUser();
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
        View view = inflater.inflate(R.layout.fragment_mill_user_completed, container, false);
        completedRecycForMiller = view.findViewById(R.id.completedRecyForMillUser);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
       // dashBoardActivity = (DashBoardActivity) getActivity();
        millUserAllTripActivity = (MillUserAllTripActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

    public void millerCompletedUpdatedTripList(List<CompletedTrip> completedTrips) {
        if (completedTrips != null && completedTrips.size() > 0) {
            completedTripAdapterForMillUser = new CompletedTripAdapterForMillUser(completedTrips, getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            completedRecycForMiller.setLayoutManager(layoutManager);
              completedRecycForMiller.setItemAnimator(new DefaultItemAnimator());
            completedRecycForMiller.setAdapter(completedTripAdapterForMillUser);
        } else {
            Toast.makeText(millUserAllTripActivity, "No data completed trip for miller ", Toast.LENGTH_SHORT).show();
        }

    }


}
