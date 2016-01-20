package net.mediavrog.ui;

import android.widget.ScrollView;

/**
 * Created by maikvlcek on 4/27/14.
 */
public interface OnScrollChangedListener {
    String TAG = OnScrollChangedListener.class.getSimpleName();

    void onScrollChanged(ScrollView who, int l, int t, int oldl, int oldt);
}