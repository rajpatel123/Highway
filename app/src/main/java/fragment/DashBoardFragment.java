package fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.highway.R;

import java.util.ArrayList;
import java.util.List;

import adapter.MyBookingFragmentPagerAdapter;


public class DashBoardFragment extends Fragment {

    private OnFragmentInteractionListener mListener;
    private TabLayout myBookingTabLayout;
    private ViewPager myBookingViewPager;

    List<Fragment> fragmentList = new ArrayList<>();
    private String supportFragmentManager;

    public DashBoardFragment() {
    }

    public static DashBoardFragment newInstance() {
        DashBoardFragment fragment = new DashBoardFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dash_board, container, false);

        myBookingTabLayout = view.findViewById(R.id.tabModeOfMyBooking);
        myBookingViewPager = view.findViewById(R.id.myBookingViewpager);

        addingFragmentWithViewPagerOnMyBooking();

        return view;
    }

    public void addingFragmentWithViewPagerOnMyBooking(){
        fragmentList.add(new OnGoingFragment());
        fragmentList.add(new UpComingFragment());
        fragmentList.add(new CompletedFragment());
        fragmentList.add(new CanceledFragment());

        MyBookingFragmentPagerAdapter myBookingFragmentPagerAdapter = new MyBookingFragmentPagerAdapter(getSupportFragmentManager(),fragmentList);

        myBookingTabLayout.setupWithViewPager(myBookingViewPager);
        myBookingViewPager.setAdapter(myBookingFragmentPagerAdapter);

        myBookingTabLayout.setOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        myBookingViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }
            @Override
            public void onPageSelected(int i) {
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    public String getSupportFragmentManager() {
        return supportFragmentManager;
    }

    public void setSupportFragmentManager(String supportFragmentManager) {
        this.supportFragmentManager = supportFragmentManager;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
