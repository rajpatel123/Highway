package com.highway.customer.customerFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.highway.R;
import com.highway.common.base.HighwayApplication;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.customerModelClass.customerInvoice.CustomerInvoice;
import com.highway.customer.customerModelClass.customerInvoice.CustomerInvoiceReq;
import com.highway.customer.customerModelClass.customerInvoice.CustomerInvoiceResp;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.highway.utils.Constants.TRIP_ID;

public class InvoiceBottomDialogFragmentForCustomer extends BottomSheetDialogFragment {

    public static final String TAG = "ActionBottomDialog";
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
    private String userId;
    TextView bookingIdCode;
    private String getBookTripIdCode;
    private String getBookId;
    private String totDistance;
    private String totalDistance;
    private TextView payAbleAmout;
    private int yy, mm, dd;
    TextView start_time,end_time;
    private DashBoardActivity dashboardActivity;

    public static InvoiceBottomDialogFragmentForCustomer newInstance() {
        InvoiceBottomDialogFragmentForCustomer fragment = new InvoiceBottomDialogFragmentForCustomer();

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

        start_time = view.findViewById(R.id.start_time);
        end_time = view.findViewById(R.id.end_time);

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
        payAbleAmout = view.findViewById(R.id.payable);



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


//        //Intent intent =getActivity().getIntent();
//        String bookTripIdCode = getActivity().getIntent().getStringExtra("bookTbripIdCode");
//       // bookingIdCode.setText(bookTripIdCode);
//
//        getBookTripIdCode = HighwayPrefs.getString(getActivity(), "bookTripIdCode");
//      //  getBookId = HighwayApplication.getInstance().getCurrentTripId();

        totDistance = (getActivity().getIntent().getStringExtra("distance"));
        distance.setText(totDistance);

        tripdate();
        getInvoiceForCustomer();
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });



        return view;
    }

    private void clicklistner() {

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


    private void getInvoiceForCustomer() {
        userId = HighwayPrefs.getString(getActivity(), Constants.ID);
        CustomerInvoiceReq customerInvoiceReq = new CustomerInvoiceReq();
        customerInvoiceReq.setBookingId(HighwayApplication.getInstance().getCurrentTripId());
        customerInvoiceReq.setCustomerId(userId);

        RestClient.getCustomerInvoice(customerInvoiceReq, new Callback<CustomerInvoiceResp>() {
            @Override
            public void onResponse(Call<CustomerInvoiceResp> call, Response<CustomerInvoiceResp> response) {
                if (response!=null && response.code()==200 && response.body()!=null){
                    if (response.body().getStatus()){
                       CustomerInvoice customerInvoice =response.body().getCustomerInvoice();
                       invoiceForCustomer(customerInvoice);
                    }
                }
            }

            @Override
            public void onFailure(Call<CustomerInvoiceResp> call, Throwable t) {
                Toast.makeText(getActivity(), "customer invoice failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void invoiceForCustomer(CustomerInvoice customerInvoice) {

        bookingId.setText(""+customerInvoice.getBookingTripCode());
        startDate.setText(""+customerInvoice.getStartDate());
        endDate.setText(""+customerInvoice.getEndDate());
        start_time.setText(""+customerInvoice.getStartTime());
        end_time.setText(" "+customerInvoice.getEndTime());
        distance.setText(""+customerInvoice.getTotDistance());
        travelTime.setText(""+customerInvoice.getTravelTime());
        fixed.setText(""+customerInvoice.getBasedFarefixed());
        distancePrice.setText(""+customerInvoice.getDistancePrice());
        peekHourCharges.setText(""+customerInvoice.getPeekHourCharges());
        nightFare.setText(""+customerInvoice.getNightFare());
        tax.setText(""+customerInvoice.getTax());
        totalAmount.setText(""+customerInvoice.getTotalAmount());
        discount.setText(""+customerInvoice.getDiscount());
        payAbleAmout.setText(""+customerInvoice.getPaymentMode());


    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashboardActivity = (DashBoardActivity) getActivity();

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
