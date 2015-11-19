package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;
import java.sql.Connection;

/**
 * Created by Chris on 11/17/2015.
 */
public abstract class CloseConn extends AsyncTask<Connection,Void,Void>{

    //Close connection to database
    //
    //INPUT: connection to database
    public void doInBackground(Connection con){

        try
        {
            con.close ();
        }
        catch (Exception e) { /* ignore close errors */ }
    }
}
