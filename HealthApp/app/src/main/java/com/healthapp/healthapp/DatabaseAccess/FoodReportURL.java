package com.healthapp.healthapp.DatabaseAccess;

import android.os.AsyncTask;

import com.healthapp.healthapp.Results;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;

/**
 * Authors: Clive Hoayun, Chris Guido
 */
public class FoodReportURL extends AsyncTask<String,Void,String[][]>
{
    String results[][] = null;
    int count;
    int size;
    int row;
    URL url;
    Document doc;
    NodeList nutrients;
    NodeList measurements;

    //        THIS IS FOR A FOOD REPORT
    public String[][] doInBackground(String... params)
    {
        try {
            //Sends a request to the food database and gets back an xml formatted reply
            url = new URL("http://api.nal.usda.gov/ndb/reports/?ndbno=" + params[0] + "&type=f&format=xml&api_key=" + User.getFood_api_key());

            //Creates a two dimensional array to hold the food's nutrition data
            doc = new XMLParsing().parseXMLfromURL(url);
            nutrients = doc.getElementsByTagName("nutrient");
            count = nutrients.item(0).getChildNodes().item(1).getChildNodes().getLength();
            size = count/2;
            results = new String[16][size];

            //Parse the document
            results[0][0] = doc.getElementsByTagName("food").item(0).getAttributes().getNamedItem("name").getNodeValue();

            //get labels for measurements
            measurements = nutrients.item(0).getChildNodes();
            for(int i=0;i<count;i++){
                int place = i/2;
                if(i%2 != 0) {
                    results[15][place] = measurements.item(1).getChildNodes().item(i).getAttributes().item(2).getNodeValue() + " " + measurements.item(1).getChildNodes().item(i).getAttributes().item(0).getNodeValue();
                }
            }

            //Fill in Results Array
            for(int i=0;i< nutrients.getLength();i++)
            {
                Node n = nutrients.item(i);
                String id = n.getAttributes().getNamedItem("nutrient_id").getNodeValue();

                //Get nutrients we want
                switch (id){
                    //energy
                    case "208": row = 1; break;
                    //protein
                    case "203": row = 2; break;
                    //total fat
                    case "204": row = 3; break;
                    //carbohydrate
                    case "205": row = 4; break;
                    //fiber
                    case "291": row = 5; break;
                    //sugar
                    case "269": row = 6; break;
                    //cholesterol
                    case "601": row = 7; break;
                    //calcium
                    case "301": row = 8; break;
                    //sodium
                    case "307": row = 9; break;
                    //iron
                    case "303": row = 10; break;
                    //saturated fat
                    case "606": row = 11; break;
                    //trans fat
                    case "605": row = 12; break;
                    //vitamin c
                    case "401": row = 13; break;
                    //vitamin a
                    case "318": row = 14; break;
                    //other
                    default: row = -1; break;
                }

                //Fill in data
                if(row != -1)
                {
                    NodeList children = n.getChildNodes().item(1).getChildNodes();
                    for (int j = 0; j < count; j++)
                        if (j % 2 != 0)
                            results[row][j / 2] = children.item(j).getAttributes().item(3).getNodeValue();
                }
            }
        }
        catch (Exception e)
        {
        }

        //Get rid of null values
        for(int i=0;i<16;i++){
            for(int j=0;j<size;j++){
                if(results[i][j] == null){
                    results[i][j] = "0";
                }
            }
        }

        return results;
    }

    public void onPostExecute(String[][] results)
    {
        Results.fillFoodReport(results);
    }
}
