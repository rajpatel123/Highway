package com.videoapp.fagments;

import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import com.videoapp.adapter.Notification_Adapter;
import com.videoapp.R;
import com.videoapp.model.Notification_Model;


public class NotificationsFragment extends Fragment {
    private List<Notification_Model> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private Notification_Adapter mAdapter;
    private OnFragmentInteractionListener mListener;

    public NotificationsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vv= inflater.inflate(R.layout.fragment_notifications, container, false);

        recyclerView = vv.findViewById(R.id.rec_notification);

        mAdapter = new Notification_Adapter(movieList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareMovieData();
        return vv;

    }

    private void prepareMovieData() {

        Notification_Model notification_model ;
        notification_model= new Notification_Model("Dev.in","1 day ago ","you got new follow ",R.drawable.logo);
        movieList.add(notification_model);
        notification_model= new Notification_Model("Dev.in","1 day ago ","you got new follow ",R.drawable.logo);
        movieList.add(notification_model);
        notification_model= new Notification_Model("Dev.in","1 day ago ","you got new follow ",R.drawable.logo);
        movieList.add(notification_model);
        notification_model= new Notification_Model("Dev.in","1 day ago ","you got new follow ",R.drawable.logo);
        movieList.add(notification_model);
        notification_model= new Notification_Model("Dev.in","1 day ago ","you got new follow ",R.drawable.logo);
        movieList.add(notification_model);
        notification_model= new Notification_Model("Dev.in","1 day ago ","you got new follow ",R.drawable.logo);
        movieList.add(notification_model);
        notification_model= new Notification_Model("Dev.in","1 day ago ","you got new follow ",R.drawable.logo);
        movieList.add(notification_model);
        notification_model= new Notification_Model("Dev.in","1 day ago ","you got new follow ",R.drawable.logo);
        movieList.add(notification_model);

        mAdapter.notifyDataSetChanged();}
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
