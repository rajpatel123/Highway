package com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.customer.customerActivity.VehicleList;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllVehicleDetailsList.VehicleDetail;

import java.util.List;

public class GetAllVehicleDetailsListAdapterForVehicleOwner extends RecyclerView.Adapter<GetAllVehicleDetailsListAdapterForVehicleOwner.ViewHolder> {

    List<VehicleDetail>vehicleDetails;
    Context context;

    public GetAllVehicleDetailsListAdapterForVehicleOwner(List<VehicleDetail> vehicleDetails1, Context context1) {
        this.context = context1;
        this.vehicleDetails = vehicleDetails1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_get_all_vehicle_list_item_fragment, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        VehicleDetail vehicleDetail = vehicleDetails.get(position);
        holder.tv1VehicleName.setText(""+ vehicleDetail.getVehicleName());
        holder.tv2VehicleNo.setText(""+vehicleDetail.getVehicleNumber());
        holder.tv3VehicleModelNo.setText(""+ vehicleDetail.getVehicleModelNo());
        holder.tv4DriverName.setText(""+ vehicleDetail.getDriverName());
        holder.tv5VehicleDescription.setText(""+ vehicleDetail.getVehicleDescription());
        // load vihicle img
    }

    @Override
    public int getItemCount() {
        return vehicleDetails.size();
    }

    public void setData(List<VehicleDetail> vehicleDetails) {

        this.vehicleDetails = vehicleDetails;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1VehicleName, tv2VehicleNo, tv3VehicleModelNo, tv4DriverName, tv5VehicleDescription;
        private ImageView img1VehicleImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1VehicleName = itemView.findViewById(R.id.TxtViewVehicleName);
            tv2VehicleNo = itemView.findViewById(R.id.txtViewVehicleNo);
            tv3VehicleModelNo = itemView.findViewById(R.id.txtViewVehicleModelNo);
            tv4DriverName = itemView.findViewById(R.id.TxtViewDriverName);
            tv5VehicleDescription = itemView.findViewById(R.id.TxtViewVehicleDescription);
            img1VehicleImg = itemView.findViewById(R.id.VehicleImageView);
        }
    }
}
