package com.healthapp.healthapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;


public class Results extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_results, menu);

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

    public void fillFoodReport(){

        //TextView productName = (TextView) findViewById(R.id.textView2);
//        productName.setText();

//        TextView calories = (TextView) findViewById(R.id.cal_amt);
//        calories.setText();
//
//        TextView totalFat = (TextView) findViewById(R.id.fat_amt);
//        totalFat.setText();
//
//        TextView satFat = (TextView) findViewById(R.id.sat_amt);
//        satFat.setText();
//
//        TextView transFat = (TextView) findViewById(R.id.trans_amt);
//        transFat.setText();
//
//        TextView cholesterol = (TextView) findViewById(R.id.chol_amt);
//        cholesterol.setText();
//
//        TextView sodium = (TextView) findViewById(R.id.sodium_amt);
//        sodium.setText();
//
//        TextView carbs = (TextView) findViewById(R.id.carb_amt);
//        carbs.setText();
//
//        TextView fiber = (TextView) findViewById(R.id.fiber_amt);
//        fiber.setText();
//
//        TextView sugar = (TextView) findViewById(R.id.sugar_amt);
//        sugar.setText();
//
//        TextView protein = (TextView) findViewById(R.id.protein_amt);
//        protein.setText();
//
//        TextView vitA = (TextView) findViewById(R.id.vitA_percent);
//        vitA.setText();
//
//        TextView vitC = (TextView) findViewById(R.id.vitC_percent);
//        vitC.setText();
//
//        TextView calcium = (TextView) findViewById(R.id.calcium_percent);
//        calcium.setText();
//
//        TextView iron = (TextView) findViewById(R.id.iron_percent);
//        iron.setText();
//
//        TextView calories = (TextView) findViewById(R.id.cal_amt);
//        calories.setText();
    }
}
