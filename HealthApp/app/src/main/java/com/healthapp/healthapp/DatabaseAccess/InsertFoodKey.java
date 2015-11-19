package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public abstract class InsertFoodKey extends AsyncTask<User,Void,Void>{

    //Inserts the Food Database key for users
    //
    //INPUT: User's username and password, as well as the API key the User obtains, and the connection to the database
    public void doInBackground(User obj) throws SQLException{
        Statement stmt = obj.con.createStatement();

        //Query
        String update = "INSERT INTO Users (food_api_key) "
                + "VALUES (" + obj.food_api_key + ") "
                + "WHERE "
                + 	"username = " + obj.username
                + 	"AND "
                + 	"password = " + obj.password;

        //Execute
        try {
            stmt.execute(update);
        }

        //Exception
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
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
    }
}
