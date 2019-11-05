package com.highwayjprproject.fragment.customer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.highwayjprproject.R;

public class CustomerCancelFragment extends Fragment {
    private RecyclerView cancleRecycler;


    public CustomerCancelFragment() {
        // Required empty public constructor
    }


    public static CustomerCancelFragment newInstance(String param1, String param2) {
        CustomerCancelFragment fragment = new CustomerCancelFragment();
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
        View view= inflater.inflate(R.layout.fragment_customer_cancel, container, false);

        cancleRecycler = view.findViewById(R.id.Cancled_RecyclerView);
       // prepareList();
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
