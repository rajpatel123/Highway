package com.highway.customer.customerFragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.highway.R;
import com.highway.common.base.HighwayApplication;
import com.highway.common.base.activity.DashBoardActivity;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.BookingProcessActivtiy;
import com.highway.customer.customerAdapter.GoodsTypeAdapter;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodTypeDatum;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodsTypeDataRequest;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodsTypeDataResponse;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoodsTypeDialogFragmentForCustomer extends BottomSheetDialogFragment implements GoodsTypeAdapter.OnGoodTypeSelect {

    public static final String TAG = "ActionBottomDialog";
    TextView paymentMode;
    Button payNow;
    Button doneBtn;
    TextView bookingId;
    RecyclerView recyclerViewGoodsTypeList;
    GoodsTypeAdapter goodsTypeAdapter;
    GoodsTypeDataResponse goodsTypeDataResponse;


    private BookingProcessActivtiy  dashboardActivity;

    public static GoodsTypeDialogFragmentForCustomer newInstance() {
        GoodsTypeDialogFragmentForCustomer fragment = new GoodsTypeDialogFragmentForCustomer();

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
        View view = inflater.inflate(R.layout.fragment_goods_bottom_dialog__for_customer, container, false);


        recyclerViewGoodsTypeList = view.findViewById(R.id.goodTypesRecyclerView);
        doneBtn = view.findViewById(R.id.doneBtn);


        doneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        showGoodTypeRV();
        getGoodsTypeList();

        return view;
    }


    public void getGoodsTypeList() {

        GoodsTypeDataRequest goodsTypeDataRequest = new GoodsTypeDataRequest();
        String user_Id = HighwayPrefs.getString(getActivity(), Constants.ID);
        goodsTypeDataRequest.setUserId(user_Id);

        RestClient.selectUrGoodsType(goodsTypeDataRequest, new Callback<GoodsTypeDataResponse>() {
            @Override
            public void onResponse(Call<GoodsTypeDataResponse> call, Response<GoodsTypeDataResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {

                        goodsTypeDataResponse = response.body();
                        goodsTypeAdapter.setData(goodsTypeDataResponse);
                        goodsTypeAdapter.notifyDataSetChanged();
                    }
                } else {
                    Toast.makeText(getActivity(), "response failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GoodsTypeDataResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void showGoodTypeRV() {
        goodsTypeAdapter = new GoodsTypeAdapter(goodsTypeDataResponse, getActivity(), this::onSelectGoodType); // aaccording to sir initialize above ...................
        recyclerViewGoodsTypeList.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerViewGoodsTypeList.setAdapter(goodsTypeAdapter);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dashboardActivity  = (BookingProcessActivtiy) getActivity();

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


    @Override
    public void onSelectGoodType(int position ,String id, String type) {
        dashboardActivity.goodsTpeSpinner.setText(type);

        for (GoodTypeDatum datum :goodsTypeDataResponse.getTypeData().getGoodTypeData()){
            datum.setSelected(false);
        }

        goodsTypeAdapter.notifyDataSetChanged();
        goodsTypeDataResponse.getTypeData().getGoodTypeData().get(position).setSelected(true);

        HighwayApplication.getInstance().getBookingHTripRequest().setGoodsTypeId(id);
    }
}
