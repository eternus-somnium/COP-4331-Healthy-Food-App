package com.healthapp.healthapp.DatabaseAccess;
import android.os.AsyncTask;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


/**
 * Created by Chris on 11/17/2015.
 */
public class Connect extends AsyncTask<Void,Void,Connection>{

    //Connects to database, returns connection
    protected Connection doInBackground(Void... params){

        //Initialize connection
        Connection conn = null;

        //Connect to database
        try
        {
            String url = "jdbc:mysql://db4free.net:3307/cop4331_33";
            Class.forName ("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection (url,"chrisguido4","password12345");
            System.out.println ("Database connection established");
        }

        //Exception
        catch (Exception e)
        {
            e.printStackTrace();

        }

        //Return connection
        return conn;
    }

    public Connection onPostExectuion(Connection result){

        return result;
    }
}
