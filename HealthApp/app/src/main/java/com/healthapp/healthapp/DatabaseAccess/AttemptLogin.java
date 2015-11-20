package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public class AttemptLogin extends AsyncTask<Void,Void,Integer>
{
    //User is logging in, get the User's ID, food database key, and barcode key
    //

    protected Integer doInBackground(Void... params)
    {
        //Instantiation
        Integer requestStatus;
        Statement stmt = null;
        ResultSet rs;

        //Query
        String query = "SELECT idUsers, food_api_key, barcode_key "
                + "FROM Users "
                + "WHERE "
                + 		"username = " + User.getUsername()
                + 		"AND "
                + 		"password = " + User.getPassword();

        //Execute Query
        try
        {
            stmt = User.getCon().createStatement();
            rs = stmt.executeQuery(query);
            if(rs.isBeforeFirst())
            {
                requestStatus = 1; //Success
                User.setValidUser(true);
                User.setUserID(Integer.valueOf(rs.getString(1)));
                User.setFood_api_key(rs.getString(2));
                User.setBarcode_api_key(rs.getString(3));
            }
            else
            {
                requestStatus = -1; //Failure
                User.setValidUser(false);
            }
        }

        catch(SQLException e)
        {
            requestStatus = -2; //Database access problem
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

        return requestStatus;
    }

    protected void onPostExecute(Integer requestStatus)
    {
        if(requestStatus == 1)
            Login.launchSearch();
        else
            Login.errorController(requestStatus);

    }
}
