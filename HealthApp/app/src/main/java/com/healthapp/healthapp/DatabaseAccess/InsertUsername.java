package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.VisiblePage;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Authors: Chris Guido, Clive Hoayun
 */
public class InsertUsername extends AsyncTask<VisiblePage,Void,Integer>{

    VisiblePage caller;
    //User creates new account, inserting username and password
    //
    //INPUT: username and password that the User wants, as well as the connection
    public Integer doInBackground(VisiblePage... params)
    {
        caller = params[0];
        Integer insertStatus = 0;
        Statement stmt = null;
        try {
            stmt = User.getCon().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            insertStatus = -1;
        }

        //Query
        String update = "INSERT INTO Users (username,password) "
                       +"VALUES ('" + User.getUsername() + "', '" + User.getPassword() + "')";

        //Execute query
        try {
            stmt.execute(update);
            insertStatus = 1;
        }

        //Exception
        catch (Exception e) {
            insertStatus = -1;
        }

        //Close statement
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
            new InsertFoodKey().execute(caller);
        else
            caller.resultMessageHandler(result);
    }
}
