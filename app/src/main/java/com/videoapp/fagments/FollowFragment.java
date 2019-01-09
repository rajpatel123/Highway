package com.videoapp.fagments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.videoapp.R;
import com.videoapp.activities.DashboardActivity;
import com.videoapp.adapter.FollowAdapter;
import com.videoapp.adapter.RecommendedAdapter;
import com.videoapp.model.Itemlistobject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FollowFragment extends Fragment {

    FollowAdapter adapter;
    List<Itemlistobject> list = new ArrayList<>();
    public DashboardActivity activity;
    RecyclerView rvFollow;

    List<String> arrProfilePics = new ArrayList<>(Arrays.asList("https://www.sheffield.ac.uk/polopoly_fs/1.739455!/image/A_Cochrane_300x300.jpg",
            "https://andersonstrathern.co.uk/uploads/images/ws_cropper/4_0x0_439x439_440x440_bruce_farquhar.jpg",
            "https://www.victoria.ac.nz/images/staffpics/jason-young.jpg",
            "https://st.depositphotos.com/1008939/1880/i/450/depositphotos_18807295-stock-photo-portrait-of-handsome-man.jpg",
            "https://warwick.ac.uk/fac/soc/economics/staff/aadvani/profile.jpg",
            "https://files.list.co.uk/images/2018/03/23/295e8e8950efadea7ae9acbe51572667b7b43270-original-LST284579.jpg",
            "https://st.depositphotos.com/1008939/1880/i/450/depositphotos_18807295-stock-photo-portrait-of-handsome-man.jpg",
            "https://warwick.ac.uk/fac/soc/economics/staff/aadvani/profile.jpg"));

    List<String> arrNames = new ArrayList<>(Arrays.asList("John Marley", "Steven Grace", "Charlie Harper", "Alan Harper", "Jeff Strongman",
            "Richard Gracio", "Jacob Muff", "Andrew Phill"));

    List<String> arrDesc = new ArrayList<>(Arrays.asList("hello", "hi", "", "", "make friends", "hello again", "remarkable", "thats it"));

    public FollowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_follow, container, false);
        rvFollow = (RecyclerView) view.findViewById(R.id.rvFollow);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
        rvFollow.setLayoutManager(layoutManager);
        rvFollow.setItemAnimator(new DefaultItemAnimator());
        adapter = new FollowAdapter(activity, list);
        //adapter.setClickListener(this);
        rvFollow.setAdapter(adapter);

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.activity = (DashboardActivity) activity;

    }

    @Override
    public void onResume() {
        super.onResume();

//        for (int i = 0; i < arrProfilePics.size(); i++){
//            Itemlistobject items = new Itemlistobject();
//            items.setPhoto(arrProfilePics.get(i));
//            items.setName(arrNames.get(i));
//            items.setDesc(arrDesc.get(i));
//            list.add(items);
//        }

    }


}
