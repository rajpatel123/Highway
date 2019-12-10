package com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;

public class GetAllVehicleAdapterForVehicleOwner extends RecyclerView.Adapter<GetAllVehicleAdapterForVehicleOwner.ViewHolder> {



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView vehicleName,vehicleNo,vehicleOwnerName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            vehicleName = itemView.findViewWithTag(R.id.TxtAviVehicleName);
            vehicleNo = itemView.findViewWithTag(R.id.txtViewVehicleNo);
            vehicleOwnerName = itemView.findViewWithTag(R.id.txtViewVehicleOwnerName);
            //vehicleName = itemView.findViewWithTag(R.id.TxtAviVehicleName);


        }
    }
}
