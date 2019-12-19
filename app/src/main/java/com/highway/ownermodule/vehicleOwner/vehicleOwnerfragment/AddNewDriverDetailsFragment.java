package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewDriverThroughVehicleOwner.AddNewDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.AddNewDriverThroughVehicleOwner.AddNewDriverResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.VehicleDropDown_Spinners.Data;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.VehicleDropDown_Spinners.VehicleDatum;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.VehicleDropDown_Spinners.VehicleDropDownRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.VehicleDropDown_Spinners.VehicleDropDownResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddNewDriverDetailsFragment extends Fragment {
    RecyclerView addDriverRecyView;
    private Toolbar addDriverToolbar;
    public EditText edtTxtDriverName, edtTxtDriverMobNo, edtTxtDriverEmail,
            edtTxtDriverAddress, edtTxtDlNumber, edtTxtDlExpireDate;
    public ImageView imgDatePicker;
    public Button btnAddNewDriver;
    String driverName, driverEmail, driverMobNo, driverDlNo, dlExpireDate, driverAddress ,user_Id;
    private DatePickerDialog datePickerDialog;
    private Activity view;
    private Spinner vehicleSpinners;
    VehicleDropDownResponse vehicleDropDownResponse;
    Data data;
    private String vehicleId;
    List<String> vehicleNames;
    private String userId;

    public AddNewDriverDetailsFragment() {
        // Required empty public constructor
    }

    public static AddNewDriverDetailsFragment newInstance() {
        AddNewDriverDetailsFragment fragment = new AddNewDriverDetailsFragment();
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
        edtTxtDriverEmail = view.findViewById(R.id.EdtTxtDriverEmailNos);
        edtTxtDlNumber = view.findViewById(R.id.EdtTxtDlNumber);
        edtTxtDlExpireDate = view.findViewById(R.id.EdtTxtDlExpireDate);
        imgDatePicker = view.findViewById(R.id.ImgDatePicker);
        vehicleSpinners = view.findViewById(R.id.VehicleSpinner);
        edtTxtDriverAddress = view.findViewById(R.id.EdtTxtEnterDriverAdd);
        btnAddNewDriver = view.findViewById(R.id.BtnAddNewDriver);
        vehicleSpinnersList();
        clickListener();
        return view;
    }

    public void clickListener() {

        btnAddNewDriver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidationAddDriver();

            }
        });


        vehicleSpinners.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                Toast.makeText(view, "Nothing Show DataModel", Toast.LENGTH_SHORT).show();

            }
        });
        imgDatePicker.setOnClickListener(new View.OnClickListener() {
            private int mYear, mMonth, mDay;

            @Override
            public void onClick(View v) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        datePickerDialog.getDatePicker().setMinDate(c.getTimeInMillis());
                        //dobDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                        edtTxtDlExpireDate.setText(year + "-" + (monthOfYear + 1) + "-" + dayOfMonth);
                    }
                }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }


    public boolean inputValidation() {
        boolean check = true;

        driverName = edtTxtDriverName.getText().toString().trim();
        driverMobNo = edtTxtDriverMobNo.getText().toString().trim();
        driverEmail = edtTxtDriverEmail.getText().toString().trim();
        driverDlNo = edtTxtDlNumber.getText().toString().trim();
        dlExpireDate = edtTxtDlExpireDate.getText().toString().trim();
        driverAddress = edtTxtDriverAddress.getText().toString().trim();

        if (driverName.isEmpty() && edtTxtDriverName.length() != 3) {
            edtTxtDriverName.setError("pls enter name");
            check = false;
        } else {
            edtTxtDriverName.setError(null);
            check = true;
        }

        if (driverMobNo.isEmpty() && edtTxtDriverMobNo.length() != 10) {
            edtTxtDriverMobNo.setError("pls Enter valid mobile number");
            check = false;
        } else {
            edtTxtDriverMobNo.setError(null);
            check = true;
        }

        if (driverDlNo.isEmpty() && edtTxtDlNumber.length() != 16) {
            edtTxtDlNumber.setError("pls Enter valid dl number");
            check = false;
        } else {
            edtTxtDlNumber.setError(null);
            check = true;
        }

        if (dlExpireDate.isEmpty()) {
            edtTxtDlExpireDate.setError("pls Enter valid dl expiry date in format yy-mm-dd ");
            check = false;
        } else {
            edtTxtDlNumber.setError(null);
            check = true;
        }

       /* if (TextUtils.isEmpty(vehicleSpinners)) {
            Utils.displayToast(getContext(), "Please select state");
         check =false;
        }else{
        }*/

        if (driverAddress.isEmpty()) {
            edtTxtDriverAddress.setError("pls Enter valid dl number");
            check = false;
        } else {
            edtTxtDriverAddress.setError(null);
            check = true;
        }
        return check;

    }

    public void vehicleSpinnersList() {

        VehicleDropDownRequest vehicleDropDownRequest = new VehicleDropDownRequest();
        user_Id = HighwayPrefs.getString(getActivity(),Constants.ID);
        /*vehicleDropDownRequest.setUserId(user_Id);*/
        vehicleDropDownRequest.setUserId("5");

        RestClient.getVehicleList(vehicleDropDownRequest, new Callback<VehicleDropDownResponse>() {
            @Override
            public void onResponse(Call<VehicleDropDownResponse> call, Response<VehicleDropDownResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        vehicleDropDownResponse=response.body();
                        Data data = vehicleDropDownResponse.getData();
                        VehicleDatum vehicleDatum = new VehicleDatum();
                        vehicleDatum.setVehicleName("--Select Vehicle--");
                        data.getVehicleData().add(0, vehicleDatum);

                        if (data != null && data.getVehicleData().size() > 0) {

                            vehicleNames = new ArrayList<>();

                            for (VehicleDatum vehicleDatum1 : vehicleDropDownResponse.getData().getVehicleData()) {
                                vehicleNames.add(vehicleDatum1.getVehicleName());
                            }
                            ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, vehicleNames);
                            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            vehicleSpinners.setAdapter(dataAdapter);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<VehicleDropDownResponse> call, Throwable t) {
                Toast.makeText(view, "Failure", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void ValidationAddDriver() {
        if (inputValidation()) {

            AddNewDriverRequest addNewDriverRequest = new AddNewDriverRequest();
            addNewDriverRequest.setDriverName(driverName);
            addNewDriverRequest.setDriverMobile(driverMobNo);
            addNewDriverRequest.setDriverEmail(driverEmail);
            addNewDriverRequest.setDriverDLNo(driverDlNo);
            addNewDriverRequest.setDlexpiryDate(dlExpireDate);
            addNewDriverRequest.setDriverAddress(driverAddress);
            addNewDriverRequest.setVehicleId(vehicleId);
            userId = HighwayPrefs.getString(getActivity(),Constants.ID);
            /*addNewDriverRequest.setOwnerId("5");*/
            addNewDriverRequest.setOwnerId(userId);

            Utils.showProgressDialog(getActivity());

            RestClient.addNewDriver(addNewDriverRequest, new Callback<AddNewDriverResponse>() {
                @Override
                public void onResponse(Call<AddNewDriverResponse> call, Response<AddNewDriverResponse> response) {
                    Utils.dismissProgressDialog();

                    if (response.body() != null) {
                        if (response.body().getStatus()) {
                           // if (response.body().getId()) {
                                Intent intent = new Intent(getActivity(), DashBoardActivity.class);
                                startActivity(intent);
                                getActivity().finish();
                                Toast.makeText(getActivity(), "Add new Driver SuccessFully", Toast.LENGTH_SHORT).show();

                           // }
                        }
                    }
                }

                @Override
                public void onFailure(Call<AddNewDriverResponse> call, Throwable t) {
                    Toast.makeText(getActivity(), "Add new Driver Failed ", Toast.LENGTH_SHORT).show();
                }
            });
        }
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
