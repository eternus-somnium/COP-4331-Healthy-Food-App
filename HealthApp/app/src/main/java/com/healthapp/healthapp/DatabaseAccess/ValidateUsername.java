package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.VisiblePage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Authors: Chris Guido, Clive Hoayun
 */
public class ValidateUsername extends AsyncTask<VisiblePage,Void,Integer>{
    VisiblePage caller;
    //User attempts to create a new account
    //
    //INPUT: username User wants and the connection established to database
    //
    //OUTPUT: Counts the number of instances a username occurs in the database
    //		  If the count is 0, the username has not yet been taken
    //		  If the count is 1, the username has been taken
    protected Integer doInBackground(VisiblePage... params)
    {
        caller = params[0];
        //Initialize variables
        Statement stmt = null;
        Integer result = 0;
        int count = 0;

        //Query
        String query = "SELECT COUNT(*) as A "
                     + "FROM Users "
                     + "WHERE username = '" + User.getUsername() + "'";

        //Execute query
        try {
            stmt = User.getCon().createStatement();

            //Get result
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("A");
            }
            if(count == 0) result = 1;
            else result = -2;
        }

        //Exception
        catch (Exception e )
        {
            result = -1;
        }

        //Close statement
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                }
            }
        }
        return result;
    }

    protected void onPostExecute(Integer result)
    {
        if(result == 1)
            new InsertUsername().execute(caller);
        else
            caller.resultMessageHandler(result);
    }
}
