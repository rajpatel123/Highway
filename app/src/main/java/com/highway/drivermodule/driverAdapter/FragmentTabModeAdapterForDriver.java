package com.highway.drivermodule.driverAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.highway.drivermodule.driverFragment.CancelFragmentForDriver;
import com.highway.drivermodule.driverFragment.CompletedFragmentForDriver;
import com.highway.drivermodule.driverFragment.OnGoingFragmentForDriver;
import com.highway.drivermodule.driverFragment.PendingFragmentForDriver;
import com.highway.drivermodule.driverFragment.UpComingFragmentForDriver;

import java.util.List;

public class FragmentTabModeAdapterForDriver extends FragmentPagerAdapter {
    List<Fragment>driverfragmentlist;

    public FragmentTabModeAdapterForDriver(@NonNull FragmentManager fm, List<Fragment>driverfragmentlist) {
        super(fm);
        this.driverfragmentlist=driverfragmentlist;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return driverfragmentlist.get(position);
    }

    @Override
    public int getCount() {
        return driverfragmentlist.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (driverfragmentlist.get(position)instanceof UpComingFragmentForDriver){
            return "UPCOMING";
        }else if (driverfragmentlist.get(position)instanceof OnGoingFragmentForDriver){
            return "ONGOING";
       /* }else if (driverfragmentlist.get(position)instanceof PendingFragmentForDriver){
            return "PENDING";*/
        }else if (driverfragmentlist.get(position)instanceof CompletedFragmentForDriver){
            return "COMPLETED";
        }if (driverfragmentlist.get(position)instanceof CancelFragmentForDriver){
            return "CANCEL";
        }
        return "";
        //return super.getPageTitle(position);
    }
}
