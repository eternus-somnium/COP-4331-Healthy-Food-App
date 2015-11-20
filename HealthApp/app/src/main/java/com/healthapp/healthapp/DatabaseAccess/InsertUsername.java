package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public class InsertUsername extends AsyncTask<Void,Void,Integer>{

    //User creates new account, inserting username and password
    //
    //INPUT: username and password that the User wants, as well as the connection
    public Integer doInBackground(Void... params){
        Integer insertStatus;
        Statement stmt = null;
        try {
            stmt = User.getCon().createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        //Query
        String update = "INSERT INTO Users (username,password)"
                +"VALUES ('" + User.getUsername() + "', '" + User.getPassword() + "')";

        //Execute query
        try {
            stmt.execute(update);
            insertStatus = 1;
        }

        //Exception
        catch (Exception e) {
            e.printStackTrace();
            insertStatus = -1;
        }

        //Close statement
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
