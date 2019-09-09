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

import modelstatically.CompletedFragmentModelStatically;

public class CompletedFragmentAdapter extends RecyclerView.Adapter<CompletedFragmentAdapter.MyViewHolder> {

    Context context;
    List<CompletedFragmentModelStatically> completedFragmentModelStaticallies;

    public CompletedFragmentAdapter(Context context, List<CompletedFragmentModelStatically> completedFragmentModelStaticallies){
        this.context = context;
        this.completedFragmentModelStaticallies = completedFragmentModelStaticallies;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_completed_item,viewGroup,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        CompletedFragmentModelStatically completedFragmentModelStatically = completedFragmentModelStaticallies.get(i);

        myViewHolder.tvNoCompleted.setText(completedFragmentModelStatically.getTvNOCompleted());
        myViewHolder.tvPresent.setText(completedFragmentModelStatically.getTvPresent());

      //  myViewHolder.transportImgView.


    }

    @Override
    public int getItemCount() {
        return completedFragmentModelStaticallies.size() ;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvNoCompleted,tvPresent;
        private ImageView transportImgView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNoCompleted = itemView.findViewById(R.id.NoCompletedBooking);
            tvPresent = itemView.findViewById(R.id.Completedpresent);
            transportImgView = itemView.findViewById(R.id.completedImg);
        }
    }
}
