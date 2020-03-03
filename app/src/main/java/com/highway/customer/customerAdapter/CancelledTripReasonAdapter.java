package com.highway.customer.customerAdapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReasonResponse;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReson;
import com.highway.utils.Utils;

import java.util.Collections;
import java.util.List;

public class CancelledTripReasonAdapter extends RecyclerView.Adapter<CancelledTripReasonAdapter.MyViewHolder> {
    Context context;
    CancelTripReson cancelTripResons;
    List<CancelTripReson> cancelTripResonList;
    OnCancelReasonTypeSelect onCancelReasonTypeSelect;
    private RadioGroup lastCheckedRadioGroup = null;
    public int row_index;
    //CancelledTripReasonAdapter cancelledTripReasonAdapter;

    public CancelledTripReasonAdapter(List<CancelTripReson> cancelTripResonList, Context context) {
        this.cancelTripResonList = cancelTripResonList;
        this.context = context;
        //this.onCancelReasonTypeSelect = onCancelReasonTypeSelect1;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cancle_reason_item_activity, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        CancelTripReson cancelTripReson = cancelTripResonList.get(position);
        holder.canRsnTv.setText("" + cancelTripReson.getCancelReason());


        holder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelReasonTypeSelect != null) {
                    row_index = position;
                    onCancelReasonTypeSelect.onSelectedReasonType(cancelTripReson.getCancelId(),
                            cancelTripReson.getCancelReason());
                    notifyDataSetChanged();
                }
            }
        });

        if(row_index==position){
            holder.linearLayout.setBackgroundColor(Color.parseColor("#FFB400"));
            holder.canRsnTv.setTextColor(Color.parseColor("#ffffff"));
        }
        else
        {
            holder.linearLayout.setBackgroundColor(Color.parseColor("#ffffff"));
            holder.canRsnTv.setTextColor(Color.parseColor("#000000"));
        }

    }

    @Override
    public int getItemCount() {
        if (cancelTripResonList != null && cancelTripResonList.size() > 0) {
            return cancelTripResonList.size();
        }
        return 0;
    }

    public void setData(OnCancelReasonTypeSelect onCancelReasonTypeSelect) {
        this.onCancelReasonTypeSelect = onCancelReasonTypeSelect;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //private RadioGroup cancleReasonRadioGroup;
        public RadioButton cancelReasonBtn;
        public TextView canRsnTxtView;
        public TextView canRsnTv;
        LinearLayout linearLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            canRsnTv = itemView.findViewById(R.id.CancelRsnTv);
            linearLayout = itemView.findViewById(R.id.linearlayoutclick);

        }
    }

    public interface OnCancelReasonTypeSelect {
        void onSelectedReasonType(String id, String type);
    }


}
