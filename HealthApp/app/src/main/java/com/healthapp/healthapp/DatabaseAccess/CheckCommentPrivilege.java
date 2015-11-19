package com.healthapp.healthapp.DatabaseAccess;

import android.os.AsyncTask;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Chris on 11/17/2015.
 */
public abstract class CheckCommentPrivilege extends AsyncTask<User,Void,Integer>{

    //Checks if User is able to leave a comment on a particular item
    //
    //INPUT: food ID, User ID, connection to database
    //
    //OUTPUT: the number of times the User with that User ID has left a comment on the food with the food ID
    //		  (the number of times someone has commented on a specific food item)
    //
    //		  if the count is 0, the User can comment on the item
    //		  if the count is not 0, the User can not comment on the item
    public int doInBackground(User obj) throws SQLException {

        //Variables
        Statement stmt = null;
        int count = 0;

        //Query
        String query = "SELECT COUNT(*) as flag"
                + "FROM Rating"
                + "WHERE "
                + 		"foodID = " + User.getFoodID()
                + 		"AND "
                + 		"Users_idUsers = " + obj.getUserID();

        //Execute
        try {
            stmt = obj.getCon().createStatement();

            //Answer
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                count = rs.getInt("flag");
            }
        }

        //Exception
        catch (SQLException e ) {
        }

        //Close
        finally {
            if (stmt != null) { stmt.close(); }
        }

        //Return count
        return count;
    }

    public int onPostExecution(int result){

        return result;
    }
}
