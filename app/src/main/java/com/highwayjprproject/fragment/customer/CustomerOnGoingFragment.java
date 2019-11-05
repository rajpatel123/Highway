package com.highwayjprproject.fragment.customer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.highwayjprproject.R;


public class CustomerOnGoingFragment extends Fragment {
    private RecyclerView ongoingRecycler;

    public CustomerOnGoingFragment() {
        // Required empty public constructor
    }


    public static CustomerOnGoingFragment newInstance() {
        CustomerOnGoingFragment fragment = new CustomerOnGoingFragment();
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
       View  view = inflater.inflate(R.layout.fragment_customer_on_going, container, false);
        ongoingRecycler = view.findViewById(R.id.onGoingRecyclerView);
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
