package net.mediavrog.web;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Maik on 26.03.14.
 */
public class CustomWebViewClient extends WebViewClient {
    public static final String TAG = CustomWebViewClient.class.getSimpleName();
    private Uri mInitialUrl;
    private boolean mShouldOpenLinksInternally = true;
    private WebViewLoadingListener mListener;

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (view.getContext() == null || url == null) return false;

        Uri targetUri = Uri.parse(url);

        // load a page in the same WebViev if only a redirect was detected
        if (mShouldOpenLinksInternally || wasSimpleRedirect(targetUri)) {
            //App.log(TAG, url + " was a simple redirect from " + mInitialUrl.toString() + ", so loaded in the current webview.");
            return false;
        } else {
            // open links in new act_browser or handles snapdish scheme
            Intent i = new Intent(Intent.ACTION_VIEW, targetUri);
            view.getContext().startActivity(i);

            // tell the webview we want to handle this ourselves, so it will not load the given url
            return true;
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        if (mInitialUrl == null) mInitialUrl = Uri.parse(url);
        if (mListener != null) mListener.onPageStarted(view, url);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        if (mListener != null) mListener.onPageFinished(view, url);
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
        if (mListener != null) mListener.onError(view, errorCode, description, failingUrl);
    }

    public void setShouldOpenLinksInternally(boolean openLinksExternally) {
        this.mShouldOpenLinksInternally = openLinksExternally;
    }

    public void setLoadingListener(WebViewLoadingListener listener) {
        mListener = listener;
    }

    /**
     * Dumb check for a server redirect. We assume a simple redirect if the path stayed the same, but one of the following differed:
     * <ul>
     * <li>query string</li>
     * <li>domain or subdomain</li>
     * <li>protocol</li>
     * <li>... basically everthing other than the path</li>
     * </ul>
     *
     * @param targetUrl
     * @return
     */
    private boolean wasSimpleRedirect(Uri targetUrl) {
        if (mInitialUrl == null || targetUrl == null) return true;
        return (mInitialUrl.getPath() != null && mInitialUrl.getPath().equals(targetUrl.getPath()));
    }

    public interface WebViewLoadingListener {
        String TAG = WebViewLoadingListener.class.getSimpleName();

        void onPageStarted(WebView view, String url);

        void onPageFinished(WebView view, String url);

        void onError(WebView view, int errorCode, String description, String failingUrl);
    }
}
