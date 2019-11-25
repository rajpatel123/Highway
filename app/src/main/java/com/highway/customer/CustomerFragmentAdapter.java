package com.highway.customer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.highway.customer.customerFragment.CustomerCancelFragment;
import com.highway.customer.customerFragment.CustomerCompletedFragment;
import com.highway.customer.customerFragment.CustomerOnGoingFragment;
import com.highway.customer.customerFragment.CustomerPendingFragment;
import com.highway.customer.customerFragment.CustomerUpCommingFragment;

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
