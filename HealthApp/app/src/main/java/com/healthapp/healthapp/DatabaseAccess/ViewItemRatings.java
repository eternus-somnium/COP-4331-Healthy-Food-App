package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.ReviewsActivity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public class ViewItemRatings extends AsyncTask<Void,Void,Rating[]>{

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
    protected Rating[] doInBackground(Void... params) {

        //Variables
        Statement stmt = null;
        ResultSet rs = null;
        Rating reviews[] = null;

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
            int i = 0;
            while(rs.next())
            {
                reviews[i].setUsername(rs.getString("U.username"));
                reviews[i].setRating(rs.getFloat("R.rating"));
                reviews[i].setComment(rs.getString("R.comment"));
                i++;
            }
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

        //Return Ratings
        return reviews;
    }

    protected void onPostExecute(Rating[] results)
    {
        ReviewsActivity.populateList(results); //Do something
    }
}
