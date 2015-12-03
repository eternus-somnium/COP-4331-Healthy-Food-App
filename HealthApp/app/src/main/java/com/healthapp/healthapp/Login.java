package com.healthapp.healthapp;

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
            username.setText(restoreUser.trim());
            password.setText(restorePass.trim());
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

            User.setUsername(uname.getText().toString().trim());
            User.setPassword(pword.getText().toString().trim());

            //Validate entries
            if(!usernameOK())
                resultMessageHandler(2);
            else if(!passwordOK())
                resultMessageHandler(3);
            else {
                // Make loading circle visible
                bar.setVisibility(View.VISIBLE);
                rl.setVisibility(View.VISIBLE);
                loginButton.setVisibility(View.GONE);
                try {
                    if (User.getCon() == null || User.getCon().isClosed()) {
                        new Connect().execute(instance);
                    }
                } catch (Exception e) {
                    resultMessageHandler(-1);
                }
            }
        }
    };

    //Called if a connection to the application database is found or successfully established
    public void onConnection()
    {
        new AttemptLogin().execute(instance);
    }

    //Called if the user successfully logs in
    public static void launchSearch()
    {
        instance.storePreferences();
        instance.gotoSearch(vi);
    }

    //Stores the user's username and password for future login attempts
    public void storePreferences()
    {
        //Store preferences
        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString("username", User.getUsername());
        editor.putString("password", User.getPassword());
        editor.commit();
    }

    //validates the username
    public boolean usernameOK()
    {
        EditText p1 = (EditText) findViewById(R.id.username);

        if(p1.getText().toString().equals(""))
            return false;
        else return true;
    }

    //validates the password
    public boolean passwordOK()
    {
        EditText p1 = (EditText) findViewById(R.id.password);

        if(p1.getText().toString().equals(""))
            return false;
        else return true;
    }

    //Overrides VisiblePage result message handler to stop loading circle
    public void resultMessageHandler(Integer i)
    {
        // Make loading circle invisible
        bar.setVisibility(View.GONE);
        rl.setVisibility(View.GONE);
        loginButton.setVisibility(View.VISIBLE);

        new ResultMessageHandler().showResultDialog(i, instance);
    }
}
