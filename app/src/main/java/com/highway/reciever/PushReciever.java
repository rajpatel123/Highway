package com.highway.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import com.highway.drivermodule.driverFragment.IncomingRequestFragmentForDriver;

public class PushReciever extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Fragment fragment3 = IncomingRequestFragmentForDriver.newInstance();
        Bundle bundle = new Bundle();
        fragment3.setArguments(bundle);
    }
}
