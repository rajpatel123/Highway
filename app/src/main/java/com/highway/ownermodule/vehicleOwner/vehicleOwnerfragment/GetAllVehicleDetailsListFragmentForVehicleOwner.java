package com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.commonretrofit.RestClient;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter.GetAllVehicleDetailsListAdapterForVehicleOwner;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllVehicleDetailsList.DataVehicle;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllVehicleDetailsList.GetAllVehicleDetailsRequest;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllVehicleDetailsList.GetAllVehicleDetailsResponse;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllVehicleDetailsList.VehicleDetail;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GetAllVehicleDetailsListFragmentForVehicleOwner extends Fragment {
    Toolbar getAllVehicleToolbar;
    RecyclerView getAllVehicleRecyclerView;

    List<VehicleDetail> vehicleDetails = new ArrayList<>();
    GetAllVehicleDetailsListFragmentForVehicleOwner getAllVehicleDetailsListFragmentForVehicleOwner;
    GetAllVehicleDetailsListAdapterForVehicleOwner getAllVehicleDetailsListAdapterForVehicleOwner;
    String userId;
    GetAllVehicleDetailsResponse getAllVehicleDetailsResponse;

    public GetAllVehicleDetailsListFragmentForVehicleOwner() {
        // Required empty public constructor
    }


    public static GetAllVehicleDetailsListFragmentForVehicleOwner newInstance() {
        GetAllVehicleDetailsListFragmentForVehicleOwner fragment = new GetAllVehicleDetailsListFragmentForVehicleOwner();
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
        View view = inflater.inflate(R.layout.fragment_get_all_vehicle_details_list_fragment_for_vehicle_owner, container, false);
        getAllVehicleRecyclerView = view.findViewById(R.id.GetAllVehicleRecyclerView);

        getAllVehicleDetailsListAdapterForVehicleOwner = new GetAllVehicleDetailsListAdapterForVehicleOwner(vehicleDetails, getContext());

        getAllVehicle();
        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
        recyclerOperation();
    }

    public void recyclerOperation() {

        if (vehicleDetails != null && vehicleDetails.size() > 0) {
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
            getAllVehicleRecyclerView.setLayoutManager(layoutManager);
            getAllVehicleRecyclerView.setItemAnimator(new DefaultItemAnimator());
            getAllVehicleRecyclerView.setAdapter(getAllVehicleDetailsListAdapterForVehicleOwner);

        }
    }

    public void getAllVehicle() {
        GetAllVehicleDetailsRequest getAllVehicleDetailsRequest = new GetAllVehicleDetailsRequest();
        userId = HighwayPrefs.getString(getContext(), Constants.ID);

        Log.d("UserId",""+userId);

        getAllVehicleDetailsRequest.setOwnerId("5");
        /*getAllVehicleDetailsRequest.setOwnerId(userId);*/

        RestClient.getAllVehicleDetails(getAllVehicleDetailsRequest, new Callback<GetAllVehicleDetailsResponse>() {
            @Override
            public void onResponse(Call<GetAllVehicleDetailsResponse> call, Response<GetAllVehicleDetailsResponse> response) {

                if (response.body()!=null){
                    if (response.body().getStatus()){
                        getAllVehicleDetailsResponse = response.body();
                        DataVehicle data = getAllVehicleDetailsResponse.getData();
                        vehicleDetails= data.getVehicleDetails();

                        if (vehicleDetails.size()>0){
                            if (getAllVehicleDetailsListAdapterForVehicleOwner !=null){
                                getAllVehicleDetailsListAdapterForVehicleOwner.setData(vehicleDetails);
                                getAllVehicleDetailsListAdapterForVehicleOwner.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(getActivity(), "No vehicle list found", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<GetAllVehicleDetailsResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "No vehicle list found", Toast.LENGTH_SHORT).show();
            }
        });

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
