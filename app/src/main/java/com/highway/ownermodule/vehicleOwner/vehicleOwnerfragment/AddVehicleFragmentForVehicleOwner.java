package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addVehicleModel.AddVehicleRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addVehicleModel.AddVehicleResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleTypeDropDowan.Data;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.assignD2V.driverAssignSpinner.DriverDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleTypeDropDowan.VehicleDatum;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleTypeDropDowan.VehicleTypeDropDowanRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.vehicleTypeDropDowan.VehicleTypeDropDowanResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddVehicleFragmentForVehicleOwner extends Fragment {

    RecyclerView addVehicleRecycView;
    Toolbar addVehicleToolbar;
    private EditText edtVehicleDescription, edtTxtVehicleNos, edtTxtvehicleModelNos, edtTxtvehicleName,
            edtTxtVehileLoadCapicity,edtTxtVehicleSize;
    private Button btnAddNewVehicle;
    private String vehicleName,vehicleModelNos,vehicleNos,vehicleDescription,user_Id;
    public  String vehileLoadCapicity,vehicleSize;
    private Spinner driversSpinner;
    public  Spinner vehiclesTypeSpinner;
    String textEd, txtEnd, isReached;
    String driverText;
    List<String> driverNames;
    List<String> vehicleNames;
    String driverId;
    String vehicleTypeId;
    DriverDropDownResponse driverDropDownResponse;
    VehicleTypeDropDowanResponse vehicleTypeDropDowanResponse;

    public AddVehicleFragmentForVehicleOwner() {
        // Required empty public constructor
    }


    public static AddVehicleFragmentForVehicleOwner newInstance() {
        AddVehicleFragmentForVehicleOwner fragment = new AddVehicleFragmentForVehicleOwner();
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

        //edtTxtvehicleName = view.findViewById(R.id.EdtTxtvehicleName);
        edtTxtvehicleModelNos = view.findViewById(R.id.EdtTxtvehicleModelNos);
        edtTxtVehicleNos = view.findViewById(R.id.EdtTxtVehicleNos);
        btnAddNewVehicle = view.findViewById(R.id.BtnAddNewVehicle);
        vehiclesTypeSpinner = view.findViewById(R.id.VehicleTypeSpinner);
        edtVehicleDescription = view.findViewById(R.id.EdtVehicleDescription);
        edtTxtVehileLoadCapicity = view.findViewById(R.id.EdtTxtVehileLoadCapicity);
        edtTxtVehicleSize = view.findViewById(R.id.EdtTxtVehicleSize);
        vehicleSpinnersList();
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

        vehiclesTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if (vehicleTypeDropDowanResponse != null && vehicleTypeDropDowanResponse.getData()!=null
                        &&  vehicleTypeDropDowanResponse.getData().getVehicleData()!=null
                        && vehicleTypeDropDowanResponse.getData().getVehicleData().size() > 0) {

                    vehicleTypeId = vehicleTypeDropDowanResponse.getData().getVehicleData().get(position).getVehicleTypeId();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(getActivity(), "Nothing Show DataModel", Toast.LENGTH_SHORT).show();

            }
        });




    }

    public boolean inputValidation() {
        boolean check = false;

        //vehicleName = edtTxtvehicleName.getText().toString().trim();
        vehicleModelNos = edtTxtvehicleModelNos.getText().toString().trim();
        vehicleNos = edtTxtVehicleNos.getText().toString().trim();
        vehicleDescription = edtVehicleDescription.getText().toString().trim();
        vehileLoadCapicity = edtTxtVehileLoadCapicity.getText().toString().trim();
        vehicleSize = edtTxtVehicleSize.getText().toString().trim();

       /* if (vehicleName.isEmpty() && edtTxtvehicleName.length() != 3) {
            edtTxtvehicleName.setError("pls enter valid vehicle name.. max length 3");
            check = false;
        } else {
            edtTxtvehicleName.setError(null);
            check = true;
        }
*/
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

        if (vehileLoadCapicity.isEmpty()){
            edtTxtVehileLoadCapicity.setError("pls enter loading capicity");
            check= false;
        }else {
            edtTxtVehileLoadCapicity.setError(null);
            check = true;
        }
        if (vehicleSize.isEmpty()){
            edtTxtVehicleSize.setError("pls enter valid vehicle size in length X weidhth X height ");
            check= false;
        }else {
            edtTxtVehicleSize.setError(null);
            check = true;
        }

        if (vehicleDescription.isEmpty() && edtVehicleDescription.length()<=10) {
            edtVehicleDescription.setError("pls enter valid vehicle description");
            check = false;
        } else {
            edtVehicleDescription.setError(null);
            check = true;
        }

        return check;
    }

    public void vehicleSpinnersList() {

        VehicleTypeDropDowanRequest vehicleTypeDropDowanRequest  = new VehicleTypeDropDowanRequest();
        user_Id = HighwayPrefs.getString(getActivity(),Constants.ID);
        vehicleTypeDropDowanRequest.setUserId(user_Id);
        /* vehicleDropDownRequest.setUserId("5");*/

        RestClient.getVehicleTypeList(vehicleTypeDropDowanRequest, new Callback<VehicleTypeDropDowanResponse>() {
            @Override
            public void onResponse(Call<VehicleTypeDropDowanResponse> call, Response<VehicleTypeDropDowanResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        vehicleTypeDropDowanResponse=response.body();
                        Data data = vehicleTypeDropDowanResponse.getData();
                        VehicleDatum vehicleDatum = new VehicleDatum();
                        vehicleDatum.setVehicleName("-- Select Vehicle Type --");
                        data.getVehicleData().add(0, vehicleDatum);

                        if (data != null && data.getVehicleData().size() > 0) {

                            vehicleNames = new ArrayList<>();

                            for (VehicleDatum vehicleDatum1 : vehicleTypeDropDowanResponse.getData().getVehicleData()) {
                                vehicleNames.add(vehicleDatum1.getVehicleName());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, vehicleNames);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            vehiclesTypeSpinner.setAdapter(dataAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<VehicleTypeDropDowanResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void ValidationAddNewVehicle() {

        if (inputValidation()) {
            AddVehicleRequest addVehicleRequest = new AddVehicleRequest();
            addVehicleRequest.setVehicleNumber(vehicleNos);
            addVehicleRequest.setVehicleModelNo(vehicleModelNos);
            addVehicleRequest.setVehicleCapacity(vehileLoadCapicity);
            addVehicleRequest.setVehicleSize(vehicleSize);
            addVehicleRequest.setVehicleDescription(vehicleDescription);
            addVehicleRequest.setVehicleTypeId(vehicleTypeId);
            user_Id= HighwayPrefs.getString(getActivity(),Constants.ID);
            addVehicleRequest.setOwnerId(user_Id);
            if (Utils.isInternetConnected(getActivity())) {

                Utils.showProgressDialog(getActivity());
                RestClient.addNewVehicle(addVehicleRequest, new Callback<AddVehicleResponse>() {
                    @Override
                    public void onResponse(Call<AddVehicleResponse> call, Response<AddVehicleResponse> response) {
                        Utils.dismissProgressDialog();
                        if (response.body() != null) {
                            if (response.body().getStatus()) {
                                //if (response.body().getId())
                                Intent intent = new Intent(getActivity(), DashBoardActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                                Toast.makeText(getActivity(), "Vehicle added Successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<AddVehicleResponse> call, Throwable t) {
                        Toast.makeText(getActivity(), "Faield Add Vehicle", Toast.LENGTH_SHORT).show();
                    }
                });
            }
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
