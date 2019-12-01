package com.highway.drivermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.drivermodule.diverModels.UpcomingTrip;

import java.util.List;

public class UpComingTripAdapter extends RecyclerView.Adapter<UpComingTripAdapter.MyViewHolder> {

    private List<UpcomingTrip> upcomingTrips;
    private Context context;

    @NonNull
    @Override
    public UpComingTripAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragmentupcomingrecycleritem, parent, false);

        return new UpComingTripAdapter.MyViewHolder(itemView);
    }


    public UpComingTripAdapter(List<UpcomingTrip> upcomingTrips, Context context) {

        this.upcomingTrips = upcomingTrips;
        this.context = context;

    }
    @Override
    public void onBindViewHolder(@NonNull UpComingTripAdapter.MyViewHolder holder, int position) {

        UpcomingTrip upcomingTrip = upcomingTrips.get(position);
        holder.vechileNametextView.setText(upcomingTrip.getVehicleName());
        holder.vehicleNumbertv.setText(upcomingTrip.getVehicleNumber());
        holder.faretv.setText(upcomingTrip.getFare());
        holder.startdatetv.setText(upcomingTrip.getStartDate());
        holder.endDateTv.setText(upcomingTrip.getEndDate());
        holder.dropTimeTv.setText(upcomingTrip.getDropTime());
        holder.pickUpTime.setText(upcomingTrip.getPickupTime());
        holder.CustomerName.setText(upcomingTrip.getCustomerName());

    }

    @Override
    public int getItemCount() {
        return  upcomingTrips.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView vechileNametextView,vehicleNumbertv,faretv,startdatetv,endDateTv,dropTimeTv,pickUpTime,CustomerName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            vechileNametextView = itemView.findViewById(R.id.Vechilename);
            vehicleNumbertv = itemView.findViewById(R.id.vechilenumber);
            faretv = itemView.findViewById(R.id.fare);
            startdatetv = itemView.findViewById(R.id.startdate);
            endDateTv = itemView.findViewById(R.id.enddate);
            dropTimeTv = itemView.findViewById(R.id.droptime);
            pickUpTime = itemView.findViewById(R.id.pickuptime);
            CustomerName = itemView.findViewById(R.id.username);

        }
    }
}
