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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.UpcomingTrip;
import com.highway.millUserModule.milluserActivity.MillUserAllTripActivity;
import com.highway.millUserModule.milluserAdapter.UpComingTripAdapterForMillUser;

import java.util.List;


public class UpComingFragmentForMillUser extends Fragment {
    RecyclerView  upComingRecycForMiller;
    DashBoardActivity dashBoardActivity;
    MillUserAllTripActivity millUserAllTripActivity;
    List<UpcomingTrip>upcomingTrips;
    UpComingTripAdapterForMillUser upComingTripAdapterForMillUser;


    public UpComingFragmentForMillUser() {
        // Required empty public constructor
    }


    public static UpComingFragmentForMillUser newInstance() {
        UpComingFragmentForMillUser fragment = new UpComingFragmentForMillUser();
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
        View view = inflater.inflate(R.layout.fragment_mill_user_up_coming, container, false);
        upComingRecycForMiller = view.findViewById(R.id.upComingRecycForMiller);


       return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        //dashBoardActivity = (DashBoardActivity) getActivity();
        millUserAllTripActivity = (MillUserAllTripActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public  void millerUpCOmingUpdatedTripList(List<UpcomingTrip>upcomingTrips){
        if (upcomingTrips != null && upcomingTrips.size()>0){
            upComingTripAdapterForMillUser = new UpComingTripAdapterForMillUser(upcomingTrips,getActivity());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            upComingRecycForMiller.setLayoutManager(layoutManager);
            upComingRecycForMiller.setItemAnimator(new DefaultItemAnimator());
            upComingRecycForMiller.setAdapter(upComingTripAdapterForMillUser);
        }else{
            Toast.makeText(millUserAllTripActivity, "No upcoming trip for miller", Toast.LENGTH_SHORT).show();
        }
    }

}
