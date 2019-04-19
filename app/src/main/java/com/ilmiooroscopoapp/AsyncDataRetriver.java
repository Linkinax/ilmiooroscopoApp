package com.ilmiooroscopoapp;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class AsyncDataRetriver extends Thread {

    private String Sign = "leone";
    private String  AmoreField= "cacca";

    public String getHealthField() {
        return HealthField;
    }

    public String getGeneralField() {
        return GeneralField;
    }

    private String  HealthField= " ";
    private String  GeneralField= " ";
    private  String DataField = " ";


    private Thread thread = new Thread(new Runnable() {

        @Override
        public void run() {
            try {
                //
                doInBackground();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    });

    public AsyncDataRetriver(String Sign){
        this.Sign= Sign;
    }
    public String getAmoreField(){
        return  this.AmoreField;
    }
    public InputStream getData(String url){

        InputStream data = null;
        return data;
    }
    public void doInBackground() {

        class Dio{
            String Data;
            String Amore;

            @Override
            public  String toString(){
                return Data+ "\t" + Amore;
            }
        }

        String surl = "http://www.ilmiooroscopo.it/oroscopo"+ Sign;
        URL url = null;
        try {
            url = new URL(surl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        URLConnection request = null;
        try {
            request = url.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            request.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Convert to a JSON object to print data
        JsonParser jp = new JsonParser(); //from gson
        JsonElement root = null; //Convert the input stream to a json element
        try {

            String ck=  request.getContent().toString();
            root = jp.parse(new InputStreamReader((InputStream) request.getContent()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object.
        String Amore = rootobj.get("Amore").getAsString(); //just grab the Amore
        String Health = rootobj.get("Generale").getAsString();
        String Work = rootobj.get("Lavoro").getAsString();
        String Date = rootobj.get("Data").getAsString();

        this.AmoreField = Amore;
        this.HealthField = Health;
        this.GeneralField = Work;
        this.DataField = Date;
    }

    public String getDataField() {
        return  this.DataField;
    }
}
