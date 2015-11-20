package com.healthapp.healthapp.DatabaseAccess;

import android.os.AsyncTask;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
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
            results[16] = doc.getElementsByTagName("measure").item(0).getAttributes().getNamedItem("label").getNodeValue();
            //String id = null;
            for(int i=0;i<entries.getLength();i++){
                Node n = entries.item(i);

                String id = n.getAttributes().getNamedItem("nutrient_id").getNodeValue();
                switch (id){
                    //energy
                    case "208":
                        results[1] = entries.item(i).getNodeValue();
                        break;
                    //protein
                    case "203":
                        results[2] = entries.item(i).getNodeValue();
                        break;
                    //total fat
                    case "204":
                        results[3] = entries.item(i).getNodeValue();
                        break;
                    //carbohydrate
                    case "205":
                        results[4] = entries.item(i).getNodeValue();
                        break;
                    //fiber
                    case "291":
                        results[5] = entries.item(i).getNodeValue();
                        break;
                    //sugar
                    case "269":
                        results[6] = entries.item(i).getNodeValue();
                        break;
                    //cholesterol
                    case "601":
                        results[7] = entries.item(i).getNodeValue();
                        break;
                    //calcium
                    case "301":
                        results[8] = entries.item(i).getNodeValue();
                        break;
                    //sodium
                    case "307":
                        results[9] = entries.item(i).getNodeValue();
                        break;
                    //iron
                    case "303":
                        results[10] = entries.item(i).getNodeValue();
                        break;
                    //saturated fat
                    case "606":
                        results[11] = entries.item(i).getNodeValue();
                        break;
                    //trans fat
                    case "605":
                        results[12] = entries.item(i).getNodeValue();
                        break;
                    //vitamin c
                    case "401":
                        results[13] = entries.item(i).getNodeValue();
                        break;
                    default: break;
                }
            }



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
