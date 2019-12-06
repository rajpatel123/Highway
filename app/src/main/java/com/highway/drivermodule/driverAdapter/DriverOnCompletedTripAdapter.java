package com.highway.drivermodule.driverAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.common.base.commonModel.customer_diver_owner_Models_class.CompletedTrip;

import java.util.List;

public class DriverOnCompletedTripAdapter extends RecyclerView.Adapter<DriverOnCompletedTripAdapter.MyViewHolder> {

    private List<CompletedTrip> completedTrips;
    private Context context;

    @NonNull
    @Override
    public DriverOnCompletedTripAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.on_completed_fragment_recycler_item, parent, false);

        return new DriverOnCompletedTripAdapter.MyViewHolder(itemView);
    }


    public DriverOnCompletedTripAdapter(List<CompletedTrip> completedTrips, Context context) {

        this.completedTrips = completedTrips;
        this.context = context;

    }
    @Override
    public void onBindViewHolder(@NonNull DriverOnCompletedTripAdapter.MyViewHolder holder, int position) {

        CompletedTrip completedTrip = completedTrips.get(position);
        holder.vechileNametextView.setText(completedTrip.getVehicleName());
        holder.vehicleNumbertv.setText(completedTrip.getVehicleNumber());
        holder.faretv.setText(completedTrip.getFare());
        holder.startdatetv.setText(completedTrip.getStartDate());
        holder.endDateTv.setText(completedTrip.getEndDate());
        holder.dropTimeTv.setText(completedTrip.getDropTime());
        holder.pickUpTime.setText(completedTrip.getPickupTime());
        holder.CustomerName.setText(completedTrip.getCustomerName());

    }

    @Override
    public int getItemCount() {
        return  completedTrips.size();
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
