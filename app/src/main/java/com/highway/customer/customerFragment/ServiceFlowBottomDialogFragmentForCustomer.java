package com.highway.customer.customerFragment;

import android.app.AlertDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.highway.R;

import butterknife.BindView;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class ServiceFlowBottomDialogFragmentForCustomer extends Fragment {

    private OnFragmentInteractionListener mListener;
    Unbinder unbinder;
    TextView sos;
    TextView otp;
    CircleImageView avatar;
    TextView firstName;
    TextView status;
    RatingBar rating;
    Button cancel;
    Button sharedRide;
    Unbinder unbinder1;
    ImageView image;
    TextView serviceTypeName;
    TextView serviceNumber;
    TextView serviceModel;
    Button call;
    TextView lblgeo_value;
    NestedScrollView nestedScrollView;

    String providerPhoneNumber = null;
   // FirebaseDatabase database;
    //DatabaseReference myRef;
    String shareRideText = "";
    //private CancelRequestInterface callback;
    LatLng origin, providerLatLng;
    AlertDialog otpDialog;

    public ServiceFlowBottomDialogFragmentForCustomer() { }

    public static ServiceFlowBottomDialogFragmentForCustomer newInstance() {
        ServiceFlowBottomDialogFragmentForCustomer fragment = new ServiceFlowBottomDialogFragmentForCustomer();
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
        View view = inflater.inflate(R.layout.fragment_service_flow_bottom_dialog_fragment_for_customer, container, false);
        sos = view.findViewById(R.id.sos);
        otp = view.findViewById(R.id.otp);
        avatar = view.findViewById(R.id.avatar);
        firstName = view.findViewById(R.id.first_name);
        status = view.findViewById(R.id.status);
        rating = view.findViewById(R.id.rating);
        cancel = view.findViewById(R.id.cancel);
        sharedRide = view.findViewById(R.id.share_ride);
        image = view.findViewById(R.id.image);
        serviceTypeName = view.findViewById(R.id.service_type_name);
        serviceNumber = view.findViewById(R.id.service_number);
        serviceModel = view.findViewById(R.id.service_model);
        call = view.findViewById(R.id.call);
        lblgeo_value = view.findViewById(R.id.lblgeo);
        nestedScrollView = view.findViewById(R.id.nested_scroll_view);

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
