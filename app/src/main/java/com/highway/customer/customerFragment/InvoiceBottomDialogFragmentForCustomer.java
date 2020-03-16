package com.highway.customer.customerFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.highway.R;

import java.text.NumberFormat;

import static com.highway.utils.Constants.TRIP_ID;

public class InvoiceBottomDialogFragmentForCustomer extends BottomSheetDialogFragment {

    private OnFragmentInteractionListener mListener;
    TextView paymentMode;
    Button payNow;
    Button done;
    TextView bookingId;

    NumberFormat numberFormat;
    TextView distance;
    TextView travelTime;
    TextView fixed;
    TextView tax;
    TextView totalAmount;
    TextView payable;
    TextView walletDetection;
    LinearLayout lnrWalletDetection;
    TextView discount;
    LinearLayout lnrDiscount;
    TextView distancePrice;
    TextView peekHourCharges;
    TextView nightFare;
    LinearLayout layout_normal_flow;
    LinearLayout layout_rental_flow;
    TextView rentalNormalPrice;
    TextView rentalTotalDistance;
    TextView rentalExtraHrKmPrice;
    TextView rentalTravelTime;
    TextView rentalHours;

    LinearLayout layout_outstation_flow;
    TextView outstationDistanceTravelled;
    TextView outstationDistanceFare;
    TextView outstationDriverBeta;
    TextView outstationRoundSingle;
    TextView outstationNoOfDays;
    TextView startDate;
    TextView endDate;
    NestedScrollView nestedScrollView;
    private String tripId;




    public static InvoiceBottomDialogFragmentForCustomer newInstance(String tripId) {
        InvoiceBottomDialogFragmentForCustomer fragment = new InvoiceBottomDialogFragmentForCustomer();
        Bundle args = new Bundle();
        args.putString(TRIP_ID,tripId);
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
        View view = inflater.inflate(R.layout.fragment_invoice_bottom_dialog__for_customer, container, false);


        bookingId = view.findViewById(R.id.booking_id);
        startDate = view.findViewById(R.id.start_date);
        endDate = view.findViewById(R.id.end_date);
        distance = view.findViewById(R.id.distance);
        travelTime = view.findViewById(R.id.travel_time);

        fixed = view.findViewById(R.id.fixed);
        distancePrice = view.findViewById(R.id.distance_price);
        peekHourCharges = view.findViewById(R.id.peek_hour_charges);
        nightFare = view.findViewById(R.id.night_fare);
        tax = view.findViewById(R.id.tax);
        totalAmount = view.findViewById(R.id.total);
        walletDetection = view.findViewById(R.id.wallet_detection);
        discount = view.findViewById(R.id.discount);
        paymentMode = view.findViewById(R.id.payment_mode);
        done = view.findViewById(R.id.done);
        payNow = view.findViewById(R.id.payable);



        layout_normal_flow = view.findViewById(R.id.layout_normal_flow);
        layout_rental_flow = view.findViewById(R.id.layout_rental_flow);
        rentalNormalPrice = view.findViewById(R.id.rental_normal_price);
        rentalTotalDistance = view.findViewById(R.id.rental_total_distance_km);
        rentalExtraHrKmPrice = view.findViewById(R.id.rental_extra_hr_km_price);
        rentalTravelTime = view.findViewById(R.id.rental_travel_time);
        rentalHours = view.findViewById(R.id.rental_hours);
        layout_outstation_flow = view.findViewById(R.id.layout_outstation_flow);
        outstationDistanceTravelled = view.findViewById(R.id.outstation_distance_travelled);
        outstationDistanceFare = view.findViewById(R.id.outstation_distance_fare);
        outstationDriverBeta = view.findViewById(R.id.outstation_driver_beta);
        outstationRoundSingle = view.findViewById(R.id.outstation_round_single);
        outstationNoOfDays = view.findViewById(R.id.outstation_no_of_days);


        if (getArguments()!=null){
            tripId = getArguments().getString(TRIP_ID);
        }





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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
