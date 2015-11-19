package com.healthapp.healthapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * Created by Clive on 11/12/2015.
 */
public class BarcodeConfirmationDialog extends DialogFragment
{

    public static BarcodeConfirmationDialog newInstance(int title, String message)
    {
        BarcodeConfirmationDialog frag = new BarcodeConfirmationDialog();
        Bundle args = new Bundle();
        args.putInt("title", title);
        args.putString("message", message);
        frag.setArguments(args);
        return frag;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        int title = getArguments().getInt("title");
        String message = getArguments().getString("message");

        return new AlertDialog.Builder(getActivity())
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.alert_dialog_ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((BarcodeScan)getActivity()).doPositiveClick();
                            }
                        }
                )
                .setNegativeButton(R.string.alert_dialog_cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                ((BarcodeScan)getActivity()).doNegativeClick();
                            }
                        }
                )
                .create();
    }
}