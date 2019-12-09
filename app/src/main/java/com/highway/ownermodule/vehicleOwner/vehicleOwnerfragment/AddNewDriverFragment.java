package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


import com.highway.R;


public class AddNewDriverFragment extends Fragment {
    RecyclerView addDriverRecyView;
    private Toolbar addDriverToolbar;
    public EditText edtTxtDriverName, edtTxtDriverMobNo, edtTxtDriverEmailNos, edtTxtDriverAddress, edtTxtDlNumber;
    public Button btnAddNewDriver;
    private Activity view;

    public AddNewDriverFragment() {
        // Required empty public constructor
    }

    public static AddNewDriverFragment newInstance() {
        AddNewDriverFragment fragment = new AddNewDriverFragment();
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
        View view = inflater.inflate(R.layout.fragment_add_new_driver, container, false);

        edtTxtDriverName = view.findViewById(R.id.EdtTxtDriverName);
        edtTxtDriverMobNo = view.findViewById(R.id.EdtTxtDriverMobNo);
        edtTxtDriverEmailNos = view.findViewById(R.id.EdtTxtDriverEmailNos);
        edtTxtDriverAddress = view.findViewById(R.id.EdtTxtEnterDriverAdd);
        edtTxtDlNumber = view.findViewById(R.id.EdtTxtDlNumber);
        btnAddNewDriver = view.findViewById(R.id.BtnAddNewDriver);
        btnAddNewDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                inputDriverDetailsValidation();
            }
        });
        return view;
    }

    public void inputDriverDetailsValidation() {

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
