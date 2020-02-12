package com.highway.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.text.Html;
import android.text.Spanned;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


/**
 * Created by Manish Bhargav on 1/6/2017 11:42.
 */

public class BaseUtil {


    private static final Gson GSON = new Gson();
    private static String TAG = "BaseUtil";

    public BaseUtil() {


    }

    public static <Model> Model objectFromString(String json, Class<Model> classOfModel) {
        Log.d(TAG, "objectFromString() called with: json = [" + json + "], classOfModel = [" + classOfModel + "]");

        Model model = null;

        try {
            model = GSON.fromJson(json, classOfModel);
        } catch (JsonSyntaxException e) {
            Log.d(TAG, "JsonSyntaxException", e);
        } catch (Exception e) {
            Log.d(TAG, "Exception", e);
        }

        return model;
    }

    public static <Model> String jsonFromModel(Model model) {

        String json = null;

        try {
            json = GSON.toJson(model);
        } catch (JsonSyntaxException e) {
            Log.d(TAG, "JsonSyntaxException", e);
        } catch (Exception e) {
            Log.d(TAG, "Exception", e);
        }
        Log.d(TAG, "jsonFromModel() called with: model = [" + model + "]" + json);

        return json;
    }

    public static <Model> Model objectFromTypeModel(String json, Type listType) {


        Model daysList = null;
        try {
            daysList = GSON.fromJson(json, listType);
        } catch (JsonSyntaxException e) {
            Log.d(TAG, "JsonSyntaxException", e);
        } catch (Exception e) {
            Log.d(TAG, "Exception", e);
        }

        return daysList;
    }


    public static boolean isEqual(String string1, String string2) {
        if (string1 == null || string2 == null)
            return false;
/*        if (string1.isEmpty() && string2.isEmpty())
            return true;*/
        return string1.equals(string2);

    }

    public static float calculateDpToPixel(float dp, Context context) {

        Resources r = context.getResources();
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return px;

    }

    public static int dpToPx(float dp, Context context) {

  /*      DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.density / DisplayMetrics.DENSITY_DEFAULT));*/
        Resources r = context.getResources();
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics());
        return px;
    }

    public static boolean isEqualIgnoreCode(String string1, String string2) {
        if (string1 == null || string2 == null)
            return false;
        if (string1.isEmpty() || string2.isEmpty())
            return false;
        return string1.equalsIgnoreCase(string2);

    }

    public static boolean isNullOrEmpty(String string) {

        if (string == null)
            return true;
        return string.isEmpty();
    }

    public static int screenHeight(AppCompatActivity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;

    }

    public static String formatDate(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
        String stringDate = format.format(date);
        return stringDate;
    }

    public static String formatDateWithTime(Date date) {

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yy 'at' HH:mm");
        String stringDate = "NA";
        try {
            stringDate = format.format(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringDate;
    }

    public static Spanned fromHtml(String html) {
        Spanned result = null;
        if (html != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {

                result = Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY);
            } else {
                result = Html.fromHtml(html);
            }
        }

        return result;
    }

    public static int screenWidth(AppCompatActivity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static Spanned htmlToSpan(String text) {

        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(text, Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(text);
        }
        return result;
    }


    public static String numberToTime(int number) {

        boolean isNegative = false;
        if (number < 0) {
            isNegative = true;
            number = number * -1;
        }
        int seconds = number % 60;
        int minutes = (number - seconds) / 60;
        int hours = minutes / 60;


        String hour = "";
        String min = "";
        String sec = "";

        if (hours < 10)
            hour = "0" + hours;
        else hour = "" + hours;

        if (hours > 0) {
            minutes = minutes % 60;
            if (minutes < 10)
                min = "0" + minutes;
            else min = "" + minutes;
        } else {
            if (minutes < 10)
                min = "0" + minutes;
            else min = "" + minutes;
        }

        if (seconds < 10)
            sec = "0" + seconds;
        else sec = "" + seconds;

        return isNegative ? "-" + hour + ":" + min + ":" + sec : hour + ":" + min + ":" + sec;
    }

    public static Bitmap getBitmapFromView(View view) {
        Log.d(TAG, "getBitmapFromView() called with: view = [" + view + "]");
        //Define a bitmap with the same size as the view
        Bitmap returnedBitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        //Bind a canvas to it
        Canvas canvas = new Canvas(returnedBitmap);
        //Get the view's background
        Drawable bgDrawable = view.getBackground();
        if (bgDrawable != null)
            //has background drawable, then draw it on the canvas
            bgDrawable.draw(canvas);
        //  else
        //does not have background drawable, then draw white background on the canvas
        canvas.drawColor(Color.WHITE);
        //  canvas.drawColor(Color.parseColor("#22FFFFFF"));
        // draw the view on the canvas
        view.draw(canvas);
        //return the bitmap
        return returnedBitmap;
    }

    public static File getFileFromBitmap(Context context, Bitmap bitmap) throws IOException {
        Log.d(TAG, "getFileFromBitmap() called with: context = [" + context + "], bitmap = [" +
                bitmap + "]");
        // String root = Environment.getExternalStorageDirectory().toString();
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).toString();
        String pathDir = "/Timeinator";
        File myDir = new File(root + pathDir);

        if (!myDir.exists())
            myDir.mkdirs();
        else {
            Log.d(TAG, "getFileFromBitmap() called with: myDir.exit = [" + myDir.exists() +
                    "], myDir = [" + myDir + "]");
        }

        File file = new File(myDir.getAbsolutePath(), "shareimage.png");
        // file.createNewFile();
        //  if (file.exists()) file.delete();
//Convert bitmap to byte array
        //Bitmap bitmap = your bitmap;
        //  file.createNewFile();
/*
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    bitmap.compress(Bitmap.CompressFormat.PNG, 0 */
        /*ignored for PNG*//*
, bos);
    byte[] bitmapdata = bos.toByteArray();
*/

//write the bytes in file
        if (!file.exists()) {
            Log.d(TAG, "getFileFromBitmap() called with: context = [" + myDir + "], bitmap = [" + bitmap + "]");
        }
        FileOutputStream fos = new FileOutputStream(file);
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
        //fos.write(bitmapdata);
        fos.flush();
        fos.close();
        return file;
    }

    public static void initShareIntent(String type, String _text, Context context, File filePath) {
        Log.d(TAG, "initShareIntent() called with: type = [" + type + "], _text = [" + _text +
                "], context = [" + context + "], filePath = [" + filePath + "]");
        //File filePath = context.getFileStreamPath("shareimage.jpg");  //optional //internal storage
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, _text);
        Uri photoURI = FileProvider.getUriForFile(context, "com.example.fileprovider", filePath);
        shareIntent.putExtra(Intent.EXTRA_STREAM, photoURI);  //optional//use this when you want to send an image
        shareIntent.setType("image/jpeg");
        //  shareIntent.setPackage("com.facebook.katana");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> activityList = pm.queryIntentActivities(shareIntent, 0);
        // final ComponentName name = new ComponentName(activity.applicationInfo.packageName, activity.name);
        // shareIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        shareIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        //shareIntent.setComponent(name);
        // context.startActivity(shareIntent);
        context.startActivity(Intent.createChooser(shareIntent, "Share Image shot"));

    }

    public static String twoDecimalPointNumber(float amount) {
        return String.format("$%,.2f", amount);
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }
}
