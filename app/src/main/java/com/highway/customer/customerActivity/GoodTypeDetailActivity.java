package com.highway.customer.customerActivity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GoodTypeDetailActivity extends AppCompatActivity {


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private String title;
    RecyclerView recyclerViewTypeList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_type_detail);

        recyclerViewTypeList = findViewById(R.id.typeList);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
           getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           getSupportActionBar().setDisplayShowHomeEnabled(true);

        }
       getSupportActionBar().setTitle("Select your goods type");

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return super.onOptionsItemSelected(item);
    }

}
