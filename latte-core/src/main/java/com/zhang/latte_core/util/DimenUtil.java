package com.zhang.latte_core.util;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.zhang.latte_core.app.Latte;

/**
 * Created by 德医互联 on 2017/8/29.
 */

public class DimenUtil {
    public static int getScreenWidth(){
        final Resources resources= Latte.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    public static int getScreenHeight(){
        final Resources resources= Latte.getApplicationContext().getResources();
        final DisplayMetrics dm=resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
