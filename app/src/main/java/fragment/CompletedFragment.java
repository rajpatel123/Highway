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

import adapterstatically.CompletedFragmentAdapter;
import adapterstatically.UpComingFragmentAdapter;
import modelstatically.CompletedFragmentModelStatically;
import modelstatically.UpComingFragmentModelStatically;


public class CompletedFragment extends Fragment {

    private RecyclerView recyclerView;
    private CompletedFragmentAdapter completedFragmentAdapter;

    List<CompletedFragmentModelStatically> completedFragmentModelStaticallies = new ArrayList<>();



    public CompletedFragment() {
        // Required empty public constructor
    }


  /*  public static CompletedFragment newInstance() {
        CompletedFragment fragment = new CompletedFragment();
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
        View view = inflater.inflate(R.layout.fragment_completed, container, false);
        recyclerView =  view.findViewById(R.id.complete_RecyclerView);

        preparelist();

        return view;


    }

    public void preparelist(){

        completedFragmentModelStaticallies = new ArrayList<>();

        CompletedFragmentModelStatically completedFragmentModelStatically = new CompletedFragmentModelStatically();

        completedFragmentModelStatically.setTvNOCompleted("NoCompletedBooking");
        completedFragmentModelStatically.setTvPresent("Present");
       // completedFragmentModelStatically.setTransportImgView("");
        completedFragmentModelStaticallies.add(completedFragmentModelStatically);
    }

    @Override
    public void onResume() {
        super.onResume();

        showCompleteHistoryDetails();   // Implements recycler

    }

    public void showCompleteHistoryDetails(){

        if (completedFragmentModelStaticallies !=null && completedFragmentModelStaticallies.size()>0){

            Log.d("Data Size ",""+completedFragmentModelStaticallies.size());

            completedFragmentAdapter = new CompletedFragmentAdapter(getActivity(),completedFragmentModelStaticallies);

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(completedFragmentAdapter);

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
