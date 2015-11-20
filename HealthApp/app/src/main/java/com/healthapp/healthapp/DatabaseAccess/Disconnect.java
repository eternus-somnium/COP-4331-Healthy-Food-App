package com.healthapp.healthapp.DatabaseAccess;

/**
 * Created by Clive on 11/19/2015.
 */
public class Disconnect
{
    public Integer disconnectFromDatabase()
    {
        Integer requestStatus;
        try
        {
            User.getCon().close ();
            requestStatus=1; //Success
        }
        catch (Exception e)
        {
         /* ignore close errors */
            requestStatus = -2; //error
        }
        return requestStatus;
    }
}
