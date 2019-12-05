package com.highway.customer.customerActivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.Vehicle;
import com.highway.utils.Utils;

import java.util.List;

public class BookingVehicleAdapter extends RecyclerView.Adapter<BookingVehicleAdapter.ViewHolder> {
    private Context context;
    private List<Vehicle> vehicles;
    private OnClickEvents onClickEvents;

    public BookingVehicleAdapter(Context context, List<Vehicle> vehicles) {
        this.context = context;
        this.vehicles = vehicles;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_vehicle_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Vehicle vehicle = vehicles.get(position);
        holder.tataAceTv1.setText(vehicle.getvName());
        holder.timeTv2.setText(vehicle.getDuration());
        holder.faireChargeTv3.setText(vehicle.getFare());

        if (vehicle.isSelected()){
            Utils.setTintForImage(context,holder.vehicleIcons,R.color.email_color);
        }else{
            Utils.setTintForImage(context,holder.vehicleIcons,R.color.email_gray);
        }

        holder.infoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickEvents != null) {
                    onClickEvents.onCLickInfo(holder.getAdapterPosition());

                }
            }
        });


        holder.vehicleIcons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickEvents != null) {
                    onClickEvents.onCLickTruck(holder.getAdapterPosition());

                }
            }
        });


        //holder.truckImg1

    }

    @Override
    public int getItemCount() {
        if (vehicles != null && vehicles.size() > 0) {
            return vehicles.size();
        } else {
            return 0;
        }
    }

    public void setOnClickEvents(OnClickEvents onClickEvents) {
        this.onClickEvents = onClickEvents;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tataAceTv1, timeTv2, faireChargeTv3;
        ImageView vehicleIcons, infoImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tataAceTv1 = itemView.findViewById(R.id.tataAceTv1);
            timeTv2 = itemView.findViewById(R.id.timeDurationTv2);
            faireChargeTv3 = itemView.findViewById(R.id.faireChargeTv3);
            vehicleIcons = itemView.findViewById(R.id.vehicleImg1);
            infoImg = itemView.findViewById(R.id.infoImg);
        }
    }

    public interface OnClickEvents {
        void onCLickInfo(int position);

        void onCLickTruck(int position);
    }
}
