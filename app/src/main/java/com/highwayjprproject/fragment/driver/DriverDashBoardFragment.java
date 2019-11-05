package com.highwayjprproject.fragment.driver;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.highwayjprproject.R;
import com.highwayjprproject.adapter.DriverFragmentAdapter;

import java.util.ArrayList;
import java.util.List;


public class DriverDashBoardFragment extends Fragment {
    private TabLayout driverTabLayout;
    private ViewPager driverViewPager;
    List<Fragment> driverFragmentList = new ArrayList<>();

    public DriverDashBoardFragment() {
        // Required empty public constructor
    }


    public static DriverDashBoardFragment newInstance() {
        DriverDashBoardFragment fragment = new DriverDashBoardFragment();
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
        View view = inflater.inflate(R.layout.fragment_driver_dash_board, container, false);

        driverTabLayout = view.findViewById(R.id.drivertabMode);
        driverViewPager = view.findViewById(R.id.driverViewPager);

        driverFragmentList.add(new DriverUpComingFragment());
        driverFragmentList.add(new DriverOnGoingFragment());
        driverFragmentList.add(new DriverPendingFragment());
        driverFragmentList.add(new DriverCompletedFragment());
        driverFragmentList.add(new DriverCancelFragment());

        DriverFragmentAdapter driverFragmentAdapter = new DriverFragmentAdapter(getActivity().
                getSupportFragmentManager(),driverFragmentList);

        driverViewPager.setAdapter(driverFragmentAdapter);
        driverTabLayout.setupWithViewPager(driverViewPager);

        driverTabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
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
        driverViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
