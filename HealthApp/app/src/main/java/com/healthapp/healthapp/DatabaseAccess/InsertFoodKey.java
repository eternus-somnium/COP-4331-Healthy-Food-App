package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.VisiblePage;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Authors: Chris Guido, Clive Hoayun
 */
public class InsertFoodKey extends AsyncTask<VisiblePage,Void,Integer>
{
    VisiblePage caller;
    //Inserts the Food Database key for users
    //
    //INPUT: User's username and password, as well as the API key the User obtains, and the connection to the database
    protected Integer doInBackground(VisiblePage... params)
    {
        caller = params[0];
        Integer insertStatus;
        Statement stmt = null;
        try {
            stmt = User.getCon().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            insertStatus = -1;
        }

        //Query
        String update = "UPDATE Users " +
                        "SET api_key = '"+User.getFood_api_key()+"' " +
                        "WHERE " +
                            "username = '"+User.getUsername()+"' " +
                            "AND " +
                            "password = '"+User.getPassword()+"'";

        //Execute
        try {
            stmt.executeUpdate(update);
            insertStatus = 1;
        }

        //Exception
        catch (Exception e) {
            insertStatus = -1;
        }

        //Close connection
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                }
            }
        }
        return insertStatus;
    }

    protected void onPostExecute(Integer result)
    {
        if(result == 1)
            new InsertBarcodeKey().execute(caller);
        else
            caller.resultMessageHandler(result);
    }
}
