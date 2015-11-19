package com.healthapp.healthapp.DatabaseAccess;

import org.w3c.dom.Document;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by Clive on 11/19/2015.
 */
public class XMLParsing
{
    public Document parseXMLfromURL(URL url) throws  Exception
    {

        URLConnection con = url.openConnection();
        Document doc = parseXML(con.getInputStream());

        return doc;
    }

    private Document parseXML(InputStream stream) throws Exception
    {
        DocumentBuilderFactory objDocumentBuilderFactory = null;
        DocumentBuilder objDocumentBuilder = null;
        Document doc = null;
        try
        {
            objDocumentBuilderFactory = DocumentBuilderFactory.newInstance();
            objDocumentBuilder = objDocumentBuilderFactory.newDocumentBuilder();

            doc = objDocumentBuilder.parse(stream);
        }
        catch(Exception ex)
        {
            throw ex;
        }

        return doc;
    }
}
