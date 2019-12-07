package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.net.Uri;
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

public class GetAllVehicleFragmentForVehicleOwner extends Fragment {
    Toolbar getAllVehicleToolbar;
    RecyclerView getAllVehicleRecyclerView;

    public GetAllVehicleFragmentForVehicleOwner() {
        // Required empty public constructor
    }


    public static GetAllVehicleFragmentForVehicleOwner newInstance(String param1, String param2) {
        GetAllVehicleFragmentForVehicleOwner fragment = new GetAllVehicleFragmentForVehicleOwner();
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
        View view=  inflater.inflate(R.layout.fragment_get_all_vehicle_fragment_for_vehicle_owner, container, false);

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        getAllVehicleToolbar =  view.findViewById(R.id.GetAllVehicleToolbar);
        activity.setSupportActionBar(getAllVehicleToolbar);

        getAllVehicleRecyclerView = view.findViewById(R.id.GetAllVehicleRecyclerView);

     return view;
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
