package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.highway.R;
import com.highway.common.base.activity.DashBoardActivity;

public class InvoiceBottomDialogFragment extends BottomSheetDialogFragment
    implements View.OnClickListener {
  public static final String TAG = "ActionBottomDialog";
  private ItemClickListener mListener;
  private Button confirmPaymentBtn;

  public static InvoiceBottomDialogFragment newInstance() {
    return new InvoiceBottomDialogFragment();
  }
  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_invoice_dialog, container, false);

    confirmPaymentBtn= view.findViewById(R.id.btnConfirmPayment);


    return view;
  }
  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);


    confirmPaymentBtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        ((DashBoardActivity) getActivity()).showratingBottomSheet();
        dismiss();

      }
    });
  }
  @Override
  public void onAttach(Context context) {
    super.onAttach(context);

  }
  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }
  @Override public void onClick(View view) {
    TextView tvSelected = (TextView) view;
    mListener.onItemClick(tvSelected.getText().toString());
    dismiss();
  }
  public interface ItemClickListener {
    void onItemClick(String item);
  }
}