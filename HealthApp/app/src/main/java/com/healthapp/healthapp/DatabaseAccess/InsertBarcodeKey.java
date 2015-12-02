package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.ConnectCall;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Authors: Chris Guido
 */
public class InsertBarcodeKey extends AsyncTask<ConnectCall,Void,Integer>{

    ConnectCall caller;
    //Insert barcode database key to User
    //
    //INPUT: User's username and password, the barcode scanner key they obtained, and the connection to the database
    protected Integer doInBackground(ConnectCall... params)
    {
        caller = params[0];
        Integer insertStatus;
        Statement stmt = null;
        try {
            stmt = User.getCon().createStatement();
        } catch (SQLException e) {
            insertStatus = -1;
        }

        //Query
        String update = "UPDATE Users " +
                        "SET barcode_key = '"+User.getBarcode_api_key()+ "' " +
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
        caller.resultMessageHandler(result);
    }
}
