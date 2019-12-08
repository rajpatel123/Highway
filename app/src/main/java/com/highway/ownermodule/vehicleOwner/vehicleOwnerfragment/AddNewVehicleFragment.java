package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.highway.R;

public class AddNewVehicleFragment extends Fragment {

    RecyclerView addVehicleRecycView;
    Toolbar addVehicleToolbar;
    private EditText edtVehicleDescription,edtTxtVehicleNos,edtTxtvehicleModelNos,edtTxtvehicleName;
    private Button btnAddNewVehicle;

    public AddNewVehicleFragment() {
        // Required empty public constructor
    }


    public static AddNewVehicleFragment newInstance() {
        AddNewVehicleFragment fragment = new AddNewVehicleFragment();
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
        View view =  inflater.inflate(R.layout.fragment_add_new_vehicle, container, false);

        edtTxtvehicleName = view.findViewById(R.id.EdtTxtvehicleName);
        edtTxtvehicleModelNos = view.findViewById(R.id.EdtTxtvehicleModelNos);
        edtTxtVehicleNos = view.findViewById(R.id.EdtTxtVehicleNos);
        edtVehicleDescription = view.findViewById(R.id.EdtVehicleDescription);
        btnAddNewVehicle = view.findViewById(R.id.BtnAddNewVehicle);

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
