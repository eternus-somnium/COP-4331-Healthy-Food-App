package com.healthapp.healthapp;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.*;

/**
 * Created by Clive on 11/17/2015.
 */
public class DatabaseAccess
{
    //Connects to database, returns connection
    public Connection Connect(){

        //Initialize connection
        Connection conn = null;

        //Connect to database
        try
        {
            String url = "jdbc:mysql://db4free.net:3307/cop4331_33";
            Class.forName ("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection (url,"chrisguido4","password12345");
            System.out.println ("Database connection established");
        }

        //Exception
        catch (Exception e)
        {
            e.printStackTrace();

        }

        //Return connection
        return conn;
    }

    //User attempts to create a new account
    //
    //INPUT: username user wants and the connection established to database
    //
    //OUTPUT: Counts the number of instances a username occurs in the database
    //		  If the count is 0, the username has not yet been taken
    //		  If the count is 1, the username has been taken
    public int ValidateUsername(String Username, Connection con) throws SQLException {

        //Initialize variables
        Statement stmt = null;
        int count = 0;

        //Query
        String query = "SELECT COUNT(*) as A"
                + "FROM Users"
                + "WHERE username = " + Username;

        //Execute query
        try {
            stmt = con.createStatement();

            //Get result
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("A");

            }
        }

        //Exception
        catch (SQLException e ) {
        }

        //Close statement
        finally {
            if (stmt != null) { stmt.close(); }
        }

        //Return count
        return count;
    }

    //User creates new account, inserting username and password
    //
    //INPUT: username and password that the user wants, as well as the connection
    public void InsertUsername(String username, String password, Connection conn) throws SQLException{

        Statement stmt = conn.createStatement();

        //Query
        String update = "INSERT INTO Users (username,password)"
                +"VALUES (" + username + ", " + password + ")";

        //Execute query
        try {
            stmt.execute(update);
        }

        //Exception
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        //Close statement
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    //Inserts the Food Database key for users
    //
    //INPUT: user's username and password, as well as the API key the user obtains, and the connection to the database
    public void InsertFoodKey(String username, String password, String key, Connection conn) throws SQLException{
        Statement stmt = conn.createStatement();

        //Query
        String update = "INSERT INTO Users (api_key) "
                + "VALUES (" + key + ") "
                + "WHERE "
                + 	"username = " + username
                + 	"AND "
                + 	"password = " + password;

        //Execute
        try {
            stmt.execute(update);
        }

        //Exception
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        //Close connection
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    //Insert barcode database key to user
    //
    //INPUT: user's username and password, the barcode scanner key they obtained, and the connection to the database
    public void InsertBarcodeKey(String username, String password, String key, Connection conn) throws SQLException{

        Statement stmt = conn.createStatement();

        //Query
        String update = "INSERT INTO Users (barcode_key) "
                + "VALUES (" + key + ") "
                + "WHERE "
                + 	"username = " + username
                + 	"AND "
                + 	"password = " + password;

        //Execute
        try {
            stmt.execute(update);
        }

        //Exception
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        //Close connection
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    //User logging in, check to see if username and password are okay
    //
    //INPUT: user's username, password, and the connection to the database
    //
    //OUTPUT: count of how many instances that username and password occur
    //		  If the count is 0, the username and password are incorrect and/or don't exist in database
    //		  If the count is 1, the username/password pair exists in the database
    public int CheckUsernamePassword(String username, String password, Connection con) throws SQLException {

        //Variables
        Statement stmt = null;
        int count = 0;

        //Query
        String query = "SELECT COUNT(*) as A"
                + "FROM Users"
                + "WHERE "
                + 		"username = " + username
                + 		"AND "
                + 		"password = " + password;

        //Execute
        try {
            stmt = con.createStatement();

            //Results
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("A");
            }
        }

        //Exception
        catch (SQLException e ) {
        }

        //Close connection
        finally {
            if (stmt != null) { stmt.close(); }
        }

        //Return count
        return count;
    }

    //User is logging in, get the user's ID, food database key, and barcode key
    //
    //INPUT: user's username and password, and connection to database
    //
    //OUTPUT: this function outputs a ResultSet, which is the results to the query being executed
    //		  The ResultSet will consist of the following variables:
    //
    //		  userID (String), food database key (String), barcode database key (String)
    //
    //        These variables will now be available to be used in the parent function
    public ResultSet GetIDKeys(String username, String password, Connection con) throws SQLException {

        //Instantiation
        Statement stmt = null;
        ResultSet rs = null;

        //Query
        String query = "SELECT idUsers, api_key, barcode_key "
                + "FROM Users "
                + "WHERE "
                + 		"username = " + username
                + 		"AND "
                + 		"password = " + password;

        //Execute Query
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        }

        catch (SQLException e ) {
        }

        finally {
            if (stmt != null) { stmt.close(); }
        }

        return rs;
    }

    //User has selected a specific item, collect all reviews on the item
    //
    //INPUT: the ID of the food item and the connection to the database
    //
    //OUTPUT: this function outputs a ResultSet, which is the results to the query being executed
    //		  The ResultSet will consist of the following variables:
    //
    //		  the Rating (int), the Comment (String) associated with the rating, and the Username (String) of the user who left the rating
    //
    //        These variables will now be available to be used in the parent function
    public ResultSet Ratings(int foodID, Connection con) throws SQLException {

        //Variables
        Statement stmt = null;
        ResultSet rs = null;

        //Query
        String query = "SELECT U.username, R.rating, R.comment "
                + "FROM "
                + 		"Rating as R "
                + 		"inner join "
                + 		"Users as U "
                + 		"on "
                + 		"R.Users_idUsers = U.idUsers "
                + "WHERE R.fodID = " + foodID;

        //Execute
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(query);
        }

        //Exception
        catch (SQLException e ) {
        }

        //Close
        finally {
            if (stmt != null) { stmt.close(); }
        }

        //Return ResultSet
        return rs;
    }

