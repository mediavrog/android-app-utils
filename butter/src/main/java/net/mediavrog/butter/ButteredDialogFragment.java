package net.mediavrog.butter;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by maikvlcek on 6/21/15.
 */
public abstract class ButteredDialogFragment extends DialogFragment {
    public static final String TAG = ButteredDialogFragment.class.getSimpleName();

    protected boolean isModal = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (isModal) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else {
            return doBuildView(inflater, container);
        }
    }

    @Override
    public void onViewCreated(final View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupUi(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected View doBuildView(LayoutInflater inflater, ViewGroup container) {
        View view = buildView(inflater, container);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        // custom view
        View v = doBuildView(getActivity().getLayoutInflater(), null);
        builder.setView(v);

        setupDialog(builder);

        // setup view
        setupUi(v);

        // Create the AlertDialog object and return it
        return builder.create();
    }

    protected abstract void setupDialog(AlertDialog.Builder builder);

    protected abstract void setupUi(View view);

    protected abstract View buildView(LayoutInflater inflater, ViewGroup container);
}
