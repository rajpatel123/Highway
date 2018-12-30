package com.videoapp.fagments;

import android.content.Context;
import android.media.MediaRecorder;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.VideoView;


import com.videoapp.adapter.DashboardAdapter;

import com.videoapp.activities.MainActivity;
import com.videoapp.R;


public class DashboardFragment extends Fragment  {

    public MainActivity activity;
  /*  private ImageButton recordVideo;*/
    private DashboardAdapter adapter;
    private RadioGroup recordSelectionRadioGroup;
    private RadioButton selectedRadioButton;
    MediaRecorder recorder;
    SurfaceHolder holder;
    boolean recording = false;
    private com.videoapp.fagments.HomeFragment.OnFragmentInteractionListener mListener;

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

        String videoLink = "";//"android.resource://" + activity.getPackageName() + "/" + R.raw.videoplayback;

      /*  recordVideo = v.findViewById(R.id.record_video);*/

        RecyclerView recyclerView = v.findViewById(R.id.videos_recyclerview);
        int numberOfColumns = 2;
        recyclerView.setLayoutManager(new GridLayoutManager(activity, numberOfColumns));
        adapter = new DashboardAdapter(getActivity(), videoLink);
        //adapter.setClickListener(this);
        recyclerView.setAdapter(adapter);

        /*gridView = v.findViewById(R.id.gridview);

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getContext(), Main2Activity.class);
                startActivity(intent);

            }
        });*/

     /*   recordVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                final Dialog dialog = new Dialog(activity);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.record_video_time);
                //dialog.setTitle("Your Title goes here");

                Button dialogButton = (Button) dialog.findViewById(R.id.button_ok);

                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recordSelectionRadioGroup = dialog.findViewById(R.id.record_video_radio_group);
                        int selectedId = recordSelectionRadioGroup.getCheckedRadioButtonId();

                        selectedRadioButton = dialog.findViewById(selectedId);
                        Toast.makeText(activity,selectedRadioButton.getText().toString(),Toast.LENGTH_SHORT).show();

                        int time = 0;

                        if(selectedRadioButton.getText().toString().equals("30 Seconds"))
                            time = 30;
                        else if(selectedRadioButton.getText().toString().equals("60 Seconds"))
                            time = 60;
                        else if(selectedRadioButton.getText().toString().equals("90 Seconds"))
                            time = 90;

                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        intent.putExtra(MediaStore.EXTRA_DURATION_LIMIT, time);
                        intent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                        startActivityForResult(intent, 100);

                        dialog.dismiss();
                    }
                });

                dialog.show();
                Window window = dialog.getWindow();
                window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            }
        });*/

        return v;
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);
        this.activity = (MainActivity) activity;
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return 8;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.row_data, null);
            //getting view in row_data
            VideoView videoView = view1.findViewById(R.id.image_feeds);

            String link1 = "android.resource://" + activity.getPackageName() + "/" + R.raw.videoplayback;
            Uri uri = Uri.parse(link1);
            videoView.setVideoURI(uri);
            videoView.start();
            /*MediaController mediaController = new MediaController(getContext());
            videoView.setMediaController(mediaController);
            mediaController.setAnchorView(videoView);*/

            /*ImageView image = view1.findViewById(R.id.image_feeds);
            image.setImageResource(fruitImages[i]);*/
            return view1;
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

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
