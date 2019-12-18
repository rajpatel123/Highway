package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.commonretrofit.RestClient;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter.GetAllDriverListAdapterForVehicleOwner;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter.UpcomingTripAdapterForVehicleOwner;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.DriverDropDown_Spinners.DriverDropDownResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllDriverList.Data;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllDriverList.DriverDetail;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllDriverList.GetAllDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllDriverList.GetAllDriverResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllDriverListFragmentForVehicleOwner extends Fragment {

    RecyclerView getAllDriverListRecyc;

    List<DriverDetail>driverDetails = new ArrayList<>();
    GetAllDriverListAdapterForVehicleOwner getAllDriverListAdapterForVehicleOwner;
    String userId;
    GetAllDriverResponse getAllDriverResponse;


    public GetAllDriverListFragmentForVehicleOwner() {
        // Required empty public constructor
    }


    public static GetAllDriverListFragmentForVehicleOwner newInstance() {
        GetAllDriverListFragmentForVehicleOwner fragment = new GetAllDriverListFragmentForVehicleOwner();
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
        View view = inflater.inflate(R.layout.fragment_get_all_driver_list_fragment_for_vehicle_owner, container, false);
        getAllDriverListRecyc = view.findViewById(R.id.getAllDriverListRecy);


        getAllDriverList();
        recyclerOperation();



        return view;
    }


    public void getAllDriverList(){
        GetAllDriverRequest getAllDriverRequest = new GetAllDriverRequest();
        userId  = HighwayPrefs.getString(getActivity(), Constants.ID);
        getAllDriverRequest.setOwnerId(userId);

        RestClient.getAllDriverDetails(getAllDriverRequest, new Callback<GetAllDriverResponse>() {
            @Override
            public void onResponse(Call<GetAllDriverResponse> call, Response<GetAllDriverResponse> response) {
                if (response.body()!=null){
                    if (response.body().getStatus()){
                        getAllDriverResponse = response.body();
                        Data data = getAllDriverResponse.getData();
                        driverDetails= data.getDriverDetails();

                       if (getAllDriverListAdapterForVehicleOwner!=null){
                           getAllDriverListAdapterForVehicleOwner.setData(driverDetails);
                           getAllDriverListAdapterForVehicleOwner.notifyDataSetChanged();
                       }
                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllDriverResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();

            }
        });
    }


    public void recyclerOperation(){
        if (driverDetails !=null && driverDetails.size()>0){
            getAllDriverListAdapterForVehicleOwner = new GetAllDriverListAdapterForVehicleOwner(driverDetails,getContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            getAllDriverListRecyc.setLayoutManager(layoutManager);
            getAllDriverListRecyc.setItemAnimator(new DefaultItemAnimator());
            getAllDriverListRecyc.setAdapter(getAllDriverListAdapterForVehicleOwner);
        }else{
            Toast.makeText(getActivity(), "No Driver data found by this user id ", Toast.LENGTH_SHORT).show();
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
