package com.healthapp.healthapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.healthapp.healthapp.DatabaseAccess.Rating;
import com.healthapp.healthapp.DatabaseAccess.ViewItemRatings;

public class ReviewsActivity extends AppCompatActivity {

    private static Demo_Reviews instance = null;
    private static View vi;
    private static LinearLayout itemsLayout;
    Button createReviewButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reviews);

        // Initialize done button
        createReviewButton = (Button) findViewById(R.id.create_button);

        // Set Click Listener
        createReviewButton.setOnClickListener(createReviewListener);

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
            goToCreateReview(v);
        }
    };

    public void goToCreateReview(View v) {
        Intent intent = new Intent(this,CreateReview.class);
        this.startActivity(intent);
    }

    public static void populateList(Rating[] items) {
//        if(items[0].equals("false")){
//            // print error message
////            AlertDialog alertDialog = new AlertDialog.Builder(instance).create();
////            alertDialog.setTitle("Alert");
////            alertDialog.setMessage(items[0]);
////            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
////                    new DialogInterface.OnClickListener() {
////                        public void onClick(DialogInterface dialog, int which) {
////                            dialog.dismiss();
////                        }
////                    });
////            alertDialog.show();
//        }
//
//        else {
//
//            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
//                    RelativeLayout.LayoutParams.WRAP_CONTENT);
//
//            final String[] databaseKeys = new String[items.length];
//
//            for(int i=0; i < items.length; i++) {
//                Button foodItem = new Button(instance);
//                foodItem.setTransformationMethod(null);
//                foodItem.setText(items[i]);
//                databaseKeys[i] = items[i];
//                foodItem.setId(i);
//
//
//                itemsLayout.addView(foodItem, lp);
//
//            }
//        }
    }

}
