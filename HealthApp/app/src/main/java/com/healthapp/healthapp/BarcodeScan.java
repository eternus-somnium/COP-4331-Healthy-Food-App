package com.healthapp.healthapp;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import java.util.List;

import me.dm7.barcodescanner.zbar.BarcodeFormat;
import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by Clive on 10/29/2015.
 */

public class BarcodeScan extends AppCompatActivity implements ZBarScannerView.ResultHandler
{
        private ZBarScannerView mScannerView;
        String barcode, productName;
        List<BarcodeFormat> formats;

        @Override
        public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
/*Causing a break
                //formats.add(BarcodeFormat.PARTIAL);
                formats.add( BarcodeFormat.EAN8);
                formats.add( BarcodeFormat.UPCE);
                formats.add( BarcodeFormat.ISBN10);
                formats.add( BarcodeFormat.UPCA);
                formats.add( BarcodeFormat.EAN13);
                formats.add( BarcodeFormat.ISBN13);
                formats.add( BarcodeFormat.I25);
                //formats.add( BarcodeFormat.DATABAR);
                //formats.add( BarcodeFormat.DATABAR_EXP);
                //formats.add( BarcodeFormat.CODABAR);
                formats.add( BarcodeFormat.CODE39);
                //formats.add( BarcodeFormat.PDF417);
                formats.add( BarcodeFormat.CODE93);
                formats.add( BarcodeFormat.CODE128);
*/
                mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view

                //mScannerView.setFormats(formats);

                setContentView(mScannerView);                // Set the scanner view as the content view
                }

        @Override
        public void onResume() {
                super.onResume();
                mScannerView.setResultHandler(this); // Register ourselves as a handler for scan results.
                mScannerView.startCamera();          // Start camera on resume
                }

        @Override
        public void onPause() {
                super.onPause();
                mScannerView.stopCamera();           // Stop camera on pause
                }

        @Override
        public void handleResult(Result rawResult) {
                Toast.makeText(this, "Contents = " + rawResult.getContents() +
                ", Format = " + rawResult.getBarcodeFormat().getName(), Toast.LENGTH_SHORT).show();
                barcode = rawResult.getContents();

                showDialog();
        }

        void showDialog()
        {
                DialogFragment newFragment = MyAlertDialogFragment.newInstance(
                        R.string.alert_dialog_two_buttons_title, barcode);
                newFragment.show(getFragmentManager(), "dialog");
        }

        public void doPositiveClick()
        {
                // Do stuff here.
                //Test Results
                Intent result = new Intent(barcode);
                setResult(Activity.RESULT_OK, result);
                finish();
                //Log.i("FragmentAlertDialog", "Positive click!");
        }

        public void doNegativeClick() {
                // Do stuff here.
                mScannerView.startCamera();
                //Log.i("FragmentAlertDialog", "Negative click!");
        }
}
