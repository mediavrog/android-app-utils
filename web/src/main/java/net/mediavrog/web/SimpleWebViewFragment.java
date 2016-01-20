package net.mediavrog.web;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;


/**
 * Created by maikvlcek on 1/13/16.
 */
public class SimpleWebViewFragment extends WebViewFragment {
    public static final String TAG = SimpleWebViewFragment.class.getSimpleName();

    protected static final String A_START_URL = "startUrl";
    protected static final String A_LINKS_INTERNAL = "openLinksInternally";

    protected String mUrl;
    protected boolean mOpenLinksInternally;

    public SimpleWebViewFragment() {
        super();
    }

    static SimpleWebViewFragment newInstance(String url, boolean openLinksInternally) {
        SimpleWebViewFragment f = new SimpleWebViewFragment();

        Bundle args = new Bundle();
        //args.putParcelable(ARG_CARD_INFO, cardInfo);
        args.putString(A_START_URL, url);
        args.putBoolean(A_LINKS_INTERNAL, openLinksInternally);

        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUrl = getArguments().getString(A_START_URL);
        mOpenLinksInternally = getArguments().getBoolean(A_LINKS_INTERNAL);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeWebView(getWebView(), getCurrentUrl(), getWebViewClient(), App.getUserAgentString(false));
    }

    @Override
    public void onDestroyView() {
        mUrl = getCurrentUrl();
        super.onDestroyView();
    }

    public void openUrl(String url) {
        if (getWebView() != null) getWebView().loadUrl(url);
    }

    public String getCurrentUrl() {
        return getWebView() == null || getWebView().getUrl() == null ? mUrl : getWebView().getUrl();
    }

    public boolean tryGoBack() {
        if (getWebView() != null && getWebView().canGoBack()) {
            getWebView().goBack();
            return true;
        }
        return false;
    }

    public void reload() {
        if (getWebView() != null) getWebView().reload();
    }

    protected WebViewClient getWebViewClient() {
        CustomWebViewClient client = new CustomWebViewClient();
        client.setShouldOpenLinksInternally(mOpenLinksInternally);
        return client;
    }

    protected void initializeWebView(WebView web, String url, WebViewClient client, String userAgent) {
        web.setWebViewClient(client == null ? new CustomWebViewClient() : client);

        web.setInitialScale(1);

        web.getSettings().setJavaScriptEnabled(true);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);

        web.getSettings().setSupportZoom(true);
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);

        web.getSettings().setUserAgentString(web.getSettings().getUserAgentString() + " " + userAgent);
//		App.logW(TAG, "setting user agent for web view to: " + web.getSettings().getUserAgentString());

        if (url != null) web.loadUrl(url);
    }

}
