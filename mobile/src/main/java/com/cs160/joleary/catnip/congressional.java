package com.cs160.joleary.catnip;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
//import com.loopj.android.http.*;
import org.json.*;


import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class congressional extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congressional);
        final ListView listView = (ListView)findViewById(R.id.personView);
        String[] names = {"Alice", "Bob", "Eve"};
        String[] emails = {"email@Alice", "email@Bob", "email@Eve"};
        String[] websites = {"Alice.com", "Bob.com", "Eve.com"};
        String[] tweets = {"I am Alice", "I am Bob", "I am Eve"};
        int[] photos = {R.drawable.lexy_160, R.drawable.lexy_160, R.drawable.lexy_160};
        final PersonArrayAdapter adapter = new PersonArrayAdapter(this, names, emails, websites, tweets, photos);
        listView.setAdapter(adapter);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        JSONObject sunshineJSON;

        if (extras != null) {
            String type = extras.getString("type");
            if (type.equals("zip")) {
                String zip = extras.getString("zip");
                new SunshineRestClient().execute("zip=94501");

            } else {
                String latitude = extras.getString("latitude");
                String  longitude = extras.getString("longitude");
            }

        }
    }
    private class SunshineRestClient extends AsyncTask<String, String, String> {
        private final String baseUrl = "http://congress.api.sunlightfoundation.com/legislators/locate?";
        private final String apikey="&apikey=bc29918f07bb41ceb87fdb41db03658f";
        @Override
        protected String doInBackground(String... params) {

            String urlString=baseUrl + params[0] + apikey; // URL to call

            String resultToDisplay = "";

            InputStream in = null;

            // HTTP Get
            try {

                URL url = new URL(urlString);

                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

                in = new BufferedInputStream(urlConnection.getInputStream());

            } catch (Exception e ) {

                System.out.println(e.getMessage());

                return e.getMessage();

            }

            try {
            //System.out.println("RESULTS: " + new JSONObject(in.toString()));
                byte[] contents = new byte[1024];
                int bytesRead=0;
                String strFileContents = "";
                while( (bytesRead = in.read(contents)) != -1){
                    strFileContents += new String(contents, 0, bytesRead);
                }
                //System.out.print("RESULTS: " + strFileContents);
                resultToDisplay = strFileContents;
            } catch (Exception e) {

                System.out.println("FUCK");
                return e.getMessage();
            }
            return resultToDisplay;
        }
    }
}
