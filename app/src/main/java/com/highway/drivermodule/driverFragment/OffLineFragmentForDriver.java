package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.highway.R;

public class OffLineFragmentForDriver extends Fragment {

    Button goOnlineBtn;
    TextView status;

    private OnFragmentInteractionListener mListener;

    public OffLineFragmentForDriver() {
        // Required empty public constructor
    }

    public static OffLineFragmentForDriver newInstance() {
        OffLineFragmentForDriver fragment = new OffLineFragmentForDriver();
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
        View view =  inflater.inflate(R.layout.fragment_off_line_fragment_for_driver, container, false);
        goOnlineBtn = view.findViewById(R.id.go_online_btn);
        status = view.findViewById(R.id.status);

        return view;
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

    public interface OnFragmentInteractionListener {

        void onFragmentInteraction(Uri uri);
    }
}
