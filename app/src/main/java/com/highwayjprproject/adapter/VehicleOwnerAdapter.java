package com.highwayjprproject.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.highwayjprproject.fragment.Vehicle_Owner.VehicleCancelFragment;
import com.highwayjprproject.fragment.Vehicle_Owner.VehicleCompletedFragment;
import com.highwayjprproject.fragment.Vehicle_Owner.VehicleOnGoingFragment;
import com.highwayjprproject.fragment.Vehicle_Owner.VehiclePendingFragment;
import com.highwayjprproject.fragment.Vehicle_Owner.VehicleUpComingFragment;

import java.util.List;

public class VehicleOwnerAdapter extends FragmentPagerAdapter {
    List<Fragment> vehicleOwnerfragment;

    public VehicleOwnerAdapter(@NonNull FragmentManager fm,  List<Fragment> fragments) {
        super(fm);
        this.vehicleOwnerfragment =fragments;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return vehicleOwnerfragment.get(position);
    }

    @Override
    public int getCount() {
        return vehicleOwnerfragment.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (vehicleOwnerfragment.get(position)instanceof VehicleUpComingFragment){
            return "UPCOMING";
        }else if (vehicleOwnerfragment.get(position)instanceof VehicleOnGoingFragment){
            return "ONGOING";
        }else if (vehicleOwnerfragment.get(position)instanceof VehiclePendingFragment){
            return "PENDING";
        }else if (vehicleOwnerfragment.get(position)instanceof VehicleCompletedFragment){
            return "COMPLETED";
        }if (vehicleOwnerfragment.get(position)instanceof VehicleCancelFragment){
            return "CANCEL";
        }
        return " ";
        //return super.getPageTitle(position);
    }
}
