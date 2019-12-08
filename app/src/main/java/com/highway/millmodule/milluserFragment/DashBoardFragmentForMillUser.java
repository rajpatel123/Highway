package com.highway.millmodule.milluserFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.highway.R;
import com.highway.millmodule.milluserAdapter.FragmentTabModeAdapterForMiller;

import java.util.ArrayList;
import java.util.List;

public class DashBoardFragmentForMillUser extends Fragment {

    private TabLayout millUsertabMode;
    private ViewPager millUserViewPager;

    List<Fragment>milluserfragmentlist = new ArrayList<>();

    public DashBoardFragmentForMillUser() {
        // Required empty public constructor
    }

    public static DashBoardFragmentForMillUser newInstance() {
        DashBoardFragmentForMillUser fragment = new DashBoardFragmentForMillUser();
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

        milluserfragmentlist.add(new UpComingFragmentForMillUser());
        milluserfragmentlist.add(new OnGoingFragmentForMillUser());
        milluserfragmentlist.add(new PendingFragmentForMillUser());
        milluserfragmentlist.add(new CompletedFragmentForMillUser());
        milluserfragmentlist.add(new CancelFragmentForMillUser());

        FragmentTabModeAdapterForMiller fragmentTabModeAdapterForMiller = new FragmentTabModeAdapterForMiller(getActivity().getSupportFragmentManager(),milluserfragmentlist);

        millUserViewPager.setAdapter(fragmentTabModeAdapterForMiller);
        millUsertabMode.setupWithViewPager(millUserViewPager);

        millUsertabMode.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
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
