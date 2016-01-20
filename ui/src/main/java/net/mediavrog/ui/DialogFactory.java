package net.mediavrog.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;

import net.mediavrog.ic_card_expensetracker.R;

/**
 * Created by Maik on 11.03.14.
 */
public class DialogFactory {

    public static AlertDialog.Builder createPrompt(Context ctx, int titleResId, int messageResId, int defaultValueResId, final OnReceiveUserInputListener listener) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.dialog_prompt, null);
        final EditText input = (EditText) layout.findViewById(R.id.input);
        if (defaultValueResId != 0) input.setText(defaultValueResId);

        return new AlertDialog.Builder(ctx)
                .setTitle(ctx.getString(titleResId))
                .setMessage(ctx.getString(messageResId))
                .setView(layout)
                .setPositiveButton(ctx.getString(android.R.string.ok), new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        if (listener != null)
                            listener.receiveResult(String.valueOf(input.getText()));
                    }
                }).setNegativeButton(ctx.getString(android.R.string.cancel), null);
    }

    public static AlertDialog.Builder createHtmlAssetPopup(Context ctx, int titleResId, String assetName) {
        WebView wv = (WebView) LayoutInflater.from(ctx).inflate(R.layout.simple_web_view, null);
        wv.loadUrl("file:///android_asset/" + assetName + ".html");

        return new AlertDialog.Builder(ctx)
                .setTitle(ctx.getString(titleResId))
                .setView(wv)
                .setNeutralButton(android.R.string.ok, null);
    }

    public interface OnReceiveUserInputListener {
        public void receiveResult(String userInput);
    }
}
