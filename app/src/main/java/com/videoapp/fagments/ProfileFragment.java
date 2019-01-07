package com.videoapp.fagments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
import android.net.Uri;
import android.os.AsyncTask;
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

import java.net.URL;
import java.util.ArrayList;

import com.videoapp.activities.InviteActivity;
import com.videoapp.activities.LoginActivity;
import com.videoapp.activities.My_vedio_edit_profife;
import com.videoapp.R;
import com.videoapp.activities.RateUsActivity;
import com.videoapp.activities.wallet;
import com.videoapp.model.DataNote;
import com.videoapp.model.DataNoteInformation;
import com.videoapp.utill.AppConstants;
import com.videoapp.utill.VideoAppPrefs;


public class ProfileFragment extends Fragment {
    private TextView mTextViewEmpty;
    private ProgressBar mProgressBarLoading;
    private ImageView mImageViewEmpty;
    ImageView imageView_logout, profile_image;
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
        invite = view.findViewById(R.id.relinvite);
        countertext = view.findViewById(R.id.counter_text);
        plusText1 = view.findViewById(R.id.plusText);
        profile_image = (ImageView) view.findViewById(R.id.profile_image);
        initCounter();

        new AsyncTask<Void,Void,Bitmap>(){
            Bitmap targetBitmap=null;
            @Override
            protected Bitmap doInBackground(Void... voids) {
                try {
                    URL url = new URL("https://www.victoria.ac.nz/images/staffpics/jason-young.jpg");
                    Bitmap image= BitmapFactory.decodeStream(url.openConnection().getInputStream());
                    int targetWidth = 250;
                    int targetHeight = 250;
                    Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                            targetHeight,Bitmap.Config.ARGB_8888);

                    Canvas canvas = new Canvas(targetBitmap);
                    Path path = new Path();
                    path.addCircle(((float) targetWidth - 1) / 2,
                            ((float) targetHeight - 1) / 2,
                            (Math.min(((float) targetWidth),
                                    ((float) targetHeight)) / 2),
                            Path.Direction.CCW);

                    canvas.clipPath(path);
                    Bitmap sourceBitmap = image;
                    canvas.drawBitmap(sourceBitmap,
                            new Rect(0, 0, sourceBitmap.getWidth(),
                                    sourceBitmap.getHeight()),
                            new Rect(0, 0, targetWidth, targetHeight), null);
                    return targetBitmap;
                } catch(Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Bitmap bitmap) {
                super.onPostExecute(bitmap);

                profile_image.setImageBitmap(bitmap);
            }
        }.execute();


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
                Intent i=new Intent(getActivity(),RateUsActivity.class);
                getActivity().startActivity(i);
            }
        });
        invite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getActivity(),InviteActivity.class);
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
                VideoAppPrefs.putString(getActivity(), AppConstants.CUSTOMER_ID,"");
                startActivity(new Intent(getActivity(), LoginActivity.class));
                getActivity().finish();
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







