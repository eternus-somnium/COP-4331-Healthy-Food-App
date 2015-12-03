package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import java.sql.SQLException;
import java.sql.Statement;
/**
 * Authors: Chris Guido
 */

//NEEDS TO BE IMPLEMENTED
public class UpdateReview extends AsyncTask<Rating,Void,Integer>{

    Statement stmt = null;
    //User wants to edit rating on food that already exists
    //
    //INPUT: New Rating, New Comment, The Food ID, and the User ID
    protected Integer doInBackground(Rating... params)
    {
        Integer status = 0;
        //Query
        String update = "Update Rating " +
                        "SET " +
                            "rating ='" + params[0].getRating() + "', " +
                            "comment ='" + params[0].getComment()+"' " +
                        "WHERE " +
                            "foodID = '"+params[0].getFoodID()+"' " +
                            "AND " +
                            "Users_idUsers='"+params[0].getUserID()+"'";


        //Execute
        try {
            stmt = User.getCon().createStatement();
            stmt.executeUpdate(update);
        }

        //Exception
        catch (Exception e) {
            status = 1;
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
}
