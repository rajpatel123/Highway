package com.highway.millUserModule.milluserFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsRequest;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.AllHighwayTripsResponse;
import com.highway.commonretrofit.RestClient;
import com.highway.millUserModule.milluserAdapter.FragmentTabModeAdapterForMiller;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashBoardFragmentForMillUser extends Fragment {

    private TabLayout millUsertabMode;
    private ViewPager millUserViewPager;
    DashBoardActivity dashBoardActivity;
    CancelFragmentForMillUser cancelFragmentForMillUser;
    CompletedFragmentForMillUser completedFragmentForMillUser;
    OnGoingFragmentForMillUser onGoingFragmentForMillUser;
    PendingFragmentForMillUser pendingFragmentForMillUser;
    UpComingFragmentForMillUser upComingFragmentForMillUser;

    List<Fragment>milluserfragmentlist = new ArrayList<>();
    private String userId;

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

        upComingFragmentForMillUser = new UpComingFragmentForMillUser();
        onGoingFragmentForMillUser = new OnGoingFragmentForMillUser();
        pendingFragmentForMillUser = new PendingFragmentForMillUser();
        completedFragmentForMillUser = new CompletedFragmentForMillUser();
        cancelFragmentForMillUser = new CancelFragmentForMillUser();

        milluserfragmentlist.add(upComingFragmentForMillUser);
        milluserfragmentlist.add(onGoingFragmentForMillUser);
        milluserfragmentlist.add(pendingFragmentForMillUser);
        milluserfragmentlist.add(completedFragmentForMillUser);
        milluserfragmentlist.add(cancelFragmentForMillUser);

        FragmentTabModeAdapterForMiller fragmentTabModeAdapterForMiller = new FragmentTabModeAdapterForMiller(getActivity()
                .getSupportFragmentManager(),milluserfragmentlist);

        millUserViewPager.setAdapter(fragmentTabModeAdapterForMiller);
        millUsertabMode.setupWithViewPager(millUserViewPager);
        millUserViewPager.setOffscreenPageLimit(5);

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
        dashBoardActivity = (DashBoardActivity) getActivity();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        getMillUserCompletedDetail();
    }

    public void getMillUserCompletedDetail(){
        AllHighwayTripsRequest allHighwayTripsRequest = new AllHighwayTripsRequest();
        userId = HighwayPrefs.getString(getContext(), Constants.ID);
        /* allHighwayTripsRequest.setUserId(userId);*/
        allHighwayTripsRequest.setUserId("5");

       RestClient.allMillerTrip(allHighwayTripsRequest, new Callback<AllHighwayTripsResponse>() {
           @Override
           public void onResponse(Call<AllHighwayTripsResponse> call, Response<AllHighwayTripsResponse> response) {

               if (response.body() != null) {
                   if (response.body().getStatus()) {
                       AllHighwayTripsResponse allHighwayTripsResponse = response.body();
                       if (allHighwayTripsResponse != null) {
                           if (allHighwayTripsResponse.getCompletedTrips() != null && allHighwayTripsResponse.getCompletedTrips().size() > 0) {
                               dashBoardActivity.setCompletedTrips(allHighwayTripsResponse.getCompletedTrips());
                           }
                           if (allHighwayTripsResponse.getOngoingTrips() != null && allHighwayTripsResponse.getOngoingTrips().size() > 0) {
                               dashBoardActivity.setOngoingTrips(allHighwayTripsResponse.getOngoingTrips());
                           }
                           if (allHighwayTripsResponse.getUpcomingTrips() != null && allHighwayTripsResponse.getUpcomingTrips().size() > 0) {
                               dashBoardActivity.setUpcomingTrips(allHighwayTripsResponse.getUpcomingTrips());
                           }
                           if (allHighwayTripsResponse.getCancelTrips() != null && allHighwayTripsResponse.getCancelTrips().size() > 0) {
                               dashBoardActivity.setCancelTrips(allHighwayTripsResponse.getCancelTrips());
                           }
                           updateAllMillerFragment();
                       }
                   }
               } else {
                   Toast.makeText(dashBoardActivity, "Response failed", Toast.LENGTH_SHORT).show();
               }

           }

           @Override
           public void onFailure(Call<AllHighwayTripsResponse> call, Throwable t) {
               Toast.makeText(dashBoardActivity, "failed", Toast.LENGTH_SHORT).show();

           }
       });

    }

    public void updateAllMillerFragment() {
        completedFragmentForMillUser.millerCompletedUpdatedTripList(dashBoardActivity.getCompletedTrips());
        onGoingFragmentForMillUser.millerOnGoingdUpdatedTripList(dashBoardActivity.getOngoingTrips());
        cancelFragmentForMillUser.millerCancleUpdatedTripList(dashBoardActivity.getCancelTrips());
        upComingFragmentForMillUser.millerUpCOmingUpdatedTripList(dashBoardActivity.getUpcomingTrips());

    }
}
