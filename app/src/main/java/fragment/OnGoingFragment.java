package fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import adapterstatically.OnGoingFragmentAdapter;
import adapterstatically.UpComingFragmentAdapter;
import modelstatically.OnGoingFragmentModelStatically;

public class OnGoingFragment extends Fragment {

    private RecyclerView recyclerView;
    private OnGoingFragmentAdapter onGoingFragmentAdapter;


    List<OnGoingFragmentModelStatically> onGoingFragmentModelStaticallies = new ArrayList<>();


    public OnGoingFragment() {
        // Required empty public constructor
    }

  /*  public static OnGoingFragment newInstance() {
        OnGoingFragment fragment = new OnGoingFragment();
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
        View view =  inflater.inflate(R.layout.fragment_on_going, container, false);

        recyclerView = view.findViewById(R.id.OnGoing_RecyclerView);

        preparelist();
        return view;
    }



    @Override
    public void onResume(){
        super.onResume();
        onGoingHistoryDetails();

    }

    public void preparelist(){

        onGoingFragmentModelStaticallies = new ArrayList<>();

        OnGoingFragmentModelStatically onGoingFragmentModelStatically = new OnGoingFragmentModelStatically();

        onGoingFragmentModelStatically.setTvNoOnGoing("No OnGoing Booking ");
        onGoingFragmentModelStatically.setTvOnGoingPresent("Present ");

        onGoingFragmentModelStaticallies.add(onGoingFragmentModelStatically);

    }

    public  void onGoingHistoryDetails(){

        if (onGoingFragmentModelStaticallies!=null && onGoingFragmentModelStaticallies.size()>0){

            Log.d("Data Size ",""+onGoingFragmentModelStaticallies.size());

            onGoingFragmentAdapter = new OnGoingFragmentAdapter(onGoingFragmentModelStaticallies, getActivity());

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());

            recyclerView.setAdapter(onGoingFragmentAdapter);
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
