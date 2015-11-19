package com.healthapp.healthapp.DatabaseAccess;

import android.os.AsyncTask;

import com.healthapp.healthapp.BarcodeScan;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Clive on 11/18/2015.
 */
public class BarcodeDecodeURL extends AsyncTask<String,Void,String>
{
    //Get InputStream of from URL search
    //
    //INPUT: search term input by User, API key that belongs to User
    //
    //OUTPUT: the InputStream of the URL (XML format)
    //        THIS IS FOR A SEARCH

    protected String doInBackground(String... params)
    {
        InputStream input = null;
        try
        {
            input = new URL("http://api.upcdatabase.org/xml/" + User.barcode_api_key  + "/" + params[0]).openStream();
        }
        catch (MalformedURLException e)
        {

        }
        catch (IOException f) {

        }

        return parseXML(input);
    }

    protected void onPostExecute(String result)
    {
        BarcodeScan.launchDialog(result);

        //AsyncTask cannot return an InputStream
        //XML Parsing goes here?

    }

    String parseXML(InputStream in)
    {
        String itemName = null;
        String valid = null;




        if(valid.equals("true"))
            return itemName;
        else
            return "Error";
    }
}