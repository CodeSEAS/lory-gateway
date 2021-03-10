package com.lory.gateway;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;



public class GetURLContentTest {
    public static void main(String[] args) {
        //common way of getting web info   
//        try {
//            URL url=new URL("https://www.javatpoint.com/java-tutorial");
//            System.out.println("Protocol: "+url.getProtocol());// Using getProtocol() method of the URL class
//            System.out.println("Host Name: "+url.getHost()); // Using getHost() method
//            System.out.println("Port Number: "+url.getPort());  // Using getPort() method
//            System.out.println("File Name: "+url.getFile());    //Using getFile() method
//        } catch (Exception e) {
//            System.out.println(e);
//        }
        String output  = getUrlContents("https://finance.yahoo.com/");
        System.out.println(output);
    }
    private static String getUrlContents(String theUrl)
    {
        StringBuilder content = new StringBuilder();
        // Use try and catch to avoid the exceptions
        try
        {
            URL url = new URL(theUrl); // creating a url object
            URLConnection urlConnection = url.openConnection(); // creating a urlconnection object

            // wrapping the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;
            // reading from the urlconnection using the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }
}
