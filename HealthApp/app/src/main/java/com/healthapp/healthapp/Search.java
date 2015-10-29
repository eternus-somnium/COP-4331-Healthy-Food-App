package com.healthapp.healthapp;


import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;



public class Search extends AppCompatActivity {

    private EditText searchField;
    private String sField;
    ImageButton sButton;
    ImageButton cButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Initializing text field
        searchField = (EditText) findViewById(R.id.search_field);
        sField = searchField.getText().toString();

        // Initialize image button
        sButton = (ImageButton) findViewById(R.id.imageButton);
        sButton.setOnClickListener(barcodeListener);
        cButton = (ImageButton) findViewById(R.id.clear_button);


        // Set Text Watcher
        searchField.addTextChangedListener(myWatcher);
        // Set Click Listener
        cButton.setOnClickListener(clearListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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

    /*Clear Button Listener*/
    private View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            searchField = (EditText) findViewById(R.id.search_field);
            sField = searchField.getText().toString();

            searchField.setText("");
        }
    };
    /*Barcode Scanner Button Listener*/
    private View.OnClickListener barcodeListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchSimpleActivity(v);
        }
    };
    /*Search Button Listener*/
    private View.OnClickListener searchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            launchSearchActivity(v);
        }
    };

    /*Text Watcher*/
    private final TextWatcher myWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            searchField = (EditText) findViewById(R.id.search_field);
            sField = searchField.getText().toString();

            // Initialize image button
            sButton = (ImageButton) findViewById(R.id.imageButton);
            if(sField.matches("")){
                sButton.setImageResource(R.drawable.scanner);
                sButton.setOnClickListener(barcodeListener);
                cButton = (ImageButton) findViewById(R.id.clear_button);
                cButton.setVisibility(View.INVISIBLE);
            }
            else {
                sButton.setImageResource(R.drawable.search);
                searchField = (EditText) findViewById(R.id.search_field);
                sField = searchField.getText().toString();

                // Initialize image button
                sButton = (ImageButton) findViewById(R.id.imageButton);
                sButton.setOnClickListener(searchListener);

                cButton = (ImageButton) findViewById(R.id.clear_button);
                cButton.setVisibility(View.VISIBLE);

            }
        }
    };

    /*Intent to go to Barcode Scanner activity*/
    public void launchSimpleActivity(View v) {
        Intent intent = new Intent(this, BC_Test.class);
        startActivity(intent);
    }
    /*Intent to go to Search activity*/
    public void launchSearchActivity(View v) {
        Intent intent = new Intent(this, Results.class);
        startActivity(intent);
    }

}
