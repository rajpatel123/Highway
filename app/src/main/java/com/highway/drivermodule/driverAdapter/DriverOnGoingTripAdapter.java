package com.highway.drivermodule.driverAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.commonModel.customer_diver_owner_Models_class.OngoingTrip;

import java.util.List;

public class DriverOnGoingTripAdapter extends RecyclerView.Adapter<DriverOnGoingTripAdapter.MyViewHolder> {

    private List<OngoingTrip> ongoingTrips;
    private Context context;

    @NonNull
    @Override
    public DriverOnGoingTripAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragmentupcomingrecycleritem, parent, false);

        return new DriverOnGoingTripAdapter.MyViewHolder(itemView);
    }


    public DriverOnGoingTripAdapter(List<OngoingTrip> ongoingTrips, Context context) {

        this.ongoingTrips = ongoingTrips;
        this.context = context;

    }
    @Override
    public void onBindViewHolder(@NonNull DriverOnGoingTripAdapter.MyViewHolder holder, int position) {

        OngoingTrip ongoingTrip = ongoingTrips.get(position);
        holder.vechileNametextView.setText(ongoingTrip.getVehicleName());
        holder.vehicleNumbertv.setText(ongoingTrip.getVehicleNumber());
        holder.faretv.setText(ongoingTrip.getFare());
        holder.startdatetv.setText(ongoingTrip.getStartDate());
        holder.endDateTv.setText(ongoingTrip.getEndDate());
        holder.dropTimeTv.setText(ongoingTrip.getDropTime());
        holder.pickUpTime.setText(ongoingTrip.getPickupTime());
        holder.CustomerName.setText(ongoingTrip.getDriverName());

    }

    @Override
    public int getItemCount() {
        return  ongoingTrips.size();
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
