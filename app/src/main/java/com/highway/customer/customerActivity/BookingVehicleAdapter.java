package com.highway.customer.customerActivity;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.customer.customerModelClass.bookingVehicleList.BookingVehicleListResponse;
import com.highway.customer.customerModelClass.bookingVehicleList.VehicleList;
import com.highway.utils.HighwayPrefs;
import com.highway.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class BookingVehicleAdapter extends RecyclerView.Adapter<BookingVehicleAdapter.ViewHolder> {
    private Context context;

    OnClickEvents onClickEvents;
    BookingVehicleListResponse bookingVehicleListResponse;
    List<ImageView> imageViewList = new ArrayList<>();
    List<View>itemViewList = new ArrayList<>();

    public int previousPosition = -1;
    public SingleViewItemBinding previousView;
    private int row_index;

    public BookingVehicleAdapter(BookingVehicleListResponse bookingVehicleListResponse1, Context context1, OnClickEvents onClickEvents1) {
        this.context = context1;
        this.bookingVehicleListResponse = bookingVehicleListResponse1;
        this.onClickEvents = onClickEvents1;
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
       // holder.tv1.setText(android_versionnames[position]);
        VehicleList vehicleList = bookingVehicleListResponse.getVehicleData().getVehicleList().get(position);

        holder.tataAceTv1.setText(""+vehicleList.getVehicleName());
        holder.faireChargeTv3.setText("\u20B9"+vehicleList.getVehicleFare());


        holder.infoImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickEvents != null) {
                    onClickEvents.onCLickInfo(holder.getAdapterPosition());
                }
            }
        });


        holder.row_linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickEvents != null) {
                    row_index = position;
                    onClickEvents.onCLickTruck(holder.getAdapterPosition(),
                            vehicleList.getVehicleFare());
                    notifyDataSetChanged();
                }
            }
        });
        if(row_index==position){
            holder.row_linearlayout.setBackgroundColor(Color.parseColor("#FFB400"));
           // holder.canRsnTv.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.row_linearlayout.setBackgroundColor(Color.parseColor("#ffffff"));
            //holder.canRsnTv.setTextColor(Color.parseColor("#000000"));
        }


        if (vehicleList.isSelected()){
            Utils.setTintForImage(context,holder.vehicleIcons,R.color.email_color);
        }else{
            Utils.setTintForImage(context,holder.vehicleIcons,R.color.email_gray);
        }
    }

    @Override
    public int getItemCount() {
       if (bookingVehicleListResponse!=null
               && bookingVehicleListResponse.getVehicleData().getVehicleList()!=null
               && bookingVehicleListResponse.getVehicleData().getVehicleList().size()>0){
            return bookingVehicleListResponse.getVehicleData().getVehicleList().size();
       }else {
           return 0;
       }
    }

    public void setOnClickEvents(OnClickEvents onClickEvents) {
        this.onClickEvents = onClickEvents;
    }

    public void setData(BookingVehicleListResponse bookingVehicleListResponse) {
        this.bookingVehicleListResponse = bookingVehicleListResponse;

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tataAceTv1, timeTv2, faireChargeTv3;
        ImageView vehicleIcons, infoImg;
        LinearLayout  row_linearlayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tataAceTv1 = itemView.findViewById(R.id.tataAceTv1);
            timeTv2 = itemView.findViewById(R.id.timeDurationTv2);
            faireChargeTv3 = itemView.findViewById(R.id.faireChargeTv3);
            vehicleIcons = itemView.findViewById(R.id.vehicleImg1);
            infoImg = itemView.findViewById(R.id.infoImg);
            row_linearlayout = itemView.findViewById(R.id.Llayout1);
        }
    }

    public interface OnClickEvents {
        void onCLickInfo(int position);
        void onCLickTruck(int position, String vehicleFare);

    }
}
