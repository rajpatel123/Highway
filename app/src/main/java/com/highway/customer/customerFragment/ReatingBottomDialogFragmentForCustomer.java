package com.highway.customer.customerFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.highway.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ReatingBottomDialogFragmentForCustomer extends Fragment {

    CircleImageView avatar;
    TextView providerName;
    RatingBar rating;
    EditText comment;
    Button submit;
    private OnFragmentInteractionListener mListener;

    public ReatingBottomDialogFragmentForCustomer() {
        // Required empty public constructor
    }


    public static ReatingBottomDialogFragmentForCustomer newInstance(String param1, String param2) {
        ReatingBottomDialogFragmentForCustomer fragment = new ReatingBottomDialogFragmentForCustomer();
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
        View view = inflater.inflate(R.layout.fragment_reating_bottom_dialog_fragment_for_customer, container, false);

        avatar = view.findViewById(R.id.avatar);
        providerName = view.findViewById(R.id.provider_name);
        rating = view.findViewById(R.id.rating);
        comment = view.findViewById(R.id.comment);
        submit = view.findViewById(R.id.submit);

    return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
