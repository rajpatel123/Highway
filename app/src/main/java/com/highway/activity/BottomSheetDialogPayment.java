package com.highway.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.highway.R;

import fragment.NewBookingFragmentMapActivity;

public class BottomSheetDialogPayment extends NewBookingFragmentMapActivity {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View v = inflater.inflate(R.layout.bottomsheet_payment_dialog_layout,container,false);
       return v;
    }
}
