package com.highway.millUserModule.milluserAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.highway.millUserModule.milluserFragment.CancelFragmentForMillUser;
import com.highway.millUserModule.milluserFragment.CompletedFragmentForMillUser;
import com.highway.millUserModule.milluserFragment.OnGoingFragmentForMillUser;
import com.highway.millUserModule.milluserFragment.PendingFragmentForMillUser;
import com.highway.millUserModule.milluserFragment.UpComingFragmentForMillUser;

import java.util.List;

public class FragmentTabModeAdapterForMiller extends FragmentPagerAdapter {
    List<Fragment> milluserfragment;
    public FragmentTabModeAdapterForMiller(@NonNull FragmentManager fm, List<Fragment> milluserfragment) {
        super(fm);
        this.milluserfragment=milluserfragment;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return milluserfragment.get(position);
    }

    @Override
    public int getCount() {
        return milluserfragment.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        if (milluserfragment.get(position)instanceof UpComingFragmentForMillUser){
            return "UPCOMING";
        }else if (milluserfragment.get(position)instanceof OnGoingFragmentForMillUser){
            return "ONGOING";
        /*}else if (milluserfragment.get(position)instanceof PendingFragmentForMillUser){
            return "PENDING";*/
        }else if(milluserfragment.get(position)instanceof CompletedFragmentForMillUser){
            return "COMPLETED";
        }if (milluserfragment.get(position)instanceof CancelFragmentForMillUser){
            return "CANCEL";
        }
        return "";
        //return super.getPageTitle(position);
    }
}
