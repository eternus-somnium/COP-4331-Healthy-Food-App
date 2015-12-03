package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.CreateReview;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Authors: Chris Guido
 */
public class InsertReview extends AsyncTask<Object,Void,Integer>{

    Statement stmt = null;
    CreateReview caller;
    //User has rated a food item, insert comment into database
    //
    //INPUT: ID of food, ID of User, the User's rating, the comment the User may have written, and the connection to the database
    protected Integer doInBackground(Object... params)
    {
        Integer status = 0;
        caller = (CreateReview) params[0];
        Review r = (Review) params[1];
        //Query
        String update = "INSERT INTO Rating (foodID, Users_idUsers, rating, comment) "
                      + "VALUES ('" + r.getFoodID()
                      + "','" + r.getUserID()
                      + "','" + r.getRating()
                      + "','" + r.getComment()
                      + "')";

        //Execute
        try {
            stmt = User.getCon().createStatement();
            stmt.executeUpdate(update);
            status = 6;
        }

        //Exception
        catch (Exception e) {
            status = -1;
        }

        //Close
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                }
                catch (SQLException e) {
                }
            }
        }
        return status;
    }

    protected void onPostExecute(Integer status)
    {
        caller.resultMessageHandler(status);
    }
}
