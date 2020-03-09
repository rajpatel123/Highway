package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.drivermodule.updateTripStatusByDriver.UpdateTripStatusByDriverReq;
import com.highway.drivermodule.updateTripStatusByDriver.UpdateTripStatusByDriverResp;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InvoiceBottomDialogFragment extends BottomSheetDialogFragment
        implements View.OnClickListener {
    public static final String TAG = "ActionBottomDialog";
    private ItemClickListener mListener;
    private Button confirmPaymentBtn;
    public String userId;
    private String tripId;

    public static InvoiceBottomDialogFragment newInstance(String tripId) {
        InvoiceBottomDialogFragment invoiceBottomDialogFragment = new InvoiceBottomDialogFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tripId", tripId);
        invoiceBottomDialogFragment.setArguments(bundle);

        return invoiceBottomDialogFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice_dialog, container, false);
        confirmPaymentBtn = view.findViewById(R.id.btnConfirmPayment);


        if (getArguments() != null && TextUtils.isEmpty(getArguments().getString("tripId"))) {
            tripId = getArguments().getString("tripId");
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        confirmPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afterCmpltRidDriverStatus();


            }
        });
    }

    private void afterCmpltRidDriverStatus() {
        userId = HighwayPrefs.getString(getActivity(), Constants.ID);
        UpdateTripStatusByDriverReq updateTripStatusByDriverReq = new UpdateTripStatusByDriverReq();
        updateTripStatusByDriverReq.setDriverId(HighwayPrefs.getString(getContext(), Constants.ID));
        updateTripStatusByDriverReq.setTripId(tripId);
        updateTripStatusByDriverReq.setTRIPSTATS(Constants.INVOICE);
        updateTripStatusByDriverReq.setUpdatedAt("" + System.currentTimeMillis());

        RestClient.updateTripStatusByDriver(updateTripStatusByDriverReq, new Callback<UpdateTripStatusByDriverResp>() {
            @Override
            public void onResponse(Call<UpdateTripStatusByDriverResp> call, Response<UpdateTripStatusByDriverResp> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        ((DashBoardActivity) getActivity()).showratingBottomSheet(tripId);
                        dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateTripStatusByDriverResp> call, Throwable t) {
                Toast.makeText(getActivity(), "failure!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        TextView tvSelected = (TextView) view;
        mListener.onItemClick(tvSelected.getText().toString());
        dismiss();
    }

    public interface ItemClickListener {
        void onItemClick(String item);
    }
}