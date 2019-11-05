package com.highwayjprproject.fragment.Vehicle_Owner;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.highwayjprproject.R;
import com.highwayjprproject.adapter.MillUserFragmentAdapter;
import com.highwayjprproject.adapter.VehicleOwnerAdapter;
import com.highwayjprproject.fragment.milluser.MillUserCancelFragment;
import com.highwayjprproject.fragment.milluser.MillUserCompletedFragment;
import com.highwayjprproject.fragment.milluser.MillUserOnGoingFragment;
import com.highwayjprproject.fragment.milluser.MillUserPendingFragment;
import com.highwayjprproject.fragment.milluser.MillUserUpComingFragment;

import java.util.ArrayList;
import java.util.List;

public class VehicleDashBoardFragment extends Fragment {

    private TabLayout vehicleOwnerTabMode;
    private ViewPager vehicleOwnerViewPager;

    List<Fragment> vehicleOwnerfragmentlist = new ArrayList<>();

    public VehicleDashBoardFragment() {
    }

    public static VehicleDashBoardFragment newInstance(String param1, String param2) {
        VehicleDashBoardFragment fragment = new VehicleDashBoardFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_vehicle_dash_board, container, false);

        vehicleOwnerViewPager = view.findViewById(R.id.VehicleOwnerViewPager);
        vehicleOwnerTabMode= view.findViewById(R.id.VehicleOwnertabMode);

        vehicleOwnerfragmentlist.add(new VehicleUpComingFragment());
        vehicleOwnerfragmentlist.add(new VehicleOnGoingFragment());
        vehicleOwnerfragmentlist.add(new VehiclePendingFragment());
        vehicleOwnerfragmentlist.add(new VehicleCompletedFragment());
        vehicleOwnerfragmentlist.add(new VehicleCancelFragment());

        VehicleOwnerAdapter vehicleOwnerAdapter = new VehicleOwnerAdapter(getActivity().getSupportFragmentManager(),vehicleOwnerfragmentlist);

        vehicleOwnerViewPager.setAdapter(vehicleOwnerAdapter);
        vehicleOwnerTabMode.setupWithViewPager(vehicleOwnerViewPager);


        vehicleOwnerTabMode.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        vehicleOwnerViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
