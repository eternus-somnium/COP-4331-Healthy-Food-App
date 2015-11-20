package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public class CheckUsernamePassword extends AsyncTask<Void,Void,Integer>{

    //User logging in, check to see if username and password are okay
    //
    //INPUT: User's username, password, and the connection to the database
    //
    //OUTPUT: count of how many instances that username and password occur
    //		  If the count is 0, the username and password are incorrect and/or don't exist in database
    //		  If the count is 1, the username/password pair exists in the database
    protected Integer doInBackground(Void... params){

        //Variables
        Statement stmt = null;
        ResultSet rs = null;
        Integer count = 0;

        //Query
        String query = "SELECT COUNT(*) as A"
                + "FROM Users"
                + "WHERE "
                + 		"username = '" + User.getUsername()
                + 		"' AND "
                + 		"password = '" + User.getPassword() + "'";

        //Execute
        try {
            stmt = User.getCon().createStatement();

            //Results
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("A");
            }
        }

        //Exception
        catch (SQLException e ) {
            e.printStackTrace();
        }

        //Close connection
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //Return count
        return count;
    }

    public Integer onPostExecution(Integer result){

        return result;
    }
}
