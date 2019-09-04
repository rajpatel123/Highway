package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.highway.R;

import java.util.ArrayList;
import java.util.List;

import adapterstatically.UpComingFragmentAdapter;
import modelstatically.UpComingFragmentModelStatically;

public class UpComingFragment extends Fragment {

    private RecyclerView recyclerView;
    private UpComingFragmentAdapter upComingFragmentAdapter;
    private RecyclerView.Adapter UpComingFragmentAdapter;

    List<UpComingFragmentModelStatically> upComingFragmentModelStaticallies = new ArrayList<>();



    public UpComingFragment() {

    }


    /*public static UpComingFragment newInstance() {

        UpComingFragment fragment = new UpComingFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_up_coming, container, false);

        recyclerView =  view.findViewById(R.id.Upcoming_RecyclerView);
        return view;


    }

    @Override
    public void onResume() {
        super.onResume();

        showUpComingHistoryDetails();   // Implements recycler

    }

    public void showUpComingHistoryDetails(){


        if (upComingFragmentModelStaticallies!=null && upComingFragmentModelStaticallies.size()>0){

            Log.d("Data Size ",""+upComingFragmentModelStaticallies.size());

            upComingFragmentAdapter = new UpComingFragmentAdapter(getActivity(),upComingFragmentModelStaticallies,this);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(upComingFragmentAdapter);

            List<UpComingFragmentModelStatically> upComingFragmentModelStaticallyList = new ArrayList<>();

            for (int i = 0; i < 20; i++) {
                UpComingFragmentModelStatically upcominglist = new UpComingFragmentModelStatically();

               upcominglist.setUpcomingDateTv("30 Aug" + i);
               upcominglist.setUpcomingTimeTv("04:45 PM" + i);
               upcominglist.setUpcomingPickUpAddressTv("IBM India Pvt Ltd D3 Block Vittal Mallya rd ,Kg Halli,D'Souza Layout ,Ashok" + i);
               upcominglist.setUpcomingDeliverAddressTv("IBM India Pvt Ltd D3 Block Vittal Mallya rd ,Kg Halli,D'Souza Layout ,Ashok" + i);
               upcominglist.setUpcomingIndicationTv("UPCOMING" + i);
               upcominglist.setUpcomingVehicleNameTv("TATA ACE " + i);

               /*upcominglist.setVUpcomingImgImg().toString();
               upcominglist.setUpcomingDeliverImgImg().toString();*/

               upComingFragmentModelStaticallyList.add(upcominglist);
            }

            UpComingFragmentAdapter adapter = new UpComingFragmentAdapter(this, upComingFragmentModelStaticallyList);

            recyclerView.setAdapter(adapter);


        }

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
