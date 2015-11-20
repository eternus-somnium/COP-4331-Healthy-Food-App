package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

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
        Integer requestStatus=0;
        PreparedStatement stmt = null;
        ResultSet rs;

        //Query
        String query = "SELECT idUsers, api_key, barcode_key "
                + "FROM cop4331_33.Users "
                + "WHERE "
                + 		"username = '" + User.getUsername()
                + 		"' AND "
                + 		"password = '" + User.getPassword() + "'";

        //Execute Query
        try
        {
            stmt = User.getCon().prepareStatement(query);
            stmt.setQueryTimeout(3600);
            rs = stmt.executeQuery();

            if(rs.isBeforeFirst())
            {
                rs.next();
                requestStatus = 1; //Success
                User.setValidUser(true);
                User.setUserID(Integer.valueOf(rs.getInt("idUsers")));
                User.setFood_api_key(rs.getString("api_key"));
                User.setBarcode_api_key(rs.getString("barcode_key"));
            }
            else
            {
                requestStatus = -2; //Failure
                User.setValidUser(false);
            }
        }

        catch(SQLException e)
        {
            requestStatus = -1; //Database access problem
            e.printStackTrace();
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
        System.out.println(requestStatus);
        if(requestStatus == 1)
            Login.launchSearch();
        else
            Login.errorController(requestStatus);

    }
}
