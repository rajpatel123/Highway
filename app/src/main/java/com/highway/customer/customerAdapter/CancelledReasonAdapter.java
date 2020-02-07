package com.highway.customer.customerAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;

import java.util.List;

public class CancelledReasonAdapter extends RecyclerView.Adapter<CancelledReasonAdapter.MyViewHolder> {
    Context context;

    public CancelledReasonAdapter(Context context1) {
        this.context = context1;
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

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private RadioButton canRsnRdBtn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            canRsnRdBtn = itemView.findViewById(R.id.radioButton1);
        }
    }
}
