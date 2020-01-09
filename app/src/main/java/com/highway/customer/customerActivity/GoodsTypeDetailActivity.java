package com.highway.customer.customerActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.commonretrofit.RestClient;
import com.highway.customer.customerModelClass.selectYoursGoodsType.selectUrGoodsModel.GoodTypeDatum;
import com.highway.customer.customerModelClass.selectYoursGoodsType.selectUrGoodsModel.SelectUrGoodsTypeDataRequest;
import com.highway.customer.customerModelClass.selectYoursGoodsType.selectUrGoodsModel.SelectUrGoodsTypeDataResponse;
import com.highway.customer.customerModelClass.selectYoursGoodsType.selectUrGoodsModel.TypeData;
import com.highway.customer.customerModelClass.selectYoursGoodsType.selectYoursGoodsTypeAdapter.GoodsTypeAdapter;
import com.highway.ownermodule.vehicleOwner.vehicleOwnerAdapter.GetAllVehicleDetailsListAdapterForVehicleOwner;
import com.highway.ownermodule.vehicleOwner.vehileOwnerModelsClass.getAllVehicleDetailsList.DataVehicle;
import com.highway.utils.Constants;
import com.highway.utils.HighwayPrefs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GoodsTypeDetailActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String title;
    private String user_Id;

    RecyclerView recyclerViewGoodsTypeList;
    GoodsTypeAdapter goodsTypeAdapter;
    GoodsTypeDetailActivity goodsTypeDetailActivity;

    public List<GoodTypeDatum> getGoodTypeDatumList() {
        return goodTypeDatumList;
    }
    public void setGoodTypeDatumList(List<GoodTypeDatum> goodTypeDatumList) {
        this.goodTypeDatumList = goodTypeDatumList;
    }

    List<GoodTypeDatum> goodTypeDatumList = new ArrayList<>();
    Context context;
    SelectUrGoodsTypeDataResponse selectUrGoodsTypeDataResponse;
    TypeData typeData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_type_detail);
        recyclerViewGoodsTypeList = findViewById(R.id.goodTypeList);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
        getSupportActionBar().setTitle("Select your goods type");

        goodsTypeAdapter = new GoodsTypeAdapter(goodTypeDatumList, getApplicationContext());

        getGoodsTypeList();

    }


    @Override
    protected void onResume() {
        super.onResume();
        //getGoodsTypeList();
        goodsTypeRecy();
    }

    public void getGoodsTypeList() {

        SelectUrGoodsTypeDataRequest selectUrGoodsTypeDataRequest = new SelectUrGoodsTypeDataRequest();
        user_Id = HighwayPrefs.getString(getApplicationContext(), Constants.ID);
        selectUrGoodsTypeDataRequest.setUserId(user_Id);

        RestClient.selectUrGoodsType(selectUrGoodsTypeDataRequest, new Callback<SelectUrGoodsTypeDataResponse>() {
            @Override
            public void onResponse(Call<SelectUrGoodsTypeDataResponse> call, Response<SelectUrGoodsTypeDataResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {

                        selectUrGoodsTypeDataResponse = response.body();
                        typeData = selectUrGoodsTypeDataResponse.getTypeData();
                        goodTypeDatumList = typeData.getGoodTypeData();

                        if (goodTypeDatumList.size()>0){
                            if (goodsTypeAdapter !=null){
                                goodsTypeAdapter.setData(goodTypeDatumList);
                                goodsTypeAdapter.notifyDataSetChanged();
                            }
                        }else{
                            Toast.makeText(getApplicationContext(), "No goods type data found", Toast.LENGTH_SHORT).show();
                        }

                        /*SelectUrGoodsTypeDataResponse selectUrGoodsTypeDataResponse = response.body();
                        if (selectUrGoodsTypeDataResponse.getTypeData().getGoodTypeData()!=null && selectUrGoodsTypeDataResponse.getTypeData().getGoodTypeData().size()>0){
                            goodsTypeRecy(); *//*goodsTypeDetailActivity.setGoodTypeDatumList(goodTypeDatumList);*//*
                        }*/
                    }
                }/*else{
                    Toast.makeText(goodsTypeDetailActivity, "response failed", Toast.LENGTH_SHORT).show();
                }*/
            }

            @Override
            public void onFailure(Call<SelectUrGoodsTypeDataResponse> call, Throwable t) {
                Toast.makeText(GoodsTypeDetailActivity.this, "Failure", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void goodsTypeRecy() {
       List<GoodTypeDatum> goodTypeData = new ArrayList<>();
        if (goodTypeData != null && goodTypeData.size() > 0) {
          //   goodsTypeAdapter = new GoodsTypeAdapter(goodTypeData, getApplicationContext());
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerViewGoodsTypeList.setLayoutManager(layoutManager);
            recyclerViewGoodsTypeList.setItemAnimator(new DefaultItemAnimator());
            recyclerViewGoodsTypeList.setAdapter(goodsTypeAdapter);

        } else {
            Toast.makeText(getApplicationContext(), "No data  for goodsType !", Toast.LENGTH_SHORT).show();

        }
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
