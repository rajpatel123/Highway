package com.highwayjprproject.usermodule.userAdapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.highwayjprproject.customers.customerFragment.CustomerCancelFragment;
import com.highwayjprproject.customers.customerFragment.CustomerCompletedFragment;
import com.highwayjprproject.customers.customerFragment.CustomerOnGoingFragment;
import com.highwayjprproject.customers.customerFragment.CustomerPendingFragment;
import com.highwayjprproject.customers.customerFragment.CustomerUpCommingFragment;

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
