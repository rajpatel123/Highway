package com.highway.drivermodule.driverFragment;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.highway.R;

public class RatingBottomDialogFragment extends BottomSheetDialogFragment
    implements View.OnClickListener {
  public static final String TAG = "ActionBottomDialog";
  private ItemClickListener mListener;
  private String tripId;

  public static RatingBottomDialogFragment newInstance(String tripId) {
    RatingBottomDialogFragment ratingBottomDialogFragment = new RatingBottomDialogFragment();
    Bundle bundle = new Bundle();
    bundle.putString("tripId", tripId);
    ratingBottomDialogFragment.setArguments(bundle);


    return ratingBottomDialogFragment;

  }
  @Nullable @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    if (getArguments() != null && TextUtils.isEmpty(getArguments().getString("tripId"))) {
      tripId = getArguments().getString("tripId");
    }
    return inflater.inflate(R.layout.fragment_reatingbar, container, false);
  }
  @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
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