package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Chris on 11/17/2015.
 */
public abstract class SearchURL extends AsyncTask<String,Void,Void>{

    //Get InputStream of from URL search
    //
    //INPUT: search term input by User, API key that belongs to User
    //
    //OUTPUT: the InputStream of the URL (XML format)
    //        THIS IS FOR A SEARCH
    public InputStream doInBackground(String search_term, String api_key) throws MalformedURLException, IOException{

        InputStream input = new URL("http://api.nal.usda.gov/ndb/search/?format=xml&q="+search_term+"&food_api_key="+api_key).openStream();

        return input;

    }

    protected void onPostExecute(InputStream result){
        //AsyncTask cannot return an InputStream
        //XML Parsing goes here?

    }
}
