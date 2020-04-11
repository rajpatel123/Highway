package com.highway.customer.customerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.model.LatLng;
import com.highway.R;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.CompletedTrip;
import com.highway.utils.Utils;

import java.util.List;

public class CompletedTripAdapterForCustomer extends RecyclerView.Adapter<CompletedTripAdapterForCustomer.ViewHolder> {
    Context context;
    List<CompletedTrip> completedTrips;
    public TripDetailsListInterface tripDetailsListInterface;


    public CompletedTripAdapterForCustomer(List<CompletedTrip> completedTrips, Context context) {
        this.completedTrips = completedTrips;
        this.context = context;
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

        CompletedTrip completedTrip = completedTrips.get(position);

        LatLng sourceAddLatLng = new LatLng(Double.parseDouble(completedTrip.getSourceLat()), Double.parseDouble(completedTrip.getSourceLong()));
        LatLng destAddLatLng = new LatLng(Double.parseDouble(completedTrip.getDestinationLat()), Double.parseDouble(completedTrip.getDestinationLong()));

        holder.tv1CompleteDate.setText("" + completedTrip.getEndDate());
        holder.tv2SourceAddress.setText(" " + Utils.getAddress(context, sourceAddLatLng));
        holder.tv4DestAddress.setText(" " + Utils.getAddress(context, destAddLatLng));
        holder.tv3SourceTime.setText("" + completedTrip.getPickupTime());
        holder.tv5DestTime.setText("" + completedTrip.getDropTime());
        holder.tv6VehicleName.setText("" + completedTrip.getVehicleName());
        holder.tv7FairCharge.setText("" + completedTrip.getFare());
        // img

//        holder.cardView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (tripDetailsListInterface != null) {
//                    tripDetailsListInterface.tripDetailsList(completedTrips.get(position).getTripType());
//                }
//            }
//        });


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tripDetailsListInterface != null) {
                    tripDetailsListInterface.tripDetailsList(completedTrips.get(position).getTripType());
                }
            }
        });

    }


    public void setTripDetailsListInterface(TripDetailsListInterface tripDetailsListInterface1) {
        this.tripDetailsListInterface = tripDetailsListInterface1;
    }

    public interface TripDetailsListInterface {       ///
        public void tripDetailsList(String title);
    }


    @Override
    public int getItemCount() {
        return completedTrips.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tv1CompleteDate, tv2SourceAddress, tv3SourceTime, tv4DestAddress, tv5DestTime, tv6VehicleName, tv7FairCharge;
        private ImageView img1SourceIndicator, img2DestIndicator, img3_gmap_locator, img4VehicleImg;
        private CardView cardView;
        private LinearLayout linearLayout;

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
            linearLayout= itemView.findViewById(R.id.LLout1);



        }
    }
}
