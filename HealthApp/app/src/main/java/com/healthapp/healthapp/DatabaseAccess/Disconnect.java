package com.healthapp.healthapp.DatabaseAccess;

/**
 * Authors: Chris Guido
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
