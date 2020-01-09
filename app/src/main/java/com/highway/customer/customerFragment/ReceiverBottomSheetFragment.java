package com.highway.customer.customerFragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.highway.R;

public class ReceiverBottomSheetFragment extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";
    EditText edtTextMobNo,edtName;

    public ReceiverBottomSheetFragment() {

    }

    public static ReceiverBottomSheetFragment newInstance() {
        ReceiverBottomSheetFragment fragment = new ReceiverBottomSheetFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_receiver_bottim_sheet, container, false);

        edtTextMobNo = view.findViewById(R.id.mobileNumber);
        edtName = view.findViewById(R.id.user_name);

        return view;
    }

    public void initview(){

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void show(FragmentManager supportFragmentManager, Object tag) {

    }
}
