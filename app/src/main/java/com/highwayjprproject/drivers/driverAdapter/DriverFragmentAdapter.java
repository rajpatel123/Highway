package com.highwayjprproject.drivers.driverAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.highwayjprproject.drivers.driverFragment.DriverCancelFragment;
import com.highwayjprproject.drivers.driverFragment.DriverCompletedFragment;
import com.highwayjprproject.drivers.driverFragment.DriverOnGoingFragment;
import com.highwayjprproject.drivers.driverFragment.DriverPendingFragment;
import com.highwayjprproject.drivers.driverFragment.DriverUpComingFragment;
import java.util.List;

public class DriverFragmentAdapter  extends FragmentPagerAdapter {
    List<Fragment>driverfragmentlist;

    public DriverFragmentAdapter(@NonNull FragmentManager fm, List<Fragment>driverfragmentlist) {
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

        if (driverfragmentlist.get(position)instanceof DriverUpComingFragment){
            return "UPCOMING";
        }else if (driverfragmentlist.get(position)instanceof DriverOnGoingFragment){
            return "ONGOING";
        }else if (driverfragmentlist.get(position)instanceof DriverPendingFragment){
            return "PENDING";
        }else if (driverfragmentlist.get(position)instanceof DriverCompletedFragment){
            return "COMPLETED";
        }if (driverfragmentlist.get(position)instanceof DriverCancelFragment){
            return "CANCEL";
        }
        return "";
        //return super.getPageTitle(position);
    }
}
