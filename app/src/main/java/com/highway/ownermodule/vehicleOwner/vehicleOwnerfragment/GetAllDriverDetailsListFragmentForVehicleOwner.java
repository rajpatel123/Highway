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
import com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter.GetAllDriverDetailsListAdapterForVehicleOwner;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllDriverDetailsList.Data;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllDriverDetailsList.DriverDetail;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllDriverDetailsList.GetAllDriverRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllDriverDetailsList.GetAllDriverResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllDriverDetailsListFragmentForVehicleOwner extends Fragment {

    RecyclerView getAllDriverListRecyc;

    List<DriverDetail> driverDetails = new ArrayList<>();
    GetAllDriverDetailsListAdapterForVehicleOwner getAllDriverDetailsListAdapterForVehicleOwner;
    String userId;
    GetAllDriverResponse getAllDriverResponse;


    public GetAllDriverDetailsListFragmentForVehicleOwner() {
        // Required empty public constructor
    }


    public static GetAllDriverDetailsListFragmentForVehicleOwner newInstance() {
        GetAllDriverDetailsListFragmentForVehicleOwner fragment = new GetAllDriverDetailsListFragmentForVehicleOwner();
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
        View view = inflater.inflate(R.layout.fragment_get_all_driver_details_list_fragment_for_vehicle_owner, container, false);
        getAllDriverListRecyc = view.findViewById(R.id.getAllDriverListRecy);

      //  Adapter yhi  initialize krna tha kyunki starting me driverDetails data nhi hoga aur     recyclerOperation();  me check ga hai ki size 0 se greater ho tbhi set kre  iski vjh se hi jb api response ata hai to adapter null mil jata hai
        getAllDriverDetailsListAdapterForVehicleOwner = new GetAllDriverDetailsListAdapterForVehicleOwner(driverDetails,getContext());


        recyclerOperation();

        getAllDriverList();

        return view;
    }


    public void getAllDriverList(){
        GetAllDriverRequest getAllDriverRequest = new GetAllDriverRequest();
        userId  = HighwayPrefs.getString(getActivity(), Constants.ID);
        getAllDriverRequest.setOwnerId("5");
       /* getAllDriverRequest.setOwnerId(userId);*/

        RestClient.getAllDriverDetails(getAllDriverRequest, new Callback<GetAllDriverResponse>() {
            @Override
            public void onResponse(Call<GetAllDriverResponse> call, Response<GetAllDriverResponse> response) {
                if (response.body()!=null){
                    if (response.body().getStatus()){
                        getAllDriverResponse = response.body();
                        Data data = getAllDriverResponse.getData();
                        driverDetails= data.getDriverDetails();

                       if (getAllDriverDetailsListAdapterForVehicleOwner !=null){
                           getAllDriverDetailsListAdapterForVehicleOwner.setData(driverDetails);
                           getAllDriverDetailsListAdapterForVehicleOwner.notifyDataSetChanged();
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
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            getAllDriverListRecyc.setLayoutManager(layoutManager);
            getAllDriverListRecyc.setItemAnimator(new DefaultItemAnimator());
            getAllDriverListRecyc.setAdapter(getAllDriverDetailsListAdapterForVehicleOwner);

        }else{
            Toast.makeText(getActivity(), "Some thing is wrong", Toast.LENGTH_SHORT).show();
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
