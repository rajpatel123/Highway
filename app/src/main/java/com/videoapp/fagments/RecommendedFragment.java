package com.videoapp.fagments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.videoapp.ItemDecor.ItemOffsetDecoration;
import com.videoapp.R;
import com.videoapp.activities.DashboardActivity;
import com.videoapp.activities.LoginActivity;
import com.videoapp.adapter.DashboardAdapter;
import com.videoapp.adapter.RecommendedAdapter;
import com.videoapp.model.Itemlistobject;
import com.videoapp.model.RecommendedApiResponse;
import com.videoapp.retrofit.RestClient;
import com.videoapp.utill.AppConstants;
import com.videoapp.utill.AppUtils;
import com.videoapp.utill.VideoAppPrefs;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecommendedFragment extends Fragment {

    RecommendedAdapter adapter;
    List<Itemlistobject> list = new ArrayList<>();
    public DashboardActivity activity;
    RecyclerView rvRecommended;
    SharedPreferences preferences;

    public RecommendedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        preferences = this.getActivity().getSharedPreferences("Prefs", Context.MODE_PRIVATE);

        View view = inflater.inflate(R.layout.fragment_recommended, container, false);
        rvRecommended = (RecyclerView) view.findViewById(R.id.rvRecommended);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        AppUtils.showProgressDialog(activity, "Please wait...");

        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        StringRequest request = new StringRequest(Request.Method.POST, "http://akwebtech.com/dev/api/api.php?req=recommended",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        AppUtils.dismissProgressDialog();
                        if (response != null){

                            try{
                                JSONObject jsonObject = new JSONObject(response);
                                JSONArray jsonArray = jsonObject.getJSONArray("detail");
                                for (int i = 0; i < jsonArray.length(); i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    Itemlistobject items = new Itemlistobject();
                                    items.setName(object.getString("user_name"));
                                    list.add(items);

                                }

                            }catch (Exception e){
                                e.printStackTrace();
                            }


                            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(activity);
                            rvRecommended.setLayoutManager(layoutManager);
                            rvRecommended.setItemAnimator(new DefaultItemAnimator());
                            adapter = new RecommendedAdapter(activity, list);
                            //adapter.setClickListener(this);
                            rvRecommended.setAdapter(adapter);

                            adapter.notifyDataSetChanged();

                        }
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                AppUtils.dismissProgressDialog();
                Toast.makeText(activity, "Error loading data!", Toast.LENGTH_SHORT).show();
            }
        })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("user_id", preferences.getString("USER_ID", null));
                return map;
            }
        };

        requestQueue.add(request);




    }



    @Override
    public void onResume() {
        super.onResume();



    }





    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.activity = (DashboardActivity) activity;


    }



}
