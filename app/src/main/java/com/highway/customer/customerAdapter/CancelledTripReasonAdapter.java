package com.highway.customer.customerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReasonResponse;
import com.highway.customer.customerModelClass.cancleTripModel.cancleReason.CancelTripReson;

public class CancelledTripReasonAdapter extends RecyclerView.Adapter<CancelledTripReasonAdapter.MyViewHolder> {
    Context context;
    CancelTripReasonResponse cancelTripReasonResponse;
    OnCancelReasonTypeSelect onCancelReasonTypeSelect;
    private RadioGroup lastCheckedRadioGroup = null;
    //CancelledTripReasonAdapter cancelledTripReasonAdapter;

    public CancelledTripReasonAdapter(CancelTripReasonResponse cancelTripReasonResponse1, Context context1/*, OnCancelReasonTypeSelect onCancelReasonTypeSelect1*/) {
        this.cancelTripReasonResponse = cancelTripReasonResponse1;
        this.context = context1;
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
        CancelTripReson cancelTripReson = cancelTripReasonResponse.getCancelTripReson().get(position);

       holder.canRsnTxtView.setText(cancelTripReson.getCancelReason());

        int id = (position+1)*1;
        RadioButton rb = new RadioButton(CancelledTripReasonAdapter.this.context);
        rb.setId(id++);
        holder.cancleReasonRadioGroup.addView(rb);

       /* holder.canRsnRdBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onCancelReasonTypeSelect != null) {
                    onCancelReasonTypeSelect.onSelectedReasonType(cancelTripReson.getCancelId(), cancelTripReson.getCancelReason());
                }
            }
        });*/
    }

    @Override
    public int getItemCount() {
        if (cancelTripReasonResponse != null
                && cancelTripReasonResponse.getCancelTripReson() != null
                && cancelTripReasonResponse.getCancelTripReson().size() > 0) {
            return cancelTripReasonResponse.getCancelTripReson().size();
        } else {
            return 0;
        }
    }

    public void setData(CancelTripReasonResponse cancelTripReasonResponse) {
        this.cancelTripReasonResponse = cancelTripReasonResponse;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RadioGroup cancleReasonRadioGroup;
        public TextView canRsnTxtView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

           cancleReasonRadioGroup = itemView.findViewById(R.id.cancleReasonRadioGroup);
            canRsnTxtView = itemView.findViewById(R.id.cancelReasonText);

            cancleReasonRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                    if (lastCheckedRadioGroup != null
                            && lastCheckedRadioGroup.getCheckedRadioButtonId() != radioGroup.getCheckedRadioButtonId()
                            && lastCheckedRadioGroup.getCheckedRadioButtonId() != -1) {
                        lastCheckedRadioGroup.clearCheck();

                        Toast.makeText(CancelledTripReasonAdapter.this.context,
                                "cancelled reason clicked " + radioGroup.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();

                    }
                    lastCheckedRadioGroup = radioGroup;

                }
            });

        }
    }

    public interface OnCancelReasonTypeSelect {
        void onSelectedReasonType(String id, String type);
    }
}
