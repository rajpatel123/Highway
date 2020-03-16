package com.highway.drivermodule.driverFragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.FragmentActivity;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.highway.R;
import com.highway.common.base.HighwayApplication;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.drivermodule.updateTripStatusByDriver.UpdateTripStatusByDriverReq;
import com.highway.drivermodule.updateTripStatusByDriver.UpdateTripStatusByDriverResp;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.highway.utils.Constants.INVOICE;
import static com.highway.utils.Constants.TRIP_ID;

public class InvoiceBottomDialogFragmentForDriver extends BottomSheetDialogFragment
        implements View.OnClickListener {
    public static final String TAG = "ActionBottomDialog";
    private ItemClickListener mListener;
    private Button confirmPaymentBtn;
    public String userId;


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
    TextView distance;
    private String totDistance;
    private String tripId;
    private DashBoardActivity mActivity;

    public static InvoiceBottomDialogFragmentForDriver newInstance() {
        return new InvoiceBottomDialogFragmentForDriver();
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_invoice_dialog_for_driver, container, false);

        bookingId = view.findViewById(R.id.booking_id);
        startDate = view.findViewById(R.id.start_date);
        endDate = view.findViewById(R.id.end_date);
        totalDistance = view.findViewById(R.id.total_distance);
        travelTime = view.findViewById(R.id.travel_time);
        fixed = view.findViewById(R.id.fixed);
        distanceFare = view.findViewById(R.id.distance_fare);
        peekHourCharges = view.findViewById(R.id.peek_hour_charges);
        nightFare = view.findViewById(R.id.night_fare);
        totalAmount = view.findViewById(R.id.total_amount);
        tax = view.findViewById(R.id.tax);
        discount = view.findViewById(R.id.discount);
        payableAmount = view.findViewById(R.id.payable_amount);
        tax2 = view.findViewById(R.id.tax2);
        commission = view.findViewById(R.id.commission);
        tds = view.findViewById(R.id.tds);
        providerEarnings = view.findViewById(R.id.provider_earnings);
        lblPaymentType = view.findViewById(R.id.lblPaymentType);
        confirmPaymentBtn = view.findViewById(R.id.btnConfirmPayment);

        paymentModeLayout = view.findViewById(R.id.payment_mode_layout);
        discountLayout = view.findViewById(R.id.discount_layout);
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
        nestedScrollView = view.findViewById(R.id.nested_scroll_view);


        tripdate();
        //Intent intent =getActivity().getIntent();
        String bookTripIdCode = getActivity().getIntent().getStringExtra("bookTripIdCode");
        bookingId.setText(bookTripIdCode);
        totDistance = (getActivity().getIntent().getStringExtra("distance"));
        totalDistance.setText(totDistance);






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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        confirmPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                afterCmpltRidDriverStatus();

               // ((DashBoardActivity) getActivity()).showratingBottomSheetDriver("");
                dismiss();
              //  ((DashBoardActivity) getActivity()).showratingBottomSheetForCustomer(tripId);

            }
        });
    }

    private void afterCmpltRidDriverStatus() {
        userId = HighwayPrefs.getString(getActivity(), Constants.ID);
        UpdateTripStatusByDriverReq updateTripStatusByDriverReq = new UpdateTripStatusByDriverReq();
        updateTripStatusByDriverReq.setDriverId(userId);
        updateTripStatusByDriverReq.setTripId(HighwayApplication.getInstance().getCurrentTripId());
        updateTripStatusByDriverReq.setTRIPSTATS(INVOICE);
        updateTripStatusByDriverReq.setUpdatedAt(""+System.currentTimeMillis());

        RestClient.updateTripStatusByDriver(updateTripStatusByDriverReq, new Callback<UpdateTripStatusByDriverResp>() {
            @Override
            public void onResponse(Call<UpdateTripStatusByDriverResp> call, Response<UpdateTripStatusByDriverResp> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        ((DashBoardActivity) mActivity).showratingBottomSheetDriver();
                        dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateTripStatusByDriverResp> call, Throwable t) {
                Toast.makeText(getActivity(), "failure!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mActivity = (DashBoardActivity) getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        TextView tvSelected = (TextView) view;
        mListener.onItemClick(tvSelected.getText().toString());
        dismiss();
    }

    public interface ItemClickListener {
        void onItemClick(String item);
    }
}