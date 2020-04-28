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
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.GetAllTripByUserIdRequest;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userTrip.GetAllTripByUserIdResponse;
import com.highway.commonretrofit.RestClient;
import com.highway.millUserModule.milluserActivity.MillUserAllTripActivity;
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
    MillUserAllTripActivity millUserAllTripActivity;

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
      //  pendingFragmentForMillUser = new PendingFragmentForMillUser();
        completedFragmentForMillUser = new CompletedFragmentForMillUser();
        cancelFragmentForMillUser = new CancelFragmentForMillUser();

        milluserfragmentlist.add(upComingFragmentForMillUser);
        milluserfragmentlist.add(onGoingFragmentForMillUser);
       // milluserfragmentlist.add(pendingFragmentForMillUser);
        milluserfragmentlist.add(completedFragmentForMillUser);
        milluserfragmentlist.add(cancelFragmentForMillUser);

        FragmentTabModeAdapterForMiller fragmentTabModeAdapterForMiller = new FragmentTabModeAdapterForMiller(getActivity()
                .getSupportFragmentManager(),milluserfragmentlist);

        millUserViewPager.setAdapter(fragmentTabModeAdapterForMiller);
        millUsertabMode.setupWithViewPager(millUserViewPager);
        millUserViewPager.setOffscreenPageLimit(4);

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
        //dashBoardActivity = (DashBoardActivity) getActivity();
        millUserAllTripActivity = (MillUserAllTripActivity) getActivity();
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
        GetAllTripByUserIdRequest getAllTripByUserIdRequest = new GetAllTripByUserIdRequest();
        userId = HighwayPrefs.getString(getContext(), Constants.ID);
        /* allHighwayTripsRequest.setUserId(userId);*/
        getAllTripByUserIdRequest.setUserId("5");

       RestClient.allMillerTrip(getAllTripByUserIdRequest, new Callback<GetAllTripByUserIdResponse>() {
           @Override
           public void onResponse(Call<GetAllTripByUserIdResponse> call, Response<GetAllTripByUserIdResponse> response) {

               if (response.body() != null) {
                   if (response.body().getStatus()) {
                       GetAllTripByUserIdResponse getAllTripByUserIdResponse = response.body();
                       if (getAllTripByUserIdResponse != null) {
                           if (getAllTripByUserIdResponse.getCompletedTrips() != null && getAllTripByUserIdResponse.getCompletedTrips().size() > 0) {
                               millUserAllTripActivity.setCompletedTrips(getAllTripByUserIdResponse.getCompletedTrips());
                           }
                           if (getAllTripByUserIdResponse.getOngoingTrips() != null && getAllTripByUserIdResponse.getOngoingTrips().size() > 0) {
                               millUserAllTripActivity.setOngoingTrips(getAllTripByUserIdResponse.getOngoingTrips());
                           }
                           if (getAllTripByUserIdResponse.getUpcomingTrips() != null && getAllTripByUserIdResponse.getUpcomingTrips().size() > 0) {
                               millUserAllTripActivity.setUpcomingTrips(getAllTripByUserIdResponse.getUpcomingTrips());
                           }
                           if (getAllTripByUserIdResponse.getCancelTrips() != null && getAllTripByUserIdResponse.getCancelTrips().size() > 0) {
                               millUserAllTripActivity.setCancelTrips(getAllTripByUserIdResponse.getCancelTrips());
                           }
                           updateAllMillerFragment();
                       }
                   }
               } else {
                   Toast.makeText(millUserAllTripActivity, "Response failed", Toast.LENGTH_SHORT).show();
               }

           }

           @Override
           public void onFailure(Call<GetAllTripByUserIdResponse> call, Throwable t) {
               Toast.makeText(millUserAllTripActivity, "failed", Toast.LENGTH_SHORT).show();

           }
       });

    }

    public void updateAllMillerFragment() {
        completedFragmentForMillUser.millerCompletedUpdatedTripList(millUserAllTripActivity.getCompletedTrips());
        onGoingFragmentForMillUser.millerOnGoingdUpdatedTripList(millUserAllTripActivity.getOngoingTrips());
        cancelFragmentForMillUser.millerCancleUpdatedTripList(millUserAllTripActivity.getCancelTrips());
        upComingFragmentForMillUser.millerUpCOmingUpdatedTripList(millUserAllTripActivity.getUpcomingTrips());

    }
}
