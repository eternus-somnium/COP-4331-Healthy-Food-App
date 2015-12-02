package com.healthapp.healthapp.DatabaseAccess;

import android.os.AsyncTask;

import java.sql.Connection;

/**
 * Authors: Chris Guido
 */
public class CloseConn extends AsyncTask<Connection,Void,Integer>
{
    //Close connection to database
    //
    //INPUT: connection to database
    protected Integer doInBackground(Connection... params)
    {
        Integer requestStatus;
        try
        {
            params[0].close ();
            requestStatus=1; //Success
        }
        catch (Exception e)
        {
         /* ignore close errors */
            requestStatus = -2; //error
        }
        return requestStatus;
    }
    protected void onPostExecution(Integer requestStatus)
    {

    }

}
