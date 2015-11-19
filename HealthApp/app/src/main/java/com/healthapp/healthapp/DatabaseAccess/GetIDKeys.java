package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public class GetIDKeys extends AsyncTask<User,Void,User>{

    //User is logging in, get the User's ID, food database key, and barcode key
    //
    //INPUT: User's username and password, and connection to database
    //
    //OUTPUT: this function outputs a ResultSet, which is the results to the query being executed
    //		  The ResultSet will consist of the following variables:
    //
    //		  userID (String), food database key (String), barcode database key (String)
    //
    //        These variables will now be available to be used in the parent function
    public User doInBackground(User... params)
    {
        //Instantiation
        Statement stmt = null;
        ResultSet rs = null;

        //Query
        String query = "SELECT idUsers, food_api_key, barcode_key "
                + "FROM Users "
                + "WHERE "
                + 		"username = " + params[0].username
                + 		"AND "
                + 		"password = " + params[0].password;

        //Execute Query
        try {
            stmt = params[0].con.createStatement();
            rs = stmt.executeQuery(query);

            params[0].userID = Integer.valueOf(rs.getString(1));
            params[0].food_api_key = rs.getString(2);
            params[0].barcode_api_key = rs.getString(3);
        }

        catch(SQLException e)
        {

        }

        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return params[0];
    }

    public User onPostExecution(User u){

        return u;
    }
}
