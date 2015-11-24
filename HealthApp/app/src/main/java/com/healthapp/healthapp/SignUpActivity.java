package com.healthapp.healthapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.healthapp.healthapp.DatabaseAccess.Connect;
import com.healthapp.healthapp.DatabaseAccess.User;
import com.healthapp.healthapp.DatabaseAccess.ValidateUsername;


public class SignUpActivity extends ConnectCall {

    private static SignUpActivity instance;
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

        if(!User.getBarcode_api_key().equals(""))
        {
            // Validate username
            try {
                if (User.getCon() == null || User.getCon().isClosed()) {
                    new Connect().execute(instance);
                } else new ValidateUsername().execute();
            } catch (Exception e) {
                errorController(-1);
            }
        }
        else errorController(-3);
    }

    public void gotoLogin(View v) {
        Intent intent = new Intent(this,Login.class);
        startActivity(intent);
    }

    private View.OnClickListener doneListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            SignUp();
            gotoLogin(v);
        }
    };

    public void onConnection()
    {
        new ValidateUsername().execute();
    }

    void showErrorDialog(Integer i)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(instance).create();

        alertDialog.setTitle("Alert");
        if(i == -1){
            alertDialog.setMessage("Could not contact the application server");}
        else if(i == -2){
            alertDialog.setMessage("Username already exists");}
        else if(i == -3){
            alertDialog.setMessage("USDA API key is required");}
        else{
            alertDialog.setMessage("Alert message to be shown");}
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }

    public void errorController(Integer i)
    {
        instance.showErrorDialog(i);
    }
}
