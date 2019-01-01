package com.videoapp.fagments;

import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.videoapp.adapter.DashboardAdapter;

import com.videoapp.activities.DashboardActivity;
import com.videoapp.R;
import com.videoapp.model.VideoListResponse;
import com.videoapp.retrofit.RestClient;
import com.videoapp.utill.AppUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment  {

    public DashboardActivity activity;
  /*  private ImageButton recordVideo;*/
    private DashboardAdapter adapter;
    private RadioGroup recordSelectionRadioGroup;
    private RadioButton selectedRadioButton;
    MediaRecorder recorder;
    SurfaceHolder holder;
    boolean recording = false;
    private com.videoapp.fagments.HomeFragment.OnFragmentInteractionListener mListener;
    private RecyclerView recyclerView;

    public DashboardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = v.findViewById(R.id.videos_recyclerview);
        return v;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.activity = (DashboardActivity) activity;
    }


    @Override
    public void onResume() {
        super.onResume();
       AppUtils.showProgressDialog(getActivity(),"Please wait...");
        RestClient.getVideoList(new Callback<VideoListResponse>() {
            @Override
            public void onResponse(Call<VideoListResponse> call, Response<VideoListResponse> response) {
              AppUtils.dismissProgressDialog();
                if (response!=null && response.body()!=null){

                    int numberOfColumns = 2;
                    recyclerView.setLayoutManager(new GridLayoutManager(activity, numberOfColumns));
                    adapter = new DashboardAdapter(getActivity(), response.body().getVideoList());
                    //adapter.setClickListener(this);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<VideoListResponse> call, Throwable t) {
                AppUtils.dismissProgressDialog();
                AppUtils.displayToast(getActivity(),"Unable to get videos, please try again later");

            }
        });
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


}
