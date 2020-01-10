package com.highway.customer.customerActivity;

import android.content.Context;
import android.content.Intent;
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
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodTypeDatum;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodsTypeDataRequest;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodsTypeDataResponse;
import com.highway.customer.customerModelClass.selectYoursGoodsType.TypeData;
import com.highway.customer.customerAdapter.GoodsTypeAdapter;
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

public class GoodsTypeDetailActivity extends AppCompatActivity implements GoodsTypeAdapter.OnGoodTypeSelect {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String title;
    private String user_Id;

    RecyclerView recyclerViewGoodsTypeList;
    GoodsTypeAdapter goodsTypeAdapter;
    List<GoodTypeDatum> goodTypeDatumList = new ArrayList<>();
    Context context;
    GoodsTypeDataResponse goodsTypeDataResponse;
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

        // goodsTypeAdapter = new GoodsTypeAdapter(goodTypeDatumList, getApplicationContext());
        showGoodTypeRV();
        getGoodsTypeList();

    }


    @Override
    protected void onResume() {
        super.onResume();
        //getGoodsTypeList();
    }

    public void getGoodsTypeList() {

        GoodsTypeDataRequest goodsTypeDataRequest = new GoodsTypeDataRequest();
        user_Id = HighwayPrefs.getString(getApplicationContext(), Constants.ID);
        goodsTypeDataRequest.setUserId(user_Id);

        RestClient.selectUrGoodsType(goodsTypeDataRequest, new Callback<GoodsTypeDataResponse>() {
            @Override
            public void onResponse(Call<GoodsTypeDataResponse> call, Response<GoodsTypeDataResponse> response) {
                if (response.body() != null) {
                    if (response.body().getStatus()) {

                        goodsTypeDataResponse = response.body();
                        typeData = goodsTypeDataResponse.getTypeData();

                        if (goodTypeDatumList.size()>0){
                            if (goodsTypeAdapter !=null){
                                goodTypeDatumList = typeData.getGoodTypeData();
                                goodsTypeAdapter.setData((GoodsTypeDataResponse) goodTypeDatumList);
                                goodsTypeAdapter.notifyDataSetChanged();

                               /* goodsTypeDataResponse = response.body();
                                goodsTypeAdapter.setData(goodsTypeDataResponse);
                                goodsTypeAdapter.notifyDataSetChanged();*/
                            }
                        }
                    }
                }

            }


            @Override
            public void onFailure(Call<GoodsTypeDataResponse> call, Throwable t) {
                Toast.makeText(GoodsTypeDetailActivity.this, "Failure", Toast.LENGTH_SHORT).show();

            }
        });

    }

    public void showGoodTypeRV() {
        if (goodsTypeDataResponse != null) {
            goodsTypeAdapter = new GoodsTypeAdapter(goodsTypeDataResponse, getApplicationContext(), this);
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

    @Override
    public void onSelectGoodType(String id, String type) {
        Intent data = new Intent();
        data.putExtra("id", id);
        data.putExtra("type", type);
        setResult(RESULT_OK, data);
        finish();

    }
}
