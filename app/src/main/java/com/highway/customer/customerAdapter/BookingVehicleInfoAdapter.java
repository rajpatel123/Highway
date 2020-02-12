package com.highway.customer.customerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.customer.customerModelClass.vehicleInfo.BookingVehicleInfoResponse;
import com.highway.customer.customerModelClass.vehicleInfo.VehicleTypeInfo;

import java.util.ArrayList;
import java.util.List;

public class BookingVehicleInfoAdapter extends RecyclerView.Adapter<BookingVehicleInfoAdapter.MyViewHolder> {

    Context context;
    List<VehicleTypeInfo> vehicleTypeInfoList;

    public BookingVehicleInfoAdapter(List<VehicleTypeInfo> vehicleTypeInfoList, Context context1) {
        this.context = context1;
        this.vehicleTypeInfoList = vehicleTypeInfoList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_info_details_item, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        VehicleTypeInfo vehicleTypeInfo = vehicleTypeInfoList.get(position);
        holder.bookVehicleNameTv.setText("" + vehicleTypeInfo.getVehicleTypeName());
        holder.bookVehicleCapicityTv.setText("" + vehicleTypeInfo.getVehicleCapacity());
        holder.bookVehicleSizeTv.setText("" + vehicleTypeInfo.getVehicleSize());
        holder.bookInfo1.setText("" + vehicleTypeInfo.getVInfo1());
        holder.bookInfo2.setText("" + vehicleTypeInfo.getVInfo2());
        holder.bookInfo3.setText("" + vehicleTypeInfo.getVInfo3());
        holder.bookInfo4.setText("" + vehicleTypeInfo.getVInfo4());
        holder.bookInfo5.setText("" + vehicleTypeInfo.getVInfo5());
        holder.bookInfo6.setText("" + vehicleTypeInfo.getVInfo6());
    }


    @Override
    public int getItemCount() {
        if (vehicleTypeInfoList != null
                && vehicleTypeInfoList.size() > 0) {
            return vehicleTypeInfoList.size();
        } else {
            return 0;
        }
    }
   /* @Override
    public int getItemCount() {
        if (bookingVehicleInfoResponse != null
                && bookingVehicleInfoResponse.getVehicleTypeInfo() != null
                && bookingVehicleInfoResponse.getVehicleTypeInfo().size() > 0) {
            return bookingVehicleInfoResponse.getVehicleTypeInfo().size();

        } else {
            return 0;
        }
    }*/

   /* public void setData(BookingVehicleInfoResponse bookingVehicleInfoResponse) {
        this.bookingVehicleInfoResponse = bookingVehicleInfoResponse;
    }*/

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView bookVehicleNameTv, bookVehicleCapicityTv, bookVehicleSizeTv;
        ImageView bookVehileBookImg;
        TextView bookInfo1, bookInfo2, bookInfo3, bookInfo4, bookInfo5, bookInfo6, done;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            bookVehicleNameTv = itemView.findViewById(R.id.BookVehicleName);
            bookVehileBookImg = itemView.findViewById(R.id.BookVehicleImg);
            bookVehicleCapicityTv = itemView.findViewById(R.id.VehicleCapacity);
            bookVehicleSizeTv = itemView.findViewById(R.id.VehicleSizeTV);
            bookInfo1 = itemView.findViewById(R.id.Bookinfo1);
            bookInfo2 = itemView.findViewById(R.id.Bookinfo2);
            bookInfo3 = itemView.findViewById(R.id.Bookinfo3);
            bookInfo4 = itemView.findViewById(R.id.Bookinfo4);
            bookInfo5 = itemView.findViewById(R.id.Bookinfo5);
            bookInfo6 = itemView.findViewById(R.id.Bookinfo6);

        }
    }
}
