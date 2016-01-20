package net.mediavrog.ui;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.AutoCompleteTextView;

/**
 * Created by maik_vlcek on 6/10/15.
 */
public class DelayedAutoCompleteTextView extends AutoCompleteTextView {
    public static final String TAG = DelayedAutoCompleteTextView.class.getSimpleName();

    private final int DEFAULT_AUTOCOMPLETE_DELAY_IN_MS = 500;

    private int mDelay = DEFAULT_AUTOCOMPLETE_DELAY_IN_MS;
    private Handler mHandler = new Handler();

    public DelayedAutoCompleteTextView(Context context) {
        super(context, null);
    }

    public DelayedAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public DelayedAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * <p>Returns the time in milliseconds the actual filtering will be delayed.</p>
     *
     * @return the time in milliseconds to delay the filtering
     *
     * @see #setDelay(int)
     */
    @SuppressWarnings({"UnusedDeclaration"})
    public int getDelay() {
        return mDelay;
    }

    /**
     * <p>Specifies the time in milliseconds the actual filtering will be delayed.
     * This allows to have multiple characters typed before making a possibly expensive filter operation.</p>
     *
     * <p>When <code>delay</code> is less than or equals 0, a delay of
     * 1 is applied.</p>
     *
     * @param delay the time in milliseconds to delay the filtering
     *
     * @see #getDelay()
     */
    @SuppressWarnings({"UnusedDeclaration"})
    public void setDelay(int delay) {
        if (delay <= 0) {
            delay = 1;
        }

        this.mDelay = delay;
    }

    protected void performFiltering(final CharSequence text, final int keyCode) {
        mHandler.removeCallbacksAndMessages(null);
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                performFilteringNow(text, keyCode);
            }
        }, mDelay);
    }

    protected void performFilteringNow(CharSequence text, int keyCode) {
        super.performFiltering(text, keyCode);
    }
}
