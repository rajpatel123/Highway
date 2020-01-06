package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.highway.R;
import com.highway.commonretrofit.RestClient;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V_Spinner.AssignD2VRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.driverDropDown_Spinners.DataModel;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.driverDropDown_Spinners.DriverDatum;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.driverDropDown_Spinners.DriverDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.driverDropDown_Spinners.DriverDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleDropDown_Spinners.Data;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleDropDown_Spinners.VehicleDatum;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleDropDown_Spinners.VehicleDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleDropDown_Spinners.VehicleDropDownResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

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
    List<String>driverNames;
    private Spinner driversSpinner;
    private Spinner vehiclesSpinner;
    String driverId;
    VehicleDropDownResponse vehicleDropDownResponse;
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


        getdriverListSpinner();
        getVehicleListSpinner();

       clickListener();

        return view;
    }

    public void clickListener(){
        btnAssignD2V.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickAssignD2V();
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

                if (vehicleDropDownResponse != null && vehicleDropDownResponse.getData()!=null
                        &&  vehicleDropDownResponse.getData().getVehicleData()!=null
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


    public void getdriverListSpinner(){

        DriverDropDownRequest driverDropDownRequest = new DriverDropDownRequest();
        user_Id = HighwayPrefs.getString(getActivity(), Constants.ID);
        Log.d("Id",user_Id);
        driverDropDownRequest.setUserId(user_Id);

        RestClient.getDriverList(driverDropDownRequest, new Callback<DriverDropDownResponse>() {
            @Override
            public void onResponse(Call<DriverDropDownResponse> call, Response<DriverDropDownResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        driverDropDownResponse = response.body();

                        Log.d("data",""+response.body());
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
        VehicleDropDownRequest vehicleDropDownRequest = new VehicleDropDownRequest();
        user_Id = HighwayPrefs.getString(getActivity(),Constants.ID);
        vehicleDropDownRequest.setUserId(user_Id);
        /* vehicleDropDownRequest.setUserId("5");*/

        RestClient.getVehicleList(vehicleDropDownRequest, new Callback<VehicleDropDownResponse>() {
            @Override
            public void onResponse(Call<VehicleDropDownResponse> call, Response<VehicleDropDownResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        vehicleDropDownResponse=response.body();
                        Data data = vehicleDropDownResponse.getData();
                        VehicleDatum vehicleDatum = new VehicleDatum();
                        vehicleDatum.setVehicleName("-- Select Vehicle --");
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
            public void onFailure(Call<VehicleDropDownResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();

            }
        });


    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    public void clickAssignD2V() {
        AssignD2VRequest assignD2VRequest = new AssignD2VRequest();
        ownerId = HighwayPrefs.getString(getActivity(), Constants.ID);
        Log.d("Id",ownerId);
        assignD2VRequest.setOwnerId(ownerId);



    }


    @Override
    public void onDetach() {
        super.onDetach();
    }


}
