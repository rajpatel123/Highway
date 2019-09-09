package fragment;

import android.content.Context;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.highway.R;

import java.util.ArrayList;
import java.util.List;

import adapterstatically.WalletAdapter;
import modelstatically.WalletModelStatically;

public class WalletFragment extends Fragment {



    private WalletAdapter walletAdapter;

    List<WalletModelStatically> walletModelStaticallies = new ArrayList<>();

    public WalletFragment() {
        // Required empty public constructor
    }


    public static WalletFragment newInstance() {
        WalletFragment fragment = new WalletFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
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
      View view = inflater.inflate(R.layout.fragment_wallet, container, false);

        prepareList();
      return view;

    }

    public void prepareList(){

        walletModelStaticallies = new ArrayList<>();

        WalletModelStatically walletModelStatically = new WalletModelStatically();
        walletModelStatically.setTvNetAmount("100");
        walletModelStatically.setTvAddMoneyView("Add Money");
        walletModelStatically.setEtEnterRechargeAmount("Enter Recharge Amount");
        walletModelStatically.setTvFirstMoney("299");
        walletModelStatically.setTvSecondMoney("599");
        walletModelStatically.setTvThirdMoney("1099");
        walletModelStatically.setTvPromocode("Have a Promo Code");
        walletModelStatically.setBtnAddAmount("Add Amount");

        walletModelStaticallies.add(walletModelStatically);
    }


    @Override
    public void onResume() {
        super.onResume();

        showWalletDetails();
    }

    public void showWalletDetails(){
        if (walletModelStaticallies !=null && walletModelStaticallies.size()>0){

            Log.d("Data Size ",""+walletModelStaticallies.size());

            walletAdapter = new WalletAdapter(walletModelStaticallies, getActivity());

        }


    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

    }

}
