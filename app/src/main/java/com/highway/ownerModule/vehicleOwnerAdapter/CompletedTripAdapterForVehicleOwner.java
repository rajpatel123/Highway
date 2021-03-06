package com.highway.ownerModule.vehicleOwnerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.highway.R;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.CompletedTrip;
import com.highway.utils.Utils;

import java.util.List;

public class CompletedTripAdapterForVehicleOwner extends RecyclerView.Adapter<CompletedTripAdapterForVehicleOwner.MyViewHolder> {

    List<CompletedTrip> completedTrips;
    Context context;

    public CompletedTripAdapterForVehicleOwner(List<CompletedTrip>completedTrips, Context context){
        this.completedTrips = completedTrips;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_recycler_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CompletedTrip completedTrip = completedTrips.get(position);
         // for Address finding
        LatLng sourceAddressLatLng = new LatLng(Double.parseDouble(completedTrip.getSourceLat()),Double.parseDouble(completedTrip.getSourceLong()));
        LatLng destAddressLatLng = new LatLng(Double.parseDouble(completedTrip.getDestinationLat()),Double.parseDouble(completedTrip.getDestinationLong()));

        holder.tv1CompleteDate.setText(""+completedTrip.getEndDate());
        holder.tv2SourceAddress.setText(""+Utils.getAddress(context,sourceAddressLatLng));
        holder.tv4DestAddress.setText("" +Utils.getAddress(context,destAddressLatLng));
        holder.tv3SourceTime.setText(""+completedTrip.getPickupTime());
        holder.tv5DestTime.setText(""+completedTrip.getDropTime());
        holder.tv6VehicleName.setText(""+completedTrip.getVehicleName());
        holder.tv7FairCharge.setText(""+completedTrip.getFare());
        // load img

    }

    @Override
    public int getItemCount() {
        return completedTrips.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tv1CompleteDate,tv2SourceAddress,tv3SourceTime,tv4DestAddress,tv5DestTime, tv6VehicleName,tv7FairCharge;
        private ImageView img1SourceIndicator,img2DestIndicator,img3_gmap_locator,img4VehicleImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1CompleteDate = itemView.findViewById(R.id.Tv1CompleteDate);
            tv2SourceAddress = itemView.findViewById(R.id.Tv2SourceAddress);
            tv3SourceTime = itemView.findViewById(R.id.Tv3SourceTime);
            tv4DestAddress = itemView.findViewById(R.id.Tv4DestAddress);
            tv5DestTime = itemView.findViewById(R.id.Tv5DestTime);
            tv6VehicleName = itemView.findViewById(R.id.Tv6VehicleName);
            tv7FairCharge = itemView.findViewById(R.id.Tv7FairCharge);
            img1SourceIndicator = itemView.findViewById(R.id.Img1SourceIndicator);
            img2DestIndicator = itemView.findViewById(R.id.Img2DestIndicator);
            img4VehicleImg = itemView.findViewById(R.id.Img4VehicleImg);
        }
    }
}
