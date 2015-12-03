package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.Login;
import com.healthapp.healthapp.VisiblePage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

/**
 * Authors: Chris Guido, Clive Hoayun
 */
public class AttemptLogin extends AsyncTask<VisiblePage,Void,Integer>
{
    //User is logging in, get the User's ID, food database key, and barcode key

    VisiblePage caller;
    protected Integer doInBackground(VisiblePage... params)
    {
        //Instantiation
        caller = params[0];
        Integer requestStatus=0;
        PreparedStatement stmt = null;
        ResultSet rs;

        //Query
        String query = "SELECT idUsers, api_key, barcode_key "
                     + "FROM cop4331_33.Users "
                     + "WHERE "
                     + 		"username LIKE'" + User.getUsername()
                     + 		"' AND "
                     + 		"password LIKE '" + User.getPassword() + "'";

        //Execute Query
        try
        {
            stmt = User.getCon().prepareStatement(query);
            stmt.setQueryTimeout(3600);
            rs = stmt.executeQuery();

            //Non-null result set
            if(rs.isBeforeFirst())
            {
                rs.next();
                requestStatus = 1; //Success
                User.setValidUser(true);
                User.setUserID(Integer.valueOf(rs.getInt("idUsers")));
                User.setFood_api_key(rs.getString("api_key"));
                User.setBarcode_api_key(rs.getString("barcode_key"));
            }
            //Null Result Set, error in login credentials
            else
            {
                requestStatus = -3; //Failure
                User.setValidUser(false);
            }
        }

        //Error in connecting to database
        catch(SQLException e)
        {
            requestStatus = -1; //Database access problem
        }

        //close statement
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return requestStatus;
    }

    protected void onPostExecute(Integer requestStatus)
    {
        //Successful login
        if(requestStatus == 1)
            Login.launchSearch();

        //Error in logging in
        else
            caller.resultMessageHandler(requestStatus);

    }
}
