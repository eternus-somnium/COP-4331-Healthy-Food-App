package com.healthapp.healthapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.healthapp.healthapp.DatabaseAccess.InsertReview;
import com.healthapp.healthapp.DatabaseAccess.Review;
import com.healthapp.healthapp.DatabaseAccess.UpdateReview;
import com.healthapp.healthapp.DatabaseAccess.User;

import java.sql.SQLException;

public class CreateReview extends VisiblePage {


    Review newReview = new Review();
    private RatingBar ratingBar;
    private EditText comment;
    Button submitButton;

    private static String product;
    private static String oldComment;
    private static Float oldRating;


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

        // getExtras
        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if(extras == null) {
                product = null;
            } else {
                product= extras.getString("Product");
                oldComment = extras.getString("Old Comment");
                oldRating = extras.getFloat("Old Review");
            }
        } else {
            product = (String) savedInstanceState.getSerializable("Product");
        }

        TextView productName = (TextView) findViewById(R.id.product_name);
        productName.setText(product);

        comment.setText(oldComment);

        ratingBar.setRating(oldRating);
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
            //gotoReview(v, product);
            validateReview();
        }
    };

    void validateReview()
    {
        newReview.setFoodID(User.getFoodID());
        newReview.setUserID(User.getUserID());
        newReview.setRating(ratingBar.getRating());
        newReview.setComment(comment.getText().toString());


        if(newReview.getRating() != 0)
            addReview();
        else

            resultMessageHandler(7);

    }

    void addReview()
    {
        try
        {
            if (User.getCon() == null || User.getCon().isClosed())
            {
                gotoLogin(vi);
            }
            else
            {
                if(oldRating == 0f)
                    new InsertReview().execute(instance, newReview);
                else
                    new UpdateReview().execute(instance, newReview);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
