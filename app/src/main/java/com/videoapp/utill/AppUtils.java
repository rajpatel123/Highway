package com.videoapp.utill;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

public class AppUtils {
    private static ProgressDialog pDialog;

    public static void showProgressDialog(Context context, String message) {
        if (pDialog != null) {
            pDialog.dismiss();
        }
        pDialog = new ProgressDialog(context);
        pDialog.setMessage(message);
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.show();
    }


    public static void dismissProgressDialog() {
        try {
            if (null != pDialog && pDialog.isShowing()) {
                pDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayToast(Context applicationContext, String s) {
        Toast.makeText(applicationContext,s,Toast.LENGTH_LONG).show();

    }
}
