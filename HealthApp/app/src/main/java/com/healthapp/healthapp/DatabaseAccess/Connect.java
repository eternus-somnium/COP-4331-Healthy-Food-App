package com.healthapp.healthapp.DatabaseAccess;

import android.os.AsyncTask;

import com.healthapp.healthapp.VisiblePage;

import java.sql.DriverManager;


/**
 * Authors: Clive Hoayun, Chris Guido
 */
public class Connect extends AsyncTask<VisiblePage,Void,Integer>
{
    //Connects to database, returns connection
    VisiblePage caller;
    protected Integer doInBackground(VisiblePage... params)
    {
        //Instantiation
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
        //Successful Connection
        if (requestStatus == 1) {
            caller.onConnection();
        }
        //error in connecting
        else
            caller.resultMessageHandler(requestStatus);
    }
}
