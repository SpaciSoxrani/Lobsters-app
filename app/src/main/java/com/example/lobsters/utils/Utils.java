package com.example.lobsters.utils;

import android.content.Context;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.example.lobsters.R;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;

public class Utils {

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
}
