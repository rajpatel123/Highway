package com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment.VehicleCancelFragment;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment.VehicleCompletedFragment;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment.VehicleOnGoingFragment;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment.VehiclePendingFragment;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment.VehicleUpComingFragment;

import java.util.List;

public class VehicleOwnerTabAdapter extends FragmentPagerAdapter {
    List<Fragment> vehicleOwnerfragment;

    public VehicleOwnerTabAdapter(@NonNull FragmentManager fm, List<Fragment> fragments) {
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
