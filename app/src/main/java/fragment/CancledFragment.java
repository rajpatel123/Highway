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

import adapterstatically.CancledFragmentAdapter;
import adapterstatically.UpComingFragmentAdapter;
import modelstatically.CancledFragmentModelStatically;

public class CancledFragment extends Fragment {

    private RecyclerView recyclerView;

    private CancledFragmentAdapter cancledFragmentAdapter;

    List<CancledFragmentModelStatically> cancledFragmentModelStaticallies = new ArrayList<>();


    public CancledFragment() {
        // Required empty public constructor
    }


  /*  public static CancledFragment newInstance() {
        CancledFragment fragment = new CancledFragment();
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
        View view = inflater.inflate(R.layout.fragment_cancled, container, false);

        recyclerView = view.findViewById(R.id.Canceled_RecyclerView);

        prepareList();
        return view;
    }

    public void prepareList() {
        cancledFragmentModelStaticallies = new ArrayList<>();

        CancledFragmentModelStatically cancledFragmentModelStatically = new CancledFragmentModelStatically();

        cancledFragmentModelStatically.setTvNoCancled("No Canceled Booking");
        cancledFragmentModelStatically.setTvCancledPresent("Present");

        cancledFragmentModelStaticallies.add(cancledFragmentModelStatically);

    }

    @Override
    public void onResume() {
        super.onResume();

        showCancledHistoryDetails();   // Implements recycler

    }

    public void showCancledHistoryDetails() {

        if (cancledFragmentModelStaticallies!=null && cancledFragmentModelStaticallies.size()>0){

            Log.d("Data Size ",""+cancledFragmentModelStaticallies.size());

            cancledFragmentAdapter = new CancledFragmentAdapter(cancledFragmentModelStaticallies, getActivity());

            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
            recyclerView.setLayoutManager(mLayoutManager);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setAdapter(cancledFragmentAdapter);
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
