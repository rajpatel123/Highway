package com.highway.customer.customerFragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.loader.content.CursorLoader;

import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.highway.R;

public class ReceiverBottomSheetFragment extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";
    EditText edtTextMobNo,edtName;
    ImageView imageView;
    private static final int PICK_CONTACT = 1;

    public ReceiverBottomSheetFragment() {

    }

    public static ReceiverBottomSheetFragment newInstance() {
        ReceiverBottomSheetFragment fragment = new ReceiverBottomSheetFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_receiver_bottim_sheet, container, false);

        edtTextMobNo = view.findViewById(R.id.mobileNumber);
        edtName = view.findViewById(R.id.user_name);
        imageView = view.findViewById(R.id.phoneImg);

        imageView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent= new Intent(Intent.ACTION_PICK,  ContactsContract.Contacts.CONTENT_URI);

                startActivityForResult(intent, PICK_CONTACT);

            }
        });

        return view;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.i(TAG, "onActivityResult()");
        if (requestCode == PICK_CONTACT) {
            if (resultCode == Activity.RESULT_OK) {
                Uri contactsData = data.getData();
                CursorLoader loader = new CursorLoader(getActivity(), contactsData, null, null, null, null);
                Cursor c = loader.loadInBackground();
                if (c.moveToFirst()) {
                    Log.i(TAG, "Contacts ID: " + c.getString(c.getColumnIndex(ContactsContract.Contacts._ID)));
                    Log.i(TAG, "Contacts Name: " + c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)));
                    Log.i(TAG, "Contacts Phone Number: " + c.getString(c.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)));
                }
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    public void show(FragmentManager supportFragmentManager, Object tag) {

    }
}
