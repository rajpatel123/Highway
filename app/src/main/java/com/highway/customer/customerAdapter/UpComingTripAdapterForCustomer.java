package com.highway.customer.customerAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.highway.R;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.UpcomingTrip;
import com.highway.utils.Utils;

import java.util.List;

public class UpComingTripAdapterForCustomer extends RecyclerView.Adapter<UpComingTripAdapterForCustomer.ViewHolder> {
    List<UpcomingTrip> upcomingTrips;
    Context context;
    public UpComingBookTripInterface upComingBookTripInterface;


    public UpComingTripAdapterForCustomer(List<UpcomingTrip> upcomingTrips, Context context) {
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

        holder.tv1CompleteDate.setText("" + upcomingTrip.getStartDate());
        holder.tv2SourceAddress.setText(" " + Utils.getAddress(context, sourceAddLatLng));
        holder.tv4DestAddress.setText(" " + Utils.getAddress(context, destAddLatLng));
        holder.tv3SourceTime.setText("" + upcomingTrip.getPickupTime());
        holder.tv5DestTime.setText("" + upcomingTrip.getDropTime());
        holder.tv6VehicleName.setText("" + upcomingTrip.getVehicleName());
        holder.tv7FairCharge.setText("" + upcomingTrip.getFare());


        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (upComingBookTripInterface != null) {
                    upComingBookTripInterface.upComingBookTrip(

                           /* upcomingTrips.get(position).getSourceLat(), upcomingTrips.get(position).getSourceLong(),
                            upcomingTrips.get(position).getDestinationLat(), upcomingTrips.get(position).getDestinationLong(),
                            upcomingTrips.get(position).getName(), upcomingTrips.get(position).getRole(),
                            upcomingTrips.get(position).getVehicleName(), upcomingTrips.get(position).getVehicleNumber(),
                            upcomingTrips.get(position).getFare(), upcomingTrips.get(position).getStatus(),
                            upcomingTrips.get(position).getTripType(), upcomingTrips.get(position).getStartDate(),
                            upcomingTrips.get(position).getEndDate(), upcomingTrips.get(position).getPickupTime(),
                            upcomingTrips.get(position).getDropTime()*/

                    /*upComingBookTripInterface.upComingBookTrip(upcomingTrip.getSourceLat(),
                            upcomingTrip.getSourceLong(), upcomingTrip.getDestinationLat(),
                            upcomingTrip.getDestinationLong(), upcomingTrip.getName(),
                            upcomingTrip.getRole(), upcomingTrip.getVehicleName(),
                            upcomingTrip.getVehicleNumber(), upcomingTrip.getFare(),
                            upcomingTrip.getStatus(), upcomingTrip.getTripType(),
                            upcomingTrip.getStartDate(), upcomingTrip.getEndDate(),
                            upcomingTrip.getPickupTime(), upcomingTrip.getDropTime()*/

                            upcomingTrips.get(holder.getAdapterPosition()).getSourceLat(),
                            upcomingTrips.get(holder.getAdapterPosition()).getSourceLong(),
                            upcomingTrips.get(holder.getAdapterPosition()).getDestinationLat(),
                            upcomingTrips.get(holder.getAdapterPosition()).getDestinationLong(),
                            upcomingTrips.get(holder.getAdapterPosition()).getName(),
                            upcomingTrips.get(holder.getAdapterPosition()).getRole(),
                            upcomingTrips.get(holder.getAdapterPosition()).getVehicleName(),
                            upcomingTrips.get(holder.getAdapterPosition()).getVehicleNumber(),
                            upcomingTrips.get(holder.getAdapterPosition()).getFare(),
                            upcomingTrips.get(holder.getAdapterPosition()).getStatus(),
                            upcomingTrips.get(holder.getAdapterPosition()).getTripType(),
                            upcomingTrips.get(holder.getAdapterPosition()).getStartDate(),
                            upcomingTrips.get(holder.getAdapterPosition()).getEndDate(),
                            upcomingTrips.get(holder.getAdapterPosition()).getPickupTime(),
                            upcomingTrips.get(holder.getAdapterPosition()).getDropTime()

                    );

                }
            }
        });

    }

    public void setUpComingBookTripInterface(UpComingBookTripInterface upComingBookTripInterface1) {
        this.upComingBookTripInterface = upComingBookTripInterface1;
    }

    public interface UpComingBookTripInterface {       ///
        void upComingBookTrip(String sourceLat, String sourceLong, String destinationLat, String destinationLong,
                              String name, String role, String vehicleName, String vehicleNumber, String fare,
                              String status, String tripType, String startDate, String endDate, String pickupTime,
                              String dropTime
        );
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
        public LinearLayout linearLayout;

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
            cardView = itemView.findViewById(R.id.CardView);
            linearLayout = itemView.findViewById(R.id.LLout1);
        }
    }
}
