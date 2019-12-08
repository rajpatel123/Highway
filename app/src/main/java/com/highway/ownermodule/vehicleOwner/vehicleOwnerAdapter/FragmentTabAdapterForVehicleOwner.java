package com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment.CancelFragmentForVehicleOwner;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment.CompletedFragmentForVehicleOwner;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment.OnGoingFragmentForVehicleOwner;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment.PendingFragmentForVehicleOwner;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerfragment.UpComingFragmentForVehicleOwner;

import java.util.List;

public class FragmentTabAdapterForVehicleOwner extends FragmentPagerAdapter {
    List<Fragment> vehicleOwnerfragment;

    public FragmentTabAdapterForVehicleOwner(@NonNull FragmentManager fm, List<Fragment> fragments) {
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
        if (vehicleOwnerfragment.get(position)instanceof UpComingFragmentForVehicleOwner){
            return "UPCOMING";
        }else if (vehicleOwnerfragment.get(position)instanceof OnGoingFragmentForVehicleOwner){
            return "ONGOING";
        }else if (vehicleOwnerfragment.get(position)instanceof PendingFragmentForVehicleOwner){
            return "PENDING";
        }else if (vehicleOwnerfragment.get(position)instanceof CompletedFragmentForVehicleOwner){
            return "COMPLETED";
        }if (vehicleOwnerfragment.get(position)instanceof CancelFragmentForVehicleOwner){
            return "CANCEL";
        }
        return " ";
        //return super.getPageTitle(position);
    }
}
