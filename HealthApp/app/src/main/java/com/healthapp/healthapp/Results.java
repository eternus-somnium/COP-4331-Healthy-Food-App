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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.healthapp.healthapp.DatabaseAccess.Rating;


public class Results extends AppCompatActivity
{
    private static Results instance = null;
    private static View vi;
    private static LinearLayout reviewsLayout;

    Button showReviewButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.instance = this;
        reviewsLayout = (LinearLayout) findViewById(R.id.items_layout);
        setContentView(R.layout.activity_results);

        // Initialize done button
        showReviewButton = (Button) findViewById(R.id.review_button);

        // Set Click Listener
        showReviewButton.setOnClickListener(createReviewListener);

        String newString;
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("Food ID");
            }
        } else {
            newString= (String) savedInstanceState.getSerializable("Food ID");
        }
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

    private View.OnClickListener createReviewListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            vi = v;
            goToCreateReview(v);
        }
    };

    public void goToCreateReview(View v) {
        Intent intent = new Intent(this,Demo_Reviews.class);
        this.startActivity(intent);
    }

    public void fillFoodReport(){

//        TextView productName = (TextView) findViewById(R.id.textView2);
//        productName.setText();
//
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

    public static void populateList(Rating[] reviews)
    {
        if(reviews[0].getFoodID() == "false"){
            // print error message
            AlertDialog alertDialog = new AlertDialog.Builder(instance).create();
            alertDialog.setTitle("Alert");
            alertDialog.setMessage("There are currently no reviews for this object");
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

            for(int i=0; i < reviews.length; i++) {
                TextView review = new TextView(instance);
                review.setText(reviews[i].getRating());
                TextView user = new TextView(instance);
                user.setText(reviews[i].getRating());
                TextView comment = new TextView(instance);
                comment.setText(reviews[i].getRating());

                reviewsLayout.addView(review, lp);
                reviewsLayout.addView(user, lp);
                reviewsLayout.addView(comment, lp);

            }
        }
    }
}
