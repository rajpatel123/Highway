package com.highwayjprproject.fragment.milluser;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.highwayjprproject.R;
import com.highwayjprproject.adapter.MillUserFragmentAdapter;

import java.util.ArrayList;
import java.util.List;

public class MillUserDashBoardFragment extends Fragment {

    private TabLayout millUsertabMode;
    private ViewPager millUserViewPager;

    List<Fragment>milluserfragmentlist = new ArrayList<>();

    public MillUserDashBoardFragment() {
        // Required empty public constructor
    }

    public static MillUserDashBoardFragment newInstance() {
        MillUserDashBoardFragment fragment = new MillUserDashBoardFragment();
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
        View view = inflater.inflate(R.layout.fragment_mill_user_dash_board, container, false);

        millUserViewPager = view.findViewById(R.id.millUserViewPager);
        millUsertabMode= view.findViewById(R.id.millUsertabMode);

        milluserfragmentlist.add(new MillUserUpComingFragment());
        milluserfragmentlist.add(new MillUserOnGoingFragment());
        milluserfragmentlist.add(new MillUserPendingFragment());
        milluserfragmentlist.add(new MillUserCompletedFragment());
        milluserfragmentlist.add(new MillUserCancelFragment());

        MillUserFragmentAdapter millUserFragmentAdapter = new MillUserFragmentAdapter(getActivity().getSupportFragmentManager(),milluserfragmentlist);

        millUserViewPager.setAdapter(millUserFragmentAdapter);
        millUsertabMode.setupWithViewPager(millUserViewPager);

        millUsertabMode.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
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
        millUserViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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
