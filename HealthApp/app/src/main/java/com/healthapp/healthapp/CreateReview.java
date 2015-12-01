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
import android.widget.EditText;
import android.widget.RatingBar;

import com.healthapp.healthapp.DatabaseAccess.InsertRating;
import com.healthapp.healthapp.DatabaseAccess.Rating;
import com.healthapp.healthapp.DatabaseAccess.User;

import java.sql.SQLException;

public class CreateReview extends AppCompatActivity {

    private View vi;
    private static CreateReview instance;
    Rating newRating = new Rating();
    private RatingBar ratingBar;
    private EditText comment;
    Button submitButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_review);
        this.instance = this;

        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        comment = (EditText) findViewById(R.id.editText);

        // Initialize submit button
        submitButton = (Button) findViewById(R.id.submit_button);
        submitButton.setOnClickListener(submitListener);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_review, menu);
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

    private View.OnClickListener submitListener = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            vi = v;
            validateReview();
        }
    };

    void validateReview()
    {
        newRating.setFoodID(User.getFoodID());
        newRating.setUserID(User.getUserID());
        newRating.setRating(ratingBar.getRating());
        newRating.setComment(comment.getText().toString());


        if(newRating.getRating() != 0)
            addReview();
        else
            resultMessageHandler(2);
    }

    void addReview()
    {
        try
        {
            if (User.getCon() == null || User.getCon().isClosed())
            {
                goToLogin(vi);
            }
            else
            {
                new InsertRating().execute(newRating);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void goToLogin(View v)
    {
        Intent intent = new Intent(this,Login.class);
        this.startActivity(intent);
    }

    public void showResultDialog(Integer i)
    {
        AlertDialog alertDialog = new AlertDialog.Builder(instance).create();

        alertDialog.setTitle("Alert");
        if(i == 1){
            alertDialog.setMessage("Review successfully added");}
        else if(i == 2){
            alertDialog.setMessage("Add a star rating to leave a review");}
        else if(i == -1){
            alertDialog.setMessage("An error occurred when contacting the database");}
        else{
            alertDialog.setMessage("An unknown error occurred");}
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();

    }


    public static void resultMessageHandler(Integer i)
    {
        instance.showResultDialog(i);
    }


}
