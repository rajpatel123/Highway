package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;

public class GetAllVehicleListFragmentForVehicleOwner extends Fragment {
    Toolbar getAllVehicleToolbar;
    RecyclerView getAllVehicleRecyclerView;

    public GetAllVehicleListFragmentForVehicleOwner() {
        // Required empty public constructor
    }


    public static GetAllVehicleListFragmentForVehicleOwner newInstance() {
        GetAllVehicleListFragmentForVehicleOwner fragment = new GetAllVehicleListFragmentForVehicleOwner();
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