    //Checks if user is able to leave a comment on a particular item
    //
    //INPUT: food ID, user ID, connection to database
    //
    //OUTPUT: the number of times the user with that user ID has left a comment on the food with the food ID
    //		  (the number of times someone has commented on a specific food item)
    //
    //		  if the count is 0, the user can comment on the item
    //		  if the count is not 0, the user can not comment on the item
    public int CheckCommentPrivilege(int foodID, int userID, Connection con) throws SQLException {

        //Variables
        Statement stmt = null;
        int count = 0;

        //Query
        String query = "SELECT COUNT(*) as flag"
                + "FROM Rating"
                + "WHERE "
                + 		"foodID = " + foodID
                + 		"AND "
                + 		"Users_idUsers = " + userID;

        //Execute
        try {
            stmt = con.createStatement();

            //Answer
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("A");
            }
        }

        //Exception
        catch (SQLException e ) {
        }

        //Close
        finally {
            if (stmt != null) { stmt.close(); }
        }

        //Return count
        return count;
    }

    //User has rated a food item, insert comment into database
    //
    //INPUT: ID of food, ID of user, the user's rating, the comment the user may have written, and the connection to the database
    public void InsertRating(int foodID, int userID, int rating, String comment, Connection conn) throws SQLException{

        Statement stmt = conn.createStatement();

        //Query
        String update = "INSERT INTO Rating (foodID, Users_idUsers, rating, comment) "
                + "VALUES (" + foodID
                + "," + userID
                + "," + rating
                + "," + comment
                + ")";

        //Execute
        try {
            stmt.execute(update);
        }

        //Exception
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
        }

        //Close
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    //Close connection to database
    //
    //INPUT: connection to database
    public void CloseConn(Connection conn){

        try
        {
            conn.close ();
        }
        catch (Exception e) { /* ignore close errors */ }
    }

    //Get InputStream of from URL search
    //
    //INPUT: search term input by user, API key that belongs to user
    //
    //OUTPUT: the InputStream of the URL (XML format)
    //        THIS IS FOR A SEARCH
    public InputStream SearchURL(String search_term, String api_key) throws MalformedURLException, IOException{

        InputStream input = new URL("http://api.nal.usda.gov/ndb/search/?format=xml&q="+search_term+"&api_key="+api_key).openStream();

        return input;

    }

    //Get InputStream of from URL Food Report
    //
    //INPUT: Food ID of the user-selected food, API key that belongs to user
    //
    //OUTPUT: the InputStream of the URL (XML format)
    //        THIS IS FOR A FOOD REPORT
    public InputStream FoodReportURL(int FoodID, String api_key) throws MalformedURLException, IOException {

        InputStream input = new URL("http://api.nal.usda.gov/ndb/reports/?ndbno=" + FoodID + "&format=xml&api_key=" + api_key).openStream();

        return input;

    }
}
