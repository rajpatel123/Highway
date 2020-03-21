package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.highway.R;
import com.highway.common.base.HighwayApplication;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userRating.UpdateTripRatingByUserReq;
import com.highway.common.base.commonModel.customerDiverOwnerModelsClass.allHighwayTripModel.userRating.UpdateTripRatingByUserResp;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.customerFragment.RatingBottomDialogFragmentForCustomer;
import com.highway.drivermodule.updateTripStatusByDriver.UpdateTripStatusByDriverReq;
import com.highway.drivermodule.updateTripStatusByDriver.UpdateTripStatusByDriverResp;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.highway.utils.Constants.RATING;

public class RatingBottomDialogFragmentForDriver extends BottomSheetDialogFragment
        implements View.OnClickListener {
    public static final String TAG = "ActionBottomDialog";
    private ItemClickListener mListener;
    CircleImageView avatar;
    TextView providerName;
    public RatingBar rating;
    public EditText comment;
    Button submit;
    DashBoardActivity dashBaoardActivity;
    String cmmnt;
    private String userId;
    private String tripId;
    private IncomingRequestFragmentForDriver incomingRequestFragmentForDriver;
    public String ratingBar;
    public OnGoingFragmentForDriver onGoingFragmentForDriver;

    public static RatingBottomDialogFragmentForDriver newInstance() {
        RatingBottomDialogFragmentForDriver ratingBottomDialogFragmentForDriver = new RatingBottomDialogFragmentForDriver();

        return ratingBottomDialogFragmentForDriver;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_reatingbar_for_driver, container, false);
        avatar = view.findViewById(R.id.avatar);
        providerName = view.findViewById(R.id.provider_name);
        rating = view.findViewById(R.id.driver_rating);
        comment = view.findViewById(R.id.comments);
        submit = view.findViewById(R.id.submit);

        //  cmntAndRatingBar();


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                driverRating();
                afterCmpltRidDriverStatus();

            }
        });
        return view;
    }

    public boolean cmntAndRatingBar() {
        cmmnt = comment.getText().toString().trim();

           /* if (cmmnt.isEmpty()) {
                comment.setError("pls enter comments");
                return false;
            } else {
                comment.setError(null);
            }

        return false;*/

        ratingBar = String.valueOf(rating.getRating());
        // Toast.makeText(getActivity(), ratingBar, Toast.LENGTH_LONG).show();
        return true;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashBaoardActivity = (DashBoardActivity) getActivity();


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


    private void afterCmpltRidDriverStatus() {

        userId = HighwayPrefs.getString(dashBaoardActivity, Constants.ID);
        UpdateTripStatusByDriverReq updateTripStatusByDriverReq = new UpdateTripStatusByDriverReq();
        updateTripStatusByDriverReq.setDriverId(userId);
        updateTripStatusByDriverReq.setTripId(HighwayApplication.getInstance().getCurrentTripId());
        updateTripStatusByDriverReq.setTRIPSTATS(RATING);
        updateTripStatusByDriverReq.setUpdatedAt("" + System.currentTimeMillis());

        RestClient.updateTripStatusByDriver(updateTripStatusByDriverReq, new Callback<UpdateTripStatusByDriverResp>() {
            @Override
            public void onResponse(Call<UpdateTripStatusByDriverResp> call, Response<UpdateTripStatusByDriverResp> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {
                        //  HighwayApplication.getInstance().setCurrentTripId("");
                        //  HighwayApplication.getInstance().getCurrentTripId();
                        dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<UpdateTripStatusByDriverResp> call, Throwable t) {
                Toast.makeText(dashBaoardActivity, "failure!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void driverRating() {

        cmmnt = comment.getText().toString().trim();
        ratingBar = String.valueOf(rating.getRating());

        userId = HighwayPrefs.getString(dashBaoardActivity, Constants.ID);
        UpdateTripRatingByUserReq updateTripRatingByUserReq = new UpdateTripRatingByUserReq();
        updateTripRatingByUserReq.setRatingStatus("1");
        updateTripRatingByUserReq.setUserId(userId);
        updateTripRatingByUserReq.setRatingComment(cmmnt);
        updateTripRatingByUserReq.setRatingRate(ratingBar);
        updateTripRatingByUserReq.setTripId(HighwayApplication.getInstance().getCurrentTripId());

        RestClient.getRatingUser(updateTripRatingByUserReq, new Callback<UpdateTripRatingByUserResp>() {
            @Override
            public void onResponse(Call<UpdateTripRatingByUserResp> call, Response<UpdateTripRatingByUserResp> response) {
                if (response != null && response.code() == 200 && response.body() != null) {
                    if (response.body().getStatus()) ;
                    dismiss();
                }
            }

            @Override
            public void onFailure(Call<UpdateTripRatingByUserResp> call, Throwable t) {
                Toast.makeText(dashBaoardActivity, "failure", Toast.LENGTH_SHORT).show();
            }
        });


    }


}