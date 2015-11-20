package com.healthapp.healthapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;


import com.healthapp.healthapp.DatabaseAccess.AttemptLogin;
import com.healthapp.healthapp.DatabaseAccess.Connect;
import com.healthapp.healthapp.DatabaseAccess.User;


public class Login extends AppCompatActivity
{
    Button loginButton;
    private static Login instance = null;
    private ProgressBar bar;
    private RelativeLayout rl;
    public static Context ctx;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        this.instance = this;
        // Initialize done button
        loginButton = (Button) findViewById(R.id.login_button);

        // Set Click Listener
        loginButton.setOnClickListener(loginListener);

        rl = (RelativeLayout) findViewById(R.id.loadingPanel);
        rl.setVisibility(View.GONE);
        bar = (ProgressBar) this.findViewById(R.id.progressBar);
        bar.setVisibility(View.GONE);

        ImageView scales = (ImageView) findViewById(R.id.scales);
        scales.setImageAlpha(18);
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

            bar.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);
            try {

                if (User.getCon() == null || User.getCon().isClosed()) {
                    new Connect().execute();
                }
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

            User.setUsername(findViewById(R.id.username).toString());
            User.setPassword(findViewById(R.id.password).toString());

            new Connect().execute();
        }
    };

    public void gotoSearch() {
        Intent intent = new Intent(ctx,Search.class);
        ctx.startActivity(intent);
    }

    public void gotoSignUp(View v) {
        Intent intent = new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }

    public static void attemptLogin()
    {
        new AttemptLogin().execute();
    }

    public static void launchSearch()
    {
        instance.gotoSearch();
    }

    void showErrorDialog(Integer i)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(instance).create();

        alertDialog.setTitle("Alert");
        if(i == -1)
            alertDialog.setMessage("Could not contact the application server");
        else if(i == -2)
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

    public static void errorController(Integer i)
    {
        instance.showErrorDialog(i);
    }
}
