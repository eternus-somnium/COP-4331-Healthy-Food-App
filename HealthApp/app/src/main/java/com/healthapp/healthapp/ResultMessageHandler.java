package com.healthapp.healthapp;

import android.app.AlertDialog;
import android.content.DialogInterface;

/**
 * Created by Clive on 12/1/2015.
 */
public class ResultMessageHandler
{
    void showResultDialog(Integer i, final VisiblePage instance)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(instance).create();
        int nextScreen = 0;
        alertDialog.setTitle("Alert");
        if(i == 1)
        {
            alertDialog.setMessage("User successfully created");
            nextScreen = 1;
        }
        else if(i == 2){
            alertDialog.setMessage("Username is required");}
        else if(i == 3){
            alertDialog.setMessage("Passwords are required and must match");}
        else if (i == 4) {
            alertDialog.setMessage("Nutritional database key is required");}
        else if(i == -1){
            alertDialog.setMessage("Could not contact the application server");}
        else if(i == -2){
            alertDialog.setMessage("Username already exists");
        }
        else if(i == -3){
            alertDialog.setMessage("Login failed");
        }

        else
        {
            alertDialog.setMessage("Alert message to be shown");
        }

        final int finalNextScreen = nextScreen;
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which)
                    {
                        dialog.dismiss();
                        if(finalNextScreen == 1)
                            instance.nextScreen(1);
                    }
                });
        alertDialog.show();

    }
}
