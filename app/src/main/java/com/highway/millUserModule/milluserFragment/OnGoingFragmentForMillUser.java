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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.OngoingTrip;
import com.highway.millUserModule.milluserActivity.MillUserAllTripActivity;
import com.highway.millUserModule.milluserAdapter.OnGoingTripAdapterForMiller;

import java.util.List;

public class OnGoingFragmentForMillUser extends Fragment {
    RecyclerView onGoingRecyFormiller;
    DashBoardActivity dashBoardActivity;
    OnGoingTripAdapterForMiller onGoingTripAdapterForMiller;
    MillUserAllTripActivity millUserAllTripActivity;

    public OnGoingFragmentForMillUser() {
        // Required empty public constructor
    }


    public static OnGoingFragmentForMillUser newInstance() {
        OnGoingFragmentForMillUser fragment = new OnGoingFragmentForMillUser();
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
        View view=  inflater.inflate(R.layout.fragment_mill_user_on_going, container, false);
        onGoingRecyFormiller = view.findViewById(R.id.onGoingRecyForMillUser);

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

    public void millerOnGoingdUpdatedTripList(List<OngoingTrip>ongoingTrips){
        if (ongoingTrips!=null && ongoingTrips.size()>0){
            onGoingTripAdapterForMiller = new OnGoingTripAdapterForMiller(ongoingTrips , getActivity());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
            onGoingRecyFormiller.setLayoutManager(layoutManager);
            onGoingRecyFormiller.setItemAnimator(new DefaultItemAnimator());
            onGoingRecyFormiller.setAdapter(onGoingTripAdapterForMiller);
        }else {
            Toast.makeText(millUserAllTripActivity, "No onGoing trip for mill user", Toast.LENGTH_SHORT).show();
        }

    }

}
