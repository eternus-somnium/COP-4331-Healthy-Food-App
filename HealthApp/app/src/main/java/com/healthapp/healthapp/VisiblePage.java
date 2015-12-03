package com.healthapp.healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.healthapp.healthapp.DatabaseAccess.User;

/**
 * Created by Clive on 12/1/2015.
 */

//Acts as a parent class for all classes that display information to the user
public class VisiblePage extends AppCompatActivity
{
    static final int BARCODE_SCAN_REQUEST = 1;
    static VisiblePage instance = null;
    static View vi;

    //Calls the result message handler class
    public void resultMessageHandler(Integer i)
    {
        new ResultMessageHandler().showResultDialog(i, instance);
    }

    public void onConnection(){}
    public void storePreferences(){}

    //Controls switching between screens from the result message handler
    public void nextScreen(int i){
        if(i == 1)
        {
            gotoLogin(vi);
        }
        else if(i == 2)
        {
            gotoSignUp(vi);
        }
        else if(i == 3)
        {
            gotoSearch(vi);
        }
        else if(i == 4)
        {
            gotoBarcodeScanner(vi);
        }
        else if(i == 5)
        {
            gotoResults(vi, User.getFoodID());
        }
        else if(i == 6)
        {
            gotoReview(vi, Results.product);
        }
        else if(i == 7)
        {
            gotoCreateReview(vi, Results.product, ReviewsActivity.oldComment, ReviewsActivity.oldRating);
        }
    }

    public void gotoLogin(View v) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }
    public void gotoSignUp(View v) {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
    public void gotoSearch(View v) {
        Intent intent = new Intent(this,Search.class);
        this.startActivity(intent);
    }
    public void gotoBarcodeScanner(View v) {
        Intent intent = new Intent(this, BarcodeScan.class);
        startActivityForResult(intent, BARCODE_SCAN_REQUEST);
    }
    public void gotoResults(View v, String dbKey) {

        Intent intent = new Intent(this, Results.class);
        intent.putExtra("Food ID", dbKey);
        startActivity(intent);
    }
    public void gotoReview(View v, String product) {
        Intent intent = new Intent(this,ReviewsActivity.class);
        intent.putExtra("Product", product);
        this.startActivity(intent);
    }
    public void gotoCreateReview(View v, String product, String comment, float rating) {
        Intent intent = new Intent(this,CreateReview.class);
        intent.putExtra("Product", product);
        intent.putExtra("Old Comment", comment);
        intent.putExtra("Old Rating", rating);
        this.startActivity(intent);
    }
}
