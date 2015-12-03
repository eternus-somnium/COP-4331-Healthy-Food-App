package com.healthapp.healthapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.healthapp.healthapp.DatabaseAccess.FoodReportURL;
import com.healthapp.healthapp.DatabaseAccess.Rating;
import com.healthapp.healthapp.DatabaseAccess.User;
import com.healthapp.healthapp.DatabaseAccess.ViewItemRatings;

public class ReviewsActivity extends VisiblePage {

    private static LinearLayout reviewsLayout;
    static Button createReviewButton;
    private static String product;
    private static String oldComment;
    private static Float oldRating;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        instance = this;

        // Initialize done button
        createReviewButton = (Button) findViewById(R.id.create_button);

        // Set Click Listener
        createReviewButton.setOnClickListener(createReviewListener);

        reviewsLayout = (LinearLayout) findViewById(R.id.reviewsLayout);

        // getExtras
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                product= null;
            } else {
                product= extras.getString("Product");
            }
        } else {
            product= (String) savedInstanceState.getSerializable("Product");
        }
        //////

        new ViewItemRatings().execute();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reviews, menu);
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
            //vi = v;
            gotoCreateReview(v, product);
        }
    };

//    private View.OnClickListener editReviewListener = new View.OnClickListener()
//    {
//        @Override
//        public void onClick(View v)
//        {
//            Intent intent = new Intent(instance,CreateReview.class);
//            intent.putExtra("Product", product);
//            intent.putExtra("Old Review", oldComment);
//            instance.startActivity(intent);
//            //vi = v;
//           // gotoEditReview(v, product, oldComment);
//        }
//    };

//    public void gotoEditReview(View v, String product, String oldComment) {
//        Intent intent = new Intent(this,CreateReview.class);
//        intent.putExtra("Product", product);
//        intent.putExtra("Old Review", oldComment);
//        this.startActivity(intent);
//    }

    public static void populateList(Rating[] reviews) {
        if(reviews.equals(null)){
            // print error message
//            AlertDialog alertDialog = new AlertDialog.Builder(instance).create();
//            alertDialog.setTitle("Alert");
//            alertDialog.setMessage(items[0]);
//            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
//                    new DialogInterface.OnClickListener() {
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//            alertDialog.show();
        }

        else {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            for(int i=0; i < reviews.length; i++) {
                // Check if active user has left a review already
                if(User.getUsername().equals(reviews[i].getUsername()))
                {
                    oldComment = reviews[i].getComment();
                    oldRating = reviews[i].getRating();

                    createReviewButton.setText("Update Review");
                    createReviewButton.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View v)
                        {
                            Intent intent = new Intent(instance,CreateReview.class);
                            intent.putExtra("Product", product);
                            intent.putExtra("Old Comment", oldComment);
                            intent.putExtra("Old Rating", oldRating);
                            instance.startActivity(intent);
                            //vi = v;
                            // gotoEditReview(v, product, oldComment);
                        }

                        //flag = true;
                    });
                }
                // Initialize review data variables
                TextView username = new TextView(instance);
                TextView rating = new TextView(instance);
                TextView comment = new TextView(instance);

                // Get values for review data
                username.setText(reviews[i].getUsername());
                rating.setText(String.valueOf(reviews[i].getRating()));
                comment.setText(reviews[i].getComment());

                // Add review data to layout
                reviewsLayout.addView(username, params);
                reviewsLayout.addView(rating, params);
                reviewsLayout.addView(comment, params);

                // Add a line
                View v = new View(instance);
                v.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        5
                ));
                v.setBackgroundColor(Color.parseColor("#B3B3B3"));
                reviewsLayout.addView(v);
            }
        }
    }


}
