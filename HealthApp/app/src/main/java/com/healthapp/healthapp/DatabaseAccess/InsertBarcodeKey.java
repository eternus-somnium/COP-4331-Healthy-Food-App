package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public class InsertBarcodeKey extends AsyncTask<Void,Void,Integer>{

    //Insert barcode database key to User
    //
    //INPUT: User's username and password, the barcode scanner key they obtained, and the connection to the database
    protected Integer doInBackground(Void... params){

        Integer insertStatus;
        Statement stmt = null;
        try {
            stmt = User.getCon().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Query
        String update = "INSERT INTO Users (barcode_key) "
                + "VALUES ('" + User.getFood_api_key() + "') "
                + "WHERE "
                + 	"username = '" + User.getUsername()
                + 	"' AND "
                + 	"password = '" + User.getPassword() + "'";

        //Execute
        try {
            stmt.executeUpdate(update);
            insertStatus = 1;
        }

        //Exception
        catch (Exception e) {
            e.printStackTrace();
            insertStatus = -1;
        }

        //Close connection
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return insertStatus;
    }

}
