package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public abstract class InsertUsername extends AsyncTask<User,Void,Void>{

    //User creates new account, inserting username and password
    //
    //INPUT: username and password that the User wants, as well as the connection
    public void doInBackground(User obj) throws SQLException{

        Statement stmt = obj.con.createStatement();

        //Query
        String update = "INSERT INTO Users (username,password)"
                +"VALUES (" + obj.username + ", " + obj.password + ")";

        //Execute query
        try {
            stmt.execute(update);
        }

        //Exception
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
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
    }
}
