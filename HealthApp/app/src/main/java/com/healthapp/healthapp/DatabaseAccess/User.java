package com.healthapp.healthapp.DatabaseAccess;
import java.sql.Connection;

/**
 * Created by Chris on 11/18/2015.
 */

//Because of the use of a single parameter in the AsyncTask types for doInBackground
//I created a class that can be passed to every function
//To use it, just instantiate the class, fill in the variables necessary, and pass the object to the function
public class User
{
    static int userID;
    static String username;
    String password;

    static Connection con;
    static String food_api_key;
    static String barcode_api_key;
    static int foodID;
}
