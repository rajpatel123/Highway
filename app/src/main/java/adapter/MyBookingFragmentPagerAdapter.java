package adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import fragment.CanceledFragment;
import fragment.CompletedFragment;
import fragment.OnGoingFragment;
import fragment.UpComingFragment;

public class MyBookingFragmentPagerAdapter extends FragmentPagerAdapter {

    List<Fragment> fragments;

    public MyBookingFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        this.fragments= fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (fragments.get(position) instanceof OnGoingFragment){
            return "ONGOING";
        }else if (fragments.get(position)instanceof UpComingFragment){
            return "UPCOMING";
        }else if (fragments.get(position)instanceof CompletedFragment){
            return "COMPLETED";
        }if (fragments.get(position)instanceof CanceledFragment){
            return "CANCELED";
        }
        return " ";
      //  return super.getPageTitle(position);
    }
}
