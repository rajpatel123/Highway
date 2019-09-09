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

import modelstatically.OnGoingFragmentModelStatically;

public class OnGoingFragmentAdapter extends RecyclerView.Adapter<OnGoingFragmentAdapter.MyViewHolder> {

    Context context;
    List<OnGoingFragmentModelStatically>onGoingFragmentModelStaticallies ;

    public OnGoingFragmentAdapter(List<OnGoingFragmentModelStatically>onGoingFragmentModelStaticallies , Context context ){
        this.context  = context;
        this.onGoingFragmentModelStaticallies = onGoingFragmentModelStaticallies;

    }



    @NonNull
    @Override
    public OnGoingFragmentAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_on_going_item,viewGroup,false);
        return new MyViewHolder(view);


    }

    @Override
    public void onBindViewHolder(@NonNull OnGoingFragmentAdapter.MyViewHolder myViewHolder, int i) {

        OnGoingFragmentModelStatically onGoingFragmentModelStatically = onGoingFragmentModelStaticallies.get(i);

        myViewHolder.tvNoOnGoing.setText(onGoingFragmentModelStatically.getTvNoOnGoing());
        myViewHolder.tvOnGoingPresent.setText(onGoingFragmentModelStatically.getTvOnGoingPresent());


    }

    @Override
    public int getItemCount() {
        return onGoingFragmentModelStaticallies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNoOnGoing,tvOnGoingPresent;
        private ImageView onGoingTransportImgView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNoOnGoing = itemView.findViewById(R.id.NoOnGoingBooking);
            tvOnGoingPresent =  itemView.findViewById(R.id.OnGoingPresent);
            onGoingTransportImgView = itemView.findViewById(R.id.OnGoingImg);

        }
    }
}
