package com.healthapp.healthapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.healthapp.healthapp.DatabaseAccess.Connect;
import com.healthapp.healthapp.DatabaseAccess.User;
import com.healthapp.healthapp.DatabaseAccess.ValidateUsername;

/**
 * Authors: Bryen Buie, Clive Hoayun
 */
public class SignUpActivity extends VisiblePage {
    Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        instance = this;

        // Initialize done button
        doneButton = (Button) findViewById(R.id.done_button);

        // Set Click Listener
        doneButton.setOnClickListener(doneListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sign_up, menu);
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

    //Validates the user's information and then attempts to create a user in the database
    public void SignUp()
    {
        EditText uname = (EditText) findViewById(R.id.username);
        EditText pword = (EditText) findViewById(R.id.password);
        EditText foodkey = (EditText) findViewById(R.id.foodkey);
        EditText barcodekey = (EditText) findViewById(R.id.barcodekey);

        User.setUsername(uname.getText().toString());
        User.setPassword(pword.getText().toString());
        User.setFood_api_key(foodkey.getText().toString());
        User.setBarcode_api_key(barcodekey.getText().toString());


        //Validate entries
        if(!usernameOK())
            resultMessageHandler(2);
        else if(!passwordOK())
            resultMessageHandler(3);
        else if(!foodKeyOK())
            resultMessageHandler(4);
        else if(!barcodeKeyOK())
            resultMessageHandler(5);
        else
        {
            try
            {
                if (User.getCon() == null || User.getCon().isClosed())
                    new Connect().execute(instance);
                else onConnection();
            }
            catch (Exception e)
            {
                resultMessageHandler(-1);
            }
        }
    }

    private View.OnClickListener doneListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            vi = v;
            SignUp();
        }
    };

    //Called if a connection to the application database is found or successfully established
    public void onConnection()
    {
        new ValidateUsername().execute(instance);
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
        EditText p2 = (EditText) findViewById(R.id.confirmed_password);

        if(p1.getText().toString().equals("") ||
            !p1.getText().toString().equals(p2.getText().toString()))
            return false;
        else return true;
    }

    //validates the food database key
    public boolean foodKeyOK()
    {
        EditText p1 = (EditText) findViewById(R.id.foodkey);

        if(p1.getText().toString().equals(""))
            return false;
        else return true;
    }

    //validates the barcode database key
    public boolean barcodeKeyOK()
    {
        EditText p1 = (EditText) findViewById(R.id.barcodekey);

        if(p1.getText().toString().equals(""))
            return false;
        else return true;
    }
}
