package com.highway.ownerModule.vehicleOwnerAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.ownerModule.vehicleOwnerActivities.OwnerMapActivity;
import com.highway.ownerModule.vehileOwnerModelsClass.getAllVehicle.VehicleDetail;

import java.util.List;

public class GetAllVehicleAdapterForVehicleOwner extends RecyclerView.Adapter<GetAllVehicleAdapterForVehicleOwner.ViewHolder> {

    List<VehicleDetail>vehicleDetails;
    Context context;

    public GetAllVehicleAdapterForVehicleOwner(List<VehicleDetail> vehicleDetails1, Context context1) {
        this.context = context1;
        this.vehicleDetails = vehicleDetails1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_get_all_vehicle__item_fragment, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        VehicleDetail vehicleDetail = vehicleDetails.get(position);
        holder.tv1DriverName.setText(""+ vehicleDetail.getDriverName());
        holder.tv2VehicleName.setText(""+ vehicleDetail.getVehicleName());
        holder.tv3VehicleNumber.setText(""+vehicleDetail.getVehicleNumber());
        holder.tv4VehicleModelNo.setText(""+ vehicleDetail.getVehicleModelNo());
        holder.tv5VehicleLoadCapicity.setText(""+ vehicleDetail.getVehicleCapacity());
        holder.tv6VehicleSize.setText(""+vehicleDetail.getVehicleSize());
        holder.tv7VehicleDescription.setText(""+vehicleDetail.getVehicleDescription());

        holder.llCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if(vehicleDetail.getLatitude() != null && !vehicleDetail.getLatitude().isEmpty()) {
                    Intent i = new Intent(view.getContext(), OwnerMapActivity.class);
                    i.putExtra("lat", vehicleDetail.getLatitude());
                    i.putExtra("long", vehicleDetail.getLongitude());
                    i.putExtra("vehicalName", vehicleDetail.getVehicleName());
                    view.getContext().startActivity(i);
                }else {

                    Toast.makeText(view.getContext(),"latitude and longitude not available", Toast.LENGTH_SHORT).show();
                }

            }
        });
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

        private TextView tv1DriverName,tv2VehicleName, tv3VehicleNumber, tv4VehicleModelNo,tv5VehicleLoadCapicity,tv6VehicleSize , tv7VehicleDescription;
        private ImageView img1VehicleImg;
        LinearLayout llCard;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv1DriverName = itemView.findViewById(R.id.Tv1DriverName);
            tv2VehicleName = itemView.findViewById(R.id.Tv2VehicleName);
            tv3VehicleNumber = itemView.findViewById(R.id.Tv3VehicleNo);
            tv4VehicleModelNo = itemView.findViewById(R.id.Tv4VehicleModelNo);
            tv5VehicleLoadCapicity = itemView.findViewById(R.id.Tv5VehicleLoadCapicity);
            tv6VehicleSize = itemView.findViewById(R.id.Tv6VehicleSize);
            tv7VehicleDescription = itemView.findViewById(R.id.Tv7VehicleDescription);
            llCard = itemView.findViewById(R.id.llCard);
           // img1VehicleImg = itemView.findViewById(R.id.VehicleImageView);
        }
    }
}
