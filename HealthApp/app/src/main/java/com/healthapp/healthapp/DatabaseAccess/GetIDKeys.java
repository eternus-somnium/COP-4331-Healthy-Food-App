package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public abstract class GetIDKeys extends AsyncTask<User,Void,ResultSet>{

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
    public ResultSet doInBackground(User obj) throws SQLException {

        //Instantiation
        Statement stmt = null;
        ResultSet rs = null;

        //Query
        String query = "SELECT idUsers, food_api_key, barcode_key "
                + "FROM Users "
                + "WHERE "
                + 		"username = " + obj.username
                + 		"AND "
                + 		"password = " + obj.password;

        //Execute Query
        try {
            stmt = obj.con.createStatement();
            rs = stmt.executeQuery(query);
        }

        catch (SQLException e ) {
        }

        finally {
            if (stmt != null) { stmt.close(); }
        }

        return rs;
    }

    public ResultSet onPostExecution(ResultSet result){

        return result;
    }
}
