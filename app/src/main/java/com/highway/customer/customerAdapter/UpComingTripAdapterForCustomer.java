package com.highway.customer.customerAdapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.highway.R;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.UpcomingTrip;
import com.highway.customer.customerActivity.BookingConformedActivity;
import com.highway.utils.Utils;

import java.util.List;

public class UpComingTripAdapterForCustomer extends RecyclerView.Adapter<UpComingTripAdapterForCustomer.ViewHolder> {
    List<UpcomingTrip>upcomingTrips;
    Context context;

    public UpComingTripAdapterForCustomer(List<UpcomingTrip>upcomingTrips, Context context){
        this.context = context;
        this.upcomingTrips = upcomingTrips;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;



    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        UpcomingTrip upcomingTrip = upcomingTrips.get(position);

        LatLng sourceAddLatLng = new LatLng(Double.parseDouble(upcomingTrip.getSourceLat()), Double.parseDouble(upcomingTrip.getSourceLong()));
        LatLng destAddLatLng = new LatLng(Double.parseDouble(upcomingTrip.getDestinationLat()), Double.parseDouble(upcomingTrip.getDestinationLong()));

        holder.tv1CompleteDate.setText("" + upcomingTrip.getEndDate());
        holder.tv2SourceAddress.setText(" " + Utils.getAddress(context, sourceAddLatLng));
        holder.tv4DestAddress.setText(" " + Utils.getAddress(context, destAddLatLng));
        holder.tv3SourceTime.setText("" + upcomingTrip.getPickupTime());
        holder.tv5DestTime.setText("" + upcomingTrip.getDropTime());
        holder.tv6VehicleName.setText("" + upcomingTrip.getVehicleName());
        holder.tv7FairCharge.setText("" + upcomingTrip.getFare());

        // img

        holder.cardView.setOnClickListener(v -> {

//                LatLng sourceAddLatLng = new LatLng(Double.parseDouble(upcomingTrip.getSourceLat()), Double.parseDouble(upcomingTrip.getSourceLong()));
//                LatLng destAddLatLng = new LatLng(Double.parseDouble(upcomingTrip.getDestinationLat()), Double.parseDouble(upcomingTrip.getDestinationLong()));

            Intent intent = new Intent(context, BookingConformedActivity.class);

            intent.putExtra("SourceAddLatlog",upcomingTrips.get(holder.getAdapterPosition()).getSourceLat());
            intent.putExtra("DestAddLatlog",upcomingTrips.get(holder.getAdapterPosition()).getSourceLat());
            intent.putExtra("CompleteDate",upcomingTrips.get(holder.getAdapterPosition()).getEndDate());
            intent.putExtra("PickupTime",upcomingTrips.get(holder.getAdapterPosition()).getPickupTime());
            intent.putExtra("DropTime",upcomingTrips.get(holder.getAdapterPosition()).getDropTime());
            intent.putExtra("VehicleName",upcomingTrips.get(holder.getAdapterPosition()).getVehicleName());
            intent.putExtra("VehicleNumber",upcomingTrips.get(holder.getAdapterPosition()).getVehicleNumber());
            intent.putExtra("GetFairCharge",upcomingTrips.get(holder.getAdapterPosition()).getFare());

            context.startActivity(intent);


        });




    }

    @Override
    public int getItemCount() {

        if (upcomingTrips != null && upcomingTrips.size() > 0) {
            return upcomingTrips.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1CompleteDate, tv2SourceAddress, tv3SourceTime, tv4DestAddress, tv5DestTime, tv6VehicleName, tv7FairCharge;
        private ImageView img1SourceIndicator, img2DestIndicator, img3_gmap_locator, img4VehicleImg;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
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
            cardView= itemView.findViewById(R.id.upcomingCardView);
        }
    }
}
