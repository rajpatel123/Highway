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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.CancelTrip;
import com.highway.millUserModule.milluserActivity.MillUserAllTripActivity;
import com.highway.millUserModule.milluserAdapter.CancleTripAdapterForMillerUser;

import java.util.ArrayList;
import java.util.List;


public class CancelFragmentForMillUser extends Fragment {
    RecyclerView canRecycForMiller;
    DashBoardActivity dashBoardActivity;
    MillUserAllTripActivity millUserAllTripActivity;
    List<CancelTrip>cancelTrips = new ArrayList<>();
    CancleTripAdapterForMillerUser cancleTripAdapterForMillerUser;

    public CancelFragmentForMillUser() {
        // Required empty public constructor
    }


    public static CancelFragmentForMillUser newInstance() {
        CancelFragmentForMillUser fragment = new CancelFragmentForMillUser();
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
        View view =  inflater.inflate(R.layout.fragment_mill_user_cancel, container, false);
         canRecycForMiller  = view.findViewById(R.id.cancleRecyclerForMiller);


    return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
      //  dashBoardActivity = (DashBoardActivity) getActivity();
        millUserAllTripActivity = (MillUserAllTripActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void millerCancleUpdatedTripList(List<CancelTrip>cancelTrips){
        if (cancelTrips!=null && cancelTrips.size()>0){
            cancleTripAdapterForMillerUser = new CancleTripAdapterForMillerUser(cancelTrips,getActivity());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            canRecycForMiller.setLayoutManager(layoutManager);
            canRecycForMiller.setItemAnimator(new DefaultItemAnimator());
            canRecycForMiller.setAdapter(cancleTripAdapterForMillerUser);
        }else{
            Toast.makeText(millUserAllTripActivity, "No data for cencle trip for miller ", Toast.LENGTH_SHORT).show();
        }
    }



}
