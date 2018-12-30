package com.videoapp.fagments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import com.videoapp.activities.Invite;
import com.videoapp.activities.My_vedio_edit_profife;
import com.videoapp.R;
import com.videoapp.activities.rate;
import com.videoapp.activities.wallet;
import com.videoapp.model.DataNote;
import com.videoapp.model.DataNoteInformation;
import com.videoapp.shared_preferences.SessionManager;


public class ProfileFragment extends Fragment {
    private TextView mTextViewEmpty;
    private ProgressBar mProgressBarLoading;
    private ImageView mImageViewEmpty;
    ImageView imageView_logout;
    SessionManager session;
    Button logout_;
    RelativeLayout invite;
    RelativeLayout l;
    RelativeLayout vedio;
    RelativeLayout Wallet;
    RelativeLayout rating;
    RelativeLayout setting;
    private TextView countertext;
    private TextView plusText1;
    private int counter;
    private OnFragmentInteractionListener mListener;

    public ProfileFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.plusText:
                    plusCounter();
                    break;
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        session = new SessionManager(getActivity());
        invite = view.findViewById(R.id.relinvite);
        countertext = view.findViewById(R.id.counter_text);
        plusText1 = view.findViewById(R.id.plusText);
        initCounter();
        plusText1.setOnClickListener(onClickListener);
        Wallet=view.findViewById(R.id.relwallet);
        Wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),wallet.class);
                getActivity().startActivity(i);
            }
        });
        vedio=view.findViewById(R.id.Relmyvedio);
        vedio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),My_vedio_edit_profife.class);
                getActivity().startActivity(i);
            }
        });
        rating=view.findViewById(R.id.relrate);
        rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),rate.class);
                getActivity().startActivity(i);
            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),Invite.class);
                getActivity().startActivity(i);
            }
        });
        l=view.findViewById(R.id.relfeedback);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent(Intent.ACTION_SEND);
                sendIntent.setType("plain/text");;
                sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[] { "aap_feedback@Dev vedio.com" });
                sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback from Anonymous");
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Dev Vedio:Android:Build 302(2.3.5) device:vivo v3 Android version: 5.1.1 user Anonymous)");
                startActivity(sendIntent);

            }
        });
        setting=view.findViewById(R.id.relsetting);
        setting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //imageView_logout=view.findViewById(R.id.logout);
        logout_ = view.findViewById(R.id.logout_);

        logout_.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                session.logoutUser();
            }
        });
//
        /*imageView_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logoutUser();
                //Toast.makeText(getActivity(), "this is logout", Toast.LENGTH_SHORT).show();
            }
        });*/

        logout_ = view.findViewById(R.id.logout_);


        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        ArrayList data = new ArrayList<DataNote>();
        for (int i = 0; i < DataNoteInformation.dateArray.length; i++) {
            data.add(
                    new DataNote
                            (
                                    DataNoteInformation.textArray[i],
                                    DataNoteInformation.id[i],
                                    DataNoteInformation.dateArray[i]
                            ));
        }

        return view;



    }
    private void initCounter(){
        counter=0;
        countertext.setText(counter+"");
    }
    private void plusCounter(){
        counter++;
        countertext.setText(counter+"");
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }}







