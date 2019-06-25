package com.example.syyam.jobsapp.Utils;

import android.content.Context;

import com.kaopiz.kprogresshud.KProgressHUD;

public class Extras
{

    static KProgressHUD dialog;
    public static void showLoader(Context context) {
        dialog = KProgressHUD.create(context)
                .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                .setCancellable(true)
                .setAnimationSpeed(2)
                .setDimAmount(0.5f)
                .show();

    }

    public static void hideLoader()
    {
        if(dialog!=null)
            dialog.dismiss();
    }
}
