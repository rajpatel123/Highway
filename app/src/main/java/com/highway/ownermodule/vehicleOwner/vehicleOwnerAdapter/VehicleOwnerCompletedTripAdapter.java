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
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.CompletedTrip;

import java.util.List;

public class VehicleOwnerCompletedTripAdapter extends RecyclerView.Adapter<VehicleOwnerCompletedTripAdapter.MyViewHolder> {

    List<CompletedTrip> completedTrips;
    Context context;


    public VehicleOwnerCompletedTripAdapter(List<CompletedTrip>completedTrips,Context context){
        this.completedTrips = completedTrips;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.completed_fragment_recycler_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        CompletedTrip completedTrip = completedTrips.get(position);
        holder.tv1CompleteDate.setText(completedTrip.getEndDate());




    }

    @Override
    public int getItemCount() {
        return 0;
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
            img3_gmap_locator = itemView.findViewById(R.id.Img3_gmap_Locator);
            img4VehicleImg = itemView.findViewById(R.id.Img4VehicleImg);
        }
    }
}
