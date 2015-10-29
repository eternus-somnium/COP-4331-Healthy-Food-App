package com.healthapp.healthapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * Created by Clive on 10/29/2015.
 */
public class BarcodeScan extends AppCompatActivity implements ZBarScannerView.ResultHandler {
private ZBarScannerView mScannerView;

@Override
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mScannerView = new ZBarScannerView(this);    // Programmatically initialize the scanner view
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
        // Do something with the result here
        Log.v("TAG", rawResult.getContents()); // Prints scan results
        Log.v("TAG", rawResult.getBarcodeFormat().getName()); // Prints the scan format (qrcode, pdf417 etc.)

        Toast.makeText(this, "Contents = " + rawResult.getContents() +
        ", Format = " + rawResult.getBarcodeFormat().getName(), Toast.LENGTH_SHORT).show();
        mScannerView.startCamera();
        }
        }
