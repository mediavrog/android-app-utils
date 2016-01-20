package net.mediavrog.ui;

/**
 * Created by maikvlcek on 10/6/14.
 */
public interface OnScrollListener {
    String TAG = OnScrollListener.class.getSimpleName();

    void onScroll(int scrollX, int scrollY);
}
