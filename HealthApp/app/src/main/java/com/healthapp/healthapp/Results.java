package com.healthapp.healthapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.healthapp.healthapp.DatabaseAccess.FoodReportURL;
import com.healthapp.healthapp.DatabaseAccess.Review;


public class Results extends VisiblePage
{
    private static Results instance = null;
    private static View vi;
    private static LinearLayout reviewsLayout;
    private static String[] items;
    Button showReviewButton;
    private static int x =0;
    private ProgressBar bar;
    private RelativeLayout rl;
    public static String[][] results1;
    public static String product;
    private static String newString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.instance = this;
        reviewsLayout = (LinearLayout) findViewById(R.id.items_layout);
        setContentView(R.layout.activity_results);

        // Initialize "Show Reviews" button
        showReviewButton = (Button) findViewById(R.id.review_button);

        // Set Click Listener
        showReviewButton.setOnClickListener(createReviewListener);

        rl = (RelativeLayout) findViewById(R.id.loadingPanel);
        rl.setVisibility(View.VISIBLE);
        bar = (ProgressBar) this.findViewById(R.id.progressBar);
        bar.setVisibility(View.VISIBLE);

        // getExtras from Search
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                newString= null;
            } else {
                newString= extras.getString("Food ID");
                new FoodReportURL().execute(newString);
                rl = (RelativeLayout) findViewById(R.id.loadingPanel);
                rl.setVisibility(View.GONE);
                bar = (ProgressBar) this.findViewById(R.id.progressBar);
                bar.setVisibility(View.GONE);
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
            gotoReview(vi, product);
        }
    };



    private static AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        public void onItemSelected(AdapterView<?> arg0, View v, int position, long id) {
            populateFoodReport(position);
        }

        public void onNothingSelected(AdapterView<?> arg0) {


        }
    };

    public static void populateFoodReport(int measurement)
    {
        items = results1[15];

        // Spinner
        final Spinner dropdown = (Spinner)instance.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter(instance, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
        dropdown.setSelection(measurement);
        dropdown.setOnItemSelectedListener(spinnerListener);




        TextView productName = (TextView) instance.findViewById(R.id.textView2);
        product = results1[0][0];
        productName.setText(results1[0][0]);

        TextView calories = (TextView) instance.findViewById(R.id.cal_amt);
        calories.setText(String.valueOf(Math.round(Float.parseFloat(results1[1][measurement]))));

        TextView protein = (TextView) instance.findViewById(R.id.protein_amt);
        protein.setText(String.valueOf(Math.round(Float.parseFloat(results1[2][measurement]))));

        TextView totalFat = (TextView) instance.findViewById(R.id.fat_amt);
        totalFat.setText(String.valueOf(Math.round(Float.parseFloat(results1[3][measurement]))));

        TextView carbs = (TextView) instance.findViewById(R.id.carb_amt);
        carbs.setText(String.valueOf(Math.round(Float.parseFloat(results1[4][measurement]))));

        TextView fiber = (TextView) instance.findViewById(R.id.fiber_amt);
        fiber.setText(String.valueOf(Math.round(Float.parseFloat(results1[5][measurement]))));

        TextView sugar = (TextView) instance.findViewById(R.id.sugar_amt);
        sugar.setText(String.valueOf(Math.round(Float.parseFloat(results1[6][measurement]))));

        TextView cholesterol = (TextView) instance.findViewById(R.id.chol_amt);
        cholesterol.setText(String.valueOf(Math.round(Float.parseFloat(results1[7][measurement]))));

        TextView calcium = (TextView) instance.findViewById(R.id.calcium_percent);
        float calDV = Float.parseFloat(results1[8][measurement]);
        calDV = (calDV / 1100) * 100;
        calcium.setText(String.valueOf(Math.round(calDV)));

        TextView sodium = (TextView) instance.findViewById(R.id.sodium_amt);
        sodium.setText(String.valueOf(Math.round(Float.parseFloat(results1[9][measurement]))));

        TextView iron = (TextView) instance.findViewById(R.id.iron_percent);
        float ironDV = Float.parseFloat(results1[10][measurement]);
        ironDV = (ironDV / 14) * 100;
        iron.setText(String.valueOf(Math.round(ironDV)));

        TextView satFat = (TextView) instance.findViewById(R.id.sat_amt);
        satFat.setText(String.valueOf(Math.round(Float.parseFloat(results1[11][measurement]))));

        TextView transFat = (TextView) instance.findViewById(R.id.trans_amt);
        transFat.setText(String.valueOf(Math.round(Float.parseFloat(results1[12][measurement]))));

        TextView vitC = (TextView) instance.findViewById(R.id.vitC_percent);
        float vitCDV = Float.parseFloat(results1[13][measurement]);
        vitCDV = (vitCDV / 60) * 100;
        vitC.setText(String.valueOf(Math.round(vitCDV)));

        TextView vitA = (TextView) instance.findViewById(R.id.vitA_percent);
        float vitADV = Float.parseFloat(results1[14][measurement]);
        vitADV = (vitADV / 5000) * 100;
        vitA.setText(String.valueOf(Math.round(vitADV)));


        // Calculating Percentages
        TextView fatPercent = (TextView) instance.findViewById(R.id.fat_percent);
        float fatDV = Float.parseFloat(results1[3][measurement]);
        fatDV = (fatDV / 65) * 100;
        fatPercent.setText(String.valueOf(Math.round(fatDV)));//String.format("%.2f", fatDV));

        TextView carbPercent = (TextView) instance.findViewById(R.id.carb_percent);
        float carbDV = Float.parseFloat(results1[4][measurement]);
        carbDV = (carbDV / 300) * 100;
        carbPercent.setText(String.valueOf(Math.round(carbDV)));

        TextView fiberPercent = (TextView) instance.findViewById(R.id.fiber_percent);
        float fibDV = Float.parseFloat(results1[5][measurement]);
        fibDV = (fibDV / 25) * 100;
        fiberPercent.setText(String.valueOf(Math.round(fibDV)));

        TextView cholPercent = (TextView) instance.findViewById(R.id.chol_percent);
        float cholDV = Float.parseFloat(results1[7][measurement]);
        cholDV = (cholDV / 300) * 100;
        cholPercent.setText(String.valueOf(Math.round(cholDV)));

        TextView sodiumPercent = (TextView) instance.findViewById(R.id.sodium_percent);
        float sodDV = Float.parseFloat(results1[9][measurement]);
        sodDV = (sodDV / 2400) * 100;
        sodiumPercent.setText(String.valueOf(Math.round(sodDV)));

        TextView satPercent = (TextView) instance.findViewById(R.id.sat_percent);
        float satDV = Float.parseFloat(results1[11][measurement]);
        satDV = (satDV / 20) * 100;
        satPercent.setText(String.valueOf(Math.round(satDV)));

        TextView transPercent = (TextView) instance.findViewById(R.id.trans_percent);
        float transDV = Float.parseFloat(results1[12][measurement]);
        transDV = (transDV / 20) * 100;
        transPercent.setText(String.valueOf(Math.round(transDV)));

    }

    public static void fillFoodReport(String [][] results){

        results1 = results;
        populateFoodReport(0);
    }

    public static void populateList(Review[] reviews)
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
                review.setText(String.valueOf(reviews[i].getRating()));
                TextView user = new TextView(instance);
                user.setText(String.valueOf(reviews[i].getUserID()));
                TextView comment = new TextView(instance);
                comment.setText(String.valueOf(reviews[i].getComment()));

                reviewsLayout.addView(review, lp);
                reviewsLayout.addView(user, lp);
                reviewsLayout.addView(comment, lp);

            }
        }
    }
}
