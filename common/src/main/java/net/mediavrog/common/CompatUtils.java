package net.mediavrog.common;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

/**
 * Utility methods for working around Android deprecations etc.
 *
 * Created by maikvlcek on 2/1/15.
 */
public class CompatUtils {
    public static final String TAG = CompatUtils.class.getSimpleName();

    public static void setBackground(View view, Drawable drawable) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackground(drawable);
        } else {
            view.setBackgroundDrawable(drawable);
        }
    }

    public static void setBackgroundKeepingPadding(View view, Drawable drawable) {
        int top = view.getPaddingTop();
        int left = view.getPaddingLeft();
        int right = view.getPaddingRight();
        int bottom = view.getPaddingBottom();

        setBackground(view, drawable);
        view.setPadding(left, top, right, bottom);
    }
}