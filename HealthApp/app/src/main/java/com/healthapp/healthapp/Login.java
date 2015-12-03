package com.healthapp.healthapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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

/**
 * Authors: Bryen Buie, Clive Hoayun, Chris Guido
 */
public class Login extends VisiblePage
{
    // Variables
    Button loginButton;
    private ProgressBar bar;
    private RelativeLayout rl;
    private static final String PREFS_NAME = "HealthApp_PREFS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.instance = this;

        // Initialize login button
        loginButton = (Button) findViewById(R.id.login_button);

        // Set Click Listener
        loginButton.setOnClickListener(loginListener);

        // Initialize loading circle
        rl = (RelativeLayout) findViewById(R.id.loadingPanel);
        rl.setVisibility(View.GONE);
        bar = (ProgressBar) this.findViewById(R.id.progressBar);
        bar.setVisibility(View.GONE);

        ImageView scales = (ImageView) findViewById(R.id.scales);
        scales.setImageAlpha(18);

        //Pull shared preferences
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String restoreUser = prefs.getString("username", null);
        String restorePass = prefs.getString("password", null);

        //We have shared preferences to use
        if (restoreUser != null && restorePass != null) {
            final EditText username = (EditText) findViewById(R.id.username);
            final EditText password = (EditText) findViewById(R.id.password);
            username.setText(restoreUser);
            password.setText(restorePass);
        }
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
            vi = v;

            //Do Login
            EditText uname  = (EditText)findViewById(R.id.username);
            EditText pword = (EditText)findViewById(R.id.password);

            User.setUsername(uname.getText().toString());
            User.setPassword(pword.getText().toString());

            // Make loading circle visible
            bar.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            loginButton.setVisibility(View.GONE);
            try
            {
                if (User.getCon() == null || User.getCon().isClosed())
                {
                    new Connect().execute(instance);
                }
            }
            catch (Exception e)
            {
                resultMessageHandler(-1);
            }
        }
    };

    public void onConnection()
    {
        new AttemptLogin().execute(instance);
    }

    public static void launchSearch()
    {
        instance.storePreferences();
        instance.gotoSearch(vi);
    }

    public void storePreferences()
    {
        //Store preferences
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("username", User.getUsername());
        editor.putString("password", User.getPassword());
        editor.commit();
    }

    public void resultMessageHandler(Integer i)
    {
        new ResultMessageHandler().showResultDialog(i, instance);
    }
}
