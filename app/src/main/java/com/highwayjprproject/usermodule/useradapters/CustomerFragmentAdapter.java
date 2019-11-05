package com.highwayjprproject.usermodule.useradapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.highwayjprproject.fragment.customer.CustomerCancelFragment;
import com.highwayjprproject.fragment.customer.CustomerCompletedFragment;
import com.highwayjprproject.fragment.customer.CustomerOnGoingFragment;
import com.highwayjprproject.fragment.customer.CustomerPendingFragment;
import com.highwayjprproject.fragment.customer.CustomerUpCommingFragment;

import java.util.List;

public class CustomerFragmentAdapter extends FragmentPagerAdapter {
    List<Fragment>customerfragments;

    public CustomerFragmentAdapter(FragmentManager fm, List<Fragment>fragments) {
        super(fm);
        this.customerfragments =fragments;

    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return customerfragments.get(position);
    }

    @Override
    public int getCount() {
        return customerfragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (customerfragments.get(position)instanceof CustomerUpCommingFragment){
            return "UPCOMING";
        }else if (customerfragments.get(position)instanceof CustomerOnGoingFragment){
            return "ONGOING";
        }else if (customerfragments.get(position)instanceof CustomerPendingFragment){
            return "PENDING";
        }else if (customerfragments.get(position)instanceof CustomerCompletedFragment){
            return "COMPLETED";
        }if (customerfragments.get(position)instanceof CustomerCancelFragment){
            return "CANCEL";
        }
            return " ";
        //return super.getPageTitle(position);
    }
}
