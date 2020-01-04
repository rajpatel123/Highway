package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.highway.R;

public class Assign_D2V_FragmentForVehicleOwner extends Fragment {

    public Assign_D2V_FragmentForVehicleOwner() {
        // Required empty public constructor
    }


    public static Assign_D2V_FragmentForVehicleOwner newInstance(String param1, String param2) {
        Assign_D2V_FragmentForVehicleOwner fragment = new Assign_D2V_FragmentForVehicleOwner();
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
        return inflater.inflate(R.layout.fragment_assign__d2_v__fragment_for_vehicle_owner, container, false);
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
