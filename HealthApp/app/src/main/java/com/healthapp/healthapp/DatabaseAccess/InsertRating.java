package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.CreateReview;

import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public class InsertRating extends AsyncTask<Rating,Void,Integer>{

    Statement stmt = null;
    //User has rated a food item, insert comment into database
    //
    //INPUT: ID of food, ID of User, the User's rating, the comment the User may have written, and the connection to the database
    protected Integer doInBackground(Rating... params)
    {
        Integer status = 0;
        //Query
        String update = "INSERT INTO Rating (foodID, Users_idUsers, rating, comment) "
                    + "VALUES ('" + params[0].getFoodID()
                    + "','" + params[0].getUserID()
                    + "','" + params[0].getRating()
                    + "','" + params[0].getComment()
                    + "')";

        //Execute
        try {
            stmt = User.getCon().createStatement();
            stmt.executeUpdate(update);
            status = 1;
        }

        //Exception
        catch (Exception e) {
            System.err.println("Got an exception! ");
            System.err.println(e.getMessage());
            status = -1;
        }

        //Close
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
        return status;
    }

    protected void onPostExecute(Integer status)
    {
        CreateReview.resultMessageHandler(status);
    }
}
