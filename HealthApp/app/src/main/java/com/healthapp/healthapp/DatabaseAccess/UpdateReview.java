package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.CreateReview;
import com.healthapp.healthapp.VisiblePage;

import java.sql.SQLException;
import java.sql.Statement;
/**
 * Authors: Chris Guido
 */

//NEEDS TO BE IMPLEMENTED
public class UpdateReview extends AsyncTask<Object,Void,Integer>{

    VisiblePage caller;
    Statement stmt = null;
    //User wants to edit rating on food that already exists
    //
    //INPUT: New Review, New Comment, The Food ID, and the User ID
    protected Integer doInBackground(Object... params)
    {
        Integer status = 0;
        caller = (CreateReview) params[0];
        Review r = (Review) params[1];
        //Query
        String update = "Update Review " +
                        "SET " +
                            "rating ='" + r.getRating() + "', " +
                            "comment ='" + r.getComment()+"' " +
                        "WHERE " +
                            "foodID = '"+ r.getFoodID()+"' " +
                            "AND " +
                            "Users_idUsers='"+ r.getUserID()+"'";


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
