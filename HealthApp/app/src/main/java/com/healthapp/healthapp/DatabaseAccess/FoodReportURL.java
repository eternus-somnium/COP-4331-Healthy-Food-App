package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Chris on 11/17/2015.
 */
public abstract class FoodReportURL extends AsyncTask<User,Void,Void>{

    //Get InputStream of from URL Food Report
    //
    //INPUT: Food ID of the User-selected food, API key that belongs to User
    //
    //OUTPUT: the InputStream of the URL (XML format)
    //        THIS IS FOR A FOOD REPORT
    public InputStream doInBackground(User obj) throws MalformedURLException, IOException{

        InputStream input = new URL("http://api.nal.usda.gov/ndb/reports/?ndbno="+obj.foodID+"&format=xml&food_api_key="+obj.food_api_key).openStream();

        return input;

    }

    public void onPostExectuion(InputStream result){
        //AsyncTask cannot return InputStream
        //XML parsing goes here?

    }
}
