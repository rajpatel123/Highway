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
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.GetAllDriverList.GetAllDriverRequest;

import java.util.List;

public class GetAllVehicleListAdapterForVehicleOwner extends RecyclerView.Adapter<GetAllVehicleListAdapterForVehicleOwner.ViewHolder> {



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_get_all_vehicle_list_item_fragment, parent, false);
        return new ViewHolder(itemView);


    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {



    }

    @Override
    public int getItemCount() {
        return 0;
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
