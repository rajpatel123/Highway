package com.highway.ownerModule.vehicleOwnerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.ownerModule.vehileOwnerModelsClass.getAllDriver.DriverDetail;

import java.util.List;

public class GetAllDriverAdapterForVehicleOwner extends RecyclerView.Adapter<GetAllDriverAdapterForVehicleOwner.ViewHolder> {

   List<DriverDetail>driverDetails;
   Context context;


   public GetAllDriverAdapterForVehicleOwner(List<DriverDetail>driverDetails, Context context){
       this.driverDetails = driverDetails;
       this.context = context;
   }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_get_all_driver__item_fragment, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

       DriverDetail driverDetail = driverDetails.get(position);
       holder.tv1DriverName.setText(""+ driverDetail.getDriverName());
       holder.tv2DriverMobNo.setText(""+ driverDetail.getMobile());
       holder.tv3TxtDriverEmailNos.setText(""+ driverDetail.getEmail());
       holder.tv4TxtDlNumber.setText(""+ driverDetail.getDLNumber());
       holder.tv5TxtDlExpireDate.setText(""+ driverDetail.getExpiryDate());
       holder.tv6VehicleNumber.setText(""+ driverDetail.getVehicleNumber());
       holder.tv7TxtDriverAdd.setText(""+ driverDetail.getAddress());

       //holder.spVehicleNumber.setTe

    }

    @Override
    public int getItemCount() {
        return driverDetails.size();
    }

    public void setData(List<DriverDetail> driverDetails) {
       this.driverDetails= driverDetails;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv1DriverName, tv2DriverMobNo,tv3TxtDriverEmailNos,tv4TxtDlNumber,tv5TxtDlExpireDate,tv6VehicleNumber,tv7TxtDriverAdd;
       private Spinner spVehicleNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tv1DriverName = itemView.findViewById(R.id.TxtDriverName);
            tv2DriverMobNo = itemView.findViewById(R.id.TxtDriverMobNo);
            tv3TxtDriverEmailNos = itemView.findViewById(R.id.TxtDriverEmailNos);
            tv4TxtDlNumber = itemView.findViewById(R.id.TxtDlNumber);
            tv5TxtDlExpireDate = itemView.findViewById(R.id.TxtDlExpireDate);
            tv6VehicleNumber = itemView.findViewById(R.id.TxtVehicleNumber);
            tv7TxtDriverAdd = itemView.findViewById(R.id.TxtDriverAdd);
        }
    }
}
