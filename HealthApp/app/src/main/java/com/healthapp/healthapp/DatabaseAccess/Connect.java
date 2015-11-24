package com.healthapp.healthapp.DatabaseAccess;

import android.os.AsyncTask;

import com.healthapp.healthapp.ConnectCall;

import java.sql.DriverManager;


/**
 * Created by Chris on 11/17/2015.
 */
public class Connect extends AsyncTask<ConnectCall,Void,Integer>
{
    ConnectCall caller;
    //Connects to database, returns connection
    protected Integer doInBackground(ConnectCall... params)
    {
        Integer requestStatus;
        caller = params[0];
        //Connect to database
        try
        {
            if (User.getCon() == null || User.getCon().isClosed())
            {
                String url = "jdbc:mysql://db4free.net:3307/cop4331_33";
                Class.forName("com.mysql.jdbc.Driver");
                User.setCon(DriverManager.getConnection(url, "chrisguido4", "password12345"));
                System.out.println("Database connection established");
            }
            requestStatus = 1; // Success
        }

        //Exception
        catch (Exception e)
        {
            requestStatus = -1; //Database access problem
        }
        return requestStatus;
    }

    @Override
    protected void onPostExecute(Integer requestStatus) {
        System.out.println(requestStatus);
        if (requestStatus == 1) {
            caller.onConnection();
        } else
            caller.resultMessageHandler(requestStatus);
    }
}
