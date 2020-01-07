package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.assignD2V_Model.AssignD2VRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.assignD2V_Model.AssignD2VResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.driverAssignSpinner.DataModel;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.driverAssignSpinner.DriverDatum;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.driverAssignSpinner.DriverDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.driverAssignSpinner.DriverDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner.DataV;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner.VehicleDatum;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner.VehicleDropDowanRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.vehicleAssignSpinner.VehicleDropDowanResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Assign_D2V_FragmentForVehicleOwner extends Fragment {

    public Spinner driverSpinner;
    public Button btnAssignD2V;
    private String userId;
    private String ownerId;
    private String user_Id;
    DriverDropDownResponse driverDropDownResponse;
    List<String> vehicleNames;
    List<String> driverNames;
    private Spinner driversSpinner;
    private Spinner vehiclesSpinner;
    String driverId;
    VehicleDropDowanResponse vehicleDropDownResponse;
    String vehicleId;

    public Assign_D2V_FragmentForVehicleOwner() {
        // Required empty public constructor
    }


    public static Assign_D2V_FragmentForVehicleOwner newInstance() {
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
        View view = inflater.inflate(R.layout.fragment_assign__d2_v__fragment_for_vehicle_owner, container, false);

        driversSpinner = view.findViewById(R.id.DriversSpinner);
        vehiclesSpinner = view.findViewById(R.id.vehicleTypeSpinner);
        btnAssignD2V = view.findViewById(R.id.BtnAssignD2V);


        //getdriverListSpinner();
        getVehicleListSpinner();

        clickListener();

        return view;
    }

    public void clickListener() {
        btnAssignD2V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                assignD2V();
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
                Toast.makeText(getActivity(), "NO! Item  in this fields", Toast.LENGTH_SHORT).show();

            }
        });


        vehiclesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (vehicleDropDownResponse != null && vehicleDropDownResponse.getData() != null
                        && vehicleDropDownResponse.getData().getVehicleData() != null
                        && vehicleDropDownResponse.getData().getVehicleData().size() > 0) {

                    vehicleId = vehicleDropDownResponse.getData().getVehicleData().get(position).getVehicleId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Nothing Show DataModel", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void getdriverListSpinner() {

        DriverDropDownRequest driverDropDownRequest = new DriverDropDownRequest();
        user_Id = HighwayPrefs.getString(getActivity(), Constants.ID);
        Log.d("Id", user_Id);
        driverDropDownRequest.setUserId(user_Id);

        RestClient.getDriverList(driverDropDownRequest, new Callback<DriverDropDownResponse>() {
            @Override
            public void onResponse(Call<DriverDropDownResponse> call, Response<DriverDropDownResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        driverDropDownResponse = response.body();

                        Log.d("data", "" + response.body());
                        DataModel data = driverDropDownResponse.getData();
                        DriverDatum driverDatum = new DriverDatum();
                        driverDatum.setDriverName("--- Select Driver Name ---");
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

    public void getVehicleListSpinner() {

        VehicleDropDowanRequest vehicleDropDowanRequest = new VehicleDropDowanRequest();
        user_Id = HighwayPrefs.getString(getActivity(), Constants.ID);
        Log.d("Id", user_Id);
        vehicleDropDowanRequest.setUserId(user_Id);

        RestClient.getVehicleList(vehicleDropDowanRequest, new Callback<VehicleDropDowanResponse>() {
            @Override
            public void onResponse(Call<VehicleDropDowanResponse> call, Response<VehicleDropDowanResponse> response) {

                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        vehicleDropDownResponse = response.body();

                        Log.d("data", "" + response.body());
                        DataV data = vehicleDropDownResponse.getData();
                        VehicleDatum vehicleDatum = new VehicleDatum();
                        vehicleDatum.setVehicleName("--- Select Vehicle Name ---");
                        data.getVehicleData().add(0, vehicleDatum);

                        if (data != null && data.getVehicleData().size() > 0) {

                            vehicleNames = new ArrayList<>();
                            for (VehicleDatum vehicleDatum1 : vehicleDropDownResponse.getData().getVehicleData()) {
                                vehicleNames.add(vehicleDatum1.getVehicleName());
                            }

                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, vehicleNames);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            vehiclesSpinner.setAdapter(dataAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<VehicleDropDowanResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure! No Vehicle Name found", Toast.LENGTH_SHORT).show();

            }
        });

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public void assignD2V() {
        AssignD2VRequest assignD2VRequest = new AssignD2VRequest();
        assignD2VRequest.setDriverId(driverId);
        assignD2VRequest.setVehicleId(vehicleId);
        ownerId = HighwayPrefs.getString(getActivity(), Constants.ID);
        Log.d("Id", ownerId);
        assignD2VRequest.setOwnerId(ownerId);
        Utils.showProgressDialog(getContext());
        RestClient.assign_D2V(assignD2VRequest, new Callback<AssignD2VResponse>() {
            @Override
            public void onResponse(Call<AssignD2VResponse> call, Response<AssignD2VResponse> response) {
                Utils.dismissProgressDialog();
                if (response.body() != null) {
                    if (response.body().getStatus()) {

                        Intent intent = new Intent(getActivity(), DashBoardActivity.class);
                        startActivity(intent);
                        getActivity().finish();
                        Toast.makeText(getActivity(), "Assign Driver to Vehicle SuccessFully", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<AssignD2VResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "failure", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}
