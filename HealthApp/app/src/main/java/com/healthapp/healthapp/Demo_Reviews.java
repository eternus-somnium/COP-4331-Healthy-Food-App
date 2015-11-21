package com.healthapp.healthapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class Demo_Reviews extends AppCompatActivity {

    Button createReviewButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo__reviews);
        // Initialize done button
        createReviewButton = (Button) findViewById(R.id.create_button);

        // Set Click Listener
        createReviewButton.setOnClickListener(createReviewListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_demo__reviews, menu);
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
}
