package com.healthapp.healthapp.DatabaseAccess;

import android.os.AsyncTask;

import com.healthapp.healthapp.Results;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.net.URL;

/**
 * Created by Chris on 11/17/2015.
 */
public class FoodReportURL extends AsyncTask<String,Void,String[][]>
{
    String results[][] = null;
    int count;
    URL url;
    Document doc;
    NodeList entries;
    NodeList measurements;

    //        THIS IS FOR A FOOD REPORT
    public String[][] doInBackground(String... params)
    {
        try {
            url = new URL("http://api.nal.usda.gov/ndb/reports/?ndbno=" + params[0] + "&format=xml&api_key=" + User.getFood_api_key());
            doc = new XMLParsing().parseXMLfromURL(url);
            entries = doc.getElementsByTagName("nutrient");
            count = entries.item(0).getChildNodes().getLength();
            results = new String[16][count];

            //Parse the document
            results[0][0] = doc.getElementsByTagName("food").item(0).getNodeName();
        //    results[16] = doc.getElementsByTagName("measure").item(0).getAttributes().getNamedItem("label").getNodeValue();

            //get labels for measurements
            measurements = entries.item(0).getChildNodes();
            for(int i=0;i<count;i++){
                results[15][i] = measurements.item(i).getNodeName();
            }


            for(int i=0;i<entries.getLength();i++){
                Node n = entries.item(i);
                NodeList children = null;
                String id = n.getAttributes().getNamedItem("nutrient_id").getNodeValue();
                switch (id){
                    //energy
                    case "208":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[1][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //protein
                    case "203":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[2][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //total fat
                    case "204":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[3][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //carbohydrate
                    case "205":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[4][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //fiber
                    case "291":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[5][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //sugar
                    case "269":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[6][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //cholesterol
                    case "601":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[7][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //calcium
                    case "301":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[8][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //sodium
                    case "307":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[9][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //iron
                    case "303":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[10][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //saturated fat
                    case "606":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[11][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //trans fat
                    case "605":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[12][j] = children.item(j).getNodeValue();
                        }
                        break;
                    //vitamin c
                    case "401":
                        children = entries.item(i).getChildNodes();
                        for(int j=0;j<count;j++){
                            results[13][j] = children.item(j).getNodeValue();
                        }
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
        //Results.fillFoodReport(results);

    }
}
