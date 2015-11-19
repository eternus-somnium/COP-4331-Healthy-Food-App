package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.Search;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import java.net.URL;

/**
 * Created by Chris on 11/17/2015.
 */
public class SearchFoodURL extends AsyncTask<String,Void,String[][]>
{
    protected String[][] doInBackground(String... params)
    {
        String results[][] = null;

        URL url;
        Document doc;
        NodeList entries;

        //HERE FOR TESTING
        User.setFood_api_key("H2iC01vvlHsRKfiHSkZD49jCfs5jRILlTn13A5TC");


        try
        {
            url = new URL("http://api.nal.usda.gov/ndb/search/?format=xml&q="+params[0]+"&api_key="+ User.getFood_api_key());
            doc = new XMLParsing().parseXMLfromURL(url);
            entries = doc.getElementsByTagName("item");
            results = new String[entries.getLength()][2];


            //Parse the document
            for(int i=0; i< entries.getLength();i++)
            {
                results[i][0] = entries.item(i).getChildNodes().item(3).getTextContent();
                results[i][1] = entries.item(i).getChildNodes().item(5).getTextContent();
            }
        }
        catch (Exception e)
        {
            results = new String[1][1];
            results[0][0] = "An error occurred while performing the search";
            results[0][1] = "false";
        }

        return results;
    }

    protected void onPostExecute(String[][] results)
    {
        Search.populateList(results); //Do something
    }
}
