package com.healthapp.healthapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.sql.Connection;
import java.sql.SQLException;

public class SignUpActivity extends AppCompatActivity {

    Connection connect = DatabaseAccess.Connect();
    Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

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
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);
        EditText foodkey = (EditText) findViewById(R.id.foodkey);
        EditText barcodekey = (EditText) findViewById(R.id.barcodekey);

        int valid = 0;

        // Validate username
        try {
            valid = DatabaseAccess.ValidateUsername(username.toString(), connect);
        } catch (SQLException e) {
            AlertDialog alertDialog = new AlertDialog.Builder(SignUpActivity.this).create();
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

        // Insert user in database
        if(valid == 0)
        {
            try {
                DatabaseAccess.InsertUsername(username.toString(), password.toString(), connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Insert Food Key
        if(valid == 0)
        {
            try {
                DatabaseAccess.InsertFoodKey(username.toString(), password.toString(), foodkey.toString(), connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Insert Barcode Key
        if(valid == 0)
        {
            try {
                DatabaseAccess.InsertBarcodeKey(username.toString(), password.toString(), barcodekey.toString(), connect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

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
}
