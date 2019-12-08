package com.highway.millmodule.milluserFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.highway.R;


public class CompletedFragmentForMillUser extends Fragment {

    public CompletedFragmentForMillUser() {
        // Required empty public constructor
    }

    public static CompletedFragmentForMillUser newInstance(String param1, String param2) {
        CompletedFragmentForMillUser fragment = new CompletedFragmentForMillUser();
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
        return inflater.inflate(R.layout.fragment_mill_user_completed, container, false);
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
