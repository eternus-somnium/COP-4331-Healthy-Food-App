package com.healthapp.healthapp;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.healthapp.healthapp.DatabaseAccess.AttemptLogin;
import com.healthapp.healthapp.DatabaseAccess.Connect;
import com.healthapp.healthapp.DatabaseAccess.Disconnect;
import com.healthapp.healthapp.DatabaseAccess.User;

import java.sql.SQLException;

public class Login extends AppCompatActivity
{
    Button loginButton;
    private static Login instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.instance = this;
        // Initialize done button
        loginButton = (Button) findViewById(R.id.login_button);

        // Set Click Listener
        loginButton.setOnClickListener(loginListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private View.OnClickListener loginListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v) {

            //Do Login
            EditText username = (EditText) findViewById(R.id.username);
            EditText password = (EditText) findViewById(R.id.password);

            try {

                if (User.getCon() == null || User.getCon().isClosed())
                    new Connect().execute();
            } catch (Exception e) {
                AlertDialog alertDialog = new AlertDialog.Builder(Login.this).create();
                alertDialog.setTitle("Alert");
                alertDialog.setMessage("Alert message to be shown");
                alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                alertDialog.show();
            }
        }
    };

    public void gotoSearch(View v) {
        Intent intent = new Intent(instance,Search.class);
        startActivity(intent);
    }

    public void gotoSignUp(View v) {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public static void launchSearch()
    {
        //instance.gotoSearch(v);
    }

    void showErrorDialog(Integer i)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(instance).create();

        alertDialog.setTitle("Alert");
        if(i == 1)
            alertDialog.setMessage("Could not contact the application server");
        else if(i == 2)
            alertDialog.setMessage("Login failed");
        else
            alertDialog.setMessage("Alert message to be shown");
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    public static void ErrorController(Integer i)
    {
        instance.showErrorDialog(i);
    }
}
