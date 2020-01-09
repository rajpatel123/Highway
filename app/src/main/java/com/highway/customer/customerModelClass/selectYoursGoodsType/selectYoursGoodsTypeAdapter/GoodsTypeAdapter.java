package com.highway.customer.customerModelClass.selectYoursGoodsType.selectYoursGoodsTypeAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.customer.customerModelClass.selectYoursGoodsType.selectUrGoodsModel.GoodTypeDatum;
import com.highway.customer.customerModelClass.selectYoursGoodsType.selectUrGoodsModel.TypeData;

import java.security.AccessControlContext;
import java.util.List;

public class GoodsTypeAdapter extends RecyclerView.Adapter<GoodsTypeAdapter.ViewHolder> {
    Context context;
    List<GoodTypeDatum> goodTypeDatumList;


    public GoodsTypeAdapter(List<GoodTypeDatum> goodTypeDatumList1, Context context1) {
        this.context = context1;
        this.goodTypeDatumList = goodTypeDatumList1;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_good_type_details_recycler_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        GoodTypeDatum goodTypeDatum = goodTypeDatumList.get(position);
        holder.Tv1.setText(""+ goodTypeDatum.getGoodsTypeTitle());

    }

    @Override
    public int getItemCount() {

        if (goodTypeDatumList != null && goodTypeDatumList.size() > 0) {
            return goodTypeDatumList.size();
        } else {
            return 0;
        }
    }

    public void setData(List<GoodTypeDatum> goodTypeDatumList) {
        this.goodTypeDatumList = goodTypeDatumList;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView Tv1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Tv1 = itemView.findViewById(R.id.TvGoodsType);
        }
    }
}
