package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;


import com.highway.R;


public class AddNewDriverFragmentForVehicleOwner extends Fragment {
    RecyclerView addDriverRecyView;
    private Toolbar addDriverToolbar;

    public AddNewDriverFragmentForVehicleOwner() {
        // Required empty public constructor
    }

    public static AddNewDriverFragmentForVehicleOwner newInstance() {
        AddNewDriverFragmentForVehicleOwner fragment = new AddNewDriverFragmentForVehicleOwner();
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
       View view = inflater.inflate(R.layout.fragment_add_new_driver_for_vehicle_owner, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        addDriverToolbar =  view.findViewById(R.id.AddDriverToolbar);
        activity.setSupportActionBar(addDriverToolbar);

        addDriverRecyView = view.findViewById(R.id.AddDriverRecyclerView);

       return  view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
