package com.healthapp.healthapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


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
//        int vitADV = Integer.parseInt(vitA.toString());
//        vitADV = (/*result*/ / 5000) * 100;
//        vitA.setText();
//
//        TextView vitC = (TextView) findViewById(R.id.vitC_percent);
//        int vitCDV = Integer.parseInt(vitC.toString());
//        vitCDV = (/*result*/ / 60) * 100;
//        vitC.setText();
//
//        TextView calcium = (TextView) findViewById(R.id.calcium_percent);
//        int calDV = Integer.parseInt(calcium.toString());
//        calDV = (/*result*/ / 1100) * 100;
//        calcium.setText();
//
//        TextView iron = (TextView) findViewById(R.id.iron_percent);
//        int ironDV = Integer.parseInt(iron.toString());
//        ironDV = (/*result*/ / 14) * 100;
//        iron.setText();
//


        // Calculating Percentages
//        TextView fatPercent = (TextView) findViewById(R.id.fat_percent);
//        int fatDV = Integer.parseInt(fatPercent.toString());
//        fatDV = (/*result*/ / 65) * 100;
//        fatPercent.setText(fatDV);
//
//        TextView satPercent = (TextView) findViewById(R.id.sat_percent);
//        int satDV = Integer.parseInt(fatPercent.toString());
//        satDV = (/*result*/ / 20) * 100;
//        satPercent.setText(satDV);
//
//        TextView transPercent = (TextView) findViewById(R.id.trans_percent);
//        int transDV = Integer.parseInt(fatPercent.toString());
//        transDV = (/*result*/ / 20) * 100;
//        transPercent.setText(transDV);
//
//        TextView cholPercent = (TextView) findViewById(R.id.chol_percent);
//        int cholDV = Integer.parseInt(fatPercent.toString());
//        cholDV = (/*result*/ / 300) * 100;
//        cholPercent.setText(cholDV);
//
//        TextView sodiumPercent = (TextView) findViewById(R.id.sodium_percent);
//        int sodDV = Integer.parseInt(fatPercent.toString());
//        sodDV = (/*result*/ / 2400) * 100;
//        sodiumPercent.setText(sodDV);
//
//        TextView carbPercent = (TextView) findViewById(R.id.carb_percent);
//        int carbDV = Integer.parseInt(fatPercent.toString());
//        carbDV = (/*result*/ / 300) * 100;
//        carbPercent.setText(carbDV);
//
//        TextView fiberPercent = (TextView) findViewById(R.id.fiber_percent);
//        int fibDV = Integer.parseInt(fatPercent.toString());
//        fatDV = (/*result*/ / 25) * 100;
//        fiberPercent.setText();

    }
}
