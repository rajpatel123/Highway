package com.videoapp.fagments;

import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.videoapp.ItemDecor.ItemOffsetDecoration;
import com.videoapp.adapter.DashboardAdapter;

import com.videoapp.activities.DashboardActivity;
import com.videoapp.R;
import com.videoapp.model.VideoList;
import com.videoapp.model.VideoListResponse;
import com.videoapp.retrofit.RestClient;
import com.videoapp.utill.AppUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DashboardFragment extends Fragment {

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
    List<VideoList> videoLists = new ArrayList<>();

    List<String> arrThumb = new ArrayList<>(Arrays.asList("https://4.img-dpreview.com/files/p/E~TS590x0~articles/3925134721/0266554465.jpeg",
            "https://c1.staticflickr.com/3/2912/13981352255_fc59cfdba2_b.jpg",
            "https://www.ikea.com/ms/en_CA/img/koekken/seo_image/kitchen-design-ideas__grimslov_grey_750x400.jpg",
            "https://hips.hearstapps.com/hmg-prod.s3.amazonaws.com/images/small-kitchen-design-03-1502895000.jpg",
            "http://www.stepinit.com/wp-content/uploads/2013/06/Astonishing-Minimalist-White-Gray-Small-Kitchen-Design-Ideas.jpg",
            "https://images.meredith.com/content/dam/bhg/Images/2017/2/14/102788007.jpg.rendition.smallest.ss.jpg"));

    List<String> arrDescription = new ArrayList<>(Arrays.asList("", "Sample Text 1", "Sample Text 2", "Sample Text 3", "", "Sample Text 4"));
    List<String> arrProfilePics = new ArrayList<>(Arrays.asList("https://www.sheffield.ac.uk/polopoly_fs/1.739455!/image/A_Cochrane_300x300.jpg",
            "https://andersonstrathern.co.uk/uploads/images/ws_cropper/4_0x0_439x439_440x440_bruce_farquhar.jpg",
            "https://www.victoria.ac.nz/images/staffpics/jason-young.jpg",
            "https://st.depositphotos.com/1008939/1880/i/450/depositphotos_18807295-stock-photo-portrait-of-handsome-man.jpg",
            "https://warwick.ac.uk/fac/soc/economics/staff/aadvani/profile.jpg",
            "https://files.list.co.uk/images/2018/03/23/295e8e8950efadea7ae9acbe51572667b7b43270-original-LST284579.jpg"));

//    List<String> arrVideoURLs = new ArrayList<>(Arrays.asList(""))


    public DashboardFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerView = v.findViewById(R.id.videos_recyclerview);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(activity, numberOfColumns));
        ItemOffsetDecoration decoration = new ItemOffsetDecoration(activity, R.dimen.item_offset);
        recyclerView.addItemDecoration(decoration);
        adapter = new DashboardAdapter(activity, videoLists);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

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
//       AppUtils.showProgressDialog(getActivity(),"Please wait...");
//        RestClient.getVideoList(new Callback<VideoListResponse>() {
//            @Override
//            public void onResponse(Call<VideoListResponse> call, Response<VideoListResponse> response) {
//              AppUtils.dismissProgressDialog();
//                if (response!=null && response.body()!=null){
//
//                    int numberOfColumns = 2;
//                    recyclerView.setLayoutManager(new GridLayoutManager(activity, numberOfColumns));
//                    adapter = new DashboardAdapter(getActivity(), response.body().getVideoList());
//                    //adapter.setClickListener(this);
//                    recyclerView.setAdapter(adapter);
//                }
//            }
//
//            @Override
//            public void onFailure(Call<VideoListResponse> call, Throwable t) {
//                AppUtils.dismissProgressDialog();
//                AppUtils.displayToast(getActivity(),"Unable to get videos, please try again later");
//
//            }
//        });





        for (int i = 0; i < arrThumb.size(); i++) {
            VideoList list = new VideoList();
            list.setThumbs(arrThumb.get(i));
            list.setStrDesc(arrDescription.get(i));
            list.setProfilePics(arrProfilePics.get(i));
            videoLists.add(list);
        }





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
