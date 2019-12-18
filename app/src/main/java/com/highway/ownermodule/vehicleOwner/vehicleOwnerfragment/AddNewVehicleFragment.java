package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewVehicleModel.AddNewVehicleRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewVehicleModel.AddNewVehicleResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.DriverDropDown_Spinners.Data;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.DriverDropDown_Spinners.DriverDatum;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.DriverDropDown_Spinners.DriverDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.DriverDropDown_Spinners.DriverDropDownResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewVehicleFragment extends Fragment {

    RecyclerView addVehicleRecycView;
    Toolbar addVehicleToolbar;
    private EditText edtVehicleDescription, edtTxtVehicleNos, edtTxtvehicleModelNos, edtTxtvehicleName;
    private Button btnAddNewVehicle;
    private String vehicleName,vehicleModelNos,vehicleNos,vehicleDescription,userId;
    private Spinner driversSpinner;
    String textEd, txtEnd, isReached;
    String driverText;
    List<String> driverNames;
    String driverId;
    DriverDropDownResponse driverDropDownResponse;

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
        View view = inflater.inflate(R.layout.fragment_add_new_vehicle, container, false);

        edtTxtvehicleName = view.findViewById(R.id.EdtTxtvehicleName);
        edtTxtvehicleModelNos = view.findViewById(R.id.EdtTxtvehicleModelNos);
        edtTxtVehicleNos = view.findViewById(R.id.EdtTxtVehicleNos);
        btnAddNewVehicle = view.findViewById(R.id.BtnAddNewVehicle);
        driversSpinner = view.findViewById(R.id.DriversSpinner);
        edtVehicleDescription = view.findViewById(R.id.EdtVehicleDescription);
        driverList();
        clickListener();

        return view;

    }

    public void clickListener() {
        btnAddNewVehicle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidationAddNewVehicle();

            }
        });


        driversSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (driverDropDownResponse != null && driverDropDownResponse.getData() != null
                        && driverDropDownResponse.getData().getDriverData() != null
                        && driverDropDownResponse.getData().getDriverData().size() > 0) {

                    driverId = driverDropDownResponse.getData().getDriverData().get(position).getDriverId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public boolean inputValidation() {
        boolean check = false;

        vehicleName = edtTxtvehicleName.getText().toString().trim();
        vehicleModelNos = edtTxtvehicleModelNos.getText().toString().trim();
        vehicleNos = edtTxtVehicleNos.getText().toString().trim();
        vehicleDescription = edtVehicleDescription.getText().toString().trim();

        if (vehicleName.isEmpty() && edtTxtvehicleName.length() != 3) {
            edtTxtvehicleName.setError("pls enter valid vehicle name.. max length 3");
            check = false;
        } else {
            edtTxtvehicleName.setError(null);
            check = true;
        }

        if (vehicleNos.isEmpty() && edtTxtVehicleNos.length() != 3) {
            edtTxtVehicleNos.setError("pls enter valid vehicle number.. max length 3");
            check = false;
        } else {
            edtTxtVehicleNos.setError(null);
            check = true;
        }

        if (vehicleModelNos.isEmpty() && edtTxtVehicleNos.length() != 3) {
            edtTxtvehicleModelNos.setError("pls enter valid model number.. max length 3");
            check = false;
        } else {
            edtTxtvehicleModelNos.setError(null);
            check = true;
        }

        if (vehicleDescription.isEmpty() && edtVehicleDescription.length() != 10) {
            edtVehicleDescription.setError("pls enter valid vehicle name max length 3");
            check = false;
        } else {
            edtVehicleDescription.setError(null);
            check = true;
        }

        return check;
    }


    public void driverList() {

        DriverDropDownRequest driverDropDownRequest = new DriverDropDownRequest();
        userId = HighwayPrefs.getString(getActivity(), Constants.ID);
        Log.d("Id",userId);
        driverDropDownRequest.setUserId(userId);

        RestClient.getDriverList(driverDropDownRequest, new Callback<DriverDropDownResponse>() {
            @Override
            public void onResponse(Call<DriverDropDownResponse> call, Response<DriverDropDownResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        driverDropDownResponse = response.body();

                        Log.d("data",""+response.body());
                        Data data = driverDropDownResponse.getData();
                        DriverDatum driverDatum = new DriverDatum();
                        driverDatum.setDriverName("---Select Driver Name");
                        data.getDriverData().add(0, driverDatum);

                        if (data != null && data.getDriverData().size() > 0) {


                            driverNames = new ArrayList<>();
                            for (DriverDatum driverDatum1 : driverDropDownResponse.getData().getDriverData()) {
                                driverNames.add(driverDatum1.getDriverName());
                            }

                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, driverNames);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            driversSpinner.setAdapter(dataAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<DriverDropDownResponse> call, Throwable t) {
                Toast.makeText(getContext(), "Failure", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void ValidationAddNewVehicle() {

        if (inputValidation()) {
            AddNewVehicleRequest addNewVehicleRequest = new AddNewVehicleRequest();
            addNewVehicleRequest.setVehicleName(vehicleName);
            addNewVehicleRequest.setVehicleNumber(vehicleNos);
            addNewVehicleRequest.setVehicleModelNo(vehicleModelNos);
            addNewVehicleRequest.setVehicleDescription(vehicleDescription);
            userId = HighwayPrefs.getString(getActivity(),Constants.ID);
            addNewVehicleRequest.setOwnerId(userId);

            Utils.showProgressDialog(getActivity());
            RestClient.addNewVehicle(addNewVehicleRequest, new Callback<AddNewVehicleResponse>() {
                @Override
                public void onResponse(Call<AddNewVehicleResponse> call, Response<AddNewVehicleResponse> response) {
                    Utils.dismissProgressDialog();
                    if (response.body() != null) {
                        if (response.body().getStatus()) {
                            Intent intent = new Intent(getActivity(), DashBoardActivity.class);
                            startActivity(intent);
                            getActivity().finish();
                            Toast.makeText(getActivity(), "New vehicle added Successfully", Toast.LENGTH_SHORT).show();
                        }
                    }
                }

                @Override
                public void onFailure(Call<AddNewVehicleResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Faield Add Vehicle", Toast.LENGTH_SHORT).show();
                }
            });
        }
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
