package com.highway.drivermodule.driverFragment;

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
import com.highway.drivermodule.adapter.CancelTripAdapter;
import com.highway.drivermodule.diverModels.CancelTrip;

import java.util.ArrayList;
import java.util.List;


public class DriverCancelFragment extends Fragment {
    private List<CancelTrip> cancelTrips = new ArrayList<>();
    private RecyclerView recyclerViewCancel;
    CancelTripAdapter cancelTripAdapter;
    DashBoardActivity dashBoardActivity;
    public Context context;


    public DriverCancelFragment() {
        // Required empty public constructor
    }

    public static DriverCancelFragment newInstance() {
        DriverCancelFragment fragment = new DriverCancelFragment();
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
        LayoutInflater inflater1 = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater1.inflate(R.layout.fragment_driver_cancel, container ,false);

        recyclerViewCancel = (RecyclerView) view.findViewById(R.id.cancelrecyclerview);

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

    public void updateListCancel(List<CancelTrip> cancelTrips) {

        if (cancelTrips != null && cancelTrips.size() > 0) {
            cancelTripAdapter = new CancelTripAdapter(cancelTrips, getContext());
            final LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerViewCancel.setLayoutManager(layoutManager);
            recyclerViewCancel.setItemAnimator(new DefaultItemAnimator());
            recyclerViewCancel.setAdapter(cancelTripAdapter);


        } else {
            Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();

        }


    }


}
