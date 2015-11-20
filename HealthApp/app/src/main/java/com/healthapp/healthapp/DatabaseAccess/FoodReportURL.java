package com.healthapp.healthapp.DatabaseAccess;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.net.URL;

/**
 * Created by Chris on 11/17/2015.
 */
public class FoodReportURL extends AsyncTask<String,Void,String[]>
{
    String results[] = null;

    URL url;
    Document doc;
    NodeList entries;

    //        THIS IS FOR A FOOD REPORT
    public String[] doInBackground(String... params)
    {
        try {
            url = new URL("http://api.nal.usda.gov/ndb/reports/?ndbno=" + params[0] + "&format=xml&api_key=" + User.getFood_api_key());
            doc = new XMLParsing().parseXMLfromURL(url);
            entries = doc.getElementsByTagName("nutrient");
            results = new String[15];

            //Parse the document
            results[0] = doc.getElementsByTagName("food").item(0).getNodeName();
            results[1] = entries.item(1).getNodeValue();



        }
        catch (Exception e) {
            e.printStackTrace();
        }


        return results;

    }

    public void onPostExectuion(String[] results)
    {
        //AsyncTask cannot return InputStream
        //XML parsing goes here?

    }
}
