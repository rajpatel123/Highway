package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.highway.R;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.Unbinder;


public class InviceDialogFragment extends Fragment {


    private OnFragmentInteractionListener mListener;


    TextView bookingId;
    TextView totalAmount;
    TextView payableAmount;
    LinearLayout paymentModeLayout;
    Button btnConfirmPayment;
    TextView lblPaymentType;
    TextView totalDistance;
    TextView travelTime;
    TextView fixed;
    TextView distanceFare;
    TextView peekHourCharges;
    TextView nightFare;
    TextView tax;
    TextView discount;
    LinearLayout discountLayout;
    TextView tax2;
    TextView commission;
    TextView tds;
    TextView providerEarnings;

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
    int yy, mm, dd;

    public InviceDialogFragment() {
    }


    public static InviceDialogFragment newInstance(String param1, String param2) {
        InviceDialogFragment fragment = new InviceDialogFragment();
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
        View view = inflater.inflate(R.layout.fragment_invice_dialog, container, false);

        bookingId = view.findViewById(R.id.booking_id);
        totalAmount = view.findViewById(R.id.total_amount);
        payableAmount = view.findViewById(R.id.payable_amount);
        paymentModeLayout = view.findViewById(R.id.payment_mode_layout);
        btnConfirmPayment = view.findViewById(R.id.btnConfirmPayment);
        lblPaymentType = view.findViewById(R.id.lblPaymentType);
        totalDistance = view.findViewById(R.id.total_distance);
        fixed = view.findViewById(R.id.fixed);
        distanceFare = view.findViewById(R.id.distance_fare);
        peekHourCharges = view.findViewById(R.id.peek_hour_charges);
        nightFare = view.findViewById(R.id.night_fare);
        tax = view.findViewById(R.id.tax);
        discount = view.findViewById(R.id.discount);
        discountLayout = view.findViewById(R.id.discount_layout);
        tax2 = view.findViewById(R.id.tax2);
        commission = view.findViewById(R.id.commission);
        tds = view.findViewById(R.id.tds);
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
        startDate = view.findViewById(R.id.start_date);
        endDate = view.findViewById(R.id.end_date);
        nestedScrollView = view.findViewById(R.id.nested_scroll_view);

        tripdate();
        //Intent intent =getActivity().getIntent();
        String bookTripIdCode = getActivity().getIntent().getStringExtra("bookTripIdCode");
        bookingId.setText(bookTripIdCode);
        Float distance = Float.valueOf(getActivity().getIntent().getStringExtra("distance"));
        return view;


    }

    private void tripdate() {
        // set current date into textview -- for start date
        final Calendar c = Calendar.getInstance();
        yy = c.get(Calendar.YEAR);
        mm = c.get(Calendar.MONTH);
        dd = c.get(Calendar.DAY_OF_MONTH);
        startDate.setText(new StringBuilder()
                .append(yy).append(" ").append("-").append(mm + 1).append("-").append(dd));

        // for end date
        long date = System.currentTimeMillis();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM MM dd, yyyy h:mm a");
        String dateString = sdf.format(date);
        endDate.setText(dateString);
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
