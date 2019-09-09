package adapterstatically;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.highway.R;

import java.util.List;

import modelstatically.CancledFragmentModelStatically;
import modelstatically.UpComingFragmentModelStatically;

public class CancledFragmentAdapter extends RecyclerView.Adapter<CancledFragmentAdapter.MyViewHolder> {

    Context context;
    List<CancledFragmentModelStatically> cancledFragmentModelStaticallies;

    public CancledFragmentAdapter(List<CancledFragmentModelStatically> cancledFragmentModelStaticallies, Context context) {

        this.context = context;
        this.cancledFragmentModelStaticallies = cancledFragmentModelStaticallies;

    }


    @NonNull
    @Override
    public CancledFragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_cancled_item, viewGroup, false);
        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CancledFragmentAdapter.MyViewHolder myViewHolder, int i) {

        CancledFragmentModelStatically cancledFragmentModelStatically = cancledFragmentModelStaticallies.get(i);

        myViewHolder.tvNoCancled.setText(cancledFragmentModelStatically.getTvNoCancled());
        myViewHolder.tvCancledPresent.setText(cancledFragmentModelStatically.getTvCancledPresent());

    }

    @Override
    public int getItemCount() {
        return cancledFragmentModelStaticallies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNoCancled, tvCancledPresent;
        private ImageView cancledtransportImgView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNoCancled = itemView.findViewById(R.id.NoCanceledBooking);
            tvCancledPresent = itemView.findViewById(R.id.CanceledPresent);
            cancledtransportImgView = itemView.findViewById(R.id.cancledImg);


        }
    }
}
