package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;

import com.healthapp.healthapp.ReviewsActivity;
import com.healthapp.healthapp.DatabaseAccess.Review;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Authors: Chris Guido
 */
public class ViewItemRatings extends AsyncTask<Void,Void,Review[]>{

    //User has selected a specific item, collect all reviews on the item
    //
    //INPUT: the ID of the food item and the connection to the database
    //
    //OUTPUT: this function outputs a ResultSet, which is the results to the query being executed
    //		  The ResultSet will consist of the following variables:
    //
    //		  the Review (int), the Comment (String) associated with the rating, and the Username (String) of the User who left the rating
    //
    //        These variables will now be available to be used in the parent function
    protected Review[] doInBackground(Void... params) {

        //Variables
        Statement stmt = null;
        ResultSet rs = null;
        Review reviews[] = null;

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
            //Gets reviews from database and sets up review array
            stmt = User.getCon().createStatement();
            rs = stmt.executeQuery(query);
            int i = 0;
            rs.last();
            reviews = new Review[rs.getRow()];
            rs.beforeFirst();

            while(rs.next())
            {
                Review newReview = new Review();
                newReview.setUsername(rs.getString("U.username"));
                newReview.setRating(rs.getFloat("R.rating"));
                newReview.setComment(rs.getString("R.comment"));
                reviews[i] = newReview;
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
                }
            }
        }

        //Return Ratings
        return reviews;
    }

    protected void onPostExecute(Review[] reviews)
    {
        ReviewsActivity.populateList(reviews); //Do something
    }
}
