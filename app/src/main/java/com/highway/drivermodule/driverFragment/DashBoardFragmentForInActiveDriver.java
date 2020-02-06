package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;

import java.util.ArrayList;
import java.util.List;


public class DashBoardFragmentForInActiveDriver extends Fragment {

    private TabLayout driverTabLayout;
    private ViewPager driverViewPager;

    DashBoardActivity dashBoardActivity;


    List<Fragment> driverFragmentList = new ArrayList<>();
    private String userId;

    public DashBoardFragmentForInActiveDriver() {
        // Required empty public constructor
    }


    public static DashBoardFragmentForDriver newInstance() {
        DashBoardFragmentForDriver fragment = new DashBoardFragmentForDriver();
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
        View view = inflater.inflate(R.layout.fragment_driver_inactive, container, false);

        driverTabLayout = view.findViewById(R.id.drivertabMode);
        driverViewPager = view.findViewById(R.id.driverViewPager);

        view.findViewById(R.id.clickToGoOnline).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(dashBoardActivity, "Your account has not been approved for trips", Toast.LENGTH_LONG).show();
            }
        });

        return view;

    }
/*
    @Override
    public void onStart() {
        super.onStart();
        getDriverCompletedDetail();

    }

    }*/

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashBoardActivity = (DashBoardActivity) getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

}
