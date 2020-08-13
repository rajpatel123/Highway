package com.highway.customer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.highway.R;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodTypeDatum;
import com.highway.customer.customerModelClass.selectYoursGoodsType.GoodsTypeDataResponse;

public class GoodsSizeAdapter extends RecyclerView.Adapter<GoodsSizeAdapter.ViewHolder> {
    Context context;

    OnGoodTypeSelect onGoodTypeSelect;
    // List<GoodTypeDatum> goodTypeDatumList;
    GoodsTypeDataResponse goodsTypeDataResponse;


    public GoodsSizeAdapter(GoodsTypeDataResponse goodsTypeDataResponses1, Context context1, OnGoodTypeSelect onGoodTypeSelect) {
        this.context = context1;
        this.goodsTypeDataResponse = goodsTypeDataResponses1;
        this.onGoodTypeSelect = onGoodTypeSelect;
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

        GoodTypeDatum goodTypeDatum = goodsTypeDataResponse.getTypeData().getGoodTypeData().get(position);
        holder.goodsTypeTv1.setText("" + goodTypeDatum.getGoodsTypeTitle());

        if (goodTypeDatum.isSelected()){
            holder.goodsTypeTv1.setBackgroundResource(R.drawable.rounded_user_type_selecter);
            holder.goodsTypeTv1.setTextColor(context.getResources().getColor(R.color.white));
        }else{
            holder.goodsTypeTv1.setBackgroundResource(R.drawable.button_border);

            holder.goodsTypeTv1.setTextColor(context.getResources().getColor(R.color.black));

        }

        holder.goodsTypeTv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onGoodTypeSelect != null) {

                    onGoodTypeSelect.onSelectGoodType(holder.getAdapterPosition(),goodTypeDatum.getGoodsTypeId(), goodTypeDatum.getGoodsTypeTitle());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if (goodsTypeDataResponse != null
                && goodsTypeDataResponse.getTypeData() != null &&
                goodsTypeDataResponse.getTypeData().getGoodTypeData() != null
                && goodsTypeDataResponse.getTypeData().getGoodTypeData().size() > 0) {
            return goodsTypeDataResponse.getTypeData().getGoodTypeData().size();
        } else {
            return 0;
        }
    }

    public void setData(GoodsTypeDataResponse goodsTypeDataResponse) {
        this.goodsTypeDataResponse = goodsTypeDataResponse;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView goodsTypeTv1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goodsTypeTv1 = itemView.findViewById(R.id.TvGoodsType);
        }
    }


    public interface OnGoodTypeSelect {
        void onSelectGoodType(int position, String id, String type);
    }
}
