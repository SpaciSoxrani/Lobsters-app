package com.example.lobsters.utils;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.Drawable;

import androidx.appcompat.app.AlertDialog;

import com.example.lobsters.R;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class UiUtils {

    public static final String EVENT_BOTTOMSHEET_FRAGMENT = "event_bottomsheet_fragment";

    public static void ShowSimpleDialog(String title, String description, Context context){
        AlertDialog dialog = new MaterialAlertDialogBuilder(context)
                .setTitle(title)
                .setMessage(description)
               .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialog, int which) {
                       dialog.cancel();
                   }
               }).create();
        dialog.show();
    }

    public static BitmapDescriptor createMarkerIcon(int color) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        return BitmapDescriptorFactory.defaultMarker(hsv[0]);
    }

    public static Drawable getFavouriteDrawable(Context context, boolean isFavourite) {
        return isFavourite
                ? context.getResources().getDrawable(R.drawable.ic_red_favorite_24dp)
                : context.getResources().getDrawable(R.drawable.ic_outline_favorite_border_24dp);
    }
}
