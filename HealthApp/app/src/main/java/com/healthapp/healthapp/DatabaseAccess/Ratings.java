package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public class Ratings extends AsyncTask<Void,Void,ResultSet>{

    //User has selected a specific item, collect all reviews on the item
    //
    //INPUT: the ID of the food item and the connection to the database
    //
    //OUTPUT: this function outputs a ResultSet, which is the results to the query being executed
    //		  The ResultSet will consist of the following variables:
    //
    //		  the Rating (int), the Comment (String) associated with the rating, and the Username (String) of the User who left the rating
    //
    //        These variables will now be available to be used in the parent function
    protected ResultSet doInBackground(Void... params) {

        //Variables
        Statement stmt = null;
        ResultSet rs = null;

        //Query
        String query = "SELECT U.username, R.rating, R.comment "
                + "FROM "
                + 		"Rating as R "
                + 		"inner join "
                + 		"Users as U "
                + 		"on "
                + 		"R.Users_idUsers = U.idUsers "
                + "WHERE R.foodID = '" + User.getFoodID() + "'";

        //Execute
        try {
            stmt = User.getCon().createStatement();
            rs = stmt.executeQuery(query);
        }

        //Exception
        catch (SQLException e ) {
        }

        //Close
        finally {
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //Return ResultSet
        return rs;
    }

    public ResultSet onPostExecution(ResultSet result){

        return result;
    }
}
