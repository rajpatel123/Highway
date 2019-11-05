package com.highwayjprproject.fragment.customer;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.highwayjprproject.R;
import com.highwayjprproject.usermodule.useradapters.CustomerFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class CustomerDashBordFragment extends Fragment {
    private TabLayout myBookingTabLayout;
    private ViewPager myBookingViewPager;

    List<Fragment> customerfragmentList = new ArrayList<>();


    public static CustomerDashBordFragment newInstance() {
        CustomerDashBordFragment fragment = new CustomerDashBordFragment();
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
        View view = inflater.inflate(R.layout.fragment_customer_dash_bord, container, false);

        myBookingTabLayout = view.findViewById(R.id.tabModeOfMyBooking);
        myBookingViewPager = view.findViewById(R.id.myBookingViewPager);

        customerfragmentList.add(new CustomerUpCommingFragment());
        customerfragmentList.add(new CustomerOnGoingFragment());
        customerfragmentList.add(new CustomerPendingFragment());
        customerfragmentList.add(new CustomerCompletedFragment());
        customerfragmentList.add(new CustomerCancelFragment());

        CustomerFragmentAdapter fragmentAdapter = new CustomerFragmentAdapter(getActivity().
                getSupportFragmentManager(), customerfragmentList);

        myBookingViewPager.setAdapter(fragmentAdapter);
        myBookingTabLayout.setupWithViewPager(myBookingViewPager);

        myBookingTabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
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
        myBookingViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
