package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.highway.customer.customerFragment.DashBordFragmentForCustomer;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewDriverThroughVehicleOwner.AddNewDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.addNewDriverThroughVehicleOwner.AddNewDriverResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class AddDriverFragmentForVehicleOwner extends Fragment {
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
    private String vehicleId;
    List<String> vehicleNames;
    private String userId;
    private DashBoardFragmentForVehicleOwner dashBoardFragmentForVehicleOwner;

    public AddDriverFragmentForVehicleOwner() {
        // Required empty public constructor
    }

    public static AddDriverFragmentForVehicleOwner newInstance() {
        AddDriverFragmentForVehicleOwner fragment = new AddDriverFragmentForVehicleOwner();
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
       // vehicleSpinners = view.findViewById(R.id.VehicleSpinner);
        edtTxtDriverAddress = view.findViewById(R.id.EdtTxtEnterDriverAdd);
        btnAddNewDriver = view.findViewById(R.id.BtnAddNewDriver);
      //  vehicleSpinnersList();
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



    public void ValidationAddDriver() {
        if (inputValidation()) {

            AddNewDriverRequest addNewDriverRequest = new AddNewDriverRequest();
            addNewDriverRequest.setDriverName(driverName);
            addNewDriverRequest.setDriverMobile(driverMobNo);
            addNewDriverRequest.setDriverEmail(driverEmail);
            addNewDriverRequest.setDriverDLNo(driverDlNo);
            addNewDriverRequest.setDlexpiryDate(dlExpireDate);
            addNewDriverRequest.setDriverAddress(driverAddress);
          //  addNewDriverRequest.setVehicleId(vehicleId);
            userId = HighwayPrefs.getString(getActivity(),Constants.ID);
            addNewDriverRequest.setOwnerId(userId);

            Utils.showProgressDialog(getActivity());

            RestClient.addNewDriver(addNewDriverRequest, new Callback<AddNewDriverResponse>() {
                @Override
                public void onResponse(Call<AddNewDriverResponse> call, Response<AddNewDriverResponse> response) {
                    Utils.dismissProgressDialog();

                    if (response.body() != null) {
                        if (response.body().getStatus()) {
                          // if (response.body().getId()) {
                            dashBoardFragmentForVehicleOwner = DashBoardFragmentForVehicleOwner.newInstance();
                            Bundle bundle = new Bundle();
                            dashBoardFragmentForVehicleOwner.setArguments(bundle);
                            ((DashBoardActivity) getActivity()).replaceFragment(dashBoardFragmentForVehicleOwner, " ");
                                //Intent intent = new Intent(getActivity(), DashBoardActivity.class);
                                //startActivity(intent);
                              //  getActivity().finish();
                                Toast.makeText(getActivity(), "Add new Driver SuccessFully", Toast.LENGTH_SHORT).show();

                            //}
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
