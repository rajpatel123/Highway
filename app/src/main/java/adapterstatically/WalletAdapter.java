package adapterstatically;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.highway.R;

import java.util.List;

import modelstatically.WalletModelStatically;

public class WalletAdapter extends RecyclerView.Adapter<WalletAdapter.MyViewHolder> {

    Context context;
    List<WalletModelStatically>walletModelStaticallies;

    public WalletAdapter(List<WalletModelStatically>walletModelStaticallies,Context context){
        this.context = context;
        this.walletModelStaticallies = walletModelStaticallies;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.fragment_wallet,viewGroup,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {



    }

    @Override
    public int getItemCount() {

        return walletModelStaticallies.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView tvNetAmount,tvFirstMoney,tvSecondMoney,tvThirdMoney,tvPromocode;
        private Button btnAddAmount;
        private CheckBox cbPromocode;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvNetAmount = itemView.findViewById(R.id.netAmount);

            tvFirstMoney = itemView.findViewById(R.id.first_money);
            tvSecondMoney = itemView.findViewById(R.id.second_money);
            tvThirdMoney = itemView.findViewById(R.id.third_money);

            tvPromocode = itemView.findViewById(R.id.promoCode);

            cbPromocode = itemView.findViewById(R.id.promoCodeCheckBox);

            btnAddAmount = itemView.findViewById(R.id.addAmount);

            //tvNetAmount = itemView.findViewById(R.id.netAmount);

        }
    }
}
