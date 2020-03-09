package com.highway.drivermodule;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.highway.R;

public class MyDialogFragment extends DialogFragment
{
    //private View pic;

    public MyDialogFragment()
    {
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        View view = getActivity().getLayoutInflater().inflate(R.layout.fragment_invoice_dialog_for_driver, new LinearLayout(getActivity()), false);

        // Retrieve layout elements
        TextView title =  view.findViewById(R.id.title);

        // Set values
        title.setText("Not perfect yet");

        // Build dialog
        Dialog builder = new Dialog(getActivity());
        builder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        builder.setContentView(view);
        return builder;
    }
}