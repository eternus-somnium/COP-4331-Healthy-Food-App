package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public abstract class CheckUsernamePassword extends AsyncTask<User,Void,Integer>{

    //User logging in, check to see if username and password are okay
    //
    //INPUT: User's username, password, and the connection to the database
    //
    //OUTPUT: count of how many instances that username and password occur
    //		  If the count is 0, the username and password are incorrect and/or don't exist in database
    //		  If the count is 1, the username/password pair exists in the database
    public int doInBackground(User obj) throws SQLException {

        //Variables
        Statement stmt = null;
        int count = 0;

        //Query
        String query = "SELECT COUNT(*) as A"
                + "FROM Users"
                + "WHERE "
                + 		"username = " + obj.username
                + 		"AND "
                + 		"password = " + obj.password;

        //Execute
        try {
            stmt = obj.con.createStatement();

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

    public int onPostExecution(int result){

        return result;
    }
}
