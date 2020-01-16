package com.highway.customer.customerFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.highway.R;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

public class ConformReceiverBottomSheetFragment extends BottomSheetDialogFragment {

    public static final Object TAG =  "ActionBottomDialog";

    ReceiverBottomSheetFragment receiverBottomSheetFragment;
    LinearLayout receiverLayout;

    public TextView  confReceiverMObNumTv ,editTV,cnfReceiverNameTv,cnfBookTv;
    public String receiverUserName, receiverUserMobNo;

    public ConformReceiverBottomSheetFragment() {
    }

    public static ConformReceiverBottomSheetFragment newInstance() {
        ConformReceiverBottomSheetFragment fragment = new ConformReceiverBottomSheetFragment();
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
        View view=  inflater.inflate(R.layout.fragment_conform_receiver_bottom_sheet, container, false);

        confReceiverMObNumTv = view.findViewById(R.id.conformMObNumTv);
        editTV = view.findViewById(R.id.EditTV);
        cnfReceiverNameTv = view.findViewById(R.id.ConformReceiverNameTv3);
        cnfBookTv = view.findViewById(R.id.conformBookTv);

        getCnfPhoneName();
        return view;

    }

    public void getCnfPhoneName(){
        receiverUserMobNo = HighwayPrefs.getString(getActivity(), Constants.RECEIVERPHONENO);
        receiverUserName = HighwayPrefs.getString(getActivity(), Constants.RECEIVERNAME);
        confReceiverMObNumTv.setText(receiverUserMobNo);
        cnfReceiverNameTv.setText(receiverUserName);

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
