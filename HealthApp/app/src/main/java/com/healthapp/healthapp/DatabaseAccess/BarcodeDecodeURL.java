package com.healthapp.healthapp.DatabaseAccess;

import android.os.AsyncTask;

import com.healthapp.healthapp.BarcodeScan;

import org.w3c.dom.Document;

import java.net.URL;

/**
 * Authors: Clive Hoayun
 */
public class BarcodeDecodeURL extends AsyncTask<String,Void,String>
{
    protected String doInBackground(String... params)
    {
        String result;

        URL url;
        Document doc;


        try
        {
            url = new URL("http://api.upcdatabase.org/xml/" + User.getBarcode_api_key() + "/0" + params[0]);
            doc = new XMLParsing().parseXMLfromURL(url);

            if(doc.getElementsByTagName("valid").item(0).getTextContent().equals("true"))
                result = doc.getElementsByTagName("itemname").item(0).getTextContent();
            else
                result = doc.getElementsByTagName("reason").item(0).getTextContent();
        }
        catch (Exception e)
        {
            result = "ERROR In Parsing";
        }

        return result;
    }

    protected void onPostExecute(String result)
    {
        BarcodeScan.launchDialog(result);
    }
}