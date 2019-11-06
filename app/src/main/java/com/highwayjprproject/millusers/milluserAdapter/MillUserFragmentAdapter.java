package com.highwayjprproject.millusers.milluserAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.highwayjprproject.millusers.milluserFragment.MillUserCancelFragment;
import com.highwayjprproject.millusers.milluserFragment.MillUserCompletedFragment;
import com.highwayjprproject.millusers.milluserFragment.MillUserOnGoingFragment;
import com.highwayjprproject.millusers.milluserFragment.MillUserPendingFragment;
import com.highwayjprproject.millusers.milluserFragment.MillUserUpComingFragment;

import java.util.List;

public class MillUserFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment> milluserfragment;
    public MillUserFragmentAdapter(@NonNull FragmentManager fm,  List<Fragment> milluserfragment) {
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

        if (milluserfragment.get(position)instanceof MillUserUpComingFragment){
            return "UPCOMING";
        }else if (milluserfragment.get(position)instanceof MillUserOnGoingFragment){
            return "ONGOING";
        }else if (milluserfragment.get(position)instanceof MillUserPendingFragment){
            return "PENDING";
        }else if(milluserfragment.get(position)instanceof MillUserCompletedFragment){
            return "COMPLETED";
        }if (milluserfragment.get(position)instanceof MillUserCancelFragment){
            return "CANCEL";
        }
        return "";
        //return super.getPageTitle(position);
    }
}
