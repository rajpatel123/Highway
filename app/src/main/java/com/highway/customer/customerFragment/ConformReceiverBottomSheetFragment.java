package com.highway.customer.customerFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.highway.R;

public class ConformReceiverBottomSheetFragment extends Fragment {

    public static final Object TAG =  "ActionBottomDialog";

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
        return inflater.inflate(R.layout.fragment_conform_receiver_bottom_sheet, container, false);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public void show(Object supportFragmentManager, Object tag) {

    }


    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
