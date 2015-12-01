package com.healthapp.healthapp;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;


import com.healthapp.healthapp.DatabaseAccess.FoodReportURL;
import com.healthapp.healthapp.DatabaseAccess.SearchFoodURL;


public class Search extends AppCompatActivity
{

    private EditText searchField;
    public String sField;
    ImageButton sButton;
    ImageButton cButton;
    static final int BARCODE_SCAN_REQUEST = 1;
    public static String dbKey;

    private static Search instance = null;
    private static View vi;

    private static LinearLayout itemsLayout;
    private static ProgressBar bar;
    private static RelativeLayout rl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        instance = this;

        itemsLayout = (LinearLayout) findViewById(R.id.items_layout);

        // Initializing text field
        searchField = (EditText) findViewById(R.id.search_field);
        sField = searchField.getText().toString();

        // Initialize image button
        sButton = (ImageButton) findViewById(R.id.imageButton);

        cButton = (ImageButton) findViewById(R.id.clear_button);


        // Set Text Watcher
        searchField.addTextChangedListener(myWatcher);
        // Set Click Listener
        cButton.setOnClickListener(clearListener);

        rl = (RelativeLayout) findViewById(R.id.loadingPanel);
        rl.setVisibility(View.GONE);
        bar = (ProgressBar) this.findViewById(R.id.progressBar);
        bar.setVisibility(View.GONE);
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


    private View.OnClickListener clearListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            searchField = (EditText) findViewById(R.id.search_field);
            sField = searchField.getText().toString();

            searchField.setText("");
            itemsLayout.removeAllViews();
        }
    };

    private View.OnClickListener searchDatabase = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            bar.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            vi = v;
            new SearchFoodURL().execute(sField);
            bar.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);
        }
    };

    private View.OnClickListener parseBarcode = new View.OnClickListener() {
        @Override
        public void onClick(View v)
        {
            bar.setVisibility(View.VISIBLE);
            rl.setVisibility(View.VISIBLE);
            BarcodeScanner(v);
            bar.setVisibility(View.GONE);
            rl.setVisibility(View.GONE);
        }
    };

    private final TextWatcher myWatcher = new TextWatcher()
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after)
        {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count)
        {

        }

        @Override
        public void afterTextChanged(Editable s) {
            searchField = (EditText) findViewById(R.id.search_field);
            sField = searchField.getText().toString();

            // Initialize image button
            sButton = (ImageButton) findViewById(R.id.imageButton);
            if(sField.matches("")){
                sButton.setImageResource(R.drawable.scanner);
                cButton = (ImageButton) findViewById(R.id.clear_button);
                cButton.setVisibility(View.INVISIBLE);
                sButton.setOnClickListener(parseBarcode);
            }
            else {
                sButton.setImageResource(R.drawable.search);
                searchField = (EditText) findViewById(R.id.search_field);
                sField = searchField.getText().toString();

                // Initialize image button
                sButton = (ImageButton) findViewById(R.id.imageButton);

                cButton = (ImageButton) findViewById(R.id.clear_button);
                cButton.setVisibility(View.VISIBLE);
                sButton.setOnClickListener(searchDatabase);
            }
        }
    };

    public void BarcodeScanner(View v) {
        Intent intent = new Intent(this, BarcodeScan.class);
        startActivityForResult(intent, BARCODE_SCAN_REQUEST);
    }

    public void gotoResults(View v) {

        Intent intent = new Intent(this, Results.class);
        intent.putExtra("Food ID", dbKey);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Check which request we're responding to
        if (requestCode == BARCODE_SCAN_REQUEST)
        {
            // Make sure the request was successful
            if (resultCode == RESULT_OK)
            {
                searchField.setText(data.getAction());

            }
        }
    }

    public static void populateList(String[][] items)
    {
        if(items[0][1].equals("false")){
            // print error message
            AlertDialog alertDialog = new AlertDialog.Builder(instance).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage(items[0][0]);
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }


        else {

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            final String[] databaseKeys = new String[items.length];

            for(int i=0; i < items.length; i++) {
                Button foodItem = new Button(instance);
                foodItem.setBackgroundResource(R.drawable.healthbuttons);
                foodItem.setTransformationMethod(null);
                foodItem.setText(items[i][0]);
                databaseKeys[i] = items[i][1];
                foodItem.setId(i);
                foodItem.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Button clickedFood = (Button) v;
                        dbKey = databaseKeys[clickedFood.getId()];
                        //dbKey = String.valueOf(dbNumber);
                        instance.gotoResults(vi);
                    }
                });

                itemsLayout.addView(foodItem, lp);

            }
        }
    }

}
