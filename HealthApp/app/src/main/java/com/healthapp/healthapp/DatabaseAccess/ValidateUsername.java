package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public abstract class ValidateUsername extends AsyncTask<User,Void,Integer>{

    //User attempts to create a new account
    //
    //INPUT: username User wants and the connection established to database
    //
    //OUTPUT: Counts the number of instances a username occurs in the database
    //		  If the count is 0, the username has not yet been taken
    //		  If the count is 1, the username has been taken
    public int doInBackground(User obj) throws SQLException {

        //Initialize variables
        Statement stmt = null;
        int count = 0;

        //Query
        String query = "SELECT COUNT(*) as A"
                + "FROM Users"
                + "WHERE username = " + obj.username;

        //Execute query
        try {
            stmt = obj.con.createStatement();

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

    protected int onPostExecute(int result) {
        return result;
    }
}
