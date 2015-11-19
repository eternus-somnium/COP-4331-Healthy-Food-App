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
    private static boolean validUser = false;
    private static int userID;
    private static String username;
    private static String password;

    private static Connection con;
    private static String food_api_key;
    private static String barcode_api_key;

    private static String foodID;

    public static boolean isValidUser() {
        return validUser;
    }

    public static void setValidUser(boolean validUser) {
        User.validUser = validUser;
    }

    public static int getUserID() {
        return userID;
    }

    public static void setUserID(int userID) {
        User.userID = userID;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        User.username = username;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        User.con = con;
    }

    public static String getFood_api_key() {
        return food_api_key;
    }

    public static void setFood_api_key(String food_api_key) {
        User.food_api_key = food_api_key;
    }

    public static String getBarcode_api_key() {
        return barcode_api_key;
    }

    public static void setBarcode_api_key(String barcode_api_key) {
        User.barcode_api_key = barcode_api_key;
    }

    public static String getFoodID() {
        return foodID;
    }

    public static void setFoodID(String foodID) {
        User.foodID = foodID;
    }
}
