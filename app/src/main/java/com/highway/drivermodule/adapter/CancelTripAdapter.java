package com.highway.drivermodule.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.drivermodule.diverModels.CancelTrip;
import com.highway.drivermodule.diverModels.CompletedTrip;
import com.highway.drivermodule.diverModels.OngoingTrip;
import com.highway.drivermodule.diverModels.UpcomingTrip;

import java.util.List;

public class CancelTripAdapter extends RecyclerView.Adapter<CancelTripAdapter.MyViewHolder> {

    private List<CancelTrip> cancelTrips;
    private Context context;

    @NonNull
    @Override
    public CancelTripAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.oncompletedrecycleritem, parent, false);

        return new CancelTripAdapter.MyViewHolder(itemView);
    }


    public CancelTripAdapter(List<CancelTrip> cancelTrips, Context context) {

        this.cancelTrips = cancelTrips;
        this.context = context;

    }
    @Override
    public void onBindViewHolder(@NonNull CancelTripAdapter.MyViewHolder holder, int position) {

        CancelTrip cancelTrip = cancelTrips.get(position);
        holder.vechileNametextView.setText(cancelTrip.getVehicleName());
        holder.vehicleNumbertv.setText(cancelTrip.getVehicleNumber());
        holder.faretv.setText(cancelTrip.getFare());
        holder.startdatetv.setText(cancelTrip.getStartDate());
        holder.endDateTv.setText(cancelTrip.getEndDate());
        holder.dropTimeTv.setText(cancelTrip.getDropTime());
        holder.pickUpTime.setText(cancelTrip.getPickupTime());
        holder.CustomerName.setText(cancelTrip.getCustomerName());

    }

    @Override
    public int getItemCount() {
        return  cancelTrips.size();
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
